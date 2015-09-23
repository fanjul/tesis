package graficosFX;

import javafx.scene.Group;
import javafx.stage.Stage;

public abstract class Grafico {
	
	private Stage ventana;
	private Group root;

	public Grafico() {
		ventana = new Stage();
		root = new Group();
	}

	public abstract void graficar();

	public Stage getVentana() {
		return ventana;
	}

	public Group getRoot() {
		return root;
	}
	
}
