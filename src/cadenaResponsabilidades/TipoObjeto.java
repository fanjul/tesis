package cadenaResponsabilidades;

import java.io.File;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public abstract class TipoObjeto {

	@SuppressWarnings("unused")
	private TipoObjeto sig;
	
	public TipoObjeto siguiente() {
		return null;
	}

	public void setSiguiente(TipoObjeto obj) {
		this.sig = obj;
	}

	abstract public void ejecutarMetodo(Object obj, File archivo, ListView<String> listaMetodos, TextField textFieldNombreFuncion, TableView<String> tablaResultado/*TextArea areaResultado*/);

}
