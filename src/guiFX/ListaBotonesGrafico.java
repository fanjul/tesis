package guiFX;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class ListaBotonesGrafico extends HBox {
	
	public ListaBotonesGrafico(){
		super();
		this.setMaxHeight(81.5);
		this.setMinHeight(81.5);
		this.setMaxWidth(600);
		this.setMinWidth(600);
		this.setSpacing(30);
	}
	
	public void agregarNodo(Node nodo){
		this.getChildren().add(nodo);
	}

}
