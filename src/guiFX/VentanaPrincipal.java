package guiFX;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class VentanaPrincipal extends BorderPane{
	  
	
	public VentanaPrincipal(Integer alto, Integer ancho){
		super();
		this.setPrefHeight(alto);
		this.setPrefWidth(ancho);
		
		VBox barraMenu = new BarraMenu();
		this.setLeft(((BarraMenu) barraMenu).getBarraDeslizable());
		this.setTop(((BarraMenu) barraMenu).getBarraDeslizable().getBotonMenu());
		
		TextArea editorTexto = new TextArea();
		this.setCenter(editorTexto);
	}
}
