package guiFX;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;
import org.rosuda.JRI.Rengine;

import cadenaResponsabilidades.TipoArregloDouble;
import cadenaResponsabilidades.TipoObjeto;
import cadenaResponsabilidades.TipoString;
import dialogos.Dialogo;
import dialogos.DialogoEjecutar;
import dialogos.DialogoGuardarArchivo;
import graficosFX.Grafico;
import graficosFX.GraficoTorta;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaPrincipal extends BorderPane {

	private BarraMenu barraMenu;
	private HBox hBoxAbajo;
	private VBox panelDerecho;
	private BorderPane borderPaneMenuOpciones;
	private HBox hBoxMenuOpcionesVentana;
	private BotonImagen botonGuardarMetodo;
	private BotonImagen botonEjecutar;
	private BotonImagen botonGraficoTorta;
	private BotonImagen botonGraficoTortaPRUEBA;
	private ListaBotonesGrafico listaBotonesSuperior;
	private ListaBotonesGrafico listaBotonesInferior;
	
	static final String RUTA_METODOS = System.getProperty("user.dir") + "\\" + "Metodos Matematicos";
	static final String EXTENSION_ARCHIVOS = "met";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VentanaPrincipal(Stage primaryStage) {
		super();

		// Configuracion de la parte izquierda del borderPane (VentanaPrincipal)
		barraMenu = new BarraMenu(this);
		barraMenu.setMaxHeight(543);
		barraMenu.setMinHeight(543);
		barraMenu.setMaxWidth(120);
		barraMenu.setMinWidth(0);

		barraMenu.setSpacing(10);
		this.setLeft(((BarraMenu) barraMenu).getBarraDeslizable());

		// Configuracion de la parte de arriba del borderPane (VentanaPrincipal)
		agregarMenuVentana(primaryStage);
		borderPaneMenuOpciones = new BorderPane();
		borderPaneMenuOpciones.setLeft(((BarraMenu) barraMenu).getBarraDeslizable().getBotonMenu());
		borderPaneMenuOpciones.setRight(hBoxMenuOpcionesVentana);
		borderPaneMenuOpciones.setMaxHeight(110);
		borderPaneMenuOpciones.setMinHeight(110);
		borderPaneMenuOpciones.setMaxWidth(1365);
		borderPaneMenuOpciones.setMinWidth(1365);
		this.setTop(borderPaneMenuOpciones);

		// Configuracion de la parte de abajo del borderPane (VentanaPrincipal)
		abrirDialogoGuardarArchivo();
		abrirDialogoEjecutar();
		hBoxAbajo.getChildren().addAll(/* listaMetodos, */ botonGuardarMetodo, botonEjecutar);
		hBoxAbajo.setSpacing(120);
		hBoxAbajo.setMaxHeight(75);
		hBoxAbajo.setMinHeight(75);
		hBoxAbajo.setMaxWidth(1365);
		hBoxAbajo.setMinWidth(1365);
		hBoxAbajo.setAlignment(Pos.CENTER);
		this.setBottom(hBoxAbajo);

		// Configuracion de la parte derecha del borderPane (VentanaPrincipal)
		barraMenu.inicializarListaMetodosMatematicos(getMetodosMatematicosYaCreados());
		// Para copiar el contenido del archivo en el editor de texto
		barraMenu.getListaMetodos().getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue arg0, Object old_val, Object new_val) {

				((PanelDerecho) panelDerecho).getEditorTexto().setText((String) new_val);
				copiarContenidoArchivoEnEditorTexto();
			}
		});

		panelDerecho = PanelDerecho.getInstance();

		crearGraficoTorta();
		listaBotonesSuperior = new ListaBotonesGrafico();
		listaBotonesInferior = new ListaBotonesGrafico();
		listaBotonesSuperior.agregarNodo(botonGraficoTorta);
		listaBotonesInferior.agregarNodo(botonGraficoTortaPRUEBA);
		((PanelDerecho) panelDerecho).agregarElemento(listaBotonesSuperior);
		((PanelDerecho) panelDerecho).agregarElemento(listaBotonesInferior);
		panelDerecho.setSpacing(10);
		this.setRight(panelDerecho);
	}

	private void abrirDialogoGuardarArchivo() {
		hBoxAbajo = new HBox();

		botonGuardarMetodo = new BotonImagen("/imagenesFX/Guardar.png", "Guardar Metodo");

		botonGuardarMetodo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Dialogo dialogoGuardarMetodo = new DialogoGuardarArchivo();

				dialogoGuardarMetodo.crearDialogo();

				((DialogoGuardarArchivo) dialogoGuardarMetodo).getBotonGuardar()
						.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						// guardarMetodoMatematicoDondeQuiero(primaryStage);
						guardarMetodoMtematicoLugarPorDefecto(((DialogoGuardarArchivo) dialogoGuardarMetodo));
						// Actualiza el ComboBox con el nuevo Metodo Matematico
						// agregado
						barraMenu.getListaMetodos().setItems(getMetodosMatematicosYaCreados());
						dialogoGuardarMetodo.cerrarDialogo();
					}
				});

				((DialogoGuardarArchivo) dialogoGuardarMetodo).getBotonCancelar()
						.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						dialogoGuardarMetodo.cerrarDialogo();
					}
				});

				dialogoGuardarMetodo.mostrarDialogo();
			}
		});

	}

	private void abrirDialogoEjecutar() {
		// TODO hacer para que se ejecute cuando aprieta enter
		botonEjecutar = new BotonImagen("/imagenesFX/Ejecutar2.png", "Ejecutar");

		botonEjecutar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Dialogo dialogoEjecutar = new DialogoEjecutar();

				dialogoEjecutar.crearDialogo();

				((DialogoEjecutar) dialogoEjecutar).getBotonEjecutar().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						File archivo = new File(RUTA_METODOS + "\\"
								+ barraMenu.getListaMetodos().getSelectionModel().getSelectedItem().toString() + "."
								+ EXTENSION_ARCHIVOS);
						// ejecutar(archivo);

						Rengine re = new Rengine(new String[] { "--vanilla" }, false, null);
						String dir = agregarCuatroSparadores(archivo.getAbsolutePath());
						re.eval("source(\"" + dir + "\")");

						TipoObjeto tipoArregloDouble = new TipoArregloDouble();
						tipoArregloDouble.ejecutarMetodo(
								re.eval(((DialogoEjecutar) dialogoEjecutar).getTextFieldNombreDondeDevuelve().getText())
										.getContent(),
								archivo, barraMenu.getListaMetodos(),
								((DialogoEjecutar) dialogoEjecutar).getTextFieldNombreDondeDevuelve());

						TipoObjeto tipoString = new TipoString();
						tipoString.ejecutarMetodo(
								re.eval(((DialogoEjecutar) dialogoEjecutar).getTextFieldNombreDondeDevuelve().getText())
										.getContent(),
								archivo, barraMenu.getListaMetodos(),
								((DialogoEjecutar) dialogoEjecutar).getTextFieldNombreDondeDevuelve());
						dialogoEjecutar.cerrarDialogo();
					}
				});

				((DialogoEjecutar) dialogoEjecutar).getBotonCancelar().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						dialogoEjecutar.cerrarDialogo();
					}
				});

				dialogoEjecutar.mostrarDialogo();
			}
		});

	}

	private void agregarMenuVentana(Stage primaryStage) {

		BotonImagen botonCerrarVentana = new BotonImagen("/imagenesFX/Cerrar.png", "Cerrar");
		botonCerrarVentana.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				primaryStage.close();
			}
		});

		BotonImagen botonMinimizarVentana = new BotonImagen("/imagenesFX/Minimizar.png", "Minimizar");
		botonMinimizarVentana.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				primaryStage.setIconified(true);
			}
		});

		BotonImagen botonMaximizarVentana = new BotonImagen("/imagenesFX/Maximizar.png", "Maximizar");
		botonMaximizarVentana.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (!primaryStage.isMaximized()) {
					primaryStage.setMaximized(true);
				} else {

					primaryStage.setMaximized(false);
				}
			}
		});

		botonMaximizarVentana.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// botonMaximizarVentana.setEffect(shadow);
				if (!primaryStage.isMaximized()) {
					botonMaximizarVentana.setTooltip(new Tooltip("Maximizar"));
				} else {
					botonMaximizarVentana.setTooltip(new Tooltip("Minimizar tamanño"));
				}
			}
		});

		borderPaneMenuOpciones = new BorderPane();
		hBoxMenuOpcionesVentana = new HBox(2);
		hBoxMenuOpcionesVentana.getChildren().addAll((ToggleButton) botonMinimizarVentana,
				(ToggleButton) botonMaximizarVentana, (ToggleButton) botonCerrarVentana);

	}

	private void crearGraficoTorta() {

		botonGraficoTorta = new BotonImagen("/imagenesFX/GraficoTorta.png", "Grafico de Torta");
		botonGraficoTortaPRUEBA = new BotonImagen("/imagenesFX/GraficoTorta.png", "Grafico de Torta");
		botonGraficoTorta.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Grafico graficoTorta = new GraficoTorta();
				graficoTorta.graficar();

			}
		});

	}

	private String agregarCuatroSparadores(String dir) {

		String path = "";
		String[] numerosComoArray = dir.split("\\\\");

		for (int i = 0; i < numerosComoArray.length; i++) {
			if (i != numerosComoArray.length - 1)
				path += numerosComoArray[i] + "\\\\";
			else
				path += numerosComoArray[i];
		}

		return path;
	}

	private void copiarContenidoArchivoEnEditorTexto() {
		File archivo;
		FileReader leerArchivo = null;

		try {
			// TODO arreglar, el file tira error porque no existe al guardar uno
			// nuevo
			archivo = new File(
					RUTA_METODOS + "\\" + barraMenu.getListaMetodos().getSelectionModel().getSelectedItem().toString()
							+ "." + EXTENSION_ARCHIVOS);
			leerArchivo = new FileReader(archivo);
			BufferedReader memoriaParaLectura = new BufferedReader(leerArchivo);

			String linea = null;

			((PanelDerecho) panelDerecho).getEditorTexto().setText("");

			while ((linea = memoriaParaLectura.readLine()) != null) {

				((PanelDerecho) panelDerecho).getEditorTexto().appendText(linea);
				((PanelDerecho) panelDerecho).getEditorTexto().appendText(System.lineSeparator());

			}
			memoriaParaLectura.close();
		} catch (Exception ex) {
			ex.printStackTrace();
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

	private ObservableList<String> getMetodosMatematicosYaCreados() {
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

	private void guardarMetodoMtematicoLugarPorDefecto(DialogoGuardarArchivo dialogoGuardarMetodo) {

		File carpetaDefecto = new File(RUTA_METODOS);
		carpetaDefecto.mkdir();

		FileWriter fw = null;
		BufferedWriter bw = null;
		try {

			File archivo = new File(carpetaDefecto.getPath() + "\\"
					+ dialogoGuardarMetodo.getTextFieldNombreArchivo().getText() + "." + EXTENSION_ARCHIVOS);

			if (archivo.exists()) {
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write("El archivo de texto ya estaba creado.");

			} else {

				System.out.println("se creo archivo.met en: " + RUTA_METODOS);

				fw = new FileWriter(archivo, false);
				bw = new BufferedWriter(fw);
				String texto = ((PanelDerecho) panelDerecho).getEditorTexto().getText();

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
