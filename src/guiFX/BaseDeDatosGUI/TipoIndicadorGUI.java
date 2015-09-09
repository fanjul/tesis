package guiFX.BaseDeDatosGUI;

import baseDatos.hibernate.tablas.TipoIndicador;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TipoIndicadorGUI extends TableView<TipoIndicador> {
	
	private TableView<TipoIndicador> tablaTipoIndicador;
	private TableColumn<TipoIndicador, Integer> columnaId;
	private TableColumn<TipoIndicador, String> columnaTipo;
	private TableColumn<TipoIndicador, String> columnaObservaciones;
	
	private ObservableList<TipoIndicador> data; 

	
	@SuppressWarnings("unchecked")
	public TipoIndicadorGUI() {
		super();
		tablaTipoIndicador = new TableView<TipoIndicador>();
		
		columnaId = new TableColumn<TipoIndicador, Integer>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<TipoIndicador,Integer>("id"));
		
		columnaTipo = new TableColumn<TipoIndicador, String>("Tipo");
		columnaTipo.setCellValueFactory(new PropertyValueFactory<TipoIndicador,String>("tipo"));
		
		columnaObservaciones = new TableColumn<TipoIndicador, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<TipoIndicador,String>("observaciones"));
	
		tablaTipoIndicador.getColumns().addAll(columnaId,columnaTipo,columnaObservaciones);
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
	
}