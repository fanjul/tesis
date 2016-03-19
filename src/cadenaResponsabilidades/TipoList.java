package cadenaResponsabilidades;

import java.io.File;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RList;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TipoList extends TipoObjeto {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView<String> listaMetodos,
			TextField textFieldNombreFuncion, TableView tablaResultado) {

		setSiguiente(new TipoVector());
		if (((REXP) obj).asList() != null) {
			String[] strings = new String[100];
			String[][] tabla = new String[100][100];
			int i = 0;
			int indexRow = 0;
			RList lista = ((REXP) obj).asList();
			String[] keys = lista.keys();
			TipoObjeto objeto = null;
			// la lista tiene keys
			if (keys != null) {
				for (String key : keys) {

					// por si es una tabla dentro de una lista
					if (lista.at(key).asFactor() != null) {
						objeto = new TipoFactor();
						tabla[indexRow] = ((TipoFactor) objeto).getLista(lista.at(key));
						indexRow++;
					} else if (lista.at(key).asString() != null) {
						tabla[indexRow][i] = lista.at(key).asString();
						indexRow++;
					} else if (lista.at(key).getContent() instanceof double[]) {
						double[] dou = (double[]) lista.at(key).getContent();
						for (int j = 0; j < dou.length; j++) {
							strings[j] = Double.toString(dou[j]);

						}
						tabla[indexRow] = strings;
						indexRow++;
						strings = new String[100];
					} else if (lista.at(key).asList() != null) {
						objeto = new TipoList();
						tabla[indexRow] = ((TipoList) objeto).getString(lista.at(key));
						indexRow++;
					} else {
						// supongo que lo que viene es un Vector que lo
						// instancio mal en List
						objeto = new TipoVector();
						objeto.ejecutarMetodo(obj, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado);
						return;
					}

				}
				// la lista tiene solo informacion
			} else {
				while (lista.at(i) != null) {
					if (lista.at(i).asString() != null) {
						tabla[indexRow][0] = lista.at(i).asString();
						indexRow++;
					} else if (lista.at(i).getContent() instanceof double[]) {
						double[] dou = (double[]) lista.at(i).getContent();
						for (int j = 0; j < dou.length; j++) {
							strings[j] = Double.toString(dou[j]);
						}
						tabla[indexRow] = strings;
						indexRow++;
						strings = new String[100];
					}
					if (lista.at(i).asList() != null) {
						objeto = new TipoList();
						tabla[indexRow] = ((TipoList) objeto).getString(lista.at(i));
						indexRow++;
					}
					i++;
				}

			}

			int size = getSize(tabla);
			String[][] t = new String[size][indexRow];
			t = pasarContenido(t, tabla);
			objeto = new TipoMatrizArreglo();
			objeto.ejecutarMetodo(t, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado);

		}

		else if (super.siguiente() != null) {
			super.siguiente().ejecutarMetodo(obj, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado);
		}
	}

	private int getSize(String[][] tabla) {
		int size = 0;

		int j;
		for (int i = 0; i < tabla.length; i++) {
			for (j = 0; j < tabla[i].length; j++) {
				if (tabla[i][j] == null) {
					break;
				}
			}
			if (size < j) {
				size = j;
			}
		}

		return size;
	}

	public String[] getString(REXP list) {
		String[] result = new String[100];
		RList lista = ((REXP) list).asList();
		int i = 0;
		while (lista.at(i) != null) {
			if (lista.at(i).asString() != null) {
				result[i] = lista.at(i).asString();
			} else if (lista.at(i).getContent() instanceof double[]) {
				double[] dou = (double[]) lista.at(i).getContent();
				for (int j = 0; j < dou.length; j++) {
					result[i] = Double.toString(dou[j]);
				}
			} else if (lista.at(i).asList() != null) {
				TipoList objeto = new TipoList();
				result = objeto.getString(lista.at(i));

			}
			i++;
		}

		return result;
	}

}
