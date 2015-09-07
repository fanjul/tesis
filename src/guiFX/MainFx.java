package guiFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFx extends Application {
	private static final String HERRAMIENTA_TESIS = "HerramientaTesis";
	private static final int VENTANA_WIDTH = 400;
	private static final int VENTANA_HEIGHT = 400;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		AnchorPane cuadroPrincipal = new AnchorPane();
		cuadroPrincipal.setPrefHeight(VENTANA_HEIGHT);
		cuadroPrincipal.setPrefWidth(VENTANA_WIDTH);

		BorderPane ventana = new VentanaPrincipal(VENTANA_HEIGHT, VENTANA_WIDTH, primaryStage);

		cuadroPrincipal.getChildren().add(ventana);

		Scene escenaPrincipal = new Scene(cuadroPrincipal);

		primaryStage.setTitle(HERRAMIENTA_TESIS);
		primaryStage.setScene(escenaPrincipal);
		primaryStage.show();
	}
}