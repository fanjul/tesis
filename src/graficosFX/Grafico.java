package graficosFX;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dialogos.Dialogo;
import dialogos.DialogoGuardarGrafico;
import dialogos.DialogoSeGuardoCorrectamente;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.Chart;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class Grafico {

	static final String RUTA_GRAFICOS = System.getProperty("user.dir") + "\\" + "Graficos Guardados";

	private Stage ventana;
	private Group root;
	protected Chart chart;

	public Grafico() {
		ventana = new Stage();
		root = new Group();
		this.agregarMenu();
	}

	public abstract void graficar(TableView<String> tablaResultado);

	public Stage getVentana() {
		return ventana;
	}

	public Group getRoot() {
		return root;
	}

	private void agregarMenu() {
		SplitPane dividirPanel = new SplitPane();
		dividirPanel.setOrientation(Orientation.VERTICAL);

		MenuBar menuBar = new MenuBar();
		Menu menuGuardar = new Menu("Guardar como");

		MenuItem menuPNG = new MenuItem(" PNG...");
		menuPNG.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				Grafico.this.guardarGraficoComoPNG();
			}
		});

		menuGuardar.getItems().add(menuPNG);

		menuBar.getMenus().add(menuGuardar);

		VBox barra = new VBox(this.ventana.getWidth(), menuBar);
		dividirPanel.getItems().add(barra);
		dividirPanel.getItems().add(root);
		Scene scene = new Scene(dividirPanel);

		this.ventana.setScene(scene);
	}

	private void guardarGraficoComoPNG() {

		Dialogo dialogoGuardarGrafico = new DialogoGuardarGrafico();
		dialogoGuardarGrafico.crearDialogo();
		dialogoGuardarGrafico.mostrarDialogo();

		((DialogoGuardarGrafico) dialogoGuardarGrafico).getBotonGuardar().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Grafico.this.guardarGraficoLugarPorDefecto(((DialogoGuardarGrafico) dialogoGuardarGrafico));
				dialogoGuardarGrafico.cerrarDialogo();

			}
		});

		((DialogoGuardarGrafico) dialogoGuardarGrafico).getBotonCancelar().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialogoGuardarGrafico.cerrarDialogo();
			}
		});

	}

	private void guardarGraficoLugarPorDefecto(DialogoGuardarGrafico dialogoGuardarGrafico) {
		WritableImage imagen = chart.snapshot(new SnapshotParameters(), null);

		File carpetaDefecto = new File(RUTA_GRAFICOS);
		carpetaDefecto.mkdir();

		File archivo = new File(carpetaDefecto.getPath() + "\\"
				+ ((DialogoGuardarGrafico) dialogoGuardarGrafico).getTextFieldNombreArchivo().getText() + "." + "png");

		try {

			ImageIO.write(SwingFXUtils.fromFXImage(imagen, null), "png", archivo);
			Dialogo dialogoSeGuardoCorrectamente = new DialogoSeGuardoCorrectamente();
			((DialogoSeGuardoCorrectamente) dialogoSeGuardoCorrectamente).crearDialogo();
			dialogoSeGuardoCorrectamente.mostrarDialogo();

			((DialogoSeGuardoCorrectamente) dialogoSeGuardoCorrectamente).getBotonAceptar()
					.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							dialogoSeGuardoCorrectamente.cerrarDialogo();
						}

					});

		} catch (IOException e) {

		}

	}

}
