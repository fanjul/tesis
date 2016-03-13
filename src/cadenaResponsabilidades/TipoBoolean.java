package cadenaResponsabilidades;

import java.io.File;
import java.util.List;

import org.rosuda.JRI.RBool;
import org.rosuda.JRI.REXP;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class TipoBoolean extends TipoObjeto {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void ejecutarMetodo(Object obj, File archivo, ListView<String> listaMetodos,
			TextField textFieldNombreFuncion, TableView tablaResultado, boolean recursivo, List<String> resultado) {

		setSiguiente(null);
		if(((REXP) obj).asBool() != null){
			RBool bool = ((REXP) obj).asBool();
			ObservableList<Boolean> datos = FXCollections.observableArrayList();
			datos.add((Boolean) bool.isTRUE());
			TableColumn columna = new TableColumn("Valor");
			columna.setCellValueFactory(new Callback<CellDataFeatures<Boolean, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<Boolean, String> p) {
					return new SimpleStringProperty(Boolean.toString(p.getValue()));
				}
			});

			columna.setPrefWidth(90);
			tablaResultado.getColumns().add(columna);
			tablaResultado.setItems(datos);

			
		}
		else if (super.siguiente() != null) {
			super.siguiente().ejecutarMetodo(obj, archivo, listaMetodos, textFieldNombreFuncion, tablaResultado,recursivo,resultado);
		}
	}

}
