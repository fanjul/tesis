package guiFX.BaseDeDatosGUI;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.UmbralesXEstadosXIndicadorDAO;
import baseDatos.hibernate.tablas.UmbralesXEstadosXIndicador;
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

public class UmbralesXEstadosXIndicadorGUI extends TableView<UmbralesXEstadosXIndicador> implements AbstractBaseDeDatosGUI {
	
	private TableView<UmbralesXEstadosXIndicador> tablaUmbrales;
	private TableColumn<UmbralesXEstadosXIndicador, Integer> columnaIdIndicador;
	private TableColumn<UmbralesXEstadosXIndicador, Integer> columnaIdEstadoTipoIndicador;
	private TableColumn<UmbralesXEstadosXIndicador, Timestamp> columnaInicioUmbral;
	private TableColumn<UmbralesXEstadosXIndicador, Timestamp> columnaFinUmbral;
	private TableColumn<UmbralesXEstadosXIndicador, String> columnaOperadorUmbralSuperior;
	private TableColumn<UmbralesXEstadosXIndicador, String> columnaOperadorUmbralInferior;
	private TableColumn<UmbralesXEstadosXIndicador, Double> columnaValorUmbralSuperior;
	private TableColumn<UmbralesXEstadosXIndicador, Double> columnaValorUmbralInferior;
	private TableColumn<UmbralesXEstadosXIndicador, String> columnaObservaciones;
	
	private ObservableList<UmbralesXEstadosXIndicador> data; 

	public UmbralesXEstadosXIndicadorGUI() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public void mostrarTabla(){
		tablaUmbrales = new TableView<UmbralesXEstadosXIndicador>();
		
		columnaIdIndicador = new TableColumn<UmbralesXEstadosXIndicador, Integer>("Id Indicador");
		columnaIdIndicador.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,Integer>("idIndicador"));
		
		columnaIdEstadoTipoIndicador = new TableColumn<UmbralesXEstadosXIndicador, Integer>("Id Estado Tipo Indicador");
		columnaIdEstadoTipoIndicador.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,Integer>("idEstadoTipoIndicador"));
		
		columnaInicioUmbral = new TableColumn<UmbralesXEstadosXIndicador, Timestamp>("Inicio Umbral");
		columnaInicioUmbral.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,Timestamp>("inicioUmbral"));
		
		columnaFinUmbral = new TableColumn<UmbralesXEstadosXIndicador, Timestamp>("Fin Umbral");
		columnaFinUmbral.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,Timestamp>("finUmbral"));
		
		columnaOperadorUmbralSuperior = new TableColumn<UmbralesXEstadosXIndicador, String>("Operador Umbral Superior");
		columnaOperadorUmbralSuperior.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,String>("operadorUmbralSuperior"));
		
		columnaOperadorUmbralInferior = new TableColumn<UmbralesXEstadosXIndicador, String>("Operador Umbral Inferior");
		columnaOperadorUmbralInferior.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,String>("operadorUmbralInferior"));
		
		columnaValorUmbralSuperior = new TableColumn<UmbralesXEstadosXIndicador, Double>("Valor Umbral Superior");
		columnaValorUmbralSuperior.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,Double>("valorUmbralSuperior"));
		
		columnaValorUmbralInferior = new TableColumn<UmbralesXEstadosXIndicador, Double>("Valor Umbral Inferior");
		columnaValorUmbralInferior.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,Double>("valorUmbralInferior"));
		
		columnaObservaciones = new TableColumn<UmbralesXEstadosXIndicador, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,String>("observaciones"));
		
		tablaUmbrales.getColumns().addAll(columnaIdIndicador,columnaIdEstadoTipoIndicador,columnaInicioUmbral,columnaFinUmbral,columnaOperadorUmbralSuperior,
				columnaOperadorUmbralInferior,columnaValorUmbralSuperior,columnaValorUmbralInferior,columnaObservaciones);
	
		tablaUmbrales.getStyleClass().add("tablas");
		
		this.agregarListenerEvent();
	}

	public TableView<UmbralesXEstadosXIndicador> getTablaUmbrales() {
		return tablaUmbrales;
	}

	public TableColumn<UmbralesXEstadosXIndicador, Integer> getColumnaIdIndicador() {
		return columnaIdIndicador;
	}

	public TableColumn<UmbralesXEstadosXIndicador, Integer> getColumnaIdEstadoTipoIndicador() {
		return columnaIdEstadoTipoIndicador;
	}

	public TableColumn<UmbralesXEstadosXIndicador, Timestamp> getColumnaInicioUmbral() {
		return columnaInicioUmbral;
	}

	public TableColumn<UmbralesXEstadosXIndicador, Timestamp> getColumnaFinUmbral() {
		return columnaFinUmbral;
	}

	public TableColumn<UmbralesXEstadosXIndicador, String> getColumnaOperadorUmbralSuperior() {
		return columnaOperadorUmbralSuperior;
	}

	public TableColumn<UmbralesXEstadosXIndicador, String> getColumnaOperadorUmbralInferior() {
		return columnaOperadorUmbralInferior;
	}

	public TableColumn<UmbralesXEstadosXIndicador, Double> getColumnaValorUmbralSuperior() {
		return columnaValorUmbralSuperior;
	}

	public TableColumn<UmbralesXEstadosXIndicador, Double> getColumnaValorUmbralInferior() {
		return columnaValorUmbralInferior;
	}

	public TableColumn<UmbralesXEstadosXIndicador, String> getColumnaObservaciones() {
		return columnaObservaciones;
	}

	public ObservableList<UmbralesXEstadosXIndicador> getData() {
		return data;
	}

	public void setData(ObservableList<UmbralesXEstadosXIndicador> data) {
		this.data = data;
	}

	@Override
	public void crearTablaBaseDeDatos() {
		this.mostrarTabla();
	}


	@SuppressWarnings("unchecked")
	@Override
	public void mostrarTabla(Object consulta, FactoryConsultas factoryConsultasDAO,
			AnchorPane centroInferior) {
		
		if(!centroInferior.getChildren().isEmpty()){
			centroInferior.getChildren().remove(0);
		}
		this.setData(FXCollections.observableArrayList());
		List<UmbralesXEstadosXIndicador> lista = (List<UmbralesXEstadosXIndicador>) factoryConsultasDAO.getLista("UmbralesXEstadosXIndicador");
		lista = ((UmbralesXEstadosXIndicadorDAO)consulta).getTodos();
		for (UmbralesXEstadosXIndicador vi : lista) { 
			this.getData().add(vi);

		}
		this.getTablaUmbrales().setItems(this.getData());
		tablaUmbrales.setPrefSize(centroInferior.getMaxWidth(), centroInferior.getMaxHeight());
		centroInferior.getChildren().add(0,this.getTablaUmbrales());			
	}
	
	private void agregarListenerEvent() {
		// Para que se pueda seleccionar varias rows de la tabla
		tablaUmbrales.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Drag & Drop de tabla al editor. Si queres pone el cursor donde
		// quieras y arrastras.
		tablaUmbrales.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent me) {
				final Dragboard db = tablaUmbrales.startDragAndDrop(TransferMode.COPY);
				final ClipboardContent content = new ClipboardContent();

				String selected = "";
				Set<UmbralesXEstadosXIndicador> selec = new HashSet<UmbralesXEstadosXIndicador>(
						tablaUmbrales.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();

				for (int i = 0; i < arr.length; i++) {
					selected += (((UmbralesXEstadosXIndicador) arr[i]).getIdIndicador().toString());
					selected += " ";

				}
				content.putString(selected.toString());

				// content.putString("Drag Me!");
				db.setContent(content);
				me.consume();
			}
		});
		tablaUmbrales.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(final DragEvent de) {

			}
		});

		tablaUmbrales.setOnDragOver(new EventHandler<DragEvent>() {
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
				Set<UmbralesXEstadosXIndicador> selec = new HashSet<UmbralesXEstadosXIndicador>(
						tablaUmbrales.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();

				for (int i = 0; i < arr.length; i++) {
					selected += (((UmbralesXEstadosXIndicador) arr[i]).getIdIndicador().toString());
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

