package graficosFX;

import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;

import guiFX.EffectUtilities;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GraficoBarras extends Grafico {
	
	public GraficoBarras() {
		super();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void graficar(TableView tablaResultado) {
		super.getVentana().setScene(super.getEscena());
		CategoryAxis xAxis = new CategoryAxis();
	
		ObservableList<BarChart.Series> datos = FXCollections.observableArrayList();
		
		String[] arrNombreColumnas = null;
		double max = 0;
		
		for (int i = 0; i < tablaResultado.getItems().size(); i++) {
			//TODO poner excepcion por si lo que esta en la tabla resultados es un string
			if (tablaResultado.getItems().get(i) instanceof double[]) {
				
				
				double[] fila = (double[]) tablaResultado.getItems().get(i);
				arrNombreColumnas = new String[fila.length];  
				
				for (int valor = 0; valor < fila.length; valor++) {
					//new PieChart.Data(((TableColumn) tablaResultado.getColumns().get(valor)).getText(),fila[valor])
					if(fila[valor] > max){
						max = fila[valor];
					}
					arrNombreColumnas[valor]=((TableColumn) tablaResultado.getColumns().get(valor)).getText().toString();
					
					datos.add(new BarChart.Series(((TableColumn) tablaResultado.getColumns().get(valor)).getText(), FXCollections.observableArrayList(

							new BarChart.Data(((TableColumn) tablaResultado.getColumns().get(valor)).getText(), fila[valor])

							)));
				}
			}
		}

		NumberAxis yAxis = new NumberAxis("Units Sold", 0.0d, max, max/10);
		
		xAxis.setCategories(FXCollections.<String> observableArrayList(arrNombreColumnas));
///////////////////////////////////////////////
		
		/*
		
		
		
		ObservableList<BarChart.Series> barChartData = FXCollections.observableArrayList(

		new BarChart.Series("Apples", FXCollections.observableArrayList(

		new BarChart.Data(years[0], 567d),

		new BarChart.Data(years[1], 1292d),

		new BarChart.Data(years[2], 1292d)

		)),

		new BarChart.Series("Lemons", FXCollections.observableArrayList(

		new BarChart.Data(years[0], 956),

		new BarChart.Data(years[1], 1665),

		new BarChart.Data(years[2], 2559)

		)),

		new BarChart.Series("Oranges", FXCollections.observableArrayList(

		new BarChart.Data(years[0], 1154),

		new BarChart.Data(years[1], 1927),

		new BarChart.Data(years[2], 2774)

		))

		);
*/
		BarChart chart = new BarChart(xAxis, yAxis, datos, 25.0d);

		super.getRoot().getChildren().add(chart);

		super.getVentana().show();

	}

	
	
	
	
	
	
}
