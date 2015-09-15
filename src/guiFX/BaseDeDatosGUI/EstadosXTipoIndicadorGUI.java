package guiFX.BaseDeDatosGUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import baseDatos.hibernate.consultas.AbstractaConsulta;
import baseDatos.hibernate.consultas.EstadosXTipoIndicadorDAO;
import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.tablas.EstadosXTipoIndicador;
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

public class EstadosXTipoIndicadorGUI extends TableView<EstadosXTipoIndicador>implements AbstractBaseDeDatosGUI {
	private TableView<EstadosXTipoIndicador> tablaEstadosXTipoIndicador;
	private TableColumn<EstadosXTipoIndicador, Integer> columnaIdTipoIndicador;
	private TableColumn<EstadosXTipoIndicador, Integer> columnaIdEstado;
	private TableColumn<EstadosXTipoIndicador, String> columnaEstado;
	private TableColumn<EstadosXTipoIndicador, String> columnaRepresentacionCromatica;
	private TableColumn<EstadosXTipoIndicador, String> columnaObservaciones;

	private ObservableList<EstadosXTipoIndicador> data;

	public EstadosXTipoIndicadorGUI() {
		super();
	}

	@SuppressWarnings("unchecked")
	public void mostrarTabla() {
		tablaEstadosXTipoIndicador = new TableView<EstadosXTipoIndicador>();

		columnaEstado = new TableColumn<EstadosXTipoIndicador, String>("Estado");
		columnaEstado.setCellValueFactory(new PropertyValueFactory<EstadosXTipoIndicador, String>("estado"));

		columnaIdEstado = new TableColumn<EstadosXTipoIndicador, Integer>("id Estado");
		columnaIdEstado.setCellValueFactory(new PropertyValueFactory<EstadosXTipoIndicador, Integer>("idEstado"));

		columnaIdTipoIndicador = new TableColumn<EstadosXTipoIndicador, Integer>("Id Tipo Indicador");
		columnaIdTipoIndicador
				.setCellValueFactory(new PropertyValueFactory<EstadosXTipoIndicador, Integer>("idTipoIndicador"));

		columnaObservaciones = new TableColumn<EstadosXTipoIndicador, String>("Observaciones");
		columnaObservaciones
				.setCellValueFactory(new PropertyValueFactory<EstadosXTipoIndicador, String>("observaciones"));

		columnaRepresentacionCromatica = new TableColumn<EstadosXTipoIndicador, String>("Representacion Cronomatica");
		columnaRepresentacionCromatica.setCellValueFactory(
				new PropertyValueFactory<EstadosXTipoIndicador, String>("representacionCromatica"));

		tablaEstadosXTipoIndicador.getColumns().addAll(columnaIdTipoIndicador, columnaIdEstado, columnaEstado,
				columnaRepresentacionCromatica, columnaObservaciones);
		this.agregarListenerEvent();

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
		List<EstadosXTipoIndicador> lista = (List<EstadosXTipoIndicador>) factoryConsultasDAO
				.getLista("EstadosXTipoIndicador");
		lista = ((EstadosXTipoIndicadorDAO) consulta).getTodos();
		for (EstadosXTipoIndicador vi : lista) {
			this.getData().add(vi);

		}
		this.getTablaEstadosXTipoIndicador().setItems(this.getData());
		tablaEstadosXTipoIndicador.setPrefSize(centroInferior.getMaxWidth(), centroInferior.getMaxHeight());
		centroInferior.getChildren().add(0, this.getTablaEstadosXTipoIndicador());

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

				String selected = "";
				Set<EstadosXTipoIndicador> selec = new HashSet<EstadosXTipoIndicador>(
						tablaEstadosXTipoIndicador.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();

				for (int i = 0; i < arr.length; i++) {
					selected += (((EstadosXTipoIndicador) arr[i]).getIdTipoIndicador().toString());
					selected += " ";

				}
				content.putString(selected.toString());

				// content.putString("Drag Me!");
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
				Clipboard clipboard = Clipboard.getSystemClipboard();
				ClipboardContent content = new ClipboardContent();

				String selected = "";
				Set<EstadosXTipoIndicador> selec = new HashSet<EstadosXTipoIndicador>(
						tablaEstadosXTipoIndicador.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();

				for (int i = 0; i < arr.length; i++) {
					selected += (((EstadosXTipoIndicador) arr[i]).getIdTipoIndicador().toString());
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
