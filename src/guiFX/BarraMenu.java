package guiFX;

import java.util.List;

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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class BarraMenu extends VBox {

	private BarraMenuDeslizable barraDeslizable;
	private DropShadow shadow = new DropShadow();
	private FactoryBaseDeDatosGUI factoryBaseDeDatos;
	private AbstractBaseDeDatosGUI baseDeDatos;
	private VentanaPrincipal ventana;
	private FactoryConsultas factoryConsultasDAO;
	private Object consultasDAO;
	private ComboBox<String> comboBoxTablas;
	private AnchorPane centroInferior;
	private HBox centroSuperior;
	
	private ListView listaMetodos; 

	public BarraMenu( VentanaPrincipal ventana) {
		super();
		this.ventana = ventana;
		barraDeslizable = new BarraMenuDeslizable(this);
		VBox.setVgrow(this, Priority.ALWAYS);
		this.agregarMenuAbrirArchivo();
		this.agregarMenuBaseDeDatos();
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
				//TODO hacer para que te deje elegir archivo a abrir
		//		abrirArchivo.onMousePressedProperty();
				

			}
		});

		// Agrego el boton abrir archivo
		this.getChildren().add(botonAbrirArchivo);
	}
	
	
	private void agregarMenuListaMetodosMAtematicos(){
		listaMetodos = new ListView();
		
		
	}
	
	
	
	

//	private static void configurarElegirArchivo(final FileChooser fileChooser) {
//		fileChooser.setTitle("View Pictures");
//		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//	}

	public AbstractBaseDeDatosGUI getBaseDeDatos() {
		return baseDeDatos;
	}

	public BarraMenuDeslizable getBarraDeslizable() {
		return barraDeslizable;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void configurarPanelCentro() {
		centroSuperior = new HBox();
		centroInferior = new AnchorPane();
		centroSuperior.setSpacing(50);
		centroSuperior.setMaxHeight(50);
		centroSuperior.setMinHeight(50);
		centroSuperior.setMaxWidth(750);
		centroSuperior.setMinWidth(750);
		
		ObservableList<String> listaTablas = FXCollections.observableArrayList();
		List<String> listaTodasTablas = new DAO().getAllTables();
		for (String s : listaTodasTablas) { 
			listaTablas.add(s);
		}
		
		Label labelTabla = new Label("Tabla");
		centroSuperior.getChildren().add(labelTabla);
		comboBoxTablas = new ComboBox<String>();
		comboBoxTablas.setItems(listaTablas);
		
		centroInferior.setMinHeight(550);
		centroInferior.setMinWidth(750);
		centroInferior.setMaxHeight(550);

		centroInferior.setMaxWidth(1000);

		comboBoxTablas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue arg0, Object old_val, Object new_val) {
				factoryBaseDeDatos = new FactoryBaseDeDatosGUI();
				factoryConsultasDAO = new FactoryConsultas();
				baseDeDatos = factoryBaseDeDatos.getBaseDeDatos((String)new_val);
				baseDeDatos.crearTablaBaseDeDatos();
				consultasDAO = factoryConsultasDAO.getConsultaDAO((String)new_val);
				baseDeDatos.mostrarTabla(consultasDAO,factoryConsultasDAO,centroInferior);
			}
		});
		
		centroSuperior.getChildren().add(comboBoxTablas);
		centroSuperior.setAlignment(Pos.CENTER);
		
		SplitPane dividirCentro = new SplitPane();
		dividirCentro.setOrientation(Orientation.VERTICAL);
		dividirCentro.getItems().add(centroSuperior);
		dividirCentro.getItems().add(centroInferior);
		dividirCentro.getItems().set(0, centroSuperior);
		dividirCentro.getItems().set(1, centroInferior);

		ObservableList<SplitPane.Divider> dividers = dividirCentro.getDividers();
		for (int i = 0; i < dividers.size(); i++) {
			dividers.get(i).setPosition((i + 1.3) / 10);

		}
		
		ventana.setCenter(dividirCentro);

	}
}
