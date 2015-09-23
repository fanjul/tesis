package guiFX.BaseDeDatosGUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import baseDatos.hibernate.consultas.AbstractaConsulta;
import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.GraficoDAO;
import baseDatos.hibernate.tablas.Grafico;
import guiFX.PanelDerecho;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;


public class GraficoGUI extends TableView<Grafico> implements AbstractBaseDeDatosGUI {
	
	private TableView<Grafico> tablaGrafico;
	private TableColumn<Grafico, Integer> columnaId;
	private TableColumn<Grafico, String> columnaTipoGrafico;
	private TableColumn<Grafico, String> columnaObservaciones;
	
	private ObservableList<Grafico> data; 


	public GraficoGUI() {
		super();
	}
	
	@SuppressWarnings("unchecked")	
	public void mostrarTabla(){
		tablaGrafico = new TableView<Grafico>();
		
		columnaId = new TableColumn<Grafico, Integer>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<Grafico,Integer>("id"));
		
		columnaTipoGrafico = new TableColumn<Grafico, String>("Tipo Grafico");
		columnaTipoGrafico.setCellValueFactory(new PropertyValueFactory<Grafico,String>("tipoGrafico"));
		
		columnaObservaciones = new TableColumn<Grafico, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<Grafico,String>("observaciones"));
		
		tablaGrafico.getColumns().addAll(columnaId,columnaTipoGrafico,columnaObservaciones);

		tablaGrafico.getStyleClass().add("tablas");
		this.agregarListenerEvent();
	}


	public TableView<Grafico> getTablaGrafico() {
		return tablaGrafico;
	}


	public TableColumn<Grafico, Integer> getColumnaId() {
		return columnaId;
	}


	public TableColumn<Grafico, String> getColumnaTipoGrafico() {
		return columnaTipoGrafico;
	}


	public TableColumn<Grafico, String> getColumnaObservaciones() {
		return columnaObservaciones;
	}


	public ObservableList<Grafico> getData() {
		return data;
	}


	@Override
	public void crearTablaBaseDeDatos() {
		this.mostrarTabla();
	}

	public void setData(ObservableList<Grafico> data) {
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void mostrarTabla(AbstractaConsulta consulta, FactoryConsultas factoryConsultasDAO,
			AnchorPane centroTabla) {
		
		if(!centroTabla.getChildren().isEmpty()){
			centroTabla.getChildren().remove(0);
		}
		this.setData(FXCollections.observableArrayList());
		List<Grafico> lista = (List<Grafico>) factoryConsultasDAO.getLista("Grafico");
		lista = ((GraficoDAO)consulta).getTodos();
		for (Grafico vi : lista) { 
			this.getData().add(vi);

		}

		this.getTablaGrafico().setItems(this.getData());
		tablaGrafico.setMaxSize(centroTabla.getMaxWidth(), centroTabla.getMaxHeight());
		tablaGrafico.setMinSize(centroTabla.getMinWidth(), centroTabla.getMinHeight());

		centroTabla.getChildren().add(0,this.getTablaGrafico());		
	}

	private void agregarListenerEvent() {
		// Para que se pueda seleccionar varias rows de la tabla
		tablaGrafico.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Drag & Drop de tabla al editor. Si queres pone el cursor donde
		// quieras y arrastras.
		tablaGrafico.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent me) {
				final Dragboard db = tablaGrafico.startDragAndDrop(TransferMode.COPY);
				final ClipboardContent content = new ClipboardContent();

				String selected = "";
				Set<Grafico> selec = new HashSet<Grafico>(
						tablaGrafico.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();

				for (int i = 0; i < arr.length; i++) {
					selected += (((Grafico) arr[i]).getId().toString());
					selected += " ";

				}
				content.putString(selected.toString());

				// content.putString("Drag Me!");
				db.setContent(content);
				me.consume();
			}
		});
		tablaGrafico.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(final DragEvent de) {

			}
		});

		tablaGrafico.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(final DragEvent de) {
				de.acceptTransferModes(TransferMode.COPY);
				de.consume();
			}
		});

		PanelDerecho.getInstance().getEditorTexto().setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
			}
		});

		PanelDerecho.getInstance().getEditorTexto().setOnDragDropped(new EventHandler<DragEvent>() {
			 
			@Override
			public void handle(DragEvent event) {
				Clipboard clipboard = Clipboard.getSystemClipboard();
				ClipboardContent content = new ClipboardContent();

				String selected = "";
				Set<Grafico> selec = new HashSet<Grafico>(
						tablaGrafico.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();

				for (int i = 0; i < arr.length; i++) {
					selected += (((Grafico) arr[i]).getId().toString());
					selected += " ";

				}
				content.putString(selected);

				clipboard.setContent(content);

				PanelDerecho.getInstance().getEditorTexto().insertText(
						PanelDerecho.getInstance().getEditorTexto().getCaretPosition(), clipboard.getString());
			}

		});
	}
	
}
