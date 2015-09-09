package guiFX.BaseDeDatosGUI;

import java.math.BigDecimal;
import java.sql.Timestamp;

import baseDatos.hibernate.tablas.Indicador;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class IndicadorGUI extends TableView<Indicador> {
	
	private TableView<Indicador> tablaIndicador;
	private TableColumn<Indicador, BigDecimal> columnaId;
	private TableColumn<Indicador, String> columnaCodigo;
	private TableColumn<Indicador, String> columnaNombre;
	private TableColumn<Indicador, Integer> columnaIdUnidadDeMedida;
	private TableColumn<Indicador, Integer> columnaDireccion;
	private TableColumn<Indicador, String> columnaFormula;
	private TableColumn<Indicador, String> columnaFichaMetodologica;
	private TableColumn<Indicador, Integer> columnaIdGrafico;
	private TableColumn<Indicador, Integer> columnaIdResponsable;
	private TableColumn<Indicador, String> columnaFrecuencia;
	private TableColumn<Indicador, String> columnaPeriodo;
	private TableColumn<Indicador, Timestamp> columnaFechaUltimaActualizacion;
	private TableColumn<Indicador, Integer> columnaIdTipoIndicador;
	private TableColumn<Indicador, String> columnaObservaciones;
	
	private ObservableList<Indicador> data; 

	@SuppressWarnings("unchecked")
	public IndicadorGUI(){
		super();
		tablaIndicador = new TableView<Indicador>();
		
		columnaId = new TableColumn<Indicador, BigDecimal>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<Indicador,BigDecimal>("id"));
		
		columnaCodigo = new TableColumn<Indicador, String>("Codigo");
		columnaCodigo.setCellValueFactory(new PropertyValueFactory<Indicador,String>("codigo"));
		
		columnaNombre = new TableColumn<Indicador, String>("Nombre");
		columnaNombre.setCellValueFactory(new PropertyValueFactory<Indicador,String>("nombre"));
		
		columnaIdGrafico = new TableColumn<Indicador, Integer>("Id Grafico");
		columnaIdGrafico.setCellValueFactory(new PropertyValueFactory<Indicador,Integer>("idGrafico"));
		
		columnaIdUnidadDeMedida = new TableColumn<Indicador, Integer>("Id Unidad de Medida");
		columnaIdUnidadDeMedida.setCellValueFactory(new PropertyValueFactory<Indicador,Integer>("idUnidadDeMedida"));
		
		columnaDireccion = new TableColumn<Indicador, Integer>("Direccion");
		columnaDireccion.setCellValueFactory(new PropertyValueFactory<Indicador,Integer>("direccion"));
		
		columnaFormula = new TableColumn<Indicador, String>("formula");
		columnaFormula.setCellValueFactory(new PropertyValueFactory<Indicador,String>("formula"));
		
		columnaFichaMetodologica = new TableColumn<Indicador, String>("Ficha Metodologica");
		columnaFichaMetodologica.setCellValueFactory(new PropertyValueFactory<Indicador,String>("fichaMetodologica"));
		
		columnaIdResponsable = new TableColumn<Indicador, Integer>("Id Responsable");
		columnaIdResponsable.setCellValueFactory(new PropertyValueFactory<Indicador,Integer>("idResponsable"));
		
		columnaFrecuencia = new TableColumn<Indicador, String>("Frecuencia");
		columnaFrecuencia.setCellValueFactory(new PropertyValueFactory<Indicador,String>("frecuencia"));
		
		columnaPeriodo = new TableColumn<Indicador, String>("Periodo");
		columnaPeriodo.setCellValueFactory(new PropertyValueFactory<Indicador,String>("periodo"));
		
		columnaFechaUltimaActualizacion = new TableColumn<Indicador, Timestamp>("Fecha Ultima Actualizacion");
		columnaFechaUltimaActualizacion.setCellValueFactory(new PropertyValueFactory<Indicador,Timestamp>("fechaUltimaActualizacion"));
		
		columnaIdTipoIndicador = new TableColumn<Indicador, Integer>("Id Tipo Indicador");
		columnaIdTipoIndicador.setCellValueFactory(new PropertyValueFactory<Indicador,Integer>("idTipoIndicador"));
		
		columnaObservaciones = new TableColumn<Indicador, String>("Observaciones");
		columnaObservaciones.setCellValueFactory(new PropertyValueFactory<Indicador,String>("observaciones"));
		
		tablaIndicador.getColumns().addAll(columnaId,columnaCodigo,columnaNombre,columnaIdUnidadDeMedida,columnaDireccion,columnaFormula,
				columnaFichaMetodologica,columnaIdGrafico,columnaIdResponsable,columnaFrecuencia,columnaPeriodo,columnaFechaUltimaActualizacion,
				columnaIdTipoIndicador,columnaObservaciones);
	}

	public TableView<Indicador> getTablaIndicador() {
		return tablaIndicador;
	}

	public TableColumn<Indicador, BigDecimal> getColumnaId() {
		return columnaId;
	}

	public TableColumn<Indicador, String> getColumnaCodigo() {
		return columnaCodigo;
	}

	public TableColumn<Indicador, String> getColumnaNombre() {
		return columnaNombre;
	}

	public TableColumn<Indicador, Integer> getColumnaIdUnidadDeMedida() {
		return columnaIdUnidadDeMedida;
	}

	public TableColumn<Indicador, Integer> getColumnaDireccion() {
		return columnaDireccion;
	}

	public TableColumn<Indicador, String> getColumnaFormula() {
		return columnaFormula;
	}

	public TableColumn<Indicador, String> getColumnaFichaMetodologica() {
		return columnaFichaMetodologica;
	}

	public TableColumn<Indicador, Integer> getColumnaIdGrafico() {
		return columnaIdGrafico;
	}

	public TableColumn<Indicador, Integer> getColumnaIdResponsable() {
		return columnaIdResponsable;
	}

	public TableColumn<Indicador, String> getColumnaFrecuencia() {
		return columnaFrecuencia;
	}

	public TableColumn<Indicador, String> getColumnaPeriodo() {
		return columnaPeriodo;
	}

	public TableColumn<Indicador, Timestamp> getColumnaFechaUltimaActualizacion() {
		return columnaFechaUltimaActualizacion;
	}

	public TableColumn<Indicador, Integer> getColumnaIdTipoIndicador() {
		return columnaIdTipoIndicador;
	}

	public TableColumn<Indicador, String> getColumnaObservaciones() {
		return columnaObservaciones;
	}

	public ObservableList<Indicador> getData() {
		return data;
	}
	
	
}

