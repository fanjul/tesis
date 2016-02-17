package guiFX;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import main.Main;

public class ListaBotonesGrafico extends HBox {
	
	public ListaBotonesGrafico(){
		super();
		this.setMaxHeight(((Main.HEIGHT * 2/3) *1/4 ));
		this.setMinHeight(((Main.HEIGHT * 2/3) *1/4 ));
		this.setMaxWidth(((3*Main.WIDTH)/8) - 10);
		this.setMinWidth(((3*Main.WIDTH)/8) - 10);
		this.setSpacing(30);
	}
	
	public void agregarNodo(Node nodo){
		this.getChildren().add(nodo);
	}

}
