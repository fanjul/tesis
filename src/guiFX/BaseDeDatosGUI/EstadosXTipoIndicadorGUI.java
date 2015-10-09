package guiFX.BaseDeDatosGUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import baseDatos.hibernate.consultas.EstadosXTipoIndicadorDAO;
import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.tablas.EstadosXTipoIndicador;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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

public class EstadosXTipoIndicadorGUI extends TableView<EstadosXTipoIndicador>implements AbstractBaseDeDatosGUI {

	private TableView<EstadosXTipoIndicador> tablaEstadosXTipoIndicador;
	private TableColumn<EstadosXTipoIndicador, Integer> columnaIdTipoIndicador;
	private TableColumn<EstadosXTipoIndicador, Integer> columnaIdEstado;
	private TableColumn<EstadosXTipoIndicador, String> columnaEstado;
	private TableColumn<EstadosXTipoIndicador, String> columnaRepresentacionCromatica;
	private TableColumn<EstadosXTipoIndicador, String> columnaObservaciones;
	private EstadosXTipoIndicadorDAO consulta;
	private String texto = "";
	private ObservableList<EstadosXTipoIndicador> data;

	public EstadosXTipoIndicadorGUI() {
		super();
	}

	@SuppressWarnings("unchecked")
	public void mostrarTabla() {

		tablaEstadosXTipoIndicador = new TableView<EstadosXTipoIndicador>();
		tablaEstadosXTipoIndicador.setEditable(true);

		columnaEstado = new TableColumn<EstadosXTipoIndicador, String>("Estado");
		columnaEstado.setCellValueFactory(new PropertyValueFactory<EstadosXTipoIndicador, String>("estado"));
		columnaEstado.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaEstado.setOnEditCommit(new EventHandler<CellEditEvent<EstadosXTipoIndicador, String>>() {
			@Override
			public void handle(CellEditEvent<EstadosXTipoIndicador, String> t) {
				EstadosXTipoIndicador estadosX = new EstadosXTipoIndicador();
				estadosX = (EstadosXTipoIndicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

				estadosX.setEstado(t.getNewValue());
				EstadosXTipoIndicadorDAO estadoXDAO = new EstadosXTipoIndicadorDAO();
				estadoXDAO.actualizar(estadosX);

			}

		});

		columnaIdEstado = new TableColumn<EstadosXTipoIndicador, Integer>("id Estado");
		columnaIdEstado.setCellValueFactory(new PropertyValueFactory<EstadosXTipoIndicador, Integer>("idEstado"));

		columnaIdTipoIndicador = new TableColumn<EstadosXTipoIndicador, Integer>("Id Tipo Indicador");
		columnaIdTipoIndicador
				.setCellValueFactory(new PropertyValueFactory<EstadosXTipoIndicador, Integer>("idTipoIndicador"));

		columnaObservaciones = new TableColumn<EstadosXTipoIndicador, String>("Observaciones");
		columnaObservaciones
				.setCellValueFactory(new PropertyValueFactory<EstadosXTipoIndicador, String>("observaciones"));
		columnaObservaciones.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaObservaciones.setOnEditCommit(new EventHandler<CellEditEvent<EstadosXTipoIndicador, String>>() {
			@Override
			public void handle(CellEditEvent<EstadosXTipoIndicador, String> t) {
				EstadosXTipoIndicador estadosX = new EstadosXTipoIndicador();
				estadosX = (EstadosXTipoIndicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

				estadosX.setObservaciones(t.getNewValue());
				EstadosXTipoIndicadorDAO estadoXDAO = new EstadosXTipoIndicadorDAO();
				estadoXDAO.actualizar(estadosX);

			}

		});

		columnaRepresentacionCromatica = new TableColumn<EstadosXTipoIndicador, String>("Representacion Cronomatica");
		columnaRepresentacionCromatica.setCellValueFactory(
				new PropertyValueFactory<EstadosXTipoIndicador, String>("representacionCromatica"));
		columnaRepresentacionCromatica.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaRepresentacionCromatica
				.setOnEditCommit(new EventHandler<CellEditEvent<EstadosXTipoIndicador, String>>() {
					@Override
					public void handle(CellEditEvent<EstadosXTipoIndicador, String> t) {
						EstadosXTipoIndicador estadosX = new EstadosXTipoIndicador();
						estadosX = (EstadosXTipoIndicador) t.getTableView().getItems()
								.get(t.getTablePosition().getRow());

						estadosX.setRepresentacionCromatica(t.getNewValue());
						EstadosXTipoIndicadorDAO estadoXDAO = new EstadosXTipoIndicadorDAO();
						estadoXDAO.actualizar(estadosX);

					}

				});

		tablaEstadosXTipoIndicador.getColumns().addAll(columnaIdTipoIndicador, columnaIdEstado, columnaEstado,
				columnaRepresentacionCromatica, columnaObservaciones);
		this.agregarListenerEvent();
		tablaEstadosXTipoIndicador.getStyleClass().add("tablas");

	}

	public TableView<EstadosXTipoIndicador> getTablaEstadosXTipoIndicador() {
		return tablaEstadosXTipoIndicador;
	}

	public TableColumn<EstadosXTipoIndicador, Integer> getColumnaIdTipoIndicador() {
		return columnaIdTipoIndicador;
	}

	public TableColumn<EstadosXTipoIndicador, Integer> getColumnaIdEstado() {
		return columnaIdEstado;
	}

	public TableColumn<EstadosXTipoIndicador, String> getColumnaEstado() {
		return columnaEstado;
	}

	public TableColumn<EstadosXTipoIndicador, String> getColumnaRepresentacionCromatica() {
		return columnaRepresentacionCromatica;
	}

	public TableColumn<EstadosXTipoIndicador, String> getColumnaObservaciones() {
		return columnaObservaciones;
	}

	public ObservableList<EstadosXTipoIndicador> getData() {
		return data;
	}

	public void setData(ObservableList<EstadosXTipoIndicador> data) {
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
	public void mostrarTabla(Object consulta, FactoryConsultas factoryConsultasDAO, AnchorPane centroTabla) {
		if (!centroTabla.getChildren().isEmpty()) {
			centroTabla.getChildren().remove(0);
		}

		this.consulta = (EstadosXTipoIndicadorDAO) consulta;
		this.setData(FXCollections.observableArrayList());
		List<EstadosXTipoIndicador> lista = (List<EstadosXTipoIndicador>) factoryConsultasDAO
				.getLista("EstadosXTipoIndicador");
		lista = this.consulta.getTodos();
		for (EstadosXTipoIndicador vi : lista) {
			this.getData().add(vi);
		}

		this.getTablaEstadosXTipoIndicador().setItems(this.getData());
		tablaEstadosXTipoIndicador.setMaxSize(centroTabla.getMaxWidth(), centroTabla.getMaxHeight());
		tablaEstadosXTipoIndicador.setMinSize(centroTabla.getMinWidth(), centroTabla.getMinHeight());
		centroTabla.getChildren().add(0, this.getTablaEstadosXTipoIndicador());
	}

	private void agregarListenerEvent() {
		// Para que se pueda seleccionar varias rows de la tabla
		tablaEstadosXTipoIndicador.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Drag & Drop de tabla al editor. Si queres pone el cursor donde
		// quieras y arrastras.
		tablaEstadosXTipoIndicador.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent me) {
				final Dragboard db = tablaEstadosXTipoIndicador.startDragAndDrop(TransferMode.COPY);
				final ClipboardContent content = new ClipboardContent();

				content.putString("");
				db.setContent(content);
				me.consume();
			}
		});
		tablaEstadosXTipoIndicador.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(final DragEvent de) {

			}
		});

		tablaEstadosXTipoIndicador.setOnDragOver(new EventHandler<DragEvent>() {
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
						Set<EstadosXTipoIndicador> selec = new HashSet<EstadosXTipoIndicador>(
								tablaEstadosXTipoIndicador.getSelectionModel().getSelectedItems());
						Object[] arr = selec.toArray();

						String columnaSeleccionada = getTexto();
						for (int i = 0; i < arr.length; i++) {
							switch (columnaSeleccionada.toLowerCase()) {
							case "idtipoindicador":
								if (((EstadosXTipoIndicador) arr[i]).getIdTipoIndicador() != null) {
									selected += ((EstadosXTipoIndicador) arr[i]).getIdTipoIndicador().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "observaciones":
								if (((EstadosXTipoIndicador) arr[i]).getObservaciones() != null) {
									selected += ((EstadosXTipoIndicador) arr[i]).getObservaciones();
									selected += " ";
								} else
									selected += "";
								break;
							case "idestado":
								if (((EstadosXTipoIndicador) arr[i]).getIdEstado() != null) {
									selected += ((EstadosXTipoIndicador) arr[i]).getIdEstado().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "estado":
								if (((EstadosXTipoIndicador) arr[i]).getEstado() != null) {
									selected += ((EstadosXTipoIndicador) arr[i]).getEstado();
									selected += " ";
								} else
									selected += "";
								break;
							case "representacioncromatica":
								if (((EstadosXTipoIndicador) arr[i]).getRepresentacionCromatica() != null) {
									selected += ((EstadosXTipoIndicador) arr[i]).getRepresentacionCromatica();
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
				EstadosXTipoIndicadorGUI.this.setTexto(comboColumna.getValue());
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
