package cadenaResponsabilidades;

import java.io.File;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RFactor;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TipoFactor extends TipoObjeto {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView<String> listaMetodos,
			TextField textFieldNombreFuncion, TableView tablaResultado) {

		setSiguiente(new TipoMatrizDouble());
		if (((REXP) obj).asFactor() != null) {
			obj = ((REXP) obj).asFactor();
			RFactor factor = (RFactor) obj;
			String[] string = new String[100];
			TipoObjeto objeto = null;
			int i = 0;
			for (; i < factor.size(); i++) {

				string[i] = factor.at(i);
			}

			String[] s = new String[i];
			s = pasarContenido(s, string);
			objeto = new TipoArregloString();
			objeto.ejecutarMetodo(s, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado);
		}

		else if (super.siguiente() != null) {
			super.siguiente().ejecutarMetodo(obj, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado);
		}
	}

	public String[] getLista(REXP obj) {
		if (obj.asFactor() != null) {
			RFactor factor = obj.asFactor();
			int i = 0;
			String[] string = new String[100];
			for (; i < factor.size(); i++) {
				string[i] = factor.at(i);
			}
			String[] s = new String[i];
			pasarContenido(s, string);
			return s;
		}
		return null;
	}

}
