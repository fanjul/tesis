package guiFX.BaseDeDatosGUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.UnidadesDeMedidaDAO;
import baseDatos.hibernate.tablas.UnidadesDeMedida;
import dialogos.Dialogo;
import dialogos.DialogoSeleccionColumnaBD;
import guiFX.PanelDerecho;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class UnidadesDeMedidaGUI extends TableView<UnidadesDeMedida>implements AbstractBaseDeDatosGUI {

	private TableView<UnidadesDeMedida> tablaUnidadesdDeMedida;
	private TableColumn<UnidadesDeMedida, Integer> columnaId;
	private TableColumn<UnidadesDeMedida, String> columnaUnidadDeMedida;
	private TableColumn<UnidadesDeMedida, String> columnaObservaciones;
	private UnidadesDeMedidaDAO consulta;
	private String texto = "";
	private ObservableList<UnidadesDeMedida> data;

	public UnidadesDeMedidaGUI() {
		super();
	}

	@SuppressWarnings("unchecked")
	public void mostrarTabla() {
		tablaUnidadesdDeMedida = new TableView<UnidadesDeMedida>();

		columnaId = new TableColumn<UnidadesDeMedida, Integer>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<UnidadesDeMedida, Integer>("id"));

		columnaUnidadDeMedida = new TableColumn<UnidadesDeMedida, String>("Unidad de Medida");
		columnaUnidadDeMedida.setCellValueFactory(new PropertyValueFactory<UnidadesDeMedida, String>("unidadDeMedida"));
		
		columnaObservaciones = new TableColumn<UnidadesDeMedida, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<UnidadesDeMedida, String>("observaciones"));
		
		tablaUnidadesdDeMedida.getColumns().addAll(columnaId, columnaUnidadDeMedida, columnaObservaciones);

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
		this.consulta = (UnidadesDeMedidaDAO) consulta;
		this.setData(FXCollections.observableArrayList());
		List<UnidadesDeMedida> lista = (List<UnidadesDeMedida>) factoryConsultasDAO.getLista("UnidadesDeMedida");
		lista = this.consulta.getTodos();
		for (UnidadesDeMedida vi : lista) {
			this.getData().add(vi);

		}
		this.getTablaUnidadesdDeMedida().setItems(this.getData());
		tablaUnidadesdDeMedida.setMaxSize(centroTabla.getMaxWidth(), centroTabla.getMaxHeight());
		tablaUnidadesdDeMedida.setMinSize(centroTabla.getMinWidth(), centroTabla.getMinHeight());
		centroTabla.getChildren().add(0, this.getTablaUnidadesdDeMedida());

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

				content.putString("");
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
				Runnable callback = new Runnable() {

					@Override
					public void run() {
						Clipboard clipboard = Clipboard.getSystemClipboard();
						ClipboardContent content = new ClipboardContent();

						String selected = "";
						Set<UnidadesDeMedida> selec = new HashSet<UnidadesDeMedida>(
								tablaUnidadesdDeMedida.getSelectionModel().getSelectedItems());
						Object[] arr = selec.toArray();

						String columnaSeleccionada = getTexto();
						for (int i = 0; i < arr.length; i++) {
							switch (columnaSeleccionada.toLowerCase()) {
							case "id":
								if (((UnidadesDeMedida) arr[i]).getId() != null) {
									selected += ((UnidadesDeMedida) arr[i]).getId().toString();
									selected += ", ";
								} else
									selected += "";
								break;
							case "unidaddemedida":
								if (((UnidadesDeMedida) arr[i]).getUnidadDeMedida() != null) {
									selected += ((UnidadesDeMedida) arr[i]).getUnidadDeMedida();
									selected += ", ";
								} else
									selected += "";
								break;
							case "observaciones":
								if (((UnidadesDeMedida) arr[i]).getObservaciones() != null) {
									selected += ((UnidadesDeMedida) arr[i]).getObservaciones();
									selected += ", ";
								} else
									selected += "";
								break;

							}
						}
						selected = selected.substring(0,selected.length()-2); //eliminar la ultima coma
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
						UnidadesDeMedidaGUI.this
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
