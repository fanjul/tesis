package cadenaResponsabilidades;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RList;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TipoList extends TipoObjeto {

	@SuppressWarnings({ "rawtypes"})
	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView<String> listaMetodos,
			TextField textFieldNombreFuncion, TableView tablaResultado, boolean recursivo,
			List<String> resultado) {

		setSiguiente(new TipoVector());
		if (((REXP) obj).asList() != null) {
			String[] strings = new String[100];
			int i = 0;
			RList lista = ((REXP) obj).asList();
			String[] keys = lista.keys();
			TipoObjeto objeto = null;
			// la lista tiene keys
			if (keys != null) {
				for (String key : keys) {

					// por si es una tabla dentro de una lista
					if (lista.at(key).asFactor() != null) {
						objeto = new TipoFactor();
						objeto.ejecutarMetodo(lista.at(key), archivo, listaMetodos, textFieldNombreFuncion,
								tablaResultado,true,resultado);
					} else if (lista.at(key).asString() != null) {
						strings[i] = lista.at(key).asString();
						i++;
					} else if (lista.at(key).getContent() instanceof double[]) {
						double[] dou = (double[]) lista.at(key).getContent();
						for (int j = 0; j < dou.length; j++) {
							strings[i] = Double.toString(dou[j]);
							i++;
						}
					} else if (lista.at(key).asList() != null) {
						objeto = new TipoList();
						objeto.ejecutarMetodo(lista.at(key), archivo, listaMetodos, textFieldNombreFuncion,
								tablaResultado,true,resultado);
					}else{
						//supongo que lo que viene es un Vector que lo instancio mal en List
						objeto = new TipoVector();
						objeto.ejecutarMetodo(obj, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado, recursivo, resultado);
						return;
					}

				}
				// la lista tiene solo informacion
			} else {
				while (lista.at(i) != null) {
					if (lista.at(i).asString() != null) {
						strings[i] = lista.at(i).asString();
						i++;
					} else if (lista.at(i).getContent() instanceof double[]) {
						double[] dou = (double[]) lista.at(i).getContent();
						for (int j = 0; j < dou.length; j++) {
							strings[i] = Double.toString(dou[j]);
							i++;
						}
					}

				}

			}
			String[] s = new String[i];
			s = pasarContenido(s, strings);
			if(recursivo){
				resultado.addAll(Arrays.asList(s));
				return;
			}
			if(!resultado.isEmpty()){
				s = pasarContenido(resultado);
			}
			objeto = new TipoArregloString();
			objeto.ejecutarMetodo(s, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado,false,resultado);
		}

	else if (super.siguiente() != null) {
			super.siguiente().ejecutarMetodo(obj, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado,recursivo,resultado);
		}	
	}
	
	private String[] pasarContenido(String[] s, String[] strings) {
		int i = 0;
		while (i < strings.length && strings[i] != null) {
			s[i] = strings[i];
			i++;
		}
		return s;
	}
	
	private String[] pasarContenido(List<String> strings) {
		String[] s = new String[strings.size()];
		for(int i = 0; i < strings.size(); i++) {
			s[i] = strings.get(i);
		}
		return s;
	}
}
