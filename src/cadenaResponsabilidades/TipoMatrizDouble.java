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

public class TipoMatrizDouble extends TipoObjeto {

	/*
	 * @Override public TipoObjeto siguiente() { return sig; }
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView listaMetodos, TextField textFieldNombreFuncion,
			TableView tablaResultado) {

		if (obj instanceof double[][]) {

			double[][] matriz = null;
			if (!textFieldNombreFuncion.getText().isEmpty()) {
				matriz = (double[][]) obj;
			}

			ObservableList<double[]> datos = FXCollections.observableArrayList();
			datos.addAll(Arrays.asList(matriz));
			for (int i = 0; i < matriz[0].length; i++) {

				TableColumn tc = new TableColumn(String.valueOf(i));

				final int colNo = i;
				tc.setCellValueFactory(new Callback<CellDataFeatures<double[], String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<double[], String> param) {
						return new SimpleStringProperty(Double.toString(param.getValue()[colNo]));
					}
				});
				tc.setPrefWidth(90);
				tablaResultado.getColumns().add(tc);

			}

			tablaResultado.setItems(datos);
		}

		if (super.siguiente() != null) {
			super.siguiente().ejecutarMetodo(obj, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado);
		}

	}
}
