package cadenaResponsabilidades;

import java.io.File;
import java.util.List;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RFactor;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TipoFactor extends TipoObjeto {

	
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView<String> listaMetodos,
			TextField textFieldNombreFuncion, TableView tablaResultado, boolean recursivo,
			List<String> resultado) {
		
		setSiguiente(new TipoMatrizDouble());
		if (((REXP) obj).asFactor() != null) {
			obj = ((REXP) obj).asFactor();
			RFactor factor = (RFactor) obj;
			
			for(int i = 0; i < factor.size(); i++){
				
				factor.at(i);	
			}
		}
		
		else if (super.siguiente() != null) {
			super.siguiente().ejecutarMetodo(obj, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado,recursivo,resultado);
		}	
	}

}
