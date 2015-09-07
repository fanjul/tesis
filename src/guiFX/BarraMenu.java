package guiFX;

import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class BarraMenu extends VBox {

	private BarraMenuDeslizable barraDeslizable;

	public BarraMenu() {
		super();

		Button botonMenu = new Button("base de datos");
		this.getChildren().add(botonMenu);

		barraDeslizable = new BarraMenuDeslizable(this);
		VBox.setVgrow(this, Priority.ALWAYS);
	}

	public BarraMenuDeslizable getBarraDeslizable() {
		return barraDeslizable;
	}
}
