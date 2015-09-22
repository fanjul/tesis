package guiFX;

import java.util.List;

import baseDatos.hibernate.consultas.AbstractaConsulta;
import baseDatos.hibernate.consultas.DAO;
import baseDatos.hibernate.consultas.FactoryConsultas;
import guiFX.BaseDeDatosGUI.AbstractBaseDeDatosGUI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class BarraMenu extends VBox {

	private BarraMenuDeslizable barraDeslizable;
	private FactoryBaseDeDatosGUI factoryBaseDeDatos;
	private AbstractBaseDeDatosGUI baseDeDatos;
	private VentanaPrincipal ventana;
	private FactoryConsultas factoryConsultasDAO;
	private AbstractaConsulta consultasDAO;
	private ComboBox<String> comboBoxTablas;

	private ListView<String> listaMetodos;

	public BarraMenu(VentanaPrincipal ventana) {
		super();
		this.ventana = ventana;
		barraDeslizable = new BarraMenuDeslizable(this);
		VBox.setVgrow(this, Priority.ALWAYS);
		this.agregarMenuAbrirArchivo();
		this.agregarMenuBaseDeDatos();
		// this.agregarMenuListaMetodosMatematicos();
	}

	private void agregarMenuBaseDeDatos() {

		BotonImagen botonAbrirBaseDatos = new BotonImagen("/imagenesFX/AbrirBaseDeDatos.png", "Abrir Base de Datos");

		botonAbrirBaseDatos.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				configurarPanelCentro();
			}
		});

		this.getChildren().add(botonAbrirBaseDatos);

	}

	private void agregarMenuAbrirArchivo() {

		BotonImagen botonAbrirArchivo = new BotonImagen("/imagenesFX/AbrirArchivo.png", "Abrir Archivo");

		botonAbrirArchivo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO hacer para que te deje elegir archivo a abrir
				// abrirArchivo.onMousePressedProperty();

			}
		});

		// Agrego el boton abrir archivo
		this.getChildren().add(botonAbrirArchivo);
	}

	public void inicializarListaMetodosMatematicos(ObservableList<String> lista) {
		listaMetodos = new ListView<String>(lista);
		listaMetodos.setMaxHeight(350);
		listaMetodos.setMinHeight(0);
		listaMetodos.setMaxWidth(120);
		listaMetodos.setMinWidth(0);
		this.getChildren().add(listaMetodos);

	}

	public ListView<String> getListaMetodos() {
		return listaMetodos;
	}

	/*
	 * private static void configurarElegirArchivo(final FileChooser
	 * fileChooser) { fileChooser.setTitle("View Pictures");
	 * fileChooser.setInitialDirectory(new
	 * File(System.getProperty("user.home"))); }
	 */
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
		
		//contiene nombre de las tablas
		centroSuperior.setSpacing(50);
		centroSuperior.setMaxHeight(50);
		centroSuperior.setMinHeight(50);
		centroSuperior.setMaxWidth(765);
		centroSuperior.setMinWidth(645);

		ObservableList<String> data = FXCollections.observableArrayList();
		List<String> listaTodasTablas = new DAO().getAllTables();
		for (String s : listaTodasTablas) {
			data.add(s);
		}

		Label labelTabla = new Label("Tabla");
		centroSuperior.getChildren().add(labelTabla);
		comboBoxTablas = new ComboBox<String>();
		comboBoxTablas.setItems(data);

		//contiene la tabla
		centroCentro.setMinHeight(247);
		centroCentro.setMaxHeight(247);
		centroCentro.setMaxWidth(765);
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
		
		//contiene resultado R
		centroInferior.setMinHeight(246);
		centroInferior.setMaxHeight(246);
		centroInferior.setMinWidth(645);
		centroInferior.setMaxWidth(765);
		
		//SplitPane, una para tablas y la otra resultados R
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
		
		//SplitPane, uno con comboBox de las tablas y otro con el splitPane de Tabla con resultados R
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
