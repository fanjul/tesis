package cadenaResponsabilidades;

import java.io.File;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TipoString extends TipoObjeto{
	
	
	


	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView listaMetodos, TextField textFieldNombreFuncion) {
		if(obj instanceof String){
			System.out.println("impprimiendo resultado:   " + obj.toString());
		}
		
		if (super.siguiente() != null){
			super.siguiente().ejecutarMetodo(obj, archivo, listaMetodos,
					textFieldNombreFuncion);
		}
		
	}

}
