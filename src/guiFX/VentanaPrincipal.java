package guiFX;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.rosuda.JRI.Rengine;

import cadenaResponsabilidades.TipoArregloDouble;
import cadenaResponsabilidades.TipoArregloString;
import cadenaResponsabilidades.TipoObjeto;
import cadenaResponsabilidades.TipoString;
import dialogos.Dialogo;
import dialogos.DialogoEjecutar;
import dialogos.DialogoErrorArchivoExistente;
import dialogos.DialogoGuardarArchivo;
import dialogos.DialogoSeGuardoCorrectamente;
import graficosFX.Grafico;
import graficosFX.GraficoArea;
import graficosFX.GraficoBarras;
import graficosFX.GraficoLinea;
import graficosFX.GraficoTorta;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TableView;
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
	
	private BotonImagen botonCrearNuevoMetodo;
	private BotonImagen botonEjecutar;
	private BotonImagen botonGuardar;

	private BotonImagen botonGraficoTorta;
	private BotonImagen botonGraficoBarras;
	private BotonImagen botonGraficoLinea;
	private BotonImagen botonGraficoArea;

	private ListaBotonesGrafico listaBotonesSuperior;
	private ListaBotonesGrafico listaBotonesInferior;

	private TableView<String> tablaResultado;


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

		// areaResultado = new TextArea();
		// areaResultado.setEditable(false);
		tablaResultado = new TableView();
		tablaResultado.setEditable(false);

		// Configuracion de la parte de arriba del borderPane (VentanaPrincipal)
		agregarMenuVentana(primaryStage);
		borderPaneMenuOpciones = new BorderPane();
		borderPaneMenuOpciones.setLeft(((BarraMenu) barraMenu).getBarraDeslizable().getBotonMenu());
		borderPaneMenuOpciones.setRight(hBoxMenuOpcionesVentana);
		// borderPaneMenuOpciones.setMaxHeight(110);
		// borderPaneMenuOpciones.setMinHeight(110);
		// borderPaneMenuOpciones.setMaxWidth(1365);
		// borderPaneMenuOpciones.setMinWidth(1365);
		this.setTop(borderPaneMenuOpciones);

		// Configuracion de la parte derecha del borderPane (VentanaPrincipal)
		barraMenu.inicializarListaMetodosMatematicos(getMetodosMatematicosYaCreados());
	
		
		// Para copiar el contenido del archivo en el editor de texto
		barraMenu.getListaMetodos().getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue arg0, Object old_val, Object new_val) {
				if (barraMenu.getListaMetodos().getSelectionModel().getSelectedItem() != null) {
					((PanelDerecho) panelDerecho).getEditorTexto().setText((String) new_val);
					copiarContenidoArchivoEnEditorTexto();
			
				System.out.println("aaaaaaaaaaaaaa");
				
				}
			}
		});

		panelDerecho = PanelDerecho.getInstance();

		crearGraficoTorta();
		crearGraficoBarras();
		crearGraficoLinea();
		crearGraficoArea();

		listaBotonesSuperior = new ListaBotonesGrafico();
		listaBotonesInferior = new ListaBotonesGrafico();
		listaBotonesSuperior.agregarNodo(botonGraficoTorta);
		listaBotonesSuperior.agregarNodo(botonGraficoBarras);
		listaBotonesSuperior.agregarNodo(botonGraficoLinea);
		listaBotonesSuperior.agregarNodo(botonGraficoArea);

		listaBotonesInferior.agregarNodo(tablaResultado/* areaResultado */);

		((PanelDerecho) panelDerecho).agregarElemento(listaBotonesSuperior);
		((PanelDerecho) panelDerecho).agregarElemento(listaBotonesInferior);
		panelDerecho.setSpacing(10);
		this.setRight(panelDerecho);
		
		
		
		
		hBoxAbajo = new HBox();
		// Configuracion de la parte de abajo del borderPane (VentanaPrincipal)
		crearBotonNuevoMetodo();
		crearBotonEjecutar();
		crearBotonGuardar();
		
		
		
	
		
		hBoxAbajo.getChildren().addAll(/* listaMetodos, */ botonCrearNuevoMetodo, botonGuardar, botonEjecutar);
		hBoxAbajo.setSpacing(120);
		hBoxAbajo.setMaxHeight(75);
		hBoxAbajo.setMinHeight(75);
		hBoxAbajo.setMaxWidth(1365);
		hBoxAbajo.setMinWidth(1365);
		hBoxAbajo.setAlignment(Pos.CENTER);
		this.setBottom(hBoxAbajo);
		
		
		
		
		///////////////////// Para disablear/enablear el boton ejecutar
		BooleanBinding bb = new BooleanBinding() {
			{
				super.bind(((PanelDerecho) panelDerecho).getEditorTexto().textProperty());
			}

			@Override
			protected boolean computeValue() {
				return (barraMenu.getListaMetodos().getSelectionModel().isEmpty());
			}
		};
		((PanelDerecho) panelDerecho).getEditorTexto().disableProperty().bind(bb);
		/////////////////////////
		
	
	}

	
	
	
	
	
	
	/*private String getContenidoArchivo(File archivo) {

		String contenido = "";
		try {
			// TODO arreglar que si no es un txt no se rompa y tirar error nuevo
			// TODO arreglar, el file tira error porque no existe al guardar uno
			// nuevo (creo q ya esta)

			FileInputStream fisTargetFile = new FileInputStream(archivo);
			contenido = IOUtils.toString(fisTargetFile, "UTF-8");
			
		} catch (Exception ex) {
//			ex.printStackTrace();
//			JOptionPane.showMessageDialog(null, ex.getMessage());
		} 
		return contenido;

	}*/
	

	
	
	
	private String getContenidoArchivo(File archivo){
		try {
			return FileUtils.readFileToString(archivo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	private void copiarContenidoArchivoEnEditorTexto() {
		File archivo;
		try {

			archivo = new File(
					RUTA_METODOS + "\\" + barraMenu.getListaMetodos().getSelectionModel().getSelectedItem().toString()
							+ "." + EXTENSION_ARCHIVOS);

			((PanelDerecho) panelDerecho).getEditorTexto().clear();
			((PanelDerecho) panelDerecho).getEditorTexto().setText(getContenidoArchivo(archivo));
			
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} 
	}
	
	
	
	
	
	
	
	
	
	
	private void crearBotonGuardar() {

		botonGuardar = new BotonImagen("/imagenesFX/Guardar4.png", "Guardar");

		
		((PanelDerecho) panelDerecho).getEditorTexto().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue,
					final String newValue) {

				File carpetaDefecto2 = new File(RUTA_METODOS);
				carpetaDefecto2.mkdir();
				File archivo2 = new File(carpetaDefecto2.getPath() + "\\"
						+ barraMenu.getListaMetodos().getSelectionModel().getSelectedItem().toString() + "."
						+ EXTENSION_ARCHIVOS);

				if (newValue.equals(getContenidoArchivo(archivo2))) {
					botonGuardar.setDisable(true);
				} else {
					botonGuardar.setDisable(false);
				}
			}
		});
		
		
		
		botonGuardar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				System.out.println("se guardo: " + RUTA_METODOS);
				botonGuardar.setDisable(true);

				File carpetaDefecto = new File(RUTA_METODOS);
				carpetaDefecto.mkdir();

				FileWriter fw = null;
				BufferedWriter bw = null;

				File archivo = new File(carpetaDefecto.getPath() + "\\"
						+ barraMenu.getListaMetodos().getSelectionModel().getSelectedItem().toString() + "."
						+ EXTENSION_ARCHIVOS);
				
				try {

					fw = new FileWriter(archivo, false);
					bw = new BufferedWriter(fw);

					if (archivo.exists()) {
						bw.write("");
					}

					String texto = ((PanelDerecho) panelDerecho).getEditorTexto().getText();
					bw.write(texto, 0, texto.length());

					if (bw != null) {
						bw.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

	}
	
	
	
	private void seleccionarNuevoMetodo(Dialogo dialogoGuardarMetodo) {
		String ultimoMetodoCreado = ((DialogoGuardarArchivo) dialogoGuardarMetodo).getTextFieldNombreArchivo()
				.getText();
		ObservableList<String> elementosLista = barraMenu.getListaMetodos().getItems();
		int pos = 0;
		while (!ultimoMetodoCreado.equals(elementosLista.get(pos))) {
			pos++;
		}
		barraMenu.getListaMetodos().getSelectionModel().select(pos);
	}
	
	
	
	private void crearBotonNuevoMetodo() {
		
		botonCrearNuevoMetodo = new BotonImagen("/imagenesFX/NuevoMetodo.png", "Guardar Metodo");

		botonCrearNuevoMetodo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Dialogo dialogoGuardarMetodo = new DialogoGuardarArchivo();

				dialogoGuardarMetodo.crearDialogo();

				((DialogoGuardarArchivo) dialogoGuardarMetodo).getBotonGuardar()
						.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						// guardarMetodoMatematicoDondeQuiero(primaryStage);

						guardarNuevoMetodoMatematicoLugarPorDefecto(((DialogoGuardarArchivo) dialogoGuardarMetodo));

						// Actualiza el ComboBox con el nuevo Metodo Matematico agregado
						barraMenu.getListaMetodos().setItems(getMetodosMatematicosYaCreados());

						seleccionarNuevoMetodo(dialogoGuardarMetodo);

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
	
	

	private void crearBotonEjecutar() {
		// TODO hacer para que se ejecute cuando aprieta enter
		botonEjecutar = new BotonImagen("/imagenesFX/Ejecutar1.png", "Ejecutar");

		///////////////////// Para disablear/enablear el boton ejecutar
		BooleanBinding bb = new BooleanBinding() {
			{
				super.bind(((PanelDerecho) panelDerecho).getEditorTexto().textProperty());
			}

			@Override
			protected boolean computeValue() {
				return (((PanelDerecho) panelDerecho).getEditorTexto().getText().isEmpty());
			}
		};
		botonEjecutar.disableProperty().bind(bb);
		/////////////////////////

		botonEjecutar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Dialogo dialogoEjecutar = new DialogoEjecutar();

				dialogoEjecutar.crearDialogo();

				((DialogoEjecutar) dialogoEjecutar).getBotonEjecutar().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO se rompe cuando no guardas el metodo porque no
						// esta en la carpeta defecto
						File archivo = new File(RUTA_METODOS + "\\"
								+ barraMenu.getListaMetodos().getSelectionModel().getSelectedItem().toString() + "."
								+ EXTENSION_ARCHIVOS);
						// ejecutar(archivo);

						Rengine re = Rengine.getMainEngine();
						if (re == null)
							re = new Rengine(new String[] { "--vanilla" }, false, null);

						String dir = agregarCuatroSparadores(archivo.getAbsolutePath());
						re.eval("source(\"" + dir + "\")");

						tablaResultado.getColumns().clear();

						
						
						TipoObjeto tipoArregloDouble = new TipoArregloDouble();
						tipoArregloDouble.ejecutarMetodo(
								re.eval(((DialogoEjecutar) dialogoEjecutar).getTextFieldNombreDondeDevuelve().getText())
										.getContent(),
								archivo, barraMenu.getListaMetodos(),
								((DialogoEjecutar) dialogoEjecutar).getTextFieldNombreDondeDevuelve(),
								tablaResultado/* areaResultado */);

						TipoObjeto tipoString = new TipoString();
						tipoString.ejecutarMetodo(
								re.eval(((DialogoEjecutar) dialogoEjecutar).getTextFieldNombreDondeDevuelve().getText())
										.getContent(),
								archivo, barraMenu.getListaMetodos(),
								((DialogoEjecutar) dialogoEjecutar).getTextFieldNombreDondeDevuelve(),
								tablaResultado/* areaResultado */);
						
						TipoObjeto tipoArregloString = new TipoArregloString();
						tipoArregloString.ejecutarMetodo(
								re.eval(((DialogoEjecutar) dialogoEjecutar).getTextFieldNombreDondeDevuelve().getText())
										.getContent(),
								archivo, barraMenu.getListaMetodos(),
								((DialogoEjecutar) dialogoEjecutar).getTextFieldNombreDondeDevuelve(),
								tablaResultado/* areaResultado */);
						
						
						
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
					botonMaximizarVentana.setTooltip(new Tooltip("Minimizar taman�o"));
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

		///////////////////// Para disablear/enablear el boton grafico torta
		botonGraficoTorta.disableProperty().bind(new BooleanBinding() {
			{
				bind(tablaResultado.getColumns());
			}

			@Override
			protected boolean computeValue() {
				return tablaResultado.getColumns().size() == 0;
			}
		});
		/////////////////////////

		/*
		 * if (tablaResultado.getColumns().size() == 0) {
		 * botonGraficoTorta.setDisable(true); }
		 */

		// botonGraficoTortaPRUEBA = new
		// BotonImagen("/imagenesFX/GraficoTorta.png", "Grafico de Torta");
		botonGraficoTorta.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (tablaResultado.getColumns().size() != 0) {
					Grafico graficoTorta = new GraficoTorta();
					graficoTorta.graficar(tablaResultado);
				}

			}
		});

	}

	private void crearGraficoBarras() {
		botonGraficoBarras = new BotonImagen("/imagenesFX/GraficoBarras.png", "Grafico de Barras");

		///////////////////// Para disablear/enablear el boton grafico barras
		botonGraficoBarras.disableProperty().bind(new BooleanBinding() {
			{
				bind(tablaResultado.getColumns());
			}

			@Override
			protected boolean computeValue() {
				return tablaResultado.getColumns().size() == 0;
			}
		});
		/////////////////////////
		// botonGraficoTortaPRUEBA = new
		// BotonImagen("/imagenesFX/GraficoTorta.png", "Grafico de Torta");
		botonGraficoBarras.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (tablaResultado.getColumns().size() != 0) {
					Grafico graficoBarras = new GraficoBarras();
					graficoBarras.graficar(tablaResultado);
				}
			}
		});
	}

	private void crearGraficoLinea() {
		botonGraficoLinea = new BotonImagen("/imagenesFX/GraficoLinea.png", "Grafico de Linea");

		///////////////////// Para disablear/enablear el boton grafico torta
		botonGraficoLinea.disableProperty().bind(new BooleanBinding() {
			{
				bind(tablaResultado.getColumns());
			}

			@Override
			protected boolean computeValue() {
				return tablaResultado.getColumns().size() == 0;
			}
		});
		/////////////////////////

		// botonGraficoTortaPRUEBA = new
		// BotonImagen("/imagenesFX/GraficoTorta.png", "Grafico de Torta");
		botonGraficoLinea.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Grafico graficoLinea = new GraficoLinea();
				graficoLinea.graficar(tablaResultado);

			}
		});

	}

	private void crearGraficoArea() {
		botonGraficoArea = new BotonImagen("/imagenesFX/GraficoArea.png", "Grafico de Area");

		///////////////////// Para disablear/enablear el boton grafico torta
		botonGraficoArea.disableProperty().bind(new BooleanBinding() {
			{
				bind(tablaResultado.getColumns());
			}

			@Override
			protected boolean computeValue() {
				return tablaResultado.getColumns().size() == 0;
			}
		});
		/////////////////////////

		// botonGraficoTortaPRUEBA = new
		// BotonImagen("/imagenesFX/GraficoTorta.png", "Grafico de Torta");
		botonGraficoArea.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Grafico graficoArea = new GraficoArea();
				graficoArea.graficar(tablaResultado);

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

	private void guardarNuevoMetodoMatematicoLugarPorDefecto(DialogoGuardarArchivo dialogoGuardarMetodo) {

		File carpetaDefecto = new File(RUTA_METODOS);
		carpetaDefecto.mkdir();

		FileWriter fw = null;
		BufferedWriter bw = null;
		try {

			File archivo = new File(carpetaDefecto.getPath() + "\\"
					+ dialogoGuardarMetodo.getTextFieldNombreArchivo().getText() + "." + EXTENSION_ARCHIVOS);

			if (archivo.exists()) {
				Dialogo dialogoErrorArchivoExistente = new DialogoErrorArchivoExistente();
				dialogoErrorArchivoExistente.crearDialogo();
				dialogoErrorArchivoExistente.mostrarDialogo();

				((DialogoErrorArchivoExistente) dialogoErrorArchivoExistente).getBotonAceptar()
						.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								dialogoErrorArchivoExistente.cerrarDialogo();
							}
						});

			} else {
				
				System.out.println("se creo archivo.met en: " + RUTA_METODOS);
				
				
				String texto = "";
				
				
				fw = new FileWriter(archivo, false);
				bw = new BufferedWriter(fw);
				

				bw.write(texto, 0, texto.length());

				Dialogo dialogoSeGuardoCorrectamente = new DialogoSeGuardoCorrectamente();
				((DialogoSeGuardoCorrectamente) dialogoSeGuardoCorrectamente).crearDialogo();
				dialogoSeGuardoCorrectamente.mostrarDialogo();

				((DialogoSeGuardoCorrectamente) dialogoSeGuardoCorrectamente).getBotonAceptar()
						.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								// TODO Auto-generated method stub
								dialogoSeGuardoCorrectamente.cerrarDialogo();
							}

						});

			}
			if (bw != null) {
				bw.close();
			}

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
