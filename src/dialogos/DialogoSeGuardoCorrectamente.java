package dialogos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DialogoSeGuardoCorrectamente extends Dialogo{

	ImageView imagen;
	private Label label;
	private Button botonAceptar;
	
	public DialogoSeGuardoCorrectamente(){
		super();
		imagen = new ImageView("/imagenesFX/GuardoCorrectamente.png");
		botonAceptar = new Button("Aceptar");
		label = new Label("Se guardó correctamente");
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
