package guiFX;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import baseDatos.hibernate.tablas.Grafico;
import graficosFX.GraficoTorta;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class PanelDerecho extends VBox{
	
	private static PanelDerecho instance = null;
	private TextArea editorTexto;
	//private ToggleButton toggleGraficoTorta;
	private DropShadow shadow = new DropShadow();
	
	private PanelDerecho() {
		super();
		editorTexto = new TextArea();

		
		
		configuracionEditorTexto();
	
	//	this.getChildren().addAll(editorTexto,toggleGraficoTorta); 
		this.getChildren().add(editorTexto);
	}

	public static PanelDerecho getInstance(){
		if(instance == null){
			instance = new PanelDerecho();
		}
		return instance;
	}
	
	public TextArea getEditorTexto() {
		return editorTexto;
	}

	public void agregarElemento(Node obj){
		this.getChildren().add(obj);
	}
	

	// Drag & Drop en el area de texto
	private void configuracionEditorTexto() {

		editorTexto.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (event.getDragboard().hasFiles()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
			}
		});

		editorTexto.setOnDragDropped(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				final Dragboard dragboard = event.getDragboard();
				if (dragboard.hasFiles()) {
					Path file = dragboard.getFiles().get(0).toPath();
					try {
						editorTexto.setText(new String(Files.readAllBytes(file)));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		});

	}
	
	
	
	
	
	
}





