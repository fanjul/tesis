package graficosFX;

import guiFX.BotonImagen;
import guiFX.EffectUtilities;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class Grafico {
	private Scene escena;
	private Stage ventana;
	private Group root;

//	private BorderPane b;
	
	public Grafico() {
		ventana = new Stage();
		root = new Group();
	//	b = new BorderPane();
		escena = new Scene(root);
	//	root.getChildren().add(b);
	//	configuracionVentana(escena);
	//	EffectUtilities.configuracionVentana(escena, ventana, b, getClass());
	//	agregarMenuVentana();
	}



	private void configuracionVentana(Scene escena){
		// Transparent scene and stage
		escena.setFill(Color.rgb(34, 44, 44, 0.5));
				getVentana().initStyle(StageStyle.TRANSPARENT);
				ventana.alwaysOnTopProperty();
				Platform.setImplicitExit(true);


				EffectUtilities.makeDraggable(ventana, root);

				Platform.setImplicitExit(true);
				escena.getStylesheets().add(getClass().getResource("/archivosCSS/s1.css").toExternalForm());

			//	cuadroPrincipal.getStyleClass().add("cuadro-principal");

	}
	
	
	/* 
	    
	  private void agregarMenuVentana() {

		BotonImagen botonCerrarVentana = new BotonImagen("/imagenesFX/Cerrar.png", "Cerrar");
		botonCerrarVentana.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ventana.close();
			}
		});

		BotonImagen botonMinimizarVentana = new BotonImagen("/imagenesFX/Minimizar.png", "Minimizar");
		botonMinimizarVentana.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ventana.setIconified(true);
			}
		});

		BotonImagen botonMaximizarVentana = new BotonImagen("/imagenesFX/Maximizar.png", "Maximizar");
		botonMaximizarVentana.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (!ventana.isMaximized()) {
					ventana.setMaximized(true);
				} else {

					ventana.setMaximized(false);
				}
			}
		});

		botonMaximizarVentana.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// botonMaximizarVentana.setEffect(shadow);
				if (!ventana.isMaximized()) {
					botonMaximizarVentana.setTooltip(new Tooltip("Maximizar"));
				} else {
					botonMaximizarVentana.setTooltip(new Tooltip("Minimizar tamanño"));
				}
			}
		});

		
		BorderPane borderPaneMenuOpciones = new BorderPane();
		
		b.setTop(borderPaneMenuOpciones);
		
		HBox hBoxMenuOpcionesVentana = new HBox(3);
		hBoxMenuOpcionesVentana.getChildren().addAll((ToggleButton) botonMinimizarVentana,
				(ToggleButton) botonMaximizarVentana, (ToggleButton) botonCerrarVentana);
		
		borderPaneMenuOpciones.setRight(hBoxMenuOpcionesVentana);

	}
	  */
	
	
	public Scene getEscena() {
		return escena;
	}
	
	public abstract void graficar(TableView tablaResultado);

	public Stage getVentana() {
		return ventana;
	}

	public Group getRoot() {
		return root;
	}
	
}

