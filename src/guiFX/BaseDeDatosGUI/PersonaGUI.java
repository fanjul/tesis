package guiFX.BaseDeDatosGUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.PersonaDAO;
import baseDatos.hibernate.tablas.Persona;
import dialogos.Dialogo;
import dialogos.DialogoSeleccionColumnaBD;
import guiFX.PanelDerecho;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class PersonaGUI extends TableView<Persona>implements AbstractBaseDeDatosGUI {

	private TableView<Persona> tablaPersona;
	private TableColumn<Persona, Integer> columnaId;
	private TableColumn<Persona, String> columnaNombre;
	private TableColumn<Persona, String> columnaApellido;
	private TableColumn<Persona, String> columnaEmail;
	private TableColumn<Persona, String> columnaTipoDocumento;
	private TableColumn<Persona, String> columnaNroDocumento;
	private TableColumn<Persona, String> columnaCargo;
	private TableColumn<Persona, String> columnaObsevaciones;
	private PersonaDAO consulta;
	private String texto = "";

	private ObservableList<Persona> data;

	public PersonaGUI() {
		super();
	}

	@SuppressWarnings("unchecked")
	public void mostrarTabla() {
		tablaPersona = new TableView<Persona>();
		tablaPersona.setEditable(true);
		columnaId = new TableColumn<Persona, Integer>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("id"));

		columnaNombre = new TableColumn<Persona, String>("Nombre");
		columnaNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
		columnaNombre.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaNombre.setOnEditCommit(new EventHandler<CellEditEvent<Persona, String>>() {
			@Override
			public void handle(CellEditEvent<Persona, String> t) {
				Persona persona = new Persona();
				persona = (Persona) t.getTableView().getItems().get(t.getTablePosition().getRow());

				persona.setNombre(t.getNewValue());
				PersonaDAO personaDAO = new PersonaDAO();
				personaDAO.actualizar(persona);

			}

		});

		columnaApellido = new TableColumn<Persona, String>("Apellido");
		columnaApellido.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellido"));
		columnaApellido.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaApellido.setOnEditCommit(new EventHandler<CellEditEvent<Persona, String>>() {
			@Override
			public void handle(CellEditEvent<Persona, String> t) {
				Persona persona = new Persona();
				persona = (Persona) t.getTableView().getItems().get(t.getTablePosition().getRow());

				persona.setApellido(t.getNewValue());
				PersonaDAO personaDAO = new PersonaDAO();
				personaDAO.actualizar(persona);

			}

		});

		columnaEmail = new TableColumn<Persona, String>("Email");
		columnaEmail.setCellValueFactory(new PropertyValueFactory<Persona, String>("email"));
		columnaEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaEmail.setOnEditCommit(new EventHandler<CellEditEvent<Persona, String>>() {
			@Override
			public void handle(CellEditEvent<Persona, String> t) {
				Persona persona = new Persona();
				persona = (Persona) t.getTableView().getItems().get(t.getTablePosition().getRow());

				persona.setEmail(t.getNewValue());
				PersonaDAO personaDAO = new PersonaDAO();
				personaDAO.actualizar(persona);

			}

		});

		columnaCargo = new TableColumn<Persona, String>("Cargo");
		columnaCargo.setCellValueFactory(new PropertyValueFactory<Persona, String>("cargo"));
		columnaCargo.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaCargo.setOnEditCommit(new EventHandler<CellEditEvent<Persona, String>>() {
			@Override
			public void handle(CellEditEvent<Persona, String> t) {
				Persona persona = new Persona();
				persona = (Persona) t.getTableView().getItems().get(t.getTablePosition().getRow());

				persona.setCargo(t.getNewValue());
				PersonaDAO personaDAO = new PersonaDAO();
				personaDAO.actualizar(persona);

			}

		});

		columnaTipoDocumento = new TableColumn<Persona, String>("Tipo Documento");
		columnaTipoDocumento.setCellValueFactory(new PropertyValueFactory<Persona, String>("tipoDocumento"));
		columnaTipoDocumento.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaTipoDocumento.setOnEditCommit(new EventHandler<CellEditEvent<Persona, String>>() {
			@Override
			public void handle(CellEditEvent<Persona, String> t) {
				Persona persona = new Persona();
				persona = (Persona) t.getTableView().getItems().get(t.getTablePosition().getRow());

				persona.setTipoDocumento(t.getNewValue());
				PersonaDAO personaDAO = new PersonaDAO();
				personaDAO.actualizar(persona);

			}

		});

		columnaNroDocumento = new TableColumn<Persona, String>("Nro Documento");
		columnaNroDocumento.setCellValueFactory(new PropertyValueFactory<Persona, String>("nroDocumento"));
		columnaNroDocumento.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaNroDocumento.setOnEditCommit(new EventHandler<CellEditEvent<Persona, String>>() {
			@Override
			public void handle(CellEditEvent<Persona, String> t) {
				Persona persona = new Persona();
				persona = (Persona) t.getTableView().getItems().get(t.getTablePosition().getRow());

				persona.setNroDocumento(t.getNewValue());
				PersonaDAO personaDAO = new PersonaDAO();
				personaDAO.actualizar(persona);

			}

		});

		columnaObsevaciones = new TableColumn<Persona, String>("Observaciones");
		columnaObsevaciones.setCellValueFactory(new PropertyValueFactory<Persona, String>("observaciones"));
		columnaObsevaciones.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaObsevaciones.setOnEditCommit(new EventHandler<CellEditEvent<Persona, String>>() {
			@Override
			public void handle(CellEditEvent<Persona, String> t) {
				Persona persona = new Persona();
				persona = (Persona) t.getTableView().getItems().get(t.getTablePosition().getRow());

				persona.setObservaciones(t.getNewValue());
				PersonaDAO personaDAO = new PersonaDAO();
				personaDAO.actualizar(persona);

			}

		});

		tablaPersona.getColumns().addAll(columnaId, columnaNombre, columnaApellido, columnaEmail, columnaCargo,
				columnaTipoDocumento, columnaNroDocumento, columnaObsevaciones);

		tablaPersona.getStyleClass().add("tablas");
		this.agregarListenerEvent();
	}

	public TableView<Persona> getTablaPersona() {
		return tablaPersona;
	}

	public TableColumn<Persona, Integer> getColumnaId() {
		return columnaId;
	}

	public TableColumn<Persona, String> getColumnaNombre() {
		return columnaNombre;
	}

	public TableColumn<Persona, String> getColumnaApellido() {
		return columnaApellido;
	}

	public TableColumn<Persona, String> getColumnaEmail() {
		return columnaEmail;
	}

	public TableColumn<Persona, String> getColumnaTipoDocumento() {
		return columnaTipoDocumento;
	}

	public TableColumn<Persona, String> getColumnaNroDocumento() {
		return columnaNroDocumento;
	}

	public TableColumn<Persona, String> getColumnaCargo() {
		return columnaCargo;
	}

	public TableColumn<Persona, String> getColumnaObsevaciones() {
		return columnaObsevaciones;
	}

	public ObservableList<Persona> getData() {
		return data;
	}

	public void setData(ObservableList<Persona> data) {
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
		this.consulta = (PersonaDAO) consulta;
		this.setData(FXCollections.observableArrayList());
		List<Persona> lista = (List<Persona>) factoryConsultasDAO.getLista("Persona");
		lista = this.consulta.getTodos();
		for (Persona vi : lista) {
			this.getData().add(vi);

		}
		this.getTablaPersona().setItems(this.getData());
		tablaPersona.setMaxSize(centroTabla.getMaxWidth(), centroTabla.getMaxHeight());
		tablaPersona.setMinSize(centroTabla.getMinWidth(), centroTabla.getMinHeight());
		centroTabla.getChildren().add(0, this.getTablaPersona());
	}

	private void agregarListenerEvent() {
		// Para que se pueda seleccionar varias rows de la tabla
		tablaPersona.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Drag & Drop de tabla al editor. Si queres pone el cursor donde
		// quieras y arrastras.
		tablaPersona.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent me) {
				final Dragboard db = tablaPersona.startDragAndDrop(TransferMode.COPY);
				final ClipboardContent content = new ClipboardContent();

				content.putString("");
				db.setContent(content);
				me.consume();

			}
		});
		tablaPersona.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(final DragEvent de) {

			}
		});

		tablaPersona.setOnDragOver(new EventHandler<DragEvent>() {
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
						Set<Persona> selec = new HashSet<Persona>(tablaPersona.getSelectionModel().getSelectedItems());
						Object[] arr = selec.toArray();

						String columnaSeleccionada = getTexto();
						for (int i = 0; i < arr.length; i++) {
							switch (columnaSeleccionada.toLowerCase()) {
							case "id":
								if (((Persona) arr[i]).getId() != null) {
									selected += ((Persona) arr[i]).getId().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "nombre":
								if (((Persona) arr[i]).getNombre() != null) {
									selected += ((Persona) arr[i]).getNombre();
									selected += " ";
								} else
									selected += "";
								break;
							case "apellido":
								if (((Persona) arr[i]).getApellido() != null) {
									selected += ((Persona) arr[i]).getApellido();
									selected += " ";
								} else
									selected += "";
								break;
							case "email":
								if (((Persona) arr[i]).getEmail() != null) {
									selected += ((Persona) arr[i]).getEmail();
									selected += " ";
								} else
									selected += "";
								break;
							case "tipodocumento":
								if (((Persona) arr[i]).getTipoDocumento() != null) {
									selected += ((Persona) arr[i]).getTipoDocumento();
									selected += " ";
								} else
									selected += "";
								break;
							case "nrodocumento":
								if (((Persona) arr[i]).getNroDocumento() != null) {
									selected += ((Persona) arr[i]).getNroDocumento();
									selected += " ";
								} else
									selected += "";
								break;
							case "cargo":
								if (((Persona) arr[i]).getCargo() != null) {
									selected += ((Persona) arr[i]).getCargo();
									selected += " ";
								} else
									selected += "";
								break;
							case "observaciones":
								if (((Persona) arr[i]).getObservaciones() != null) {
									selected += ((Persona) arr[i]).getObservaciones();
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
		Dialogo dialogoColumna = new DialogoSeleccionColumnaBD();
		((DialogoSeleccionColumnaBD) dialogoColumna).crearDialogo(consulta);
		dialogoColumna.mostrarDialogo();

		((DialogoSeleccionColumnaBD) dialogoColumna).getBotonAceptar().addEventHandler(ActionEvent.ACTION,
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						PersonaGUI.this
								.setTexto(((DialogoSeleccionColumnaBD) dialogoColumna).getComboBoxColumna().getValue());
						callback.run();
						dialogoColumna.cerrarDialogo();
					}
				});

		((DialogoSeleccionColumnaBD) dialogoColumna).getBotonCancelar().addEventHandler(ActionEvent.ACTION,
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						dialogoColumna.cerrarDialogo();
					}
				});

	}

}
