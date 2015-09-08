package guiFX;

import java.io.File;
import java.util.List;

import baseDatos.hibernate.consultas.ValorIndicadorDAO;
import baseDatos.hibernate.tablas.ValorIndicador;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class BarraMenu extends VBox {

	private BarraMenuDeslizable barraDeslizable;
	private DropShadow shadow = new DropShadow();
	private final TextField texto = new TextField();
	private BaseDeDatos baseDeDatos;
	private VentanaPrincipal ventana;

	public BarraMenu( VentanaPrincipal ventana) {
		super();
		this.ventana = ventana;
		barraDeslizable = new BarraMenuDeslizable(this);
		VBox.setVgrow(this, Priority.ALWAYS);
		this.configurarMenuAbrirArchivo();
		this.configurarMenuBaseDeDatos();
	}

	private void configurarMenuBaseDeDatos() {
		ImageView abrirBaseDeDatos = new ImageView("/imagenesFX/AbrirBaseDeDatos.png");
		ToggleButton toggleButtonAbrirBaseDeDatos = new ToggleButton("", abrirBaseDeDatos);
		Tooltip toolTipAbrirBaseDeDatos = new Tooltip("Abrir Base de Datos");
		toggleButtonAbrirBaseDeDatos.setTooltip(toolTipAbrirBaseDeDatos);

		toggleButtonAbrirBaseDeDatos.setBackground(null);
		toggleButtonAbrirBaseDeDatos.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			final FileChooser fileChooser = new FileChooser();

			@Override
			public void handle(MouseEvent event) {
				baseDeDatos = new BaseDeDatos();
				baseDeDatos.setData(FXCollections.observableArrayList());

				// Prueba.main(null);
				// ValorIndicador valor = new ValorIndicador();
				// ValorIndicadorDAO asd = new ValorIndicadorDAO();
				// valor.setIdIndicador(11);
				// valor.setFecha(new Timestamp(0));
				// asd.guardar(valor);
				List<ValorIndicador> lista = new ValorIndicadorDAO().getTodos();
				for (ValorIndicador vi : lista) {
					baseDeDatos.getData().add(vi);

				}
				baseDeDatos.getTablaValorIndicador().setItems(baseDeDatos.getData());
				ventana.setCenter(baseDeDatos.getTablaValorIndicador());
			}
		});

		toggleButtonAbrirBaseDeDatos.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				toggleButtonAbrirBaseDeDatos.setEffect(shadow);
			}
		});

		toggleButtonAbrirBaseDeDatos.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				toggleButtonAbrirBaseDeDatos.setEffect(null);
			}
		});

		this.getChildren().add(toggleButtonAbrirBaseDeDatos);

	}

	private void configurarMenuAbrirArchivo() {

		ImageView abrirAbrirArchivo = new ImageView("/imagenesFX/AbrirArchivo.png");
		ToggleButton toggleButtonAbrirArchivo = new ToggleButton("", abrirAbrirArchivo);
		Tooltip toolTip = new Tooltip("Abrir Archivo");
		toggleButtonAbrirArchivo.setTooltip(toolTip);

		toggleButtonAbrirArchivo.setBackground(null);
		toggleButtonAbrirArchivo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			final FileChooser fileChooser = new FileChooser();

			@Override
			public void handle(MouseEvent event) {
				abrirAbrirArchivo.onMousePressedProperty();

			}
		});

		toggleButtonAbrirArchivo.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				toggleButtonAbrirArchivo.setEffect(shadow);
			}
		});

		toggleButtonAbrirArchivo.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				toggleButtonAbrirArchivo.setEffect(null);
			}
		});
		// Agrego el boton abrir archivo
		this.getChildren().add(toggleButtonAbrirArchivo);
	}

	private static void configurarElegirArchivo(final FileChooser fileChooser) {
		fileChooser.setTitle("View Pictures");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}

	public BaseDeDatos getBaseDeDatos() {
		return baseDeDatos;
	}

	public BarraMenuDeslizable getBarraDeslizable() {
		return barraDeslizable;
	}
}
