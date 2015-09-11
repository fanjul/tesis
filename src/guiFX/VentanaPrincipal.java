package guiFX;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VentanaPrincipal extends BorderPane {

	private VBox barraMenu;
	private Button botonGuardarMetodoMatematico;
	private TextField textFieldNombreArchivo;
	private HBox hBoxAbajoDelVBox;
	private VBox editorTexto;
	private ComboBox comboBoxSeleccionarMetodo;

	static final String RUTA_METODOS = System.getProperty("user.dir") + "\\" + "Metodos Matematicos";
	static final String EXTENSION_ARCHIVOS = "met";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VentanaPrincipal(Double alto, Double ancho) {
		super();
		this.setPrefHeight(alto);
		this.setPrefWidth(ancho);

		barraMenu = new BarraMenu(this);
		barraMenu.setSpacing(10);
		this.setLeft(((BarraMenu) barraMenu).getBarraDeslizable());
		this.setTop(((BarraMenu) barraMenu).getBarraDeslizable().getBotonMenu());

		botonGuardarMetodoMatematico = new Button("Guardar metodo");

		hBoxAbajoDelVBox = new HBox();
		this.setBottom(hBoxAbajoDelVBox);
		textFieldNombreArchivo = new TextField();

		botonGuardarMetodoMatematico.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// guardarMetodoMatematicoDondeQuiero(primaryStage);
				guardarMetodoMtematicoLugarPorDefecto();

				// Actualiza el ComboBox con el nuevo Metodo Matematico agregado
				comboBoxSeleccionarMetodo.setItems(getMetodosMatematicosYaCreados());

			}
		});

		comboBoxSeleccionarMetodo = new ComboBox(getMetodosMatematicosYaCreados());

		//Para copiar el contenido del archivo en el editor de texto
		comboBoxSeleccionarMetodo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue arg0, Object old_val, Object new_val) {
				((EditorTexto)editorTexto).getEditorTexto().setText((String) new_val);
				copiarContenidoArchivoEnEditorTexto();
			}
		});

		hBoxAbajoDelVBox.getChildren().addAll(botonGuardarMetodoMatematico, textFieldNombreArchivo, comboBoxSeleccionarMetodo);
		
		editorTexto = EditorTexto.getInstance();
		this.setRight(editorTexto);
		
		
		
		hBoxAbajoDelVBox.setSpacing(120);
	}

	private void copiarContenidoArchivoEnEditorTexto() {
		File archivo;
		FileReader leerArchivo = null;

		try {

			archivo = new File(
					RUTA_METODOS + "\\" + comboBoxSeleccionarMetodo.getSelectionModel().getSelectedItem().toString()
							+ "." + EXTENSION_ARCHIVOS);
			leerArchivo = new FileReader(archivo);
			BufferedReader memoriaParaLectura = new BufferedReader(leerArchivo);

			String linea = null;

			((EditorTexto)editorTexto).getEditorTexto().setText("");
			while ((linea = memoriaParaLectura.readLine()) != null) {
				((EditorTexto)editorTexto).getEditorTexto().appendText(linea);
				((EditorTexto)editorTexto).getEditorTexto().appendText(System.lineSeparator());
			}
			memoriaParaLectura.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} finally {
			try {
				if (null != leerArchivo) {
					leerArchivo.close();
				}
			} catch (Exception ex1) {
				JOptionPane.showMessageDialog(null, ex1.getMessage());
			}
			
		}
	}

	@SuppressWarnings("rawtypes")
	private ObservableList getMetodosMatematicosYaCreados() {
		File carpetaDefecto = new File(RUTA_METODOS);
		ObservableList<String> listaMetodos = FXCollections.observableArrayList();

		if (carpetaDefecto.exists()) {
			File[] archivos = carpetaDefecto.listFiles();
			for (File archivo : archivos) {
				String extension = FilenameUtils.getExtension(archivo.getPath());
				if (extension.equals(EXTENSION_ARCHIVOS)) {
					listaMetodos.add(
							archivo.getName().substring(0, archivo.getName().lastIndexOf("." + EXTENSION_ARCHIVOS)));
				}
			}
		}

		return listaMetodos;
	}

	private void guardarMetodoMtematicoLugarPorDefecto() {

		File carpetaDefecto = new File(RUTA_METODOS);
		carpetaDefecto.mkdir();

		FileWriter fw = null;
		BufferedWriter bw = null;
		try {

			File archivo = new File(
					carpetaDefecto.getPath() + "\\" + textFieldNombreArchivo.getText() + "." + EXTENSION_ARCHIVOS);

			if (archivo.exists()) {
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write("El archivo de texto ya estaba creado.");

			} else {

				System.out.println("se creo archivo.met en: " + RUTA_METODOS);

				fw = new FileWriter(archivo, false);
				bw = new BufferedWriter(fw);
				String texto = 				((EditorTexto)editorTexto).getEditorTexto().getText();
				bw.write(texto, 0, texto.length());
			}
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * METODO PARA GUARDAR DONDE QUIERA private void
	 * guardarMetodoMatematicoDondeQuiero(Stage primaryStage) { // TODO agregar
	 * por defecto la extension del archivo FileChooser fileChooser = new
	 * FileChooser(); File file = fileChooser.showSaveDialog(primaryStage); if
	 * (file != null) { FileWriter fw = null; BufferedWriter bw = null; try { //
	 * EL segundo parametro es un boolean // En true escribe al final // En
	 * false escribe al inicio fw = new FileWriter(file, false); bw = new
	 * BufferedWriter(fw);
	 * 
	 * String texto = editorTexto.getText(); bw.write(texto, 0, texto.length());
	 * } catch (Exception e) { editorTexto.appendText(e.toString()); } finally {
	 * try { bw.close(); } catch (Exception e2) {
	 * editorTexto.appendText(e2.toString()); } } } }
	 */

	
}
