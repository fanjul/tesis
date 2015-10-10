package guiFX.BaseDeDatosGUI;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.GraficoDAO;
import baseDatos.hibernate.consultas.IndicadorDAO;
import baseDatos.hibernate.consultas.PersonaDAO;
import baseDatos.hibernate.consultas.TipoIndicadorDAO;
import baseDatos.hibernate.consultas.UnidadesDeMedidaDAO;
import baseDatos.hibernate.tablas.Grafico;
import baseDatos.hibernate.tablas.Indicador;
import baseDatos.hibernate.tablas.Persona;
import baseDatos.hibernate.tablas.TipoIndicador;
import baseDatos.hibernate.tablas.UnidadesDeMedida;
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

public class IndicadorGUI extends TableView<Indicador>implements AbstractBaseDeDatosGUI {

	private TableView<Indicador> tablaIndicador;
	private TableColumn<Indicador, Integer> columnaId;
	private TableColumn<Indicador, String> columnaCodigo;
	private TableColumn<Indicador, String> columnaNombre;
	private TableColumn<Indicador, Integer> columnaIdUnidadDeMedida;
	private TableColumn<Indicador, Integer> columnaDireccion;
	private TableColumn<Indicador, String> columnaFormula;
	private TableColumn<Indicador, String> columnaFichaMetodologica;
	private TableColumn<Indicador, Integer> columnaIdGrafico;
	private TableColumn<Indicador, Integer> columnaIdResponsable;
	private TableColumn<Indicador, String> columnaFrecuencia;
	private TableColumn<Indicador, String> columnaPeriodo;
	private TableColumn<Indicador, Timestamp> columnaFechaUltimaActualizacion;
	private TableColumn<Indicador, Integer> columnaIdTipoIndicador;
	private TableColumn<Indicador, String> columnaObservaciones;
	private IndicadorDAO consulta;
	private String texto = "";

	private ObservableList<Indicador> data;

	public IndicadorGUI() {
		super();
	}

	@SuppressWarnings("unchecked")
	public void mostrarTabla() {
		tablaIndicador = new TableView<Indicador>();
		tablaIndicador.setEditable(true);

		columnaId = new TableColumn<Indicador, Integer>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<Indicador, Integer>("id"));

		columnaCodigo = new TableColumn<Indicador, String>("Codigo");
		columnaCodigo.setCellValueFactory(new PropertyValueFactory<Indicador, String>("codigo"));
		columnaCodigo.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaCodigo.setOnEditCommit(new EventHandler<CellEditEvent<Indicador, String>>() {
			@Override
			public void handle(CellEditEvent<Indicador, String> t) {
				Indicador indicador = new Indicador();
				indicador = (Indicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

				indicador.setCodigo(t.getNewValue());
				IndicadorDAO indicadorDAO = new IndicadorDAO();
				indicadorDAO.actualizar(indicador);

			}

		});

		columnaNombre = new TableColumn<Indicador, String>("Nombre");
		columnaNombre.setCellValueFactory(new PropertyValueFactory<Indicador, String>("nombre"));
		columnaNombre.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaNombre.setOnEditCommit(new EventHandler<CellEditEvent<Indicador, String>>() {
			@Override
			public void handle(CellEditEvent<Indicador, String> t) {
				Indicador indicador = new Indicador();
				indicador = (Indicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

				indicador.setNombre(t.getNewValue());
				IndicadorDAO indicadorDAO = new IndicadorDAO();
				indicadorDAO.actualizar(indicador);

			}

		});

		columnaIdGrafico = new TableColumn<Indicador, Integer>("Id Grafico");
		columnaIdGrafico.setCellValueFactory(new PropertyValueFactory<Indicador, Integer>("idGrafico"));
		columnaIdGrafico
				.setCellFactory(TextFieldTableCell.<Indicador, Integer> forTableColumn(new IntegerStringConverter()));
		columnaIdGrafico.setOnEditCommit(new EventHandler<CellEditEvent<Indicador, Integer>>() {
			@Override
			public void handle(CellEditEvent<Indicador, Integer> t) {
				Indicador indicador = new Indicador();
				indicador = (Indicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

				List<Grafico> lista = new GraficoDAO().getTodos();
				boolean esta = false;
				int i = 0;
				while (i < lista.size() && !esta) {
					if (lista.get(i).getId().equals(t.getNewValue())) {
						esta = true;
					}
					i++;
				}

				if (!esta) {
					Dialogo dialogoErrorFK = new DialogoErrorFK("grafico");
					dialogoErrorFK.crearDialogo();
					dialogoErrorFK.mostrarDialogo();
					((DialogoErrorFK) dialogoErrorFK).getBotonAceptar().setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							dialogoErrorFK.cerrarDialogo();
						}
					});

					t.getTableView().getItems().set(t.getTablePosition().getRow(), indicador);

				} else {

					indicador.setIdGrafico(t.getNewValue());
					IndicadorDAO indicadorDAO = new IndicadorDAO();
					indicadorDAO.actualizar(indicador);
				}
			}

		});

		columnaIdUnidadDeMedida = new TableColumn<Indicador, Integer>("Id Unidad de Medida");
		columnaIdUnidadDeMedida.setCellValueFactory(new PropertyValueFactory<Indicador, Integer>("idUnidadDeMedida"));
		columnaIdUnidadDeMedida
				.setCellFactory(TextFieldTableCell.<Indicador, Integer> forTableColumn(new IntegerStringConverter()));
		columnaIdUnidadDeMedida.setOnEditCommit(new EventHandler<CellEditEvent<Indicador, Integer>>() {
			@Override
			public void handle(CellEditEvent<Indicador, Integer> t) {
				Indicador indicador = new Indicador();
				indicador = (Indicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

				List<UnidadesDeMedida> lista = new UnidadesDeMedidaDAO().getTodos();
				boolean esta = false;
				int i = 0;
				while (i < lista.size() && !esta) {
					if (lista.get(i).getId().equals(t.getNewValue())) {
						esta = true;
					}
					i++;
				}

				if (!esta) {
					Dialogo dialogoErrorFK = new DialogoErrorFK("unidad de medida");
					dialogoErrorFK.crearDialogo();
					dialogoErrorFK.mostrarDialogo();
					((DialogoErrorFK) dialogoErrorFK).getBotonAceptar().setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							dialogoErrorFK.cerrarDialogo();
						}
					});

					t.getTableView().getItems().set(t.getTablePosition().getRow(), indicador);

				} else {

					indicador.setIdUnidadDeMedida(t.getNewValue());
					IndicadorDAO indicadorDAO = new IndicadorDAO();
					indicadorDAO.actualizar(indicador);
				}
			}

		});

		columnaDireccion = new TableColumn<Indicador, Integer>("Direccion");
		columnaDireccion.setCellValueFactory(new PropertyValueFactory<Indicador, Integer>("direccion"));
		columnaDireccion
				.setCellFactory(TextFieldTableCell.<Indicador, Integer> forTableColumn(new IntegerStringConverter()));
		columnaDireccion.setOnEditCommit(new EventHandler<CellEditEvent<Indicador, Integer>>() {
			@Override
			public void handle(CellEditEvent<Indicador, Integer> t) {
				Indicador indicador = new Indicador();
				indicador = (Indicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

				indicador.setDireccion(t.getNewValue());
				IndicadorDAO indicadorDAO = new IndicadorDAO();
				indicadorDAO.actualizar(indicador);
			}

		});

		columnaFormula = new TableColumn<Indicador, String>("formula");
		columnaFormula.setCellValueFactory(new PropertyValueFactory<Indicador, String>("formula"));
		columnaFormula.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaFormula.setOnEditCommit(new EventHandler<CellEditEvent<Indicador, String>>() {
			@Override
			public void handle(CellEditEvent<Indicador, String> t) {
				Indicador indicador = new Indicador();
				indicador = (Indicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

				indicador.setFormula(t.getNewValue());
				IndicadorDAO indicadorDAO = new IndicadorDAO();
				indicadorDAO.actualizar(indicador);

			}

		});

		columnaFichaMetodologica = new TableColumn<Indicador, String>("Ficha Metodologica");
		columnaFichaMetodologica.setCellValueFactory(new PropertyValueFactory<Indicador, String>("fichaMetodologica"));
		columnaFichaMetodologica.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaFichaMetodologica.setOnEditCommit(new EventHandler<CellEditEvent<Indicador, String>>() {
			@Override
			public void handle(CellEditEvent<Indicador, String> t) {
				Indicador indicador = new Indicador();
				indicador = (Indicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

				indicador.setFichaMetodologica(t.getNewValue());
				IndicadorDAO indicadorDAO = new IndicadorDAO();
				indicadorDAO.actualizar(indicador);

			}

		});

		columnaIdResponsable = new TableColumn<Indicador, Integer>("Id Responsable");
		columnaIdResponsable.setCellValueFactory(new PropertyValueFactory<Indicador, Integer>("idResponsable"));
		columnaIdResponsable
				.setCellFactory(TextFieldTableCell.<Indicador, Integer> forTableColumn(new IntegerStringConverter()));
		columnaIdResponsable.setOnEditCommit(new EventHandler<CellEditEvent<Indicador, Integer>>() {
			@Override
			public void handle(CellEditEvent<Indicador, Integer> t) {
				Indicador indicador = new Indicador();
				indicador = (Indicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

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

					t.getTableView().getItems().set(t.getTablePosition().getRow(), indicador);

				} else {

					indicador.setIdResponsable(t.getNewValue());
					IndicadorDAO indicadorDAO = new IndicadorDAO();
					indicadorDAO.actualizar(indicador);
				}
			}

		});

		columnaFrecuencia = new TableColumn<Indicador, String>("Frecuencia");
		columnaFrecuencia.setCellValueFactory(new PropertyValueFactory<Indicador, String>("frecuencia"));
		columnaFrecuencia.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaFrecuencia.setOnEditCommit(new EventHandler<CellEditEvent<Indicador, String>>() {
			@Override
			public void handle(CellEditEvent<Indicador, String> t) {
				Indicador indicador = new Indicador();
				indicador = (Indicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

				indicador.setFrecuencia(t.getNewValue());
				IndicadorDAO indicadorDAO = new IndicadorDAO();
				indicadorDAO.actualizar(indicador);

			}

		});

		columnaPeriodo = new TableColumn<Indicador, String>("Periodo");
		columnaPeriodo.setCellValueFactory(new PropertyValueFactory<Indicador, String>("periodo"));
		columnaPeriodo.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaPeriodo.setOnEditCommit(new EventHandler<CellEditEvent<Indicador, String>>() {
			@Override
			public void handle(CellEditEvent<Indicador, String> t) {
				Indicador indicador = new Indicador();
				indicador = (Indicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

				indicador.setPeriodo(t.getNewValue());
				IndicadorDAO indicadorDAO = new IndicadorDAO();
				indicadorDAO.actualizar(indicador);

			}

		});

		columnaFechaUltimaActualizacion = new TableColumn<Indicador, Timestamp>("Fecha Ultima Actualizacion");
		columnaFechaUltimaActualizacion
				.setCellValueFactory(new PropertyValueFactory<Indicador, Timestamp>("fechaUltimaActualizacion"));

		columnaIdTipoIndicador = new TableColumn<Indicador, Integer>("Id Tipo Indicador");
		columnaIdTipoIndicador.setCellValueFactory(new PropertyValueFactory<Indicador, Integer>("idTipoIndicador"));
		columnaIdTipoIndicador
				.setCellFactory(TextFieldTableCell.<Indicador, Integer> forTableColumn(new IntegerStringConverter()));
		columnaIdTipoIndicador.setOnEditCommit(new EventHandler<CellEditEvent<Indicador, Integer>>() {
			@Override
			public void handle(CellEditEvent<Indicador, Integer> t) {
				Indicador indicador = new Indicador();
				indicador = (Indicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

				List<TipoIndicador> lista = new TipoIndicadorDAO().getTodos();
				boolean esta = false;
				int i = 0;
				while (i < lista.size() && !esta) {
					if (lista.get(i).getId().equals(t.getNewValue())) {
						esta = true;
					}
					i++;
				}

				if (!esta) {
					Dialogo dialogoErrorFK = new DialogoErrorFK("tipo indicador");
					dialogoErrorFK.crearDialogo();
					dialogoErrorFK.mostrarDialogo();
					((DialogoErrorFK) dialogoErrorFK).getBotonAceptar().setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							dialogoErrorFK.cerrarDialogo();
						}
					});

					t.getTableView().getItems().set(t.getTablePosition().getRow(), indicador);

				} else {

					indicador.setIdTipoIndicador(t.getNewValue());
					IndicadorDAO indicadorDAO = new IndicadorDAO();
					indicadorDAO.actualizar(indicador);
				}
			}

		});

		columnaObservaciones = new TableColumn<Indicador, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<Indicador, String>("observaciones"));
		columnaObservaciones.setCellFactory(TextFieldTableCell.forTableColumn());
		columnaObservaciones.setOnEditCommit(new EventHandler<CellEditEvent<Indicador, String>>() {
			@Override
			public void handle(CellEditEvent<Indicador, String> t) {
				Indicador indicador = new Indicador();
				indicador = (Indicador) t.getTableView().getItems().get(t.getTablePosition().getRow());

				indicador.setObservaciones(t.getNewValue());
				IndicadorDAO indicadorDAO = new IndicadorDAO();
				indicadorDAO.actualizar(indicador);

			}

		});

		tablaIndicador.getColumns().addAll(columnaId, columnaCodigo, columnaNombre, columnaIdUnidadDeMedida,
				columnaDireccion, columnaFormula, columnaFichaMetodologica, columnaIdGrafico, columnaIdResponsable,
				columnaFrecuencia, columnaPeriodo, columnaFechaUltimaActualizacion, columnaIdTipoIndicador,
				columnaObservaciones);

		tablaIndicador.getStyleClass().add("tablas");

		this.agregarListenerEvent();
	}

	public TableView<Indicador> getTablaIndicador() {
		return tablaIndicador;
	}

	public TableColumn<Indicador, Integer> getColumnaId() {
		return columnaId;
	}

	public TableColumn<Indicador, String> getColumnaCodigo() {
		return columnaCodigo;
	}

	public TableColumn<Indicador, String> getColumnaNombre() {
		return columnaNombre;
	}

	public TableColumn<Indicador, Integer> getColumnaIdUnidadDeMedida() {
		return columnaIdUnidadDeMedida;
	}

	public TableColumn<Indicador, Integer> getColumnaDireccion() {
		return columnaDireccion;
	}

	public TableColumn<Indicador, String> getColumnaFormula() {
		return columnaFormula;
	}

	public TableColumn<Indicador, String> getColumnaFichaMetodologica() {
		return columnaFichaMetodologica;
	}

	public TableColumn<Indicador, Integer> getColumnaIdGrafico() {
		return columnaIdGrafico;
	}

	public TableColumn<Indicador, Integer> getColumnaIdResponsable() {
		return columnaIdResponsable;
	}

	public TableColumn<Indicador, String> getColumnaFrecuencia() {
		return columnaFrecuencia;
	}

	public TableColumn<Indicador, String> getColumnaPeriodo() {
		return columnaPeriodo;
	}

	public TableColumn<Indicador, Timestamp> getColumnaFechaUltimaActualizacion() {
		return columnaFechaUltimaActualizacion;
	}

	public TableColumn<Indicador, Integer> getColumnaIdTipoIndicador() {
		return columnaIdTipoIndicador;
	}

	public TableColumn<Indicador, String> getColumnaObservaciones() {
		return columnaObservaciones;
	}

	public ObservableList<Indicador> getData() {
		return data;
	}

	public void setData(ObservableList<Indicador> data) {
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
		this.consulta = (IndicadorDAO) consulta;
		this.setData(FXCollections.observableArrayList());
		List<Indicador> lista = (List<Indicador>) factoryConsultasDAO.getLista("Indicador");
		lista = this.consulta.getTodos();
		for (Indicador vi : lista) {
			this.getData().add(vi);

		}
		this.getTablaIndicador().setItems(this.getData());
		tablaIndicador.setMaxSize(centroTabla.getMaxWidth(), centroTabla.getMaxHeight());
		tablaIndicador.setMinSize(centroTabla.getMinWidth(), centroTabla.getMinHeight());
		centroTabla.getChildren().add(0, this.getTablaIndicador());

	}

	private void agregarListenerEvent() {
		// Para que se pueda seleccionar varias rows de la tabla
		tablaIndicador.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Drag & Drop de tabla al editor. Si queres pone el cursor donde
		// quieras y arrastras.
		tablaIndicador.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent me) {
				final Dragboard db = tablaIndicador.startDragAndDrop(TransferMode.COPY);
				final ClipboardContent content = new ClipboardContent();

				content.putString("");
				db.setContent(content);
				me.consume();

			}
		});
		tablaIndicador.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(final DragEvent de) {

			}
		});

		tablaIndicador.setOnDragOver(new EventHandler<DragEvent>() {
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
						Set<Indicador> selec = new HashSet<Indicador>(
								tablaIndicador.getSelectionModel().getSelectedItems());
						Object[] arr = selec.toArray();

						String columnaSeleccionada = getTexto();
						for (int i = 0; i < arr.length; i++) {
							switch (columnaSeleccionada.toLowerCase()) {
							case "id":
								if (((Indicador) arr[i]).getId() != null) {
									selected += ((Indicador) arr[i]).getId().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "observaciones":
								if (((Indicador) arr[i]).getObservaciones() != null) {
									selected += ((Indicador) arr[i]).getObservaciones();
									selected += " ";
								} else
									selected += "";
								break;
							case "codigo":
								if (((Indicador) arr[i]).getCodigo() != null) {
									selected += ((Indicador) arr[i]).getCodigo();
									selected += " ";
								} else
									selected += "";
								break;
							case "nombre":
								if (((Indicador) arr[i]).getNombre() != null) {
									selected += ((Indicador) arr[i]).getNombre();
									selected += " ";
								} else
									selected += "";
								break;
							case "idunidaddemedida":
								if (((Indicador) arr[i]).getIdUnidadDeMedida() != null) {
									selected += ((Indicador) arr[i]).getIdUnidadDeMedida().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "direccion":
								if (((Indicador) arr[i]).getDireccion() != null) {
									selected += ((Indicador) arr[i]).getDireccion().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "formula":
								if (((Indicador) arr[i]).getFormula() != null) {
									selected += ((Indicador) arr[i]).getFormula();
									selected += " ";
								} else
									selected += "";
								break;
							case "fichametodologica":
								if (((Indicador) arr[i]).getFichaMetodologica() != null) {
									selected += ((Indicador) arr[i]).getFichaMetodologica();
									selected += " ";
								} else
									selected += "";
								break;
							case "idgrafico":
								if (((Indicador) arr[i]).getIdGrafico() != null) {
									selected += ((Indicador) arr[i]).getIdGrafico().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "idresponsable":
								if (((Indicador) arr[i]).getIdResponsable() != null) {
									selected += ((Indicador) arr[i]).getIdResponsable().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "frecuencia":
								if (((Indicador) arr[i]).getFrecuencia() != null) {
									selected += ((Indicador) arr[i]).getFrecuencia();
									selected += " ";
								} else
									selected += "";
								break;
							case "periodo":
								if (((Indicador) arr[i]).getPeriodo() != null) {
									selected += ((Indicador) arr[i]).getPeriodo();
									selected += " ";
								} else
									selected += "";
								break;
							case "fechaultimaactualizacion":
								if (((Indicador) arr[i]).getFechaUltimaActualizacion() != null) {
									selected += ((Indicador) arr[i]).getFechaUltimaActualizacion().toString();
									selected += " ";
								} else
									selected += "";
								break;
							case "idtipoindicador":
								if (((Indicador) arr[i]).getIdTipoIndicador() != null) {
									selected += ((Indicador) arr[i]).getIdTipoIndicador().toString();
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
				IndicadorGUI.this.setTexto(comboColumna.getValue());
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
