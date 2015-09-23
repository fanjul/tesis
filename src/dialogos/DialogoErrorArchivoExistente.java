package dialogos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DialogoErrorArchivoExistente extends Dialogo{


	ImageView imagen;
	private Label label;
	private Button botonAceptar;
	
	public DialogoErrorArchivoExistente(){
		imagen = new ImageView("/imagenesFX/Incorrecto.png");
		botonAceptar = new Button("Aceptar");
		label = new Label("El nombre del método ya existe");
	}
	
	@Override
	public void crearDialogo() {
		super.gethBoxArriba().getChildren().addAll(imagen, label);
		super.gethBoxAbajo().setCenterShape(true);
		super.gethBoxAbajo().getChildren().addAll(botonAceptar);	
	}

	public ImageView getImagen() {
		return imagen;
	}

	public Button getBotonAceptar() {
		return botonAceptar;
	}
	
}
