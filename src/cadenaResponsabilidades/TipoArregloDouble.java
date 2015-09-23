package cadenaResponsabilidades;

import java.io.File;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TipoArregloDouble extends TipoObjeto {

	

	/*@Override
	public TipoObjeto siguiente() {
		return sig;
	}*/

	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView listaMetodos, TextField textFieldNombreFuncion, TextArea areaResultado) {
		//areaResultado.setText("");
		
		if (obj instanceof double[]) {
			
			double[] arr = null;
			if (!textFieldNombreFuncion.getText().isEmpty()) {

				
			//	Rengine re = new Rengine(new String[] { "--vanilla" }, false, null);;
			//	arr = re.eval(textFieldNombreFuncion.getText()).asDoubleArray();
				arr = (double[]) obj;
			}
			
			for (int i = 0; i < arr.length; i++){
				System.out.println(Double.toString(arr[i]));
				areaResultado.appendText(Double.toString(arr[i]) + "\n");
			}
		} 
		if (super.siguiente() != null){
			super.siguiente().ejecutarMetodo(obj, archivo, listaMetodos,
					textFieldNombreFuncion,areaResultado);
		}

	}
	
}
