package guiFX;

import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BotonImagen extends ToggleButton {
	private DropShadow shadow;
	// String imagenCerrarVentana = new ImageView("/imagenesFX/Cerrar.png");
	// ToggleButton toggleButtonCerrarVentana = new ToggleButton("",
	// imagenCerrarVentana);
	// String toolTip = new Tooltip("Cerrar");
	// toggleButtonCerrarVentana.setTooltip(toolTip);
	// toggleButtonCerrarVentana.getStyleClass().add("botones");

	public BotonImagen(String imagen, String toolTip) {
		super("", new ImageView(imagen));
		this.setTooltip(new Tooltip(toolTip));
		shadow = new DropShadow();

		configurarEfectos();

	}

	private void configurarEfectos() {
		this.getStyleClass().add("botones");

		this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				setEfecto(shadow);

			}
		});

		this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				setEfecto(null);
			}
		});

	}

	private void setEfecto(DropShadow s) {
		this.setEffect(s);
	}

}
