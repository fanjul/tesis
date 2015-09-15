package guiFX.BaseDeDatosGUI;

import java.util.List;

import baseDatos.hibernate.consultas.AbstractaConsulta;
import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.GraficoDAO;
import baseDatos.hibernate.tablas.Grafico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class GraficoGUI extends TableView<Grafico> implements AbstractBaseDeDatosGUI {
	
	private TableView<Grafico> tablaGrafico;
	private TableColumn<Grafico, Integer> columnaId;
	private TableColumn<Grafico, String> columnaTipoGrafico;
	private TableColumn<Grafico, String> columnaObservaciones;
	
	private ObservableList<Grafico> data; 


	public GraficoGUI() {
		super();
	}
	
	@SuppressWarnings("unchecked")	
	public void mostrarTabla(){
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


	@Override
	public void crearTablaBaseDeDatos() {
		this.mostrarTabla();
	}

	public void setData(ObservableList<Grafico> data) {
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void mostrarTabla(AbstractaConsulta consulta, FactoryConsultas factoryConsultasDAO,
			AnchorPane centroInferior) {
		
		if(!centroInferior.getChildren().isEmpty()){
			centroInferior.getChildren().remove(0);
		}
		this.setData(FXCollections.observableArrayList());
		List<Grafico> lista = (List<Grafico>) factoryConsultasDAO.getLista("Grafico");
		lista = ((GraficoDAO)consulta).getTodos();
		for (Grafico vi : lista) { 
			this.getData().add(vi);

		}
		this.getTablaGrafico().setItems(this.getData());
		tablaGrafico.setPrefSize(centroInferior.getMaxWidth(), centroInferior.getMaxHeight());
		centroInferior.getChildren().add(0,this.getTablaGrafico());		
	}

	
	
}
