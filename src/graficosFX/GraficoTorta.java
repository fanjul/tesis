package graficosFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GraficoTorta extends Grafico{

	public GraficoTorta(){
			super();
	}
	
	public void graficar(){
		
		super.getVentana().setScene(new Scene(super.getRoot()));

	     ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(

	         new PieChart.Data("Sun", 20),

	         new PieChart.Data("IBM", 12),

	         new PieChart.Data("HP", 25),

	         new PieChart.Data("Dell", 22),

	         new PieChart.Data("Apple", 30)

	     );

	    PieChart chart = new PieChart(pieChartData);

	    chart.setClockwise(false);

	    super.getRoot().getChildren().add(chart);
	    
	    super.getVentana().show();

		
	}
	
	

}
