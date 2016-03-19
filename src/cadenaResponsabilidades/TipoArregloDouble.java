package cadenaResponsabilidades;

import java.io.File;
import java.util.Arrays;

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

public class TipoArregloDouble extends TipoObjeto {


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView<String> listaMetodos,
			TextField textFieldNombreFuncion, TableView tablaResultado) {
		
		setSiguiente(new TipoArregloString());
		if (((REXP) obj).getContent() instanceof double[]) {
			obj = ((REXP) obj).getContent();
			double[] arr = null;
			if (!textFieldNombreFuncion.getText().isEmpty()) {
				arr = (double[]) obj;
			}

			ObservableList<double[]> datos = FXCollections.observableArrayList(); 
			datos.addAll(Arrays.asList(arr));
			for (int i = 0; i < arr.length; i++) {
	            TableColumn tc = new TableColumn(String.valueOf(i));
	            final int colNo = i;
	            tc.setCellValueFactory(new Callback<CellDataFeatures<double[], String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<double[], String> param) {
						return  new SimpleStringProperty(Double.toString(param.getValue()[colNo]));
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
