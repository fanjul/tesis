package dialogos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DialogoErrorFK extends Dialogo {
	
	private Button botonAceptar;
	private ImageView imagen;
	private Label mensajeError;
	
	
	public DialogoErrorFK(String mensaje) {
		imagen = new ImageView("/imagenesFX/key.png");
		botonAceptar = new Button("Aceptar");
		mensajeError = new Label("Error, el id " + mensaje + " no existe");
	}
	@Override
	public void crearDialogo() {
		super.gethBoxArriba().getChildren().addAll(imagen,mensajeError);
		super.gethBoxAbajo().getChildren().add(botonAceptar);
		
	}

	
	public Button getBotonAceptar() {
		return botonAceptar;
	}

}
