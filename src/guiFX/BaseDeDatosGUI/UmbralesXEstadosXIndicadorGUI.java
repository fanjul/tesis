package guiFX.BaseDeDatosGUI;

import java.sql.Timestamp;

import baseDatos.hibernate.tablas.UmbralesXEstadosXIndicador;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UmbralesXEstadosXIndicadorGUI extends TableView<UmbralesXEstadosXIndicador> {
	
	private TableView<UmbralesXEstadosXIndicador> tablaUmbrales;
	private TableColumn<UmbralesXEstadosXIndicador, Integer> columnaIdIndicador;
	private TableColumn<UmbralesXEstadosXIndicador, Integer> columnaIdEstadoTipoIndicador;
	private TableColumn<UmbralesXEstadosXIndicador, Timestamp> columnaInicioUmbral;
	private TableColumn<UmbralesXEstadosXIndicador, Timestamp> columnaFinUmbral;
	private TableColumn<UmbralesXEstadosXIndicador, String> columnaOperadorUmbralSuperior;
	private TableColumn<UmbralesXEstadosXIndicador, String> columnaOperadorUmbralInferior;
	private TableColumn<UmbralesXEstadosXIndicador, Double> columnaValorUmbralSuperior;
	private TableColumn<UmbralesXEstadosXIndicador, Double> columnaValorUmbralInferior;
	private TableColumn<UmbralesXEstadosXIndicador, String> columnaObservaciones;
	
	private ObservableList<UmbralesXEstadosXIndicador> data; 

	@SuppressWarnings("unchecked")
	public UmbralesXEstadosXIndicadorGUI() {
		super();
		tablaUmbrales = new TableView<UmbralesXEstadosXIndicador>();
		
		columnaIdIndicador = new TableColumn<UmbralesXEstadosXIndicador, Integer>("Id Indicador");
		columnaIdIndicador.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,Integer>("idIndicador"));
		
		columnaIdEstadoTipoIndicador = new TableColumn<UmbralesXEstadosXIndicador, Integer>("Id Estado Tipo Indicador");
		columnaIdEstadoTipoIndicador.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,Integer>("idEstadoTipoIndicador"));
		
		columnaInicioUmbral = new TableColumn<UmbralesXEstadosXIndicador, Timestamp>("Inicio Umbral");
		columnaInicioUmbral.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,Timestamp>("inicioUmbral"));
		
		columnaFinUmbral = new TableColumn<UmbralesXEstadosXIndicador, Timestamp>("Fin Umbral");
		columnaFinUmbral.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,Timestamp>("finUmbral"));
		
		columnaOperadorUmbralSuperior = new TableColumn<UmbralesXEstadosXIndicador, String>("Operador Umbral Superior");
		columnaOperadorUmbralSuperior.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,String>("operadorUmbralSuperior"));
		
		columnaOperadorUmbralInferior = new TableColumn<UmbralesXEstadosXIndicador, String>("Operador Umbral Inferior");
		columnaOperadorUmbralInferior.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,String>("operadorUmbralInferior"));
		
		columnaValorUmbralSuperior = new TableColumn<UmbralesXEstadosXIndicador, Double>("Valor Umbral Superior");
		columnaValorUmbralSuperior.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,Double>("valorUmbralSuperior"));
		
		columnaValorUmbralInferior = new TableColumn<UmbralesXEstadosXIndicador, Double>("Valor Umbral Inferior");
		columnaValorUmbralInferior.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,Double>("valorUmbralInferior"));
		
		columnaObservaciones = new TableColumn<UmbralesXEstadosXIndicador, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<UmbralesXEstadosXIndicador,String>("observaciones"));
		
		tablaUmbrales.getColumns().addAll(columnaIdIndicador,columnaIdEstadoTipoIndicador,columnaInicioUmbral,columnaFinUmbral,columnaOperadorUmbralSuperior,
				columnaOperadorUmbralInferior,columnaValorUmbralSuperior,columnaValorUmbralInferior,columnaObservaciones);
	}

	public TableView<UmbralesXEstadosXIndicador> getTablaUmbrales() {
		return tablaUmbrales;
	}

	public TableColumn<UmbralesXEstadosXIndicador, Integer> getColumnaIdIndicador() {
		return columnaIdIndicador;
	}

	public TableColumn<UmbralesXEstadosXIndicador, Integer> getColumnaIdEstadoTipoIndicador() {
		return columnaIdEstadoTipoIndicador;
	}

	public TableColumn<UmbralesXEstadosXIndicador, Timestamp> getColumnaInicioUmbral() {
		return columnaInicioUmbral;
	}

	public TableColumn<UmbralesXEstadosXIndicador, Timestamp> getColumnaFinUmbral() {
		return columnaFinUmbral;
	}

	public TableColumn<UmbralesXEstadosXIndicador, String> getColumnaOperadorUmbralSuperior() {
		return columnaOperadorUmbralSuperior;
	}

	public TableColumn<UmbralesXEstadosXIndicador, String> getColumnaOperadorUmbralInferior() {
		return columnaOperadorUmbralInferior;
	}

	public TableColumn<UmbralesXEstadosXIndicador, Double> getColumnaValorUmbralSuperior() {
		return columnaValorUmbralSuperior;
	}

	public TableColumn<UmbralesXEstadosXIndicador, Double> getColumnaValorUmbralInferior() {
		return columnaValorUmbralInferior;
	}

	public TableColumn<UmbralesXEstadosXIndicador, String> getColumnaObservaciones() {
		return columnaObservaciones;
	}

	public ObservableList<UmbralesXEstadosXIndicador> getData() {
		return data;
	}
	
	
}

