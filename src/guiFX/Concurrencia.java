package guiFX;

import java.util.List;

import baseDatos.hibernate.tablas.EstadosXTipoIndicador;
import guiFX.BaseDeDatosGUI.EstadosXTipoIndicadorGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class Concurrencia extends Task<ObservableList<EstadosXTipoIndicador>> {
	private List<EstadosXTipoIndicador> lista;
	private EstadosXTipoIndicadorGUI e ;
	
	public Concurrencia(List<EstadosXTipoIndicador> l, EstadosXTipoIndicadorGUI estadosXTipoIndicadorGUI){
		lista =l;
		this.e = estadosXTipoIndicadorGUI;
	}
	
	@Override
	protected ObservableList<EstadosXTipoIndicador> call() throws Exception {

		for (int i = 0; i < 500; i++) {
			updateProgress(i, 500);
			Thread.sleep(5);
		}
		lista = e.getConsulta().getTodos();
		
		for (EstadosXTipoIndicador vi : lista) {
			e.getData().add(vi);
		}
		
		ObservableList<EstadosXTipoIndicador> listaAux = FXCollections.observableArrayList();

		for (EstadosXTipoIndicador vi : lista) {
			listaAux.add(vi);
		}

		return listaAux;

	}

}