package guiFX.BaseDeDatosGUI;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import baseDatos.hibernate.tablas.ValorIndicador;
import guiFX.EditorTexto;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;

public class ValorIndicadorGUI extends TableView<ValorIndicador> {

	private TableView<ValorIndicador> tablaValorIndicador;
	private TableColumn<ValorIndicador, Integer> columnaIdIndicador;
	private TableColumn<ValorIndicador, Timestamp> columnaFecha;
	private TableColumn<ValorIndicador, Double> columnaValor;
	private TableColumn<ValorIndicador, String> columnaEstado;
	private TableColumn<ValorIndicador, Double> columnaVariacion;
	private TableColumn<ValorIndicador, Integer> columnaSignoVariacion;
	private TableColumn<ValorIndicador, String> columnaObservaciones;

	private ObservableList<ValorIndicador> data;
	private Clipboard clipboard;

	@SuppressWarnings({ "unchecked" })
	public ValorIndicadorGUI() {
		super();
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
	
	public Clipboard getClipboard() {
		return this.clipboard;
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

	
	private void agregarListenerEvent(){
		//logica para cuando se selecciona algo de la tabla y se pega en el editor.

		this.tablaValorIndicador.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        	tablaValorIndicador.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    		clipboard = Clipboard.getSystemClipboard();
			ClipboardContent content = new ClipboardContent();

        	Set<ValorIndicador> selec = new HashSet<ValorIndicador>(tablaValorIndicador.getSelectionModel().getSelectedItems());
        	
        	String selected = "";
        	
        	Object[] arr = selec.toArray();
        	
        	for(int i =0; i<arr.length;i++){
        		selected += (((ValorIndicador)arr[i]).getIdIndicador().toString());
        		selected += " ";

        	}
    		content.putString(selected);

        	clipboard.setContent( content );
			EditorTexto.getInstance().getEditorTexto().appendText(clipboard.getString());

        }
    });
		

		
	}
}
