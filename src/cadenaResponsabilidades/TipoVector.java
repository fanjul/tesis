package cadenaResponsabilidades;

import java.io.File;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RVector;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TipoVector extends TipoObjeto {

	@SuppressWarnings("rawtypes")
	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView<String> listaMetodos,
			TextField textFieldNombreFuncion, TableView tablaResultado) {

		setSiguiente(new TipoFactor());
		if (((REXP) obj).asVector() != null) {
			TipoObjeto objeto = null;
			obj = ((REXP) obj).asVector();
			String[][] strings = new String[100][100];
			RVector vector = (RVector) obj;
			int indexVector = 0;
			int indexRow = 0;
			int indexCol = 0;
			while (indexVector < vector.size()) {
				indexCol = 0;
				REXP element = (REXP) vector.get(indexVector);
				if (element.asFactor() != null) {
					objeto = new TipoFactor();
					strings[indexRow] = ((TipoFactor) objeto).getLista(element);
				} else if (element.asString() != null) {
					strings[indexRow][indexCol] = ((REXP) element).asString();
					indexCol++;
				} else if (element.getContent() instanceof double[]) {
					double[] dou = (double[]) ((REXP) element).getContent();
					for (int j = 0; j < dou.length; j++) {
						strings[indexRow][indexCol] = Double.toString(dou[j]);
						indexCol++;
					}
				} else if(element.asIntArray() != null){
					int [] enteros = (int[]) element.getContent();
					for(int ent : enteros) {
						strings[indexRow][indexCol] = Integer.toString(ent);
						indexCol++;
					}
				} else if(element.asVector() != null){
					objeto = new TipoVector();
					strings[indexRow] = ((TipoVector) objeto).getString(element);
				}else if (element.asList() != null) {
					objeto = new TipoList();
					strings[indexRow] = ((TipoList) objeto).getString(element);
				}
				indexVector++;
				indexRow++;
			}
			int size = getSize(strings);
			String[][] s = new String[size][indexRow];
			s = pasarContenido(s, strings);
			objeto = new TipoMatrizString();
			objeto.ejecutarMetodo(s, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado);
		}

		else if (super.siguiente() != null) {
			super.siguiente().ejecutarMetodo(obj, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado);
		}
	}
	
	public String[] getString(REXP vector) {
		String[] result = new String[100];
		RVector vec = ((REXP) vector).asVector();
		int i = 0;
		while (vec.at(i) != null) {
			if (vec.at(i).asString() != null) {
				result[i] = vec.at(i).asString();
			} else if (vec.at(i).getContent() instanceof double[]) {
				double[] dou = (double[]) vec.at(i).getContent();
				for (int j = 0; j < dou.length; j++) {
					result[i] = Double.toString(dou[j]);
				}
			} else if (vec.at(i).asVector() != null) {
				TipoVector objeto = new TipoVector();
				result = objeto.getString(vec.at(i));

			}
			i++;
		}

		return result;
	}

}
