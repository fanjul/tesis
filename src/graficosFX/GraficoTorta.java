package graficosFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GraficoTorta implements Grafico{
	private Stage ventanaGraficoTorta;
	private Group root;

	
	public GraficoTorta(){
		
		ventanaGraficoTorta = new Stage();
		root = new Group();
	
	}
	
	public void graficar(){
		
		ventanaGraficoTorta.setScene(new Scene(root));

	     ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(

	         new PieChart.Data("Sun", 20),

	         new PieChart.Data("IBM", 12),

	         new PieChart.Data("HP", 25),

	         new PieChart.Data("Dell", 22),

	         new PieChart.Data("Apple", 30)

	     );

	    PieChart chart = new PieChart(pieChartData);

	    chart.setClockwise(false);

	    root.getChildren().add(chart);
	    
	    ventanaGraficoTorta.show();

		
	}
	
	

}
