package main;

import guiFX.VentanaPrincipal;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
		primaryStage.setResizable(true);
		primaryStage.setMaximized(false);
		
		
		BorderPane ventana = new VentanaPrincipal(0.0, 0.0);

		
		AnchorPane cuadroPrincipal = new AnchorPane();
		cuadroPrincipal.setTopAnchor(ventana, 0.0);
		cuadroPrincipal.setBottomAnchor(ventana, 0.0);
		cuadroPrincipal.setLeftAnchor(ventana, 0.0);
		cuadroPrincipal.setRightAnchor(ventana, 0.0);
		
		
		
		primaryStage.iconifiedProperty().addListener(new ChangeListener<Boolean>() {

		    @Override
		    public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
		        System.out.println("minimized:" + t1.booleanValue());
		    }
		});
		primaryStage.maximizedProperty().addListener(new ChangeListener<Boolean>() {

		    @Override
		    public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
		        System.out.println("maximized:" + t1.booleanValue());
		    }
		});
		

		cuadroPrincipal.getChildren().add(ventana);

		Scene escenaPrincipal = new Scene(cuadroPrincipal);

		escenaPrincipal.getStylesheets().add(getClass().getResource("/archivosCSS/s1.css").toExternalForm()); 
		
		cuadroPrincipal.getStyleClass().add("cuadro-principal");

		
		primaryStage.setScene(escenaPrincipal);
		primaryStage.show();
	}
}