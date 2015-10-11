package dialogos;

import java.util.List;

import baseDatos.hibernate.consultas.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DialogoSeleccionColumnaBD extends Dialogo {

	private ImageView imagen;
	private Button botonAceptar;
	private Button botonCancelar;
	private ComboBox<String> comboBoxColumna;
	private Label labelColumna;
	private DAO consulta;
	
	public DialogoSeleccionColumnaBD() {
		this.imagen = new ImageView("/imagenesFX/editor.png");
		this.botonAceptar = new Button("Aceptar");
		this.botonCancelar = new Button("Cancelar");
		this.comboBoxColumna = new ComboBox<String>();
		this.labelColumna = new Label("Seleccione Columna: ");

	}
	
	public void crearDialogo(DAO consulta) {
		this.consulta = consulta;
		this.crearDialogo();
	}
	
	@Override
	public void crearDialogo() {
		ObservableList<String> listaColumnas = FXCollections.observableArrayList();
		List<String> listaTodasTablas = consulta.getColumnas();
		for (String s : listaTodasTablas) {
			listaColumnas.add(s);
		}
		comboBoxColumna.setItems(listaColumnas);
		
		super.gethBoxAbajo().getChildren().addAll(botonAceptar,botonCancelar);
		super.gethBoxArriba().getChildren().addAll(labelColumna,comboBoxColumna);
		super.getvBoxContenedor().getChildren().addAll(gethBoxArriba(),gethBoxAbajo());
	}

	public ImageView getImagen() {
		return imagen;
	}

	public Button getBotonAceptar() {
		return botonAceptar;
	}

	public Button getBotonCancelar() {
		return botonCancelar;
	}

	public ComboBox<String> getComboBoxColumna() {
		return comboBoxColumna;
	}
	
}
