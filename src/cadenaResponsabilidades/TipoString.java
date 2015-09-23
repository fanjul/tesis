package cadenaResponsabilidades;

import java.io.File;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TipoString extends TipoObjeto{
	
	
	


	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView listaMetodos, TextField textFieldNombreFuncion, TextArea areaResultado) {
		//areaResultado.setText("");
		if(obj instanceof String){
			System.out.println("impprimiendo resultado:   " + obj.toString());
			areaResultado.setText(obj.toString());
		}
		
		if (super.siguiente() != null){
			super.siguiente().ejecutarMetodo(obj, archivo, listaMetodos,
					textFieldNombreFuncion,areaResultado);
		}
		
	}

}
