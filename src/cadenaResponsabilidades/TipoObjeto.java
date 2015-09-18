package cadenaResponsabilidades;

import java.io.File;

import org.rosuda.JRI.REXP;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public abstract class TipoObjeto {

	private TipoObjeto sig;
	
	public TipoObjeto siguiente() {
		return null;
	}

	public void setSiguiente(TipoObjeto obj) {
		sig = obj;
	}

	abstract public void ejecutarMetodo(Object obj, File archivo, ComboBox comboBoxSeleccionarMetodo, TextField textFieldNombreFuncion);

}
