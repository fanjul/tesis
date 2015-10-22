package graficosFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GraficoTorta extends Grafico {

	public GraficoTorta() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public void graficar(TableView tablaResultado) {

		ObservableList<PieChart.Data> datos = FXCollections.observableArrayList();
		for (int i = 0; i < tablaResultado.getItems().size(); i++) {
			//TODO poner excepcion por si lo que esta en la tabla resultados es un string
			if (tablaResultado.getItems().get(i) instanceof double[]) {
				double[] fila = (double[]) tablaResultado.getItems().get(i);
				for (int valor = 0; valor < fila.length; valor++) {
					datos.add(new PieChart.Data(((TableColumn) tablaResultado.getColumns().get(valor)).getText(),
							fila[valor]));
				}
			}
		}

		this.chart = new PieChart(datos);
		((PieChart) chart).setClockwise(false);
		super.getRoot().getChildren().add(chart);
		super.getVentana().show();

	}

}
