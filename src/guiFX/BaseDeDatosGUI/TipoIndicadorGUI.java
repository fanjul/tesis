package guiFX.BaseDeDatosGUI;

import java.util.List;

import baseDatos.hibernate.consultas.AbstractaConsulta;
import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.TipoIndicadorDAO;
import baseDatos.hibernate.tablas.TipoIndicador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class TipoIndicadorGUI extends TableView<TipoIndicador> implements AbstractBaseDeDatosGUI{
	
	private TableView<TipoIndicador> tablaTipoIndicador;
	private TableColumn<TipoIndicador, Integer> columnaId;
	private TableColumn<TipoIndicador, String> columnaTipo;
	private TableColumn<TipoIndicador, String> columnaObservaciones;
	
	private ObservableList<TipoIndicador> data; 

	
	public TipoIndicadorGUI() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public void mostrarTabla(){
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

	public void setData(ObservableList<TipoIndicador> data) {
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
		List<TipoIndicador> lista = (List<TipoIndicador>) factoryConsultasDAO.getLista("TipoIndicador");
		lista = ((TipoIndicadorDAO)consulta).getTodos();
		for (TipoIndicador vi : lista) { 
			this.getData().add(vi);

		}
		this.getTablaTipoIndicador().setItems(this.getData());	
		centroInferior.getChildren().add(0,this.getTablaTipoIndicador());			
	}
	
}