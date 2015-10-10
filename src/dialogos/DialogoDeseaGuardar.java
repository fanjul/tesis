package dialogos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DialogoDeseaGuardar extends Dialogo{
	
	ImageView imagen;
	private Label label;
	private Button botonSi;
	private Button botonNo;
	
	public DialogoDeseaGuardar(){
		imagen = new ImageView("/imagenesFX/SignoPregunta.png");
		botonSi = new Button("Sí");
		botonNo = new Button("No");
		label = new Label("¿Quiere guardar los cambios?");
	}
	
	@Override
	public void crearDialogo() {
		super.gethBoxArriba().getChildren().addAll(imagen, label);
		super.gethBoxAbajo().setCenterShape(true);
		super.gethBoxAbajo().getChildren().addAll(botonSi, botonNo);	
	}
	

	public Button getBotonNo() {
		return botonNo;
	}

	public Button getBotonSi() {
		return botonSi;
	}
}
