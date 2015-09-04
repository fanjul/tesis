package guiFX;

import java.awt.Desktop;	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JTextArea;

import org.rosuda.JRI.Rengine;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PruebaConR extends Application {
	
	private Desktop desktop = Desktop.getDesktop();
	private String contenidoArchivo = "";
	final TextField texto = new TextField();
	
	public static void main(String[] args) {
		//Esto se utiliza para ejecutar la aplicación 
		//es como el new Contructor();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		
	//	BorderPane root = new BorderPane();
		Scene escena = new Scene(grid,300,250);
		
		final FileChooser fileChooser = new FileChooser();
		
		///////****** MenuesItems
		MenuItem menuItemAbrirArchivo = new MenuItem("Abrir Archivo");
		menuItemAbrirArchivo.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent event) {
                configureFileChooser(fileChooser);
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    ejecutar(file);
                }            	
            }
        });
		
		////**** Menues
		Menu menuArchivo = new Menu("Archivo");
		menuArchivo.getItems().addAll(menuItemAbrirArchivo);

		//** MenuBar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(menuArchivo);
		
		//pongo la barra de menu arriba
	//	root.setTop(menuBar);
		grid.add(menuBar, 1, 0);
	
		
		texto.setPromptText("Enter your last name.");
		GridPane.setConstraints(texto, 0, 1);
		grid.getChildren().add(texto);
		
		
		
		
		primaryStage.setTitle("Tesiando");
		primaryStage.setScene(escena);
		primaryStage.show();
		
		
	}
	
	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("View Pictures");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}
	

	private void ejecutar(File file) {
		Rengine re = new Rengine(new String[] { "--vanilla" }, false, null);

		String dir = agregarCuatroSparadores(file.getAbsolutePath());
		
		re.eval("source(\"" + dir + "\")");
	
		String hola = "";
		if(!texto.getText().isEmpty()){
			
		//	hola = re.eval(texto.getText()+"()").asString();
			hola = re.eval(texto.getText()).asString();
		}
		
		
		

		
		 System.out.println(hola);
		

	}
	
	private String agregarCuatroSparadores(String dir){
		
		String path = "";
		 String[] numerosComoArray = dir.split("\\\\");
		
		 for (int i = 0; i < numerosComoArray.length; i++) {
			 if(i!=numerosComoArray.length-1)
	            path += numerosComoArray[i]+"\\\\";
			 else path += numerosComoArray[i];
	        }
		 
		return path;
	}
	
}







