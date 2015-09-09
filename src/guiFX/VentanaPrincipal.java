package guiFX;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FilenameUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VentanaPrincipal extends BorderPane {

	private TextArea editorTexto;
	private VBox barraMenu;
	private Button botonGuardarMetodoMatematico;
	private TextField textFieldNombreArchivo;
	private HBox hBox;
	private ComboBox comboBoxSeleccionarMetodo;

	static final String RUTA_METODOS = System.getProperty("user.dir") + "\\" + "Metodos Matematicos";
	static final String EXTENSION_ARCHIVOS = "met";

	public VentanaPrincipal(Double alto, Double ancho) {
		super();
		this.setPrefHeight(alto);
		this.setPrefWidth(ancho);

		barraMenu = new BarraMenu(this);
		barraMenu.setSpacing(10);
		this.setLeft(((BarraMenu) barraMenu).getBarraDeslizable());
		this.setTop(((BarraMenu) barraMenu).getBarraDeslizable().getBotonMenu());

		editorTexto = new TextArea();
		configuracionEditorTexto();
		this.setRight(editorTexto);

		botonGuardarMetodoMatematico = new Button("Guardar metodo");
		// this.setBottom(botonGuardarMetodoMatematico);

		hBox = new HBox();
		this.setBottom(hBox);
		textFieldNombreArchivo = new TextField();

		// this.setRight(textFieldNombreArchivo);

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

		hBox.getChildren().addAll(botonGuardarMetodoMatematico, textFieldNombreArchivo, comboBoxSeleccionarMetodo);
		hBox.setSpacing(120);
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
				String texto = editorTexto.getText();
				bw.write(texto, 0, texto.length());
			}
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* METODO PARA GUARDAR DONDE QUIERA
	private void guardarMetodoMatematicoDondeQuiero(Stage primaryStage) {
		// TODO agregar por defecto la extension del archivo
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showSaveDialog(primaryStage);
		if (file != null) {
			FileWriter fw = null;
			BufferedWriter bw = null;
			try {
				// EL segundo parametro es un boolean
				// En true escribe al final
				// En false escribe al inicio
				fw = new FileWriter(file, false);
				bw = new BufferedWriter(fw);

				String texto = editorTexto.getText();
				bw.write(texto, 0, texto.length());
			} catch (Exception e) {
				editorTexto.appendText(e.toString());
			} finally {
				try {
					bw.close();
				} catch (Exception e2) {
					editorTexto.appendText(e2.toString());
				}
			}
		}
	}*/

	// Drag & Drop en el area de texto
	private void configuracionEditorTexto() {

		editorTexto.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (event.getDragboard().hasFiles()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
			}
		});

		editorTexto.setOnDragDropped(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				final Dragboard dragboard = event.getDragboard();
				if (dragboard.hasFiles()) {
					Path file = dragboard.getFiles().get(0).toPath();
					try {
						editorTexto.setText(new String(Files.readAllBytes(file)));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		});

	}
}
