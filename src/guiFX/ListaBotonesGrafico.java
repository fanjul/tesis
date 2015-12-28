package guiFX;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import main.MainFx;

public class ListaBotonesGrafico extends HBox {
	
	public ListaBotonesGrafico(){
		super();
		this.setMaxHeight(((MainFx.HEIGHT * 2/3) *1/4 ));
		this.setMinHeight(((MainFx.HEIGHT * 2/3) *1/4 ));
		this.setMaxWidth(((3*MainFx.WIDTH)/8) - 10);
		this.setMinWidth(((3*MainFx.WIDTH)/8) - 10);
		this.setSpacing(30);
	}
	
	public void agregarNodo(Node nodo){
		this.getChildren().add(nodo);
	}

}
