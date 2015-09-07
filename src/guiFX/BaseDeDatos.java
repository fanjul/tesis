package guiFX;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;

import baseDatos.hibernate.Prueba;
import baseDatos.hibernate.consultas.ValorIndicadorDAO;
import baseDatos.hibernate.tablas.ValorIndicador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class BaseDeDatos extends TableView<ValorIndicador> {
	
	private TableView<ValorIndicador> tablaValorIndicador;
	private TableColumn<ValorIndicador,Integer> columnaIdIndicador;
	private TableColumn<ValorIndicador,Timestamp> columnaFecha;
	private TableColumn<ValorIndicador,Double> columnaValor;
	private TableColumn<ValorIndicador,String> columnaEstado;
	private TableColumn<ValorIndicador,Double> columnaVariacion;
	private TableColumn<ValorIndicador,Integer> columnaSignoVariacion;
	private TableColumn<ValorIndicador,String> columnaObservaciones;

	private ObservableList<ValorIndicador> data; 
	
	public BaseDeDatos(){
		super();
		
		tablaValorIndicador = new TableView<ValorIndicador>();
		columnaIdIndicador = new TableColumn<ValorIndicador,Integer>("Id Indicador");
		columnaIdIndicador.setCellValueFactory(new PropertyValueFactory<ValorIndicador, Integer>("idIndicador"));
		columnaFecha = new TableColumn<ValorIndicador, Timestamp>("Fecha");
		
		columnaValor = new TableColumn<ValorIndicador, Double>("Valor");
		columnaEstado = new TableColumn<ValorIndicador, String>("Estado");
		columnaVariacion = new TableColumn<ValorIndicador, Double>("Variacion");
		columnaSignoVariacion = new TableColumn<ValorIndicador, Integer>("Signo Variacion");
		columnaObservaciones = new TableColumn<ValorIndicador, String>("Observaciones");
		tablaValorIndicador.getColumns().addAll(columnaIdIndicador,columnaFecha,columnaValor,columnaEstado,columnaVariacion,columnaSignoVariacion,columnaObservaciones);
		tablaValorIndicador.setVisible(true);
		
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
	
	public void setData(ObservableList<ValorIndicador> data){
		this.data = data;
		
	}
}