package dialogos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DialogoErrorDevolucion extends Dialogo {

	private ImageView imagen;
	private Label label;
	private Button botonAceptar;
	
	public DialogoErrorDevolucion() {
		super();
		imagen = new ImageView("/imagenesFX/preocupado.png");
		botonAceptar = new Button("Aceptar");
		label = new Label("Variable o funcion donde se devuelve no es existe o error de compilacion");
		this.dialogo.setWidth(500);
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
