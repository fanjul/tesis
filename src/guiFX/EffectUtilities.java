package guiFX;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class EffectUtilities {
	  /** makes a stage draggable using a given node */
	  public static void makeDraggable(final Stage stage, final Node byNode) {
	    final Delta dragDelta = new Delta();
	    byNode.setOnMousePressed(new EventHandler<MouseEvent>() {
	      @Override public void handle(MouseEvent mouseEvent) {
	        // record a delta distance for the drag and drop operation.
	        dragDelta.x = stage.getX() - mouseEvent.getScreenX();
	        dragDelta.y = stage.getY() - mouseEvent.getScreenY();
	        byNode.setCursor(Cursor.MOVE);
	      }
	    });
	    byNode.setOnMouseReleased(new EventHandler<MouseEvent>() {
	      @Override public void handle(MouseEvent mouseEvent) {
	        byNode.setCursor(Cursor.HAND);
	      }
	    });
	    byNode.setOnMouseDragged(new EventHandler<MouseEvent>() {
	      @Override public void handle(MouseEvent mouseEvent) {
	        stage.setX(mouseEvent.getScreenX() + dragDelta.x);
	        stage.setY(mouseEvent.getScreenY() + dragDelta.y);
	      }
	    });
	    byNode.setOnMouseEntered(new EventHandler<MouseEvent>() {
	      @Override public void handle(MouseEvent mouseEvent) {
	        if (!mouseEvent.isPrimaryButtonDown()) {
	          byNode.setCursor(Cursor.HAND);
	        }
	      }
	    });
	    byNode.setOnMouseExited(new EventHandler<MouseEvent>() {
	      @Override public void handle(MouseEvent mouseEvent) {
	        if (!mouseEvent.isPrimaryButtonDown()) {
	          byNode.setCursor(Cursor.DEFAULT);
	        }
	      }
	    });
	  }

	  /** records relative x and y co-ordinates. */
	  private static class Delta {
	    double x, y;
	  }
	  
	  public static void configuracionVentana(final Scene escena, final Stage stage, final Node nodo, Class<?> clase){
		  escena.setFill(Color.rgb(34, 44, 44, 0.5));
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.alwaysOnTopProperty();
			Platform.setImplicitExit(true);


			EffectUtilities.makeDraggable(stage, nodo);

			Platform.setImplicitExit(true);
			escena.getStylesheets().add(clase.getResource("/archivosCSS/s1.css").toExternalForm());
			nodo.getStyleClass().add("cuadro-principal");

		//	cuadroPrincipal.getStyleClass().add("cuadro-principal");

	  }
	  
	}
