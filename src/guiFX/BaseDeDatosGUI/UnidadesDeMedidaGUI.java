package guiFX.BaseDeDatosGUI;

import baseDatos.hibernate.tablas.UnidadesDeMedida;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UnidadesDeMedidaGUI extends TableView<UnidadesDeMedida> {
	
	private TableView<UnidadesDeMedida> tablaUnidadesdDeMedida;
	private TableColumn<UnidadesDeMedida, Integer> columnaId;
	private TableColumn<UnidadesDeMedida, String> columnaUnidadDeMedida;
	private TableColumn<UnidadesDeMedida, String> columnaObservaciones;
	
	private ObservableList<UnidadesDeMedida> data; 
	
	@SuppressWarnings("unchecked")
	public UnidadesDeMedidaGUI() {
		super();
		tablaUnidadesdDeMedida = new TableView<UnidadesDeMedida>();
		
		columnaId = new TableColumn<UnidadesDeMedida, Integer>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<UnidadesDeMedida,Integer>("id"));
		
		columnaUnidadDeMedida = new TableColumn<UnidadesDeMedida, String>("Unidad de Medida");
		columnaUnidadDeMedida.setCellValueFactory(new PropertyValueFactory<UnidadesDeMedida,String>("unidadDeMedida"));
		
		columnaObservaciones = new TableColumn<UnidadesDeMedida, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<UnidadesDeMedida,String>("observaciones"));
		
		tablaUnidadesdDeMedida.getColumns().addAll(columnaId,columnaUnidadDeMedida,columnaObservaciones);
		
		tablaUnidadesdDeMedida.getStyleClass().add("tablas");
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
	
	

}
