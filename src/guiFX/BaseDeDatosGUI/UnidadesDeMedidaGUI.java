package guiFX.BaseDeDatosGUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import baseDatos.hibernate.consultas.AbstractaConsulta;
import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.UnidadesDeMedidaDAO;
import baseDatos.hibernate.tablas.UnidadesDeMedida;
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

public class UnidadesDeMedidaGUI extends TableView<UnidadesDeMedida> implements AbstractBaseDeDatosGUI {
	
	private TableView<UnidadesDeMedida> tablaUnidadesdDeMedida;
	private TableColumn<UnidadesDeMedida, Integer> columnaId;
	private TableColumn<UnidadesDeMedida, String> columnaUnidadDeMedida;
	private TableColumn<UnidadesDeMedida, String> columnaObservaciones;
	
	private ObservableList<UnidadesDeMedida> data; 
	
	public UnidadesDeMedidaGUI() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public void mostrarTabla(){
		tablaUnidadesdDeMedida = new TableView<UnidadesDeMedida>();
		
		columnaId = new TableColumn<UnidadesDeMedida, Integer>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<UnidadesDeMedida,Integer>("id"));
		
		columnaUnidadDeMedida = new TableColumn<UnidadesDeMedida, String>("Unidad de Medida");
		columnaUnidadDeMedida.setCellValueFactory(new PropertyValueFactory<UnidadesDeMedida,String>("unidadDeMedida"));
		
		columnaObservaciones = new TableColumn<UnidadesDeMedida, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<UnidadesDeMedida,String>("observaciones"));
		
		tablaUnidadesdDeMedida.getColumns().addAll(columnaId,columnaUnidadDeMedida,columnaObservaciones);
		
		tablaUnidadesdDeMedida.getStyleClass().add("tablas");
		this.agregarListenerEvent();
	}

	public TableView<UnidadesDeMedida> getTablaUnidadesdDeMedida() {
		return tablaUnidadesdDeMedida;
	}

	public TableColumn<UnidadesDeMedida, Integer> getColumnaId() {
		return columnaId;
	}

	public TableColumn<UnidadesDeMedida, String> getColumnaUnidadDeMedida() {
		return columnaUnidadDeMedida;
	}

	public TableColumn<UnidadesDeMedida, String> getColumnaObservaciones() {
		return columnaObservaciones;
	}

	public ObservableList<UnidadesDeMedida> getData() {
		return data;
	}

	public void setData(ObservableList<UnidadesDeMedida> data) {
		this.data = data;
	}

	@Override
	public void crearTablaBaseDeDatos() {
		this.mostrarTabla();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void mostrarTabla(AbstractaConsulta consulta, FactoryConsultas factoryConsultasDAO,
			AnchorPane centroInferior) {

		if(!centroInferior.getChildren().isEmpty()){
			centroInferior.getChildren().remove(0);
		}
		this.setData(FXCollections.observableArrayList());
		List<UnidadesDeMedida> lista = (List<UnidadesDeMedida>) factoryConsultasDAO.getLista("UnidadesDeMedida");
		lista = ((UnidadesDeMedidaDAO)consulta).getTodos();
		for (UnidadesDeMedida vi : lista) { 
			this.getData().add(vi);

		}
		this.getTablaUnidadesdDeMedida().setItems(this.getData());	
//		tablaUnidadesdDeMedida.setPrefSize(centroInferior.getMaxWidth(), centroInferior.getMaxHeight());
		centroInferior.getChildren().add(0,this.getTablaUnidadesdDeMedida());			
	}
	
	private void agregarListenerEvent() {
		// Para que se pueda seleccionar varias rows de la tabla
		tablaUnidadesdDeMedida.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Drag & Drop de tabla al editor. Si queres pone el cursor donde
		// quieras y arrastras.
		tablaUnidadesdDeMedida.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent me) {
				final Dragboard db = tablaUnidadesdDeMedida.startDragAndDrop(TransferMode.COPY);
				final ClipboardContent content = new ClipboardContent();

				String selected = "";
				Set<UnidadesDeMedida> selec = new HashSet<UnidadesDeMedida>(
						tablaUnidadesdDeMedida.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();

				for (int i = 0; i < arr.length; i++) {
					selected += (((UnidadesDeMedida) arr[i]).getId().toString());
					selected += " ";

				}
				content.putString(selected.toString());

				// content.putString("Drag Me!");
				db.setContent(content);
				me.consume();
			}
		});
		tablaUnidadesdDeMedida.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(final DragEvent de) {

			}
		});

		tablaUnidadesdDeMedida.setOnDragOver(new EventHandler<DragEvent>() {
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
				Set<UnidadesDeMedida> selec = new HashSet<UnidadesDeMedida>(
						tablaUnidadesdDeMedida.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();

				for (int i = 0; i < arr.length; i++) {
					selected += (((UnidadesDeMedida) arr[i]).getId().toString());
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
