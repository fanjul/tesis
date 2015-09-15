package guiFX.BaseDeDatosGUI;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import baseDatos.hibernate.consultas.AbstractaConsulta;
import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.HSTumbralesXEstadosXIndicadorDAO;
import baseDatos.hibernate.tablas.HSTumbralesXEstadosXIndicador;
import guiFX.PanelDerecho;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HSTumbralesXEstadosXIndicadorGUI extends TableView<HSTumbralesXEstadosXIndicador>
		implements AbstractBaseDeDatosGUI {


	private TableView<HSTumbralesXEstadosXIndicador> tablaHSTUmbrales;
	private TableColumn<HSTumbralesXEstadosXIndicador, Integer> columnaIdIndicador;
	private TableColumn<HSTumbralesXEstadosXIndicador, Integer> columnaIdEstadoTipoIndicador;
	private TableColumn<HSTumbralesXEstadosXIndicador, Timestamp> columnaInicioUmbral;
	private TableColumn<HSTumbralesXEstadosXIndicador, Timestamp> columnaFinUmbral;
	private TableColumn<HSTumbralesXEstadosXIndicador, String> columnaOperadorUmbralSuperior;
	private TableColumn<HSTumbralesXEstadosXIndicador, String> columnaOperadorUmbralInferior;
	private TableColumn<HSTumbralesXEstadosXIndicador, Double> columnaValorUmbralSuperior;
	private TableColumn<HSTumbralesXEstadosXIndicador, Double> columnaValorUmbralInferior;
	private TableColumn<HSTumbralesXEstadosXIndicador, String> columnaObservaciones;
	private String texto = "";

	private ObservableList<HSTumbralesXEstadosXIndicador> data;

	
	
	public HSTumbralesXEstadosXIndicadorGUI(){
		super();
	}

	@SuppressWarnings("unchecked")
	public void mostrarTabla(){
		tablaHSTUmbrales = new TableView<HSTumbralesXEstadosXIndicador>();

		columnaIdIndicador = new TableColumn<HSTumbralesXEstadosXIndicador, Integer>("Id Indicador");
		columnaIdIndicador
				.setCellValueFactory(new PropertyValueFactory<HSTumbralesXEstadosXIndicador, Integer>("idIndicador"));

		columnaIdEstadoTipoIndicador = new TableColumn<HSTumbralesXEstadosXIndicador, Integer>(
				"Id Estado Tipo Indicador");
		columnaIdEstadoTipoIndicador.setCellValueFactory(
				new PropertyValueFactory<HSTumbralesXEstadosXIndicador, Integer>("idEstadoTipoIndicador"));

		columnaInicioUmbral = new TableColumn<HSTumbralesXEstadosXIndicador, Timestamp>("Inicio Umbral");
		columnaInicioUmbral.setCellValueFactory(
				new PropertyValueFactory<HSTumbralesXEstadosXIndicador, Timestamp>("inicioUmbral"));

		columnaFinUmbral = new TableColumn<HSTumbralesXEstadosXIndicador, Timestamp>("Fin Umbral");
		columnaFinUmbral
				.setCellValueFactory(new PropertyValueFactory<HSTumbralesXEstadosXIndicador, Timestamp>("finUmbral"));

		columnaOperadorUmbralSuperior = new TableColumn<HSTumbralesXEstadosXIndicador, String>(
				"Operador Umbral Superior");
		columnaOperadorUmbralSuperior.setCellValueFactory(
				new PropertyValueFactory<HSTumbralesXEstadosXIndicador, String>("operadorUmbralSuperior"));

		columnaOperadorUmbralInferior = new TableColumn<HSTumbralesXEstadosXIndicador, String>(
				"Operador Umbral Inferior");
		columnaOperadorUmbralInferior.setCellValueFactory(
				new PropertyValueFactory<HSTumbralesXEstadosXIndicador, String>("operadorUmbralInferior"));

		columnaValorUmbralSuperior = new TableColumn<HSTumbralesXEstadosXIndicador, Double>("Valor Umbral Superior");
		columnaValorUmbralSuperior.setCellValueFactory(
				new PropertyValueFactory<HSTumbralesXEstadosXIndicador, Double>("valorUmbralSuperior"));

		columnaValorUmbralInferior = new TableColumn<HSTumbralesXEstadosXIndicador, Double>("Valor Umbral Inferior");
		columnaValorUmbralInferior.setCellValueFactory(
				new PropertyValueFactory<HSTumbralesXEstadosXIndicador, Double>("valorUmbralInferior"));

		columnaObservaciones = new TableColumn<HSTumbralesXEstadosXIndicador, String>("Observaciones");
		columnaObservaciones
				.setCellValueFactory(new PropertyValueFactory<HSTumbralesXEstadosXIndicador, String>("observaciones"));

		tablaHSTUmbrales.getColumns().addAll(columnaIdIndicador, columnaIdEstadoTipoIndicador, columnaInicioUmbral,
				columnaFinUmbral, columnaOperadorUmbralSuperior, columnaOperadorUmbralInferior,
				columnaValorUmbralSuperior, columnaValorUmbralInferior, columnaObservaciones);

		this.agregarListenerEvent();
	}

	public TableView<HSTumbralesXEstadosXIndicador> getTablaHSTUmbrales() {
		return tablaHSTUmbrales;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, Integer> getColumnaIdIndicador() {
		return columnaIdIndicador;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, Integer> getColumnaIdEstadoTipoIndicador() {
		return columnaIdEstadoTipoIndicador;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, Timestamp> getColumnaInicioUmbral() {
		return columnaInicioUmbral;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, Timestamp> getColumnaFinUmbral() {
		return columnaFinUmbral;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, String> getColumnaOperadorUmbralSuperior() {
		return columnaOperadorUmbralSuperior;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, String> getColumnaOperadorUmbralInferior() {
		return columnaOperadorUmbralInferior;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, Double> getColumnaValorUmbralSuperior() {
		return columnaValorUmbralSuperior;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, Double> getColumnaValorUmbralInferior() {
		return columnaValorUmbralInferior;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, String> getColumnaObservaciones() {
		return columnaObservaciones;
	}


	public ObservableList<HSTumbralesXEstadosXIndicador> getData() {
		return data;
	}

	public void setData(ObservableList<HSTumbralesXEstadosXIndicador> data) {
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

		if (!centroInferior.getChildren().isEmpty()) {
			centroInferior.getChildren().remove(0);
		}
		this.setData(FXCollections.observableArrayList());
		List<HSTumbralesXEstadosXIndicador> lista = (List<HSTumbralesXEstadosXIndicador>) factoryConsultasDAO
				.getLista("HSTumbralesXEstadosXIndicador");
		lista = ((HSTumbralesXEstadosXIndicadorDAO) consulta).getTodos();
		for (HSTumbralesXEstadosXIndicador vi : lista) {
			this.getData().add(vi);

		}
		this.getTablaHSTUmbrales().setItems(this.getData());
		tablaHSTUmbrales.setPrefSize(centroInferior.getMaxWidth(), centroInferior.getMaxHeight());
		centroInferior.getChildren().add(0,this.getTablaHSTUmbrales());		
	}


	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	private void agregarListenerEvent() {
		// Para que se pueda seleccionar varias rows de la tabla
		tablaHSTUmbrales.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Drag & Drop de tabla al editor. Si queres pone el cursor donde
		// quieras y arrastras.
		tablaHSTUmbrales.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent me) {

				final Dragboard db = tablaHSTUmbrales.startDragAndDrop(TransferMode.COPY);

				final ClipboardContent content = new ClipboardContent();
				String selected = "";
				Set<HSTumbralesXEstadosXIndicador> selec = new HashSet<HSTumbralesXEstadosXIndicador>(
						tablaHSTUmbrales.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();
				for (int i = 0; i < arr.length; i++) {
					if ("IdIndicador".equalsIgnoreCase(getTexto())) {

						selected += (((HSTumbralesXEstadosXIndicador) arr[i]).getIdIndicador().toString());
						selected += " ";
					}
				}

				content.putString(selected.toString());

				db.setContent(content);
				me.consume();
			}
		});
		tablaHSTUmbrales.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(final DragEvent de) {

			}
		});

		tablaHSTUmbrales.setOnDragOver(new EventHandler<DragEvent>() {
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
				nuevaStage();
				Clipboard clipboard = Clipboard.getSystemClipboard();
				ClipboardContent content = new ClipboardContent();

				String selected = "";
				Set<HSTumbralesXEstadosXIndicador> selec = new HashSet<HSTumbralesXEstadosXIndicador>(
						tablaHSTUmbrales.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();
				for (int i = 0; i < arr.length; i++) {
					if ("IdIndicador".equalsIgnoreCase(getTexto())) {
						selected += (((HSTumbralesXEstadosXIndicador) arr[i]).getIdIndicador().toString());
						selected += " ";
					}

				}

				setTexto("");
				content.putString(selected);

				clipboard.setContent(content);

				PanelDerecho.getInstance().getEditorTexto().insertText(
						PanelDerecho.getInstance().getEditorTexto().getCaretPosition(), clipboard.getString());
			}

		});
	}

	public void nuevaStage() {
		Stage nuevoStage = new Stage();

		HBox ventana = new HBox();
		Label labelColumna = new Label("Seleccione Columna: ");
		TextField areaTexto = new TextField();
		Button botonAceptar = new Button("Aceptar");
		Button botonCancelar = new Button("Cancelar");
		HBox botones = new HBox();
		VBox todo = new VBox();

		botones.setSpacing(50);
		ventana.getChildren().addAll(labelColumna, areaTexto);
		ventana.setSpacing(50);
		ventana.setAlignment(Pos.CENTER);

		botonAceptar.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				HSTumbralesXEstadosXIndicadorGUI.this.setTexto(areaTexto.getText());
				nuevoStage.close();
			}
		});

		botonCancelar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				nuevoStage.close();
			}
		});

		botones.getChildren().addAll(botonAceptar, botonCancelar);
		todo.getChildren().addAll(ventana, botones);
		todo.setSpacing(100);
		Scene escena = new Scene(todo);

		nuevoStage.setScene(escena);
		nuevoStage.show();

	}
}
