package cadenaResponsabilidades;

import java.io.File;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public abstract class TipoObjeto {

	private TipoObjeto sig;
	
	public TipoObjeto siguiente() {
		return sig;
	}

	public void setSiguiente(TipoObjeto obj) {
		this.sig = obj;
	}

	@SuppressWarnings("rawtypes")
	abstract public void ejecutarMetodo(Object obj, File archivo, ListView<String> listaMetodos, TextField textFieldNombreFuncion, 
			TableView tablaResultado);

	protected String[] pasarContenido(String[] s, String[] strings) {
		int i = 0;
		while (i < strings.length && strings[i] != null) {
			s[i] = strings[i];
			i++;
		}
		return s;
	}

	protected String[][] pasarContenido(String[][] s, String[][] strings) {
		int row = 0,col = 0;
		while ( row< strings.length && strings[row][col] != null) {
			while(col < strings[row].length && strings[row][col] != null){
				s[col][row] = strings[row][col];
				col++;	
			}
			row++;
			col = 0;
		}
		return s;
	}

}
