package guiFX.BaseDeDatosGUI;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import baseDatos.hibernate.consultas.AbstractaConsulta;
import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.ValorIndicadorDAO;
import baseDatos.hibernate.tablas.ValorIndicador;
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

public class ValorIndicadorGUI extends TableView<ValorIndicador> implements AbstractBaseDeDatosGUI {

	private TableView<ValorIndicador> tablaValorIndicador;
	private TableColumn<ValorIndicador, Integer> columnaIdIndicador;
	private TableColumn<ValorIndicador, Timestamp> columnaFecha;
	private TableColumn<ValorIndicador, Double> columnaValor;
	private TableColumn<ValorIndicador, String> columnaEstado;
	private TableColumn<ValorIndicador, Double> columnaVariacion;
	private TableColumn<ValorIndicador, Integer> columnaSignoVariacion;
	private TableColumn<ValorIndicador, String> columnaObservaciones;

	private ObservableList<ValorIndicador> data;

	public ValorIndicadorGUI() {
		super();
	}

	@SuppressWarnings({ "unchecked" })
	public void mostrarTabla() {
		tablaValorIndicador = new TableView<ValorIndicador>();

		columnaIdIndicador = new TableColumn<ValorIndicador, Integer>("Id Indicador");
		columnaIdIndicador.setCellValueFactory(new PropertyValueFactory<ValorIndicador, Integer>("idIndicador"));

		columnaFecha = new TableColumn<ValorIndicador, Timestamp>("Fecha");
		columnaFecha.setCellValueFactory(new PropertyValueFactory<ValorIndicador, Timestamp>("fecha"));

		columnaValor = new TableColumn<ValorIndicador, Double>("Valor");
		columnaValor.setCellValueFactory(new PropertyValueFactory<ValorIndicador, Double>("valor"));

		columnaEstado = new TableColumn<ValorIndicador, String>("Estado");
		columnaEstado.setCellValueFactory(new PropertyValueFactory<ValorIndicador, String>("estado"));

		columnaVariacion = new TableColumn<ValorIndicador, Double>("Variacion");
		columnaVariacion.setCellValueFactory(new PropertyValueFactory<ValorIndicador, Double>("variacion"));

		columnaSignoVariacion = new TableColumn<ValorIndicador, Integer>("Signo Variacion");
		columnaSignoVariacion.setCellValueFactory(new PropertyValueFactory<ValorIndicador, Integer>("signoVariacion"));

		columnaObservaciones = new TableColumn<ValorIndicador, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<ValorIndicador, String>("observaciones"));

		tablaValorIndicador.getColumns().addAll(columnaIdIndicador, columnaFecha, columnaValor, columnaEstado,
				columnaVariacion, columnaSignoVariacion, columnaObservaciones);

		this.agregarListenerEvent();
	}

	public TableView<ValorIndicador> getTablaValorIndicador() {
		return tablaValorIndicador;
	}

	public TableColumn<ValorIndicador, Integer> getColumnaIdIndicador() {
		return columnaIdIndicador;
	}

	public TableColumn<ValorIndicador, Timestamp> getColumnaFecha() {
		return columnaFecha;
	}

	public TableColumn<ValorIndicador, Double> getColumnaValor() {
		return columnaValor;
	}

	public TableColumn<ValorIndicador, String> getColumnaEstado() {
		return columnaEstado;
	}

	public TableColumn<ValorIndicador, Double> getColumnaVariacion() {
		return columnaVariacion;
	}

	public TableColumn<ValorIndicador, Integer> getColumnaSignoVariacion() {
		return columnaSignoVariacion;
	}

	public TableColumn<ValorIndicador, String> getColumnaObservaciones() {
		return columnaObservaciones;
	}

	public ObservableList<ValorIndicador> getData() {
		return data;
	}

	public void setData(ObservableList<ValorIndicador> data) {
		this.data = data;

	}

	private void agregarListenerEvent() {
		// Para que se pueda seleccionar varias rows de la tabla
		tablaValorIndicador.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Drag & Drop de tabla al editor. Si queres pone el cursor donde
		// quieras y arrastras.
		tablaValorIndicador.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent me) {
				final Dragboard db = tablaValorIndicador.startDragAndDrop(TransferMode.COPY);
				final ClipboardContent content = new ClipboardContent();

				String selected = "";
				Set<ValorIndicador> selec = new HashSet<ValorIndicador>(
						tablaValorIndicador.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();

				for (int i = 0; i < arr.length; i++) {
					selected += (((ValorIndicador) arr[i]).getIdIndicador().toString());
					selected += " ";

				}
				content.putString(selected.toString());

				// content.putString("Drag Me!");
				db.setContent(content);
				me.consume();
			}
		});
		tablaValorIndicador.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(final DragEvent de) {

			}
		});

		tablaValorIndicador.setOnDragOver(new EventHandler<DragEvent>() {
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
				Set<ValorIndicador> selec = new HashSet<ValorIndicador>(
						tablaValorIndicador.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();

				for (int i = 0; i < arr.length; i++) {
					selected += (((ValorIndicador) arr[i]).getIdIndicador().toString());
					selected += " ";

				}
				content.putString(selected);

				clipboard.setContent(content);

				PanelDerecho.getInstance().getEditorTexto().insertText(
						PanelDerecho.getInstance().getEditorTexto().getCaretPosition(), clipboard.getString());
			}

		});
	}

	@Override
	public void crearTablaBaseDeDatos() {
		this.mostrarTabla();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void mostrarTabla(AbstractaConsulta consulta, FactoryConsultas factoryConsultasDAO,
			AnchorPane centroInferior) {
		this.setData(FXCollections.observableArrayList());
		if (!centroInferior.getChildren().isEmpty()) {
			centroInferior.getChildren().remove(0);
		}
		List<ValorIndicador> lista = (List<ValorIndicador>) factoryConsultasDAO.getLista("ValorIndicador");
		lista = ((ValorIndicadorDAO) consulta).getTodos();
		for (ValorIndicador vi : lista) {
			this.getData().add(vi);

		}
		this.getTablaValorIndicador().setItems(this.getData());
		tablaValorIndicador.setPrefSize(centroInferior.getMaxWidth(), centroInferior.getMaxHeight());
		centroInferior.getChildren().add(0, this.getTablaValorIndicador());
		

	}

}
