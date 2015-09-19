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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UmbralesXEstadosXIndicadorGUI extends TableView<UmbralesXEstadosXIndicador>
		implements AbstractBaseDeDatosGUI {

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
	private UmbralesXEstadosXIndicadorDAO consulta;
	private String texto = "";
	private ObservableList<UmbralesXEstadosXIndicador> data;

	public UmbralesXEstadosXIndicadorGUI() {
		super();
	}

	@SuppressWarnings("unchecked")
	public void mostrarTabla() {
		tablaUmbrales = new TableView<UmbralesXEstadosXIndicador>();

		columnaIdIndicador = new TableColumn<UmbralesXEstadosXIndicador, Integer>("Id Indicador");
		columnaIdIndicador
				.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador, Integer>("idIndicador"));

		columnaIdEstadoTipoIndicador = new TableColumn<UmbralesXEstadosXIndicador, Integer>("Id Estado Tipo Indicador");
		columnaIdEstadoTipoIndicador.setCellValueFactory(
				new PropertyValueFactory<UmbralesXEstadosXIndicador, Integer>("idEstadoTipoIndicador"));

		columnaInicioUmbral = new TableColumn<UmbralesXEstadosXIndicador, Timestamp>("Inicio Umbral");
		columnaInicioUmbral
				.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador, Timestamp>("inicioUmbral"));

		columnaFinUmbral = new TableColumn<UmbralesXEstadosXIndicador, Timestamp>("Fin Umbral");
		columnaFinUmbral
				.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador, Timestamp>("finUmbral"));

		columnaOperadorUmbralSuperior = new TableColumn<UmbralesXEstadosXIndicador, String>("Operador Umbral Superior");
		columnaOperadorUmbralSuperior.setCellValueFactory(
				new PropertyValueFactory<UmbralesXEstadosXIndicador, String>("operadorUmbralSuperior"));

		columnaOperadorUmbralInferior = new TableColumn<UmbralesXEstadosXIndicador, String>("Operador Umbral Inferior");
		columnaOperadorUmbralInferior.setCellValueFactory(
				new PropertyValueFactory<UmbralesXEstadosXIndicador, String>("operadorUmbralInferior"));

		columnaValorUmbralSuperior = new TableColumn<UmbralesXEstadosXIndicador, Double>("Valor Umbral Superior");
		columnaValorUmbralSuperior.setCellValueFactory(
				new PropertyValueFactory<UmbralesXEstadosXIndicador, Double>("valorUmbralSuperior"));

		columnaValorUmbralInferior = new TableColumn<UmbralesXEstadosXIndicador, Double>("Valor Umbral Inferior");
		columnaValorUmbralInferior.setCellValueFactory(
				new PropertyValueFactory<UmbralesXEstadosXIndicador, Double>("valorUmbralInferior"));

		columnaObservaciones = new TableColumn<UmbralesXEstadosXIndicador, String>("Observaciones");
		columnaObservaciones
				.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador, String>("observaciones"));

		tablaUmbrales.getColumns().addAll(columnaIdIndicador, columnaIdEstadoTipoIndicador, columnaInicioUmbral,
				columnaFinUmbral, columnaOperadorUmbralSuperior, columnaOperadorUmbralInferior,
				columnaValorUmbralSuperior, columnaValorUmbralInferior, columnaObservaciones);

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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public void crearTablaBaseDeDatos() {
		this.mostrarTabla();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void mostrarTabla(Object consulta, FactoryConsultas factoryConsultasDAO, AnchorPane centroInferior) {

		if (!centroInferior.getChildren().isEmpty()) {
			centroInferior.getChildren().remove(0);
		}
		this.consulta = (UmbralesXEstadosXIndicadorDAO) consulta;
		this.setData(FXCollections.observableArrayList());
		List<UmbralesXEstadosXIndicador> lista = (List<UmbralesXEstadosXIndicador>) factoryConsultasDAO
				.getLista("UmbralesXEstadosXIndicador");
		lista = this.consulta.getTodos();
		for (UmbralesXEstadosXIndicador vi : lista) {
			this.getData().add(vi);

		}
		this.getTablaUmbrales().setItems(this.getData());
		tablaUmbrales.setPrefSize(centroInferior.getMaxWidth(), centroInferior.getMaxHeight());
		centroInferior.getChildren().add(0, this.getTablaUmbrales());
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

				content.putString("");
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
				Runnable callback = new Runnable() {

					@Override
					public void run() {
						Clipboard clipboard = Clipboard.getSystemClipboard();
						ClipboardContent content = new ClipboardContent();

						String selected = "";
						Set<UmbralesXEstadosXIndicador> selec = new HashSet<UmbralesXEstadosXIndicador>(
								tablaUmbrales.getSelectionModel().getSelectedItems());
						Object[] arr = selec.toArray();

						String columnaSeleccionada = getTexto();
						for (int i = 0; i < arr.length; i++) {
							switch (columnaSeleccionada.toLowerCase()) {
							case "idindicador":
								if (((UmbralesXEstadosXIndicador) arr[i]).getIdIndicador() != null) {
									selected += ((UmbralesXEstadosXIndicador) arr[i]).getIdIndicador().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "idestadotipoindicador":
								if (((UmbralesXEstadosXIndicador) arr[i]).getIdEstadoTipoIndicador() != null) {
									selected += ((UmbralesXEstadosXIndicador) arr[i]).getIdEstadoTipoIndicador()
											.toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "inicioumbral":
								if (((UmbralesXEstadosXIndicador) arr[i]).getInicioUmbral() != null) {
									selected += ((UmbralesXEstadosXIndicador) arr[i]).getInicioUmbral().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "finumbral":
								if (((UmbralesXEstadosXIndicador) arr[i]).getFinUmbral() != null) {
									selected += ((UmbralesXEstadosXIndicador) arr[i]).getFinUmbral().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "operadorumbralsuperior":
								if (((UmbralesXEstadosXIndicador) arr[i]).getOperadorUmbralSuperior() != null) {
									selected += ((UmbralesXEstadosXIndicador) arr[i]).getOperadorUmbralSuperior();
									selected += " ";
								} else
									selected += "";
								break;
							case "operadorumbralinferior":
								if (((UmbralesXEstadosXIndicador) arr[i]).getOperadorUmbralInferior() != null) {
									selected += ((UmbralesXEstadosXIndicador) arr[i]).getOperadorUmbralInferior();
									selected += " ";
								} else
									selected += "";
								break;
							case "valorumbralsuperior":
								if (((UmbralesXEstadosXIndicador) arr[i]).getValorUmbralSuperior() != null) {
									selected += ((UmbralesXEstadosXIndicador) arr[i]).getValorUmbralSuperior();
									selected += " ";
								} else
									selected += "";
								break;
							case "valorumbralinferior":
								if (((UmbralesXEstadosXIndicador) arr[i]).getValorUmbralInferior() != null) {
									selected += ((UmbralesXEstadosXIndicador) arr[i]).getValorUmbralInferior();
									selected += " ";
								} else
									selected += "";
								break;
							case "observaciones":
								if (((UmbralesXEstadosXIndicador) arr[i]).getObservaciones() != null) {
									selected += ((UmbralesXEstadosXIndicador) arr[i]).getObservaciones();
									selected += " ";
								} else
									selected += "";
								break;
							}
						}
						content.putString(selected);

						clipboard.setContent(content);

						PanelDerecho.getInstance().getEditorTexto().insertText(
								PanelDerecho.getInstance().getEditorTexto().getCaretPosition(), clipboard.getString());
						setTexto("");

					}

				};
				nuevaStage(callback);

			}

		});
	}

	public void nuevaStage(Runnable callback) {
		Stage nuevoStage = new Stage();

		HBox ventana = new HBox();
		Label labelColumna = new Label("Seleccione Columna: ");
		ComboBox<String> comboColumna = new ComboBox<String>();
		Button botonAceptar = new Button("Aceptar");
		Button botonCancelar = new Button("Cancelar");
		HBox botones = new HBox();
		VBox todo = new VBox();

		botones.setSpacing(50);
		ventana.getChildren().addAll(labelColumna, comboColumna);
		ventana.setSpacing(50);
		ventana.setAlignment(Pos.CENTER);

		ObservableList<String> listaColumnas = FXCollections.observableArrayList();
		List<String> listaTodasTablas = consulta.getColumnas();
		for (String s : listaTodasTablas) {
			listaColumnas.add(s);
		}
		comboColumna.setItems(listaColumnas);

		botonAceptar.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				UmbralesXEstadosXIndicadorGUI.this.setTexto(comboColumna.getValue());
				callback.run();
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
