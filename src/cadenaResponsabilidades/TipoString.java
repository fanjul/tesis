package cadenaResponsabilidades;

import java.io.File;
import java.util.List;

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

public class TipoString extends TipoObjeto {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView<String> listaMetodos,
			TextField textFieldNombreFuncion, TableView tablaResultado,  boolean recursivo,
			List<String> resultado) { 

		setSiguiente(null);
		if (((REXP) obj).getContent() instanceof String) {
			obj = ((REXP) obj).getContent();
			ObservableList<String> datos = FXCollections.observableArrayList();
			datos.add((String) obj);
			TableColumn columna = new TableColumn("Imprime");
			//final int colNo = 0;
			columna.setCellValueFactory(new Callback<CellDataFeatures<String, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<String, String> p) {
					return new SimpleStringProperty(p.getValue());
				}
			});

			columna.setPrefWidth(230);
			tablaResultado.getColumns().add(columna);
			tablaResultado.setItems(datos);

		}

		else if (super.siguiente() != null) {
			super.siguiente().ejecutarMetodo(obj, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado,recursivo,resultado);
		}

	}

}
