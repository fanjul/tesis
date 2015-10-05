package main;

import guiFX.EffectUtilities;
import guiFX.VentanaPrincipal;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainFx extends Application {
	private static final String HERRAMIENTA_TESIS = "HerramientaTesis";

	public static void main(String[] args) {
		Application.launch(args);
	}

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle(HERRAMIENTA_TESIS);
		primaryStage.setResizable(false);
		primaryStage.setMaximized(false);

		BorderPane ventana = new VentanaPrincipal(primaryStage);
		//mismos layout para que no se agrande de mas la ventana principal.
		ventana.setMinHeight(728);//minimo alto de la pantalla
		ventana.setMinWidth(1365);//minimo ancho de la pantalla
		ventana.setMaxHeight(728);
		ventana.setMaxWidth(1365);

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

		cuadroPrincipal.getChildren().add(ventana);

		Scene escenaPrincipal = new Scene(cuadroPrincipal);

		// Transparent scene and stage
		EffectUtilities.configuracionVentana(escenaPrincipal, primaryStage, cuadroPrincipal, getClass());
		
        primaryStage.getIcons().add(new Image("/imagenesFX/Icono.png"));
     
        
     //   primaryStage.setTitle("Tesis");
		
//		escenaPrincipal.setFill(Color.rgb(34, 44, 44, 0.5));
//		primaryStage.initStyle(StageStyle.TRANSPARENT);
//		primaryStage.alwaysOnTopProperty();
//		Platform.setImplicitExit(true);
//
//
//		EffectUtilities.makeDraggable(primaryStage, cuadroPrincipal);
//
//		Platform.setImplicitExit(true);
//		escenaPrincipal.getStylesheets().add(getClass().getResource("/archivosCSS/s1.css").toExternalForm());
//
//		
//		EffectUtilities.configuracionVentana(escenaPrincipal, primaryStage, cuadroPrincipal, getClass());
//		
//		cuadroPrincipal.getStyleClass().add("cuadro-principal");

		primaryStage.setScene(escenaPrincipal);
		primaryStage.show();

	}

}
