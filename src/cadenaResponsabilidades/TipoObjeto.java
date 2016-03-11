package cadenaResponsabilidades;

import java.io.File;
import java.util.List;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public abstract class TipoObjeto {

	private TipoObjeto sig;
	
	public TipoObjeto siguiente() {
		return sig;
	}

	public void setSiguiente(TipoObjeto obj) {
		this.sig = obj;
	}

	@SuppressWarnings("rawtypes")
	abstract public void ejecutarMetodo(Object obj, File archivo, ListView<String> listaMetodos, TextField textFieldNombreFuncion, 
			TableView tablaResultado, boolean recursivo, List<String> resultado);

}
