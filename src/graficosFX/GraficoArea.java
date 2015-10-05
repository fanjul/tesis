package graficosFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;

public class GraficoArea extends Grafico {

	public GraficoArea() {
		super();
	}

	@SuppressWarnings("unchecked")
	public void graficar(TableView tablaResultado) {

		super.getVentana().setScene(new Scene(super.getRoot()));

		 NumberAxis xAxis = new NumberAxis("X Values", 1.0d, 9.0d, 2.0d);

	        xAxis.setTickLength(12.0f);

	        NumberAxis yAxis = new NumberAxis("Y Values", 0.0d, 10.0d, 2.0d);

	        ObservableList<AreaChart.Series> areaChartData = FXCollections.observableArrayList(

	                new AreaChart.Series("Series 1",FXCollections.observableArrayList(

	                    new AreaChart.Data(0,4),

	                    new AreaChart.Data(2,5),

	                    new AreaChart.Data(4,4),

	                    new AreaChart.Data(6,2),

	                    new AreaChart.Data(8,6),

	                    new AreaChart.Data(10,8)

	                )),

	                new AreaChart.Series("Series 2", FXCollections.observableArrayList(

	                    new AreaChart.Data(0,8),

	                    new AreaChart.Data(2,2),

	                    new AreaChart.Data(4,9),

	                    new AreaChart.Data(6,7),

	                    new AreaChart.Data(8,5),

	                    new AreaChart.Data(10,7)

	                )),

	                new AreaChart.Series("Series 3", FXCollections.observableArrayList(

	                    new AreaChart.Data(0,2),

	                    new AreaChart.Data(2,5),

	                    new AreaChart.Data(4,8),

	                    new AreaChart.Data(6,6),

	                    new AreaChart.Data(8,9),

	                    new AreaChart.Data(10,7)

	                ))

	        );

	        AreaChart chart = new AreaChart(xAxis, yAxis, areaChartData);

		super.getRoot().getChildren().add(chart);

		super.getVentana().show();

	}

}
