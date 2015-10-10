package guiFX.BaseDeDatosGUI;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.IndicadorDAO;
import baseDatos.hibernate.consultas.PersonaDAO;
import baseDatos.hibernate.consultas.TableroDAO;
import baseDatos.hibernate.tablas.Indicador;
import baseDatos.hibernate.tablas.Persona;
import baseDatos.hibernate.tablas.Tablero;
import dialogos.Dialogo;
import dialogos.DialogoErrorFK;
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
import javafx.util.converter.IntegerStringConverter;

public class TableroGUI extends TableView<Tablero>implements AbstractBaseDeDatosGUI {

	private TableView<Tablero> tablaTablero;
	private TableColumn<Tablero, Integer> columnaId;
	private TableColumn<Tablero, String> columnaNombre;
	private TableColumn<Tablero, Integer> columnaIdIndicador;
	private TableColumn<Tablero, Integer> columnaIdResponsable;
	private TableColumn<Tablero, Timestamp> columnaFechaUltimaActualizacion;
	private TableroDAO consulta;
	private String texto = "";
	private ObservableList<Tablero> data;

	public TableroGUI() {
		super();
	}

	@SuppressWarnings({ "unchecked" })
	public void mostrarTabla() {
		tablaTablero = new TableView<Tablero>();
		tablaTablero.setEditable(true);

		columnaId = new TableColumn<Tablero, Integer>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<Tablero, Integer>("id"));

		columnaNombre = new TableColumn<Tablero, String>("Nombre");
		columnaNombre.setCellValueFactory(new PropertyValueFactory<Tablero, String>("nombre"));
		columnaNombre.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaNombre.setOnEditCommit(new EventHandler<CellEditEvent<Tablero, String>>() {
			@Override
			public void handle(CellEditEvent<Tablero, String> t) {
				Tablero tablero = new Tablero();
				tablero = (Tablero) t.getTableView().getItems().get(t.getTablePosition().getRow());

				tablero.setNombre(t.getNewValue());
				TableroDAO valorIndicadorDAO = new TableroDAO();
				valorIndicadorDAO.actualizar(tablero);

			}
		});

		columnaIdIndicador = new TableColumn<Tablero, Integer>("Id Indicador");
		columnaIdIndicador.setCellValueFactory(new PropertyValueFactory<Tablero, Integer>("idIndicador"));
		columnaIdIndicador
				.setCellFactory(TextFieldTableCell.<Tablero, Integer> forTableColumn(new IntegerStringConverter()));
		columnaIdIndicador.setOnEditCommit(new EventHandler<CellEditEvent<Tablero, Integer>>() {
			@Override
			public void handle(CellEditEvent<Tablero, Integer> t) {
				Tablero tablero = new Tablero();
				tablero = (Tablero) t.getTableView().getItems().get(t.getTablePosition().getRow());

				IndicadorDAO indicador = new IndicadorDAO();
				List<Indicador> lista = indicador.getTodos();

				boolean esta = false;
				int i = 0;
				while (i < lista.size() && !esta) {
					if (lista.get(i).getId().equals(t.getNewValue())) {
						esta = true;
					}
					i++;
				}

				if (!esta) {
					Dialogo dialogoErrorFK = new DialogoErrorFK("indicador");
					dialogoErrorFK.crearDialogo();
					dialogoErrorFK.mostrarDialogo();
					((DialogoErrorFK) dialogoErrorFK).getBotonAceptar().setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							dialogoErrorFK.cerrarDialogo();
						}
					});

					t.getTableView().getItems().set(t.getTablePosition().getRow(), tablero);

				} else {
					tablero.setIdIndicador(t.getNewValue());
					TableroDAO tableroDAO = new TableroDAO();
					tableroDAO.actualizar(tablero);
				}

			}

		});

		columnaIdResponsable = new TableColumn<Tablero, Integer>("Id Responsable");
		columnaIdResponsable.setCellValueFactory(new PropertyValueFactory<Tablero, Integer>("idResponsable"));
		columnaIdResponsable
				.setCellFactory(TextFieldTableCell.<Tablero, Integer> forTableColumn(new IntegerStringConverter()));
		columnaIdResponsable.setOnEditCommit(new EventHandler<CellEditEvent<Tablero, Integer>>() {
			@Override
			public void handle(CellEditEvent<Tablero, Integer> t) {
				Tablero tablero = new Tablero();
				tablero = (Tablero) t.getTableView().getItems().get(t.getTablePosition().getRow());

				List<Persona> lista = new PersonaDAO().getTodos();
				boolean esta = false;
				int i = 0;
				while (i < lista.size() && !esta) {
					if (lista.get(i).getId().equals(t.getNewValue())) {
						esta = true;
					}
					i++;
				}

				if (!esta) {
					Dialogo dialogoErrorFK = new DialogoErrorFK("persona");
					dialogoErrorFK.crearDialogo();
					dialogoErrorFK.mostrarDialogo();
					((DialogoErrorFK) dialogoErrorFK).getBotonAceptar().setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							dialogoErrorFK.cerrarDialogo();
						}
					});

					t.getTableView().getItems().set(t.getTablePosition().getRow(), tablero);

				} else {
					tablero.setIdResponsable(t.getNewValue());
					TableroDAO tableroDAO = new TableroDAO();
					tableroDAO.actualizar(tablero);
				}

			}

		});

		columnaFechaUltimaActualizacion = new TableColumn<Tablero, Timestamp>("Fecha Ultima Actualizacion");
		columnaFechaUltimaActualizacion
				.setCellValueFactory(new PropertyValueFactory<Tablero, Timestamp>("fechaUltimaActualizacion"));

		tablaTablero.getColumns().addAll(columnaId, columnaNombre, columnaIdIndicador, columnaIdResponsable,
				columnaFechaUltimaActualizacion);

		tablaTablero.getStyleClass().add("tablas");
		this.agregarListenerEvent();

	}

	public TableView<Tablero> getTablaTablero() {
		return tablaTablero;
	}

	public TableColumn<Tablero, Integer> getColumnaId() {
		return columnaId;
	}

	public TableColumn<Tablero, String> getColumnaNombre() {
		return columnaNombre;
	}

	public TableColumn<Tablero, Integer> getColumnaIdIndicador() {
		return columnaIdIndicador;
	}

	public TableColumn<Tablero, Integer> getColumnaIdResponsable() {
		return columnaIdResponsable;
	}

	public TableroDAO getConsulta() {
		return consulta;
	}

	public String getTexto() {
		return texto;
	}

	public ObservableList<Tablero> getData() {
		return data;
	}

	public void setData(ObservableList<Tablero> data) {
		this.data = data;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	private void agregarListenerEvent() {
		// Para que se pueda seleccionar varias rows de la tabla
		tablaTablero.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Drag & Drop de tabla al editor. Si queres pone el cursor donde
		// quieras y arrastras.
		tablaTablero.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent me) {

				final Dragboard db = tablaTablero.startDragAndDrop(TransferMode.COPY);
				final ClipboardContent content = new ClipboardContent();

				content.putString("");
				db.setContent(content);
				me.consume();
			}
		});
		tablaTablero.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(final DragEvent de) {

			}
		});

		tablaTablero.setOnDragOver(new EventHandler<DragEvent>() {
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
						Set<Tablero> selec = new HashSet<Tablero>(tablaTablero.getSelectionModel().getSelectedItems());
						Object[] arr = selec.toArray();

						String columnaSeleccionada = getTexto();
						for (int i = 0; i < arr.length; i++) {
							switch (columnaSeleccionada.toLowerCase()) {
							case "id":
								if (((Tablero) arr[i]).getId() != null) {
									selected += ((Tablero) arr[i]).getId().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "nombre":
								if (((Tablero) arr[i]).getNombre() != null) {
									selected += ((Tablero) arr[i]).getNombre();
									selected += " ";
								} else
									selected += "";
								break;
							case "idindicador":
								if (((Tablero) arr[i]).getIdIndicador() != null) {
									selected += ((Tablero) arr[i]).getIdIndicador().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "idresponsable":
								if (((Tablero) arr[i]).getIdResponsable() != null) {
									selected += ((Tablero) arr[i]).getIdResponsable().toString();
									selected += " ";
								} else
									selected += "";
								break;

							case "fechaultimaactualizacion":
								if (((Tablero) arr[i]).getFechaUltimaActualizacion() != null) {
									selected += ((Tablero) arr[i]).getFechaUltimaActualizacion().toString();
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

	@Override
	public void crearTablaBaseDeDatos() {
		this.mostrarTabla();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void mostrarTabla(Object consulta, FactoryConsultas factoryConsultasDAO, AnchorPane centroTabla) {

		this.setData(FXCollections.observableArrayList());
		if (!centroTabla.getChildren().isEmpty()) {
			centroTabla.getChildren().remove(0);
		}
		this.consulta = (TableroDAO) consulta;
		List<Tablero> lista = (List<Tablero>) factoryConsultasDAO.getLista("Tablero");
		lista = this.consulta.getTodos();
		for (Tablero vi : lista) {
			this.getData().add(vi);

		}
		this.getTablaTablero().setItems(this.getData());
		tablaTablero.setMaxSize(centroTabla.getMaxWidth(), centroTabla.getMaxHeight());
		tablaTablero.setMinSize(centroTabla.getMinWidth(), centroTabla.getMinHeight());
		centroTabla.getChildren().add(0, this.getTablaTablero());

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
				TableroGUI.this.setTexto(comboColumna.getValue());
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
