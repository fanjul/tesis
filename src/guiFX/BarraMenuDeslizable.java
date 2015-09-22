package guiFX;

import java.io.File;

import org.rosuda.JRI.Rengine;

import cadenaResponsabilidades.TipoArregloDouble;
import cadenaResponsabilidades.TipoObjeto;
import cadenaResponsabilidades.TipoString;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BarraMenuDeslizable extends AnchorPane {
	private static final int EXPANDIR_ANCHO = 150;
	private Button botonMenu;
	private ToggleButton toggleButtonMenu;
	private DropShadow shadow = new DropShadow();
	private ImageView imagenAbrirBarraDeslizable = new ImageView("/imagenesFX/AbrirBarraDeslizable.png");
	private ImageView imagenCerrarBarraDeslizable = new ImageView("/imagenesFX/CerrarBarraDeslizable.png");

	public BarraMenuDeslizable(Node nodo) {
		this.setWidth(EXPANDIR_ANCHO);
		this.setMinWidth(0);

		this.getChildren().add(nodo);

		botonMenu = new Button("Mostrar Menu");
		setVisible(false);

		toggleButtonMenu = new ToggleButton("", imagenAbrirBarraDeslizable);
		toggleButtonMenu.setTooltip(new Tooltip("Abrir Menu"));
		toggleButtonMenu.getStyleClass().add("botones");

		toggleButtonMenu.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				toggleButtonMenu.setEffect(shadow);

			}
		});

		toggleButtonMenu.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				toggleButtonMenu.setEffect(null);
			}
		});

		toggleButtonMenu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				// create an animation to hide sidebar.
				final Animation hideSidebar = new Transition() {
					{
						setCycleDuration(Duration.millis(250));
					}

					protected void interpolate(double frac) {
						final double curWidth = EXPANDIR_ANCHO * (1.0 - frac);
						setPrefWidth(curWidth);
						setTranslateX(-EXPANDIR_ANCHO + curWidth);
					}
				};
				hideSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {
						setVisible(false);
						// botonMenu.setText("Mostrar Menu");

						// ImageView imagenAbrirCerrarBarraDeslizable = new
						// ImageView("/imagenesFX/AbrirBarraDeslizable.png");
						toggleButtonMenu.setGraphic(imagenAbrirBarraDeslizable);
						toggleButtonMenu.setTooltip(new Tooltip("Abrir Menu"));

					}

				});

				// create an animation to show a sidebar.
				final Animation showSidebar = new Transition() {
					{
						setCycleDuration(Duration.millis(250));
					}

					protected void interpolate(double frac) {
						final double curWidth = EXPANDIR_ANCHO * frac;
						setPrefWidth(curWidth);
						setTranslateX(-EXPANDIR_ANCHO + curWidth);
					}
				};
				showSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {
						// botonMenu.setText("Cerrar Menu");

						// ImageView imagenAbrirCerrarBarraDeslizable = new
						// ImageView("/imagenesFX/CerrarBarraDeslizable.png");
						toggleButtonMenu.setGraphic(imagenCerrarBarraDeslizable);
						toggleButtonMenu.setTooltip(new Tooltip("Cerrar Menu"));
					}
				});

				if (showSidebar.statusProperty().get() == Animation.Status.STOPPED
						&& hideSidebar.statusProperty().get() == Animation.Status.STOPPED) {
					if (isVisible()) {
						hideSidebar.play();
					} else {
						setVisible(true);
						showSidebar.play();
					}
				}
			}
		});
	}

	public ToggleButton getBotonMenu() {
		return toggleButtonMenu;
	}

}
