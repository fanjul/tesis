package guiFX;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BarraMenuDeslizable extends AnchorPane {
	private static final int EXPANDIR_ANCHO = 100;
	private Button botonMenu;

	public BarraMenuDeslizable(Node nodo) {
		this.setWidth(EXPANDIR_ANCHO);
		this.setMinWidth(0);
	    //this.setAlignment(Pos.CENTER);

		this.getChildren().add(nodo);
		
		  botonMenu = new Button("Menu");

	      // apply the animations when the button is pressed.
	      botonMenu.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent actionEvent) {
	          // create an animation to hide sidebar.
	          final Animation hideSidebar = new Transition() {
	            { setCycleDuration(Duration.millis(250)); }
	            protected void interpolate(double frac) {
	              final double curWidth = EXPANDIR_ANCHO * (1.0 - frac);
	              setPrefWidth(curWidth);
	              setTranslateX(-EXPANDIR_ANCHO + curWidth);
	            }
	          };
	          hideSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent actionEvent) {
	              setVisible(false);
	         
	            }
	          });
	  
	          // create an animation to show a sidebar.
	          final Animation showSidebar = new Transition() {
	            { setCycleDuration(Duration.millis(250)); }
	            protected void interpolate(double frac) {
	              final double curWidth = EXPANDIR_ANCHO * frac;
	              setPrefWidth(curWidth);
	              setTranslateX(-EXPANDIR_ANCHO + curWidth);
	            }
	          };
	          showSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent actionEvent) {
	 
	            }
	          });
	  
	          if (showSidebar.statusProperty().get() == Animation.Status.STOPPED && hideSidebar.statusProperty().get() == Animation.Status.STOPPED) {
	            if (isVisible()) {
	              hideSidebar.play();
	            } else {
	              setVisible(true);
	              showSidebar.play();
	            }
	          }
	        }
	      });
	}
	
	public Button getBotonMenu() {
		return botonMenu;
	}

}
