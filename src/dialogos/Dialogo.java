package dialogos;

import guiFX.EffectUtilities;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class Dialogo {

	protected Stage dialogo;
	protected Scene escena; 
	protected VBox vBoxContenedor;
	protected HBox hBoxAbajo;
	protected HBox hBoxArriba ;
	
	public Dialogo(){
		this.dialogo = new Stage();
		this.vBoxContenedor = new VBox(2);
		this.escena = new Scene(vBoxContenedor);
		this.dialogo.setScene(escena);
		this.hBoxArriba = new HBox(2);
		this.hBoxAbajo = new HBox(2);
		this.vBoxContenedor.getChildren().addAll(hBoxArriba,hBoxAbajo);
		configuracionDialogo();
		
	}
	
	private void configuracionDialogo(){
		this.escena.setFill(Color.rgb(34, 44, 44, 0.5));
		this.dialogo.initStyle(StageStyle.TRANSPARENT);
		this.dialogo.initModality(Modality.APPLICATION_MODAL);
		Platform.setImplicitExit(false);
		EffectUtilities.makeDraggable(dialogo, vBoxContenedor);
		this.escena.getStylesheets().add(getClass().getResource("/archivosCSS/s1.css").toExternalForm());
		this.vBoxContenedor.getStyleClass().add("cuadro-principal");
		this.hBoxAbajo.setSpacing(180);
		this.vBoxContenedor.setAlignment(Pos.BASELINE_CENTER);
		this.dialogo.setHeight(100);
		this.dialogo.setWidth(300);
		
		this.vBoxContenedor.setSpacing(20);
		this.hBoxAbajo.setSpacing(40);
		this.vBoxContenedor.setAlignment(Pos.CENTER);
		this.hBoxArriba.setAlignment(Pos.CENTER);
		this.hBoxAbajo.setAlignment(Pos.CENTER);
	}
		
	public void mostrarDialogo(){
		this.dialogo.show();
	};
	
	public void cerrarDialogo(){
		this.dialogo.close();
	}
	
	public abstract void crearDialogo();
	
	
	public Stage getDialogo() {
		return this.dialogo;
	}

	public void setDialogo(Stage dialogo) {
		this.dialogo = dialogo;
	}

	public Scene getEscena() {
		return this.escena;
	}

	public void setEscena(Scene escena) {
		this.escena = escena;
	}

	public VBox getvBoxContenedor() {
		return this.vBoxContenedor;
	}

	public void setvBoxContenedor(VBox vBoxContenedor) {
		this.vBoxContenedor = vBoxContenedor;
	}

	public HBox gethBoxAbajo() {
		return this.hBoxAbajo;
	}

	public void sethBoxAbajo(HBox hBoxAbajo) {
		this.hBoxAbajo = hBoxAbajo;
	}

	public HBox gethBoxArriba() {
		return this.hBoxArriba;
	}

	public void sethBoxArriba(HBox hBoxArriba) {
		this.hBoxArriba = hBoxArriba;
	}


	
	
	
}
