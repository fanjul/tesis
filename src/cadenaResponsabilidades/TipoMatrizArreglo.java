package cadenaResponsabilidades;

import java.io.File;
import java.util.Arrays;

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

public class TipoMatrizArreglo extends TipoObjeto{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView<String> listaMetodos,
			TextField textFieldNombreFuncion, TableView tablaResultado) {

		
		if(obj instanceof String[][]){
			String[][] matriz = null;
			if (!textFieldNombreFuncion.getText().isEmpty()) {
				matriz = (String[][]) obj;
			}

			ObservableList<String[]> datos = FXCollections.observableArrayList();
			datos.addAll(Arrays.asList(matriz));
			for (int i = 0; i < matriz[0].length; i++) {

				TableColumn tc = new TableColumn(String.valueOf(i));

				final int colNo = i;
				tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<String[], String> param) {
						return new SimpleStringProperty(param.getValue()[colNo]);
					}
				});
				tc.setPrefWidth(90);
				tablaResultado.getColumns().add(tc);

			}

			tablaResultado.setItems(datos);
		}
		
		
	}

}
