package dialogos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DialogoEjecutar extends Dialogo{

	private Label labelNombreDondeDevuelve;
	private TextField textFieldNombreDondeDevuelve;
	private Button botonEjecutar;
	private Button botonCancelar;
	
	public DialogoEjecutar(){
		super();
		labelNombreDondeDevuelve = new Label("¿Dónde devuelve? ");
		textFieldNombreDondeDevuelve = new TextField();
		botonEjecutar = new Button("Ejecutar");
		botonCancelar = new Button("Cancelar");
	}
	
	@Override
	public void crearDialogo() {
		textFieldNombreDondeDevuelve.setMaxSize(150, 10);
		super.gethBoxArriba().getChildren().addAll(labelNombreDondeDevuelve, textFieldNombreDondeDevuelve);
		super.gethBoxAbajo().getChildren().addAll(botonEjecutar,botonCancelar);
	}

	public Label getLabelNombreDondeDevuelve() {
		return labelNombreDondeDevuelve;
	}

	public TextField getTextFieldNombreDondeDevuelve() {
		return textFieldNombreDondeDevuelve;
	}

	public Button getBotonEjecutar() {
		return botonEjecutar;
	}

	public Button getBotonCancelar() {
		return botonCancelar;
	}

}
