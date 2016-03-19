package cadenaResponsabilidades;

import java.io.File;

import org.rosuda.JRI.REXP;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class TipoArregloString extends TipoObjeto {

	

	/*@Override
	public TipoObjeto siguiente() {
		return sig;
	}*/

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView<String> listaMetodos,
			TextField textFieldNombreFuncion, TableView tablaResultado) {
		
		setSiguiente(new TipoString());
		if(obj instanceof String[] || obj instanceof REXP){ 
			
			if( obj instanceof REXP && ((REXP) obj).getContent() instanceof String[]){
				obj = ((REXP) obj).getContent();
			}
			else if (obj instanceof REXP && super.siguiente() != null) {
					super.siguiente().ejecutarMetodo(obj, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado);
					return;
			}
			String[] arr = null;
			if (!textFieldNombreFuncion.getText().isEmpty()) {
				arr = (String[]) obj;
			}

			ObservableList<String[]> datos = FXCollections.observableArrayList(); 
			datos.add(arr);
			for (int i = 0; i < arr.length; i++) {
	            TableColumn tc = new TableColumn(String.valueOf(i));
	            final int colNo = i;
	            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<String[], String> param) {
						return  new SimpleStringProperty(param.getValue()[colNo]);
					}
	            });
	            tc.setPrefWidth(90);
	            tablaResultado.getColumns().add(tc);
			}
			
			tablaResultado.setItems(datos);
			} 
			
		else if (super.siguiente() != null) {
			super.siguiente().ejecutarMetodo(obj, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado);
		}	

	}

}
