package guiFX;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.TableView;

/**
 * 
 * An advanced pie chart with a variety of actions and settable properties.
 *
 * 
 * 
 * @see javafx.scene.chart.PieChart
 * 
 * @see javafx.scene.chart.Chart
 * 
 */

public class GraficoDeTorta {

	public GraficoDeTorta() {

	}

	public PieChart graficar(TableView tabla) {

		List<PieChart.Data> datas = new ArrayList<PieChart.Data>();

		ObservableList<Persona> lista = tabla.getItems();

		for (Persona p : lista) {
			datas.add(new PieChart.Data(p.getNombre(), 60));
		}

		final PieChart pc = new PieChart(FXCollections.observableArrayList(datas));

		
		// setup chart

		pc.setId("BasicPie");

		pc.setTitle("Pie Chart Example");

		return pc;

	}

}
