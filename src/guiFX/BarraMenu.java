package guiFX;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.IndicadorDAO;
import dialogos.Dialogo;
import dialogos.DialogoErrorArchivoExistente;
import guiFX.BaseDeDatosGUI.AbstractBaseDeDatosGUI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Stage;

public class BarraMenu extends VBox {

	static final String RUTA_METODOS = System.getProperty("user.dir") + "\\" + "Metodos Matematicos";
	static final String EXTENSION_ARCHIVOS = "met";

	private BarraMenuDeslizable barraDeslizable;
	private FactoryBaseDeDatosGUI factoryBaseDeDatos;
	private AbstractBaseDeDatosGUI baseDeDatos;
	private VentanaPrincipal ventana;
	private FactoryConsultas factoryConsultasDAO;
	private Object consultasDAO;
	private ComboBox<String> comboBoxTablas;
	private ListaMetodos listaMetodos;

	private BotonImagen botonAbrirArchivo;
	private BotonImagen botonAbrirBaseDatos;
	private PanelDerecho panelDerecho;
	private Stage stage;

	public BarraMenu(VentanaPrincipal ventana, PanelDerecho panelDerecho, Stage stage) {
		super();
		this.ventana = ventana;
		this.panelDerecho = panelDerecho;
		this.stage = stage;
		barraDeslizable = new BarraMenuDeslizable(this);
		VBox.setVgrow(this, Priority.ALWAYS);
		this.agregarMenuAbrirArchivo();
		this.agregarMenuBaseDeDatos();
		// this.agregarMenuListaMetodosMatematicos();
	}

	private void agregarMenuBaseDeDatos() {

		botonAbrirBaseDatos = new BotonImagen("/imagenesFX/ConectarBaseDatos.png", "Conectar Base de Datos");
		botonAbrirBaseDatos.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				configurarPanelCentro();
			}
		});
		this.getChildren().add(botonAbrirBaseDatos);
	}

	private void agregarMenuAbrirArchivo() {

		botonAbrirArchivo = new BotonImagen("/imagenesFX/AbrirArchivo.png", "Abrir Archivo");
		botonAbrirArchivo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO hacer para que te deje elegir archivo a abrir
				// abrirArchivo.onMousePressedProperty();

				FileChooser fileChooser = new FileChooser();

				// Set extension filter
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
				fileChooser.getExtensionFilters().add(extFilter);

				// Show save file dialog
				File archivo = fileChooser.showOpenDialog(stage);
				if (archivo != null) {
					guardarNuevoMetodoMatematicoLugarPorDefecto(archivo.getName().substring(0, archivo.getName().lastIndexOf(".")));
					actualizarListaMetodos();
					seleccionarNuevoMetodo(archivo.getName().substring(0, archivo.getName().lastIndexOf(".")));

					copiarContenidoArchivoEnEditorTexto(archivo);
				}

			}
		});
		// Agrego el boton abrir archivo
		this.getChildren().add(botonAbrirArchivo);
	}

	private void copiarContenidoArchivoEnEditorTexto(File archivo) {

		try {
			// panelDerecho.getEditorTexto().setDisable(false);
			panelDerecho.getEditorTexto().clear();
			panelDerecho.getEditorTexto().setText(getContenidoArchivo(archivo));

		} catch (Exception ex) {
			System.out.println("errooorrr");
		}
	}

	private String getContenidoArchivo(File archivo) {
		try {
			return FileUtils.readFileToString(archivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	private void actualizarListaMetodos() {
		listaMetodos.setItems(getMetodosMatematicosYaCreados());
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

	private void guardarNuevoMetodoMatematicoLugarPorDefecto(String nombreArchivo) {

		File carpetaDefecto = new File(RUTA_METODOS);
		carpetaDefecto.mkdir();

		FileWriter fw = null;
		BufferedWriter bw = null;
		try {

			File archivo = new File(carpetaDefecto.getPath() + "\\" + nombreArchivo + "." + EXTENSION_ARCHIVOS);

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

			}
			if (bw != null) {
				bw.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void seleccionarNuevoMetodo(String nombreArchivo) {

		ObservableList<String> elementosLista = listaMetodos.getItems();
		int pos = 0;
		while (!nombreArchivo.equals(elementosLista.get(pos))) {
			pos++;
		}
		listaMetodos.getSelectionModel().select(pos);
	}

	public BotonImagen getBotonAbrirArchivo() {
		return botonAbrirArchivo;
	}

	public BotonImagen getBotonAbrirBaseDatos() {
		return botonAbrirBaseDatos;
	}

	public void inicializarListaMetodosMatematicos(ObservableList<String> lista) {
		listaMetodos = new ListaMetodos(lista);
		listaMetodos.setMaxHeight(350);
		listaMetodos.setMinHeight(0);
		listaMetodos.setMaxWidth(120);
		listaMetodos.setMinWidth(0);

		this.getChildren().add(listaMetodos);
	}

	// private static void configurarElegirArchivo(final FileChooser
	// fileChooser) {
	// fileChooser.setTitle("View Pictures");
	// fileChooser.setInitialDirectory(new
	// File(System.getProperty("user.home")));
	// }

	public ListView<String> getListaMetodos() {
		return listaMetodos;
	}

	public AbstractBaseDeDatosGUI getBaseDeDatos() {
		return baseDeDatos;
	}

	public BarraMenuDeslizable getBarraDeslizable() {
		return barraDeslizable;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void configurarPanelCentro() {
		HBox centroSuperior = new HBox();
		SplitPane centroSplit = new SplitPane();
		AnchorPane centroCentro = new AnchorPane();
		AnchorPane centroInferior = new AnchorPane();
		SplitPane dividirCentro = new SplitPane();

		// contiene nombre de las tablas
		centroSuperior.setSpacing(50);
		centroSuperior.setMaxHeight(50);
		centroSuperior.setMinHeight(50);
		centroSuperior.setMaxWidth(765);
		centroSuperior.setMinWidth(645);

		ObservableList<String> data = FXCollections.observableArrayList();

		List<String> listaTodasTablas = new IndicadorDAO().getAllTables(); // VER
		for (String s : listaTodasTablas) {
			data.add(s);

		}

		Label labelTabla = new Label("Tabla");
		centroSuperior.getChildren().add(labelTabla);
		comboBoxTablas = new ComboBox<String>();
		comboBoxTablas.setItems(data);

		// contiene la tabla
		centroCentro.setMinHeight(247);
		centroCentro.setMaxHeight(247);

		centroCentro.setMaxWidth(645);
		centroCentro.setMinWidth(645);

		comboBoxTablas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue arg0, Object old_val, Object new_val) {
				factoryBaseDeDatos = new FactoryBaseDeDatosGUI();
				factoryConsultasDAO = new FactoryConsultas();
				baseDeDatos = factoryBaseDeDatos.getBaseDeDatos((String) new_val);
				baseDeDatos.crearTablaBaseDeDatos();
				consultasDAO = factoryConsultasDAO.getConsultaDAO((String) new_val);
				baseDeDatos.mostrarTabla(consultasDAO, factoryConsultasDAO, centroCentro);
			}
		});

		centroSuperior.getChildren().add(comboBoxTablas);
		centroSuperior.setAlignment(Pos.CENTER);

		// contiene resultado R
		centroInferior.setMinHeight(246);
		centroInferior.setMaxHeight(246);
		centroInferior.setMinWidth(645);
		centroInferior.setMaxWidth(765);

		// SplitPane, una para tablas y la otra resultados R
		centroSplit.setOrientation(Orientation.VERTICAL);
		centroSplit.setMaxHeight(493);
		centroSplit.setMinHeight(493);
		centroSplit.setMaxWidth(765);
		centroSplit.setMinWidth(645);

		centroSplit.getItems().add(centroCentro);
		centroSplit.getItems().add(centroInferior);
		centroSplit.getItems().set(0, centroCentro);
		centroSplit.getItems().set(1, centroInferior);

		ObservableList<SplitPane.Divider> dividers = centroSplit.getDividers();
		for (int i = 0; i < dividers.size(); i++) {
			dividers.get(i).setPosition((i + 1.3) / 10);

		}

		// SplitPane, uno con comboBox de las tablas y otro con el splitPane de
		// Tabla con resultados R
		dividirCentro.setMaxHeight(543);
		dividirCentro.setMinHeight(543);
		dividirCentro.setMaxWidth(765);
		dividirCentro.setMinWidth(645);

		dividirCentro.setOrientation(Orientation.VERTICAL);
		dividirCentro.getItems().add(centroSuperior);
		dividirCentro.getItems().add(centroSplit);
		dividirCentro.getItems().set(0, centroSuperior);
		dividirCentro.getItems().set(1, centroSplit);

		ObservableList<SplitPane.Divider> dividers2 = dividirCentro.getDividers();
		for (int i = 0; i < dividers2.size(); i++) {
			dividers2.get(i).setPosition((i + 1.3) / 10);

		}

		ventana.setCenter(dividirCentro);

	}

}
