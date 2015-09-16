package cadenaResponsabilidades;

import java.io.File;

import org.rosuda.JRI.Rengine;
import org.rosuda.JRI.REXP;

import com.mysql.fabric.xmlrpc.base.Array;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TipoArregloDouble extends TipoObjeto {

	

	/*@Override
	public TipoObjeto siguiente() {
		return sig;
	}*/

	@Override
	public void ejecutarMetodo(Object obj, File archivo, ComboBox comboBoxSeleccionarMetodo, TextField textFieldNombreFuncion) {
		if (obj instanceof double[]) {
			
			double[] arr = null;
			if (!textFieldNombreFuncion.getText().isEmpty()) {

				
			//	Rengine re = new Rengine(new String[] { "--vanilla" }, false, null);;
			//	arr = re.eval(textFieldNombreFuncion.getText()).asDoubleArray();
				arr = (double[]) obj;
			}
			
			for (int i = 0; i < arr.length; i++)
				System.out.println(arr[i]);

		} 
		if (super.siguiente() != null){
			super.siguiente().ejecutarMetodo(obj, archivo, comboBoxSeleccionarMetodo,
					textFieldNombreFuncion);
		}

	}
	
}
