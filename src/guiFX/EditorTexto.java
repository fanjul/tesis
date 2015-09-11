package guiFX;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class EditorTexto extends VBox{
	
	private static EditorTexto instance = null;
	private TextArea editorTexto;
	
	
	
	private EditorTexto() {
		super(2);
		editorTexto = new TextArea();
		configuracionEditorTexto();
		this.getChildren().addAll(editorTexto); 
	}

	public static EditorTexto getInstance(){
		if(instance == null){
			instance = new EditorTexto();
		}
		return instance;
	}
	
	public TextArea getEditorTexto() {
		return editorTexto;
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
