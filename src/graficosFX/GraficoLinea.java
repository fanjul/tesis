package graficosFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class GraficoLinea extends Grafico {

	public GraficoLinea() {
		super();
	}

	public void graficar() {

		super.getVentana().setScene(new Scene(super.getRoot()));

		  final NumberAxis xAxis = new NumberAxis();

	        final NumberAxis yAxis = new NumberAxis();

	        final LineChart<Number,Number> lc = new LineChart<Number,Number>(xAxis,yAxis);

	        // setup chart

	        lc.setTitle("Basic LineChart");

	        xAxis.setLabel("X Axis");

	        yAxis.setLabel("Y Axis");

	        // add starting data

	        XYChart.Series<Number,Number> series = new XYChart.Series<Number,Number>();

	        series.setName("Data Series 1");

	        series.getData().add(new XYChart.Data<Number,Number>(20d, 50d));

	        series.getData().add(new XYChart.Data<Number,Number>(40d, 80d));

	        series.getData().add(new XYChart.Data<Number,Number>(50d, 90d));

	        series.getData().add(new XYChart.Data<Number,Number>(70d, 30d));

	        series.getData().add(new XYChart.Data<Number,Number>(170d, 122d));

	        lc.getData().add(series);

		super.getRoot().getChildren().add(lc);

		super.getVentana().show();

	}

}
