package guiFX;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import main.Main;

public class PanelDerecho extends VBox {

	private static PanelDerecho instance = null;
	private TextArea editorTexto;

	private PanelDerecho() {
		super();
		this.setMaxHeight(Main.HEIGHT * 2/3);
		this.setMinHeight(Main.HEIGHT * 2/3);
		this.setMaxWidth((3*Main.WIDTH)* 1/8);
		this.setMinWidth((3*Main.WIDTH)* 1/8);

		editorTexto = new TextArea();
		configuracionEditorTexto();
		this.getChildren().add(editorTexto);
		editorTexto.setMaxHeight((Main.HEIGHT * 2/3) *1/2 );
		editorTexto.setMinHeight((Main.HEIGHT * 2/3) *1/2 );
		editorTexto.setMinWidth(((3*Main.WIDTH)* 1/8) - 10);
		editorTexto.setMaxWidth(((3*Main.WIDTH)* 1/8) - 10);
		setAlignment(Pos.CENTER);

	}

	public static PanelDerecho getInstance() {
		if (instance == null) {
			instance = new PanelDerecho();
		}
		return instance;
	}

	public TextArea getEditorTexto() {
		return editorTexto;
	}

	public void agregarElemento(Node obj) {
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
