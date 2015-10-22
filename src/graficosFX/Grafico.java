package graficosFX;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

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

		MenuItem menuPDF = new MenuItem(" PDF...");
		menuPDF.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				Grafico.this.guardarGraficoComoPDF();
			}
		});

		menuGuardar.getItems().addAll(menuPNG, menuPDF);

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

				Grafico.this.guardarGraficoLugarPorDefecto(dialogoGuardarGrafico, "png");
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

	private void guardarGraficoComoPDF() {
		Dialogo dialogoGuardarGrafico = new DialogoGuardarGrafico();
		dialogoGuardarGrafico.crearDialogo();
		dialogoGuardarGrafico.mostrarDialogo();

		((DialogoGuardarGrafico) dialogoGuardarGrafico).getBotonGuardar().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Document document = new Document();

				File carpetaDefecto = new File(RUTA_GRAFICOS);
				carpetaDefecto.mkdir();

				File archivo = new File(carpetaDefecto.getPath() + "\\"
						+ ((DialogoGuardarGrafico) dialogoGuardarGrafico).getTextFieldNombreArchivo().getText() + "."
						+ "png");
				// guardar parcialmente en png para despues pasarlo a pdf
				WritableImage imagen = chart.snapshot(new SnapshotParameters(), null);
				try {
					ImageIO.write(SwingFXUtils.fromFXImage(imagen, null), "png", archivo);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				////////////////////

				File destino = new File(carpetaDefecto.getPath() + "\\"
						+ ((DialogoGuardarGrafico) dialogoGuardarGrafico).getTextFieldNombreArchivo().getText() + "."
						+ "pdf");
				String input = archivo.getAbsolutePath();
				String output = destino.getAbsolutePath();
				try {
					FileOutputStream fos = new FileOutputStream(output);
					PdfWriter writer = PdfWriter.getInstance(document, fos);
					writer.open();
					document.open();
					document.add(Image.getInstance(input));
					document.close();
					writer.close();
					archivo.delete();
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
				} catch (Exception e) {
					e.printStackTrace();
				}
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

	private void guardarGraficoLugarPorDefecto(Dialogo dialogoGuardarGrafico, String extension) {
		WritableImage imagen = chart.snapshot(new SnapshotParameters(), null);

		File carpetaDefecto = new File(RUTA_GRAFICOS);
		carpetaDefecto.mkdir();

		File archivo = new File(carpetaDefecto.getPath() + "\\"
				+ ((DialogoGuardarGrafico) dialogoGuardarGrafico).getTextFieldNombreArchivo().getText() + "."
				+ extension);

		try {

			ImageIO.write(SwingFXUtils.fromFXImage(imagen, null), extension, archivo);
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
