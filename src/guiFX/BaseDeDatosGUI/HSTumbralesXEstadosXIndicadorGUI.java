package guiFX.BaseDeDatosGUI;

import java.sql.Timestamp;
import java.util.List;

import baseDatos.hibernate.consultas.AbstractaConsulta;
import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.HSTumbralesXEstadosXIndicadorDAO;
import baseDatos.hibernate.tablas.HSTumbralesXEstadosXIndicador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class HSTumbralesXEstadosXIndicadorGUI extends TableView<HSTumbralesXEstadosXIndicador> implements AbstractBaseDeDatosGUI {

	private TableView<HSTumbralesXEstadosXIndicador> tablaHSTUmbrales;
	private TableColumn<HSTumbralesXEstadosXIndicador, Integer> columnaIdIndicador;
	private TableColumn<HSTumbralesXEstadosXIndicador, Integer> columnaIdEstadoTipoIndicador;
	private TableColumn<HSTumbralesXEstadosXIndicador, Timestamp> columnaInicioUmbral;
	private TableColumn<HSTumbralesXEstadosXIndicador, Timestamp> columnaFinUmbral;
	private TableColumn<HSTumbralesXEstadosXIndicador, String> columnaOperadorUmbralSuperior;
	private TableColumn<HSTumbralesXEstadosXIndicador, String> columnaOperadorUmbralInferior;
	private TableColumn<HSTumbralesXEstadosXIndicador, Double> columnaValorUmbralSuperior;
	private TableColumn<HSTumbralesXEstadosXIndicador, Double> columnaValorUmbralInferior;
	private TableColumn<HSTumbralesXEstadosXIndicador, String> columnaObservaciones;
	
	private ObservableList<HSTumbralesXEstadosXIndicador> data; 
	
	public HSTumbralesXEstadosXIndicadorGUI(){
		super();
	}
	
	@SuppressWarnings("unchecked")
	public void mostrarTabla(){
		tablaHSTUmbrales = new TableView<HSTumbralesXEstadosXIndicador>();
		
		columnaIdIndicador = new TableColumn<HSTumbralesXEstadosXIndicador, Integer>("Id Indicador");
		columnaIdIndicador.setCellValueFactory(new PropertyValueFactory<HSTumbralesXEstadosXIndicador,Integer>("idIndicador"));
	
		columnaIdEstadoTipoIndicador = new TableColumn<HSTumbralesXEstadosXIndicador, Integer>("Id Estado Tipo Indicador");
		columnaIdEstadoTipoIndicador.setCellValueFactory(new PropertyValueFactory<HSTumbralesXEstadosXIndicador,Integer>("idEstadoTipoIndicador"));
		
		columnaInicioUmbral = new TableColumn<HSTumbralesXEstadosXIndicador, Timestamp>("Inicio Umbral");
		columnaInicioUmbral.setCellValueFactory(new PropertyValueFactory<HSTumbralesXEstadosXIndicador,Timestamp>("inicioUmbral"));
		
		columnaFinUmbral = new TableColumn<HSTumbralesXEstadosXIndicador, Timestamp>("Fin Umbral");
		columnaFinUmbral.setCellValueFactory(new PropertyValueFactory<HSTumbralesXEstadosXIndicador,Timestamp>("finUmbral"));
		
		columnaOperadorUmbralSuperior = new TableColumn<HSTumbralesXEstadosXIndicador, String>("Operador Umbral Superior");
		columnaOperadorUmbralSuperior.setCellValueFactory(new PropertyValueFactory<HSTumbralesXEstadosXIndicador,String>("operadorUmbralSuperior"));
		
		columnaOperadorUmbralInferior = new TableColumn<HSTumbralesXEstadosXIndicador, String>("Operador Umbral Inferior");
		columnaOperadorUmbralInferior.setCellValueFactory(new PropertyValueFactory<HSTumbralesXEstadosXIndicador,String>("operadorUmbralInferior"));
	
		columnaValorUmbralSuperior = new TableColumn<HSTumbralesXEstadosXIndicador, Double>("Valor Umbral Superior");
		columnaValorUmbralSuperior.setCellValueFactory(new PropertyValueFactory<HSTumbralesXEstadosXIndicador,Double>("valorUmbralSuperior"));
		
		columnaValorUmbralInferior = new TableColumn<HSTumbralesXEstadosXIndicador, Double>("Valor Umbral Inferior");
		columnaValorUmbralInferior.setCellValueFactory(new PropertyValueFactory<HSTumbralesXEstadosXIndicador,Double>("valorUmbralInferior"));
		
		columnaObservaciones = new TableColumn<HSTumbralesXEstadosXIndicador, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<HSTumbralesXEstadosXIndicador,String>("observaciones"));
	
		tablaHSTUmbrales.getColumns().addAll(columnaIdIndicador,columnaIdEstadoTipoIndicador,columnaInicioUmbral,columnaFinUmbral,columnaOperadorUmbralSuperior,
				columnaOperadorUmbralInferior,columnaValorUmbralSuperior,columnaValorUmbralInferior,columnaObservaciones);
	
		tablaHSTUmbrales.getStyleClass().add("tablas");
	}

	public TableView<HSTumbralesXEstadosXIndicador> getTablaHSTUmbrales() {
		return tablaHSTUmbrales;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, Integer> getColumnaIdIndicador() {
		return columnaIdIndicador;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, Integer> getColumnaIdEstadoTipoIndicador() {
		return columnaIdEstadoTipoIndicador;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, Timestamp> getColumnaInicioUmbral() {
		return columnaInicioUmbral;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, Timestamp> getColumnaFinUmbral() {
		return columnaFinUmbral;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, String> getColumnaOperadorUmbralSuperior() {
		return columnaOperadorUmbralSuperior;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, String> getColumnaOperadorUmbralInferior() {
		return columnaOperadorUmbralInferior;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, Double> getColumnaValorUmbralSuperior() {
		return columnaValorUmbralSuperior;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, Double> getColumnaValorUmbralInferior() {
		return columnaValorUmbralInferior;
	}

	public TableColumn<HSTumbralesXEstadosXIndicador, String> getColumnaObservaciones() {
		return columnaObservaciones;
	}


	public ObservableList<HSTumbralesXEstadosXIndicador> getData() {
		return data;
	}

	public void setData(ObservableList<HSTumbralesXEstadosXIndicador> data) {
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
		List<HSTumbralesXEstadosXIndicador> lista = (List<HSTumbralesXEstadosXIndicador>) factoryConsultasDAO.getLista("HSTumbralesXEstadosXIndicador");
		lista = ((HSTumbralesXEstadosXIndicadorDAO)consulta).getTodos();
		for (HSTumbralesXEstadosXIndicador vi : lista) { 
			this.getData().add(vi);

		}
		this.getTablaHSTUmbrales().setItems(this.getData());
		tablaHSTUmbrales.setPrefSize(centroInferior.getMaxWidth(), centroInferior.getMaxHeight());
		centroInferior.getChildren().add(0,this.getTablaHSTUmbrales());		
	}
	
	
}

