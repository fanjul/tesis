package guiFX.BaseDeDatosGUI;

import java.util.List;

import baseDatos.hibernate.consultas.AbstractaConsulta;
import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.UnidadesDeMedidaDAO;
import baseDatos.hibernate.tablas.UnidadesDeMedida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class UnidadesDeMedidaGUI extends TableView<UnidadesDeMedida> implements AbstractBaseDeDatosGUI {
	
	private TableView<UnidadesDeMedida> tablaUnidadesdDeMedida;
	private TableColumn<UnidadesDeMedida, Integer> columnaId;
	private TableColumn<UnidadesDeMedida, String> columnaUnidadDeMedida;
	private TableColumn<UnidadesDeMedida, String> columnaObservaciones;
	
	private ObservableList<UnidadesDeMedida> data; 
	
	public UnidadesDeMedidaGUI() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public void mostrarTabla(){
		tablaUnidadesdDeMedida = new TableView<UnidadesDeMedida>();
		
		columnaId = new TableColumn<UnidadesDeMedida, Integer>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<UnidadesDeMedida,Integer>("id"));
		
		columnaUnidadDeMedida = new TableColumn<UnidadesDeMedida, String>("Unidad de Medida");
		columnaUnidadDeMedida.setCellValueFactory(new PropertyValueFactory<UnidadesDeMedida,String>("unidadDeMedida"));
		
		columnaObservaciones = new TableColumn<UnidadesDeMedida, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<UnidadesDeMedida,String>("observaciones"));
		
		tablaUnidadesdDeMedida.getColumns().addAll(columnaId,columnaUnidadDeMedida,columnaObservaciones);
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

	@Override
	public void crearTablaBaseDeDatos() {
		this.mostrarTabla();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void mostrarTabla(AbstractaConsulta consulta, FactoryConsultas factoryConsultasDAO,
			AnchorPane centroInferior) {

		if(!centroInferior.getChildren().isEmpty()){
			centroInferior.getChildren().remove(0);
		}
		this.setData(FXCollections.observableArrayList());
		List<UnidadesDeMedida> lista = (List<UnidadesDeMedida>) factoryConsultasDAO.getLista("UnidadesDeMedida");
		lista = ((UnidadesDeMedidaDAO)consulta).getTodos();
		for (UnidadesDeMedida vi : lista) { 
			this.getData().add(vi);

		}
		this.getTablaUnidadesdDeMedida().setItems(this.getData());	
		tablaUnidadesdDeMedida.setPrefSize(centroInferior.getMaxWidth(), centroInferior.getMaxHeight());
		centroInferior.getChildren().add(0,this.getTablaUnidadesdDeMedida());			
	}
	
	

}
