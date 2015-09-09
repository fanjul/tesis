package main;

import guiFX.VentanaPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
		primaryStage.setMaximized(true);

		BorderPane ventana = new VentanaPrincipal(0.0, 0.0);

		AnchorPane cuadroPrincipal = new AnchorPane();
		cuadroPrincipal.setTopAnchor(ventana, 0.0);
		cuadroPrincipal.setBottomAnchor(ventana, 0.0);
		cuadroPrincipal.setLeftAnchor(ventana, 0.0);
		cuadroPrincipal.setRightAnchor(ventana, 0.0);

		cuadroPrincipal.getChildren().add(ventana);

		Scene escenaPrincipal = new Scene(cuadroPrincipal);

		primaryStage.setScene(escenaPrincipal);
		primaryStage.show();
	}
}