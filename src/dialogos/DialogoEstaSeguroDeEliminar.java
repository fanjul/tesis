package dialogos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DialogoEstaSeguroDeEliminar extends Dialogo{
	
	ImageView imagen;
	private Label label;
	private Button botonAceptar;
	private Button botonCancelar;
	
	public DialogoEstaSeguroDeEliminar(){
		imagen = new ImageView("/imagenesFX/SignoPregunta.png");
		botonAceptar = new Button("Aceptar");
		botonCancelar = new Button("Cancelar");
		label = new Label("¿Está seguro que quiere eliminar el archivo?");
	}
	
	@Override
	public void crearDialogo() {
		super.gethBoxArriba().getChildren().addAll(imagen, label);
		super.gethBoxAbajo().setCenterShape(true);
		super.gethBoxAbajo().getChildren().addAll(botonAceptar, botonCancelar);	
	}
	

	public Button getBotonCancelar() {
		return botonCancelar;
	}

	public Button getBotonAceptar() {
		return botonAceptar;
	}
}
