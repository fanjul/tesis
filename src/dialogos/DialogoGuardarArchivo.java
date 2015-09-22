package dialogos;

import guiFX.PanelDerecho;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DialogoGuardarArchivo extends Dialogo {
	
	private Label labelNombreMetodo;
	private TextField textFieldNombreArchivo;
	private Button botonGuardar;
	private Button botonCancelar;
		
	public DialogoGuardarArchivo() {
		super();
		textFieldNombreArchivo = new TextField();
		botonGuardar = new Button("Guardar");
		botonCancelar = new Button("Cancelar");
		labelNombreMetodo = new Label("Nombre nuevo metodo: ");
	}
	
	public void crearDialogo() {
		textFieldNombreArchivo.setMaxSize(150, 10);
		super.gethBoxArriba().getChildren().addAll(labelNombreMetodo, textFieldNombreArchivo);
		super.gethBoxAbajo().getChildren().addAll(botonGuardar, botonCancelar);

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
		botonGuardar.disableProperty().bind(bb);
		/////////////////////////

	}
	
	public TextField getTextFieldNombreArchivo() {
		return textFieldNombreArchivo;
	}

	public Button getBotonGuardar() {
		return botonGuardar;
	}

	public Button getBotonCancelar() {
		return botonCancelar;
	}

}
