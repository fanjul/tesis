package main;

import guiFX.EffectUtilities;
import guiFX.VentanaPrincipal;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class MainFx extends Application {

	// TODO cambiar el CSS de los graficos
	// TODO guardar los datos que se hicieron drag and drop si es de fecha e
	// indicador para despues mostrar
	// en los graficos nuestros
	// TODO predefinir una "variable" para R, para mostra en el grafico un
	// parrafo informativo que arme el usuario
	
	private static final String HERRAMIENTA_TESIS = "HerramientaTesis";
	public static Double WIDTH;
	public static Double HEIGHT;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle(HERRAMIENTA_TESIS);
		primaryStage.setResizable(false);

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	
		primaryStage.setX(primaryScreenBounds.getMinX());
		primaryStage.setY(primaryScreenBounds.getMinY());
		primaryStage.setWidth(primaryScreenBounds.getWidth());
		primaryStage.setHeight(primaryScreenBounds.getHeight());
		

		
		WIDTH = primaryStage.getWidth();
		HEIGHT = primaryStage.getHeight();
		
		BorderPane ventana = new VentanaPrincipal(primaryStage);

		ventana.setPrefHeight(HEIGHT);// minimo alto de la pantalla
		ventana.setPrefWidth(WIDTH);// minimo ancho de la pantalla

		AnchorPane cuadroPrincipal = new AnchorPane();
		cuadroPrincipal.setTopAnchor(ventana, 0.0);
		cuadroPrincipal.setBottomAnchor(ventana, 0.0);
		cuadroPrincipal.setLeftAnchor(ventana, 0.0);
		cuadroPrincipal.setRightAnchor(ventana, 0.0);

		primaryStage.iconifiedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
			}
		});
		primaryStage.maximizedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
			}
		});

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});

		cuadroPrincipal.getChildren().add(ventana);

		Scene escenaPrincipal = new Scene(cuadroPrincipal);

		// Transparent scene and stage
		escenaPrincipal.setFill(Color.rgb(34, 44, 44, 0.5));
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		Platform.setImplicitExit(true);

		EffectUtilities.makeDraggable(primaryStage, cuadroPrincipal);

		Platform.setImplicitExit(true);
		escenaPrincipal.getStylesheets().add(getClass().getResource("/archivosCSS/s1.css").toExternalForm());

		cuadroPrincipal.getStyleClass().add("cuadro-principal");

		primaryStage.setScene(escenaPrincipal);
		primaryStage.show();
	}

}
