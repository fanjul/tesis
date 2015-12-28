package guiFX.BaseDeDatosGUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.TipoIndicadorDAO;
import baseDatos.hibernate.tablas.TipoIndicador;
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

public class TipoIndicadorGUI extends TableView<TipoIndicador>implements AbstractBaseDeDatosGUI {

	private TableView<TipoIndicador> tablaTipoIndicador;
	private TableColumn<TipoIndicador, Integer> columnaId;
	private TableColumn<TipoIndicador, String> columnaTipo;
	private TableColumn<TipoIndicador, String> columnaObservaciones;
	private TipoIndicadorDAO consulta;
	private String texto = "";
	private ObservableList<TipoIndicador> data;

	public TipoIndicadorGUI() {
		super();
	}

	@SuppressWarnings("unchecked")
	public void mostrarTabla() {
		tablaTipoIndicador = new TableView<TipoIndicador>();
	
		columnaId = new TableColumn<TipoIndicador, Integer>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<TipoIndicador, Integer>("id"));

		columnaTipo = new TableColumn<TipoIndicador, String>("Tipo");
		columnaTipo.setCellValueFactory(new PropertyValueFactory<TipoIndicador, String>("tipo"));
		
		columnaObservaciones = new TableColumn<TipoIndicador, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<TipoIndicador, String>("observaciones"));
		
		tablaTipoIndicador.getColumns().addAll(columnaId, columnaTipo, columnaObservaciones);

		tablaTipoIndicador.getStyleClass().add("tablas");
		this.agregarListenerEvent();
	}

	public TableView<TipoIndicador> getTablaTipoIndicador() {
		return tablaTipoIndicador;
	}

	public TableColumn<TipoIndicador, Integer> getColumnaId() {
		return columnaId;
	}

	public TableColumn<TipoIndicador, String> getColumnaTipo() {
		return columnaTipo;
	}

	public TableColumn<TipoIndicador, String> getColumnaObservaciones() {
		return columnaObservaciones;
	}

	public ObservableList<TipoIndicador> getData() {
		return data;
	}

	public void setData(ObservableList<TipoIndicador> data) {
		this.data = data;
	}

	@Override
	public void crearTablaBaseDeDatos() {
		this.mostrarTabla();
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void mostrarTabla(Object consulta, FactoryConsultas factoryConsultasDAO, AnchorPane centroTabla) {

		if (!centroTabla.getChildren().isEmpty()) {
			centroTabla.getChildren().remove(0);
		}
		this.consulta = (TipoIndicadorDAO) consulta;
		this.setData(FXCollections.observableArrayList());
		List<TipoIndicador> lista = (List<TipoIndicador>) factoryConsultasDAO.getLista("TipoIndicador");
		lista = this.consulta.getTodos();
		for (TipoIndicador vi : lista) {
			this.getData().add(vi);

		}
		this.getTablaTipoIndicador().setItems(this.getData());
		tablaTipoIndicador.setMaxSize(centroTabla.getMaxWidth(), centroTabla.getMaxHeight());
		tablaTipoIndicador.setMinSize(centroTabla.getMinWidth(), centroTabla.getMinHeight());
		centroTabla.getChildren().add(0, this.getTablaTipoIndicador());
	}

	private void agregarListenerEvent() {
		// Para que se pueda seleccionar varias rows de la tabla
		tablaTipoIndicador.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Drag & Drop de tabla al editor. Si queres pone el cursor donde
		// quieras y arrastras.
		tablaTipoIndicador.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent me) {
				final Dragboard db = tablaTipoIndicador.startDragAndDrop(TransferMode.COPY);
				final ClipboardContent content = new ClipboardContent();

				content.putString("");
				db.setContent(content);
				me.consume();

			}
		});
		tablaTipoIndicador.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(final DragEvent de) {

			}
		});

		tablaTipoIndicador.setOnDragOver(new EventHandler<DragEvent>() {
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
						Set<TipoIndicador> selec = new HashSet<TipoIndicador>(
								tablaTipoIndicador.getSelectionModel().getSelectedItems());
						Object[] arr = selec.toArray();

						String columnaSeleccionada = getTexto();
						for (int i = 0; i < arr.length; i++) {
							switch (columnaSeleccionada.toLowerCase()) {
							case "id":
								if (((TipoIndicador) arr[i]).getId() != null) {
									selected += ((TipoIndicador) arr[i]).getId().toString();
									selected += ", ";
								} else
									selected += "";
								break;
							case "tipo":
								if (((TipoIndicador) arr[i]).getTipo() != null) {
									selected += ((TipoIndicador) arr[i]).getTipo();
									selected += ", ";
								} else
									selected += "";
								break;
							case "observaciones":
								if (((TipoIndicador) arr[i]).getObservaciones() != null) {
									selected += ((TipoIndicador) arr[i]).getObservaciones();
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
						TipoIndicadorGUI.this
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