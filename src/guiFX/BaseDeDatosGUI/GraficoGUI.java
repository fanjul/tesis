package guiFX.BaseDeDatosGUI;

import baseDatos.hibernate.tablas.Grafico;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class GraficoGUI extends TableView<Grafico>{
	
	private TableView<Grafico> tablaGrafico;
	private TableColumn<Grafico, Integer> columnaId;
	private TableColumn<Grafico, String> columnaTipoGrafico;
	private TableColumn<Grafico, String> columnaObservaciones;
	
	private ObservableList<Grafico> data; 


	@SuppressWarnings("unchecked")
	public GraficoGUI() {
		super();
		tablaGrafico = new TableView<Grafico>();
		
		columnaId = new TableColumn<Grafico, Integer>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<Grafico,Integer>("id"));
		
		columnaTipoGrafico = new TableColumn<Grafico, String>("Tipo Grafico");
		columnaTipoGrafico.setCellValueFactory(new PropertyValueFactory<Grafico,String>("tipoGrafico"));
		
		columnaObservaciones = new TableColumn<Grafico, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<Grafico,String>("observaciones"));
		
		tablaGrafico.getColumns().addAll(columnaId,columnaTipoGrafico,columnaObservaciones);
	}


	public TableView<Grafico> getTablaGrafico() {
		return tablaGrafico;
	}


	public TableColumn<Grafico, Integer> getColumnaId() {
		return columnaId;
	}


	public TableColumn<Grafico, String> getColumnaTipoGrafico() {
		return columnaTipoGrafico;
	}


	public TableColumn<Grafico, String> getColumnaObservaciones() {
		return columnaObservaciones;
	}


	public ObservableList<Grafico> getData() {
		return data;
	}
	
	
}
