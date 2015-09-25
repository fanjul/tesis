package dialogos;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DialogoRenombrarArchivo extends Dialogo{

	private Label label;
	private TextField textFieldNombreArchivo;
	private Button botonAceptar;
	private Button botonCancelar;
	
	public DialogoRenombrarArchivo(){
		label = new Label("Renombre el método: ");
		textFieldNombreArchivo = new TextField();
		botonAceptar = new Button("Aceptar");
		botonCancelar = new Button("Cancelar");
	}

	
	@Override
	public void crearDialogo() {
		textFieldNombreArchivo.setMaxSize(150, 10);
		super.gethBoxArriba().getChildren().addAll(label, textFieldNombreArchivo);
		super.gethBoxAbajo().getChildren().addAll(botonAceptar, botonCancelar);

		///////////////////// Para disablear/enablear el boton aceptar
		BooleanBinding bb = new BooleanBinding() {
			{
				super.bind(textFieldNombreArchivo.textProperty());
			}

			@Override
			protected boolean computeValue() {
				return (textFieldNombreArchivo.getText().isEmpty());
			}
		};
		botonAceptar.disableProperty().bind(bb);
		/////////////////////////
		
	}
	
	public TextField getTextFieldNombreArchivo() {
		return textFieldNombreArchivo;
	}


	public Button getBotonAceptar() {
		return botonAceptar;
	}


	public Button getBotonCancelar() {
		return botonCancelar;
	}

}
