package guiFX;

import java.io.File;

import org.rosuda.JRI.Rengine;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/** Example of a sidebar that slides in and out of view */
public class SlideOut extends Application {

	// Creo esto para hacer el efecto de sombra en la imagen de abrir archivo
	DropShadow shadow = new DropShadow();

	final TextField texto = new TextField();
	private TableView tabla = new TableView();	
		

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	public void start(final Stage stage) throws Exception {
		stage.setTitle("Slide out YouTube demo");

		// create a WebView to show to the right of the SideBar.

		BorderPane b = new BorderPane();

		///////////////////////////// EMPIEZA Boton Graficar
		ImageView graficar = new ImageView("/imagenesFX/Graficar.png");

		ToggleButton toggleButtonGraficar = new ToggleButton("", graficar);
		Tooltip toolTip = new Tooltip("Abrir Archivo");
		toggleButtonGraficar.setTooltip(toolTip);

		toggleButtonGraficar.setBackground(null);
		toggleButtonGraficar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			final FileChooser fileChooser = new FileChooser();

			@Override
			public void handle(MouseEvent event) {
				graficar.onMousePressedProperty();

				graficar.setEffect(shadow);

				GraficoDeTorta graficoDeTorta = new GraficoDeTorta();
				b.setRight(graficoDeTorta.graficar(tabla));

				event.consume();
			}
		});

		toggleButtonGraficar.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				toggleButtonGraficar.setEffect(shadow);
			}
		});

		toggleButtonGraficar.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				toggleButtonGraficar.setEffect(null);
			}
		});

		b.setBottom(toggleButtonGraficar);
		///////////////////////////// TERMINA Boton Graficar

		

		crearTabla();
		
		b.setLeft(tabla);
		
		b.setCenter(texto);

		// create a sidebar with some content in it.
		final Pane lyricPane = createSidebarContent(stage);
		SideBar sidebar = new SideBar(250, lyricPane);
		VBox.setVgrow(lyricPane, Priority.ALWAYS);

		// layout the scene.
		final BorderPane layout = new BorderPane();
		Pane mainPane = VBoxBuilder.create().spacing(10).children(sidebar.getControlButton(), b).build();
		layout.setLeft(sidebar);
		layout.setCenter(mainPane);

		// show the scene.
		Scene scene = new Scene(layout);
		scene.getStylesheets().add(getClass().getResource("/guiFX/slideout2.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	
	
	
	private void crearTabla() {

	/*	TableColumn nombre = new TableColumn("Nombre");
		TableColumn apellido = new TableColumn("Apellido");
		TableColumn email = new TableColumn("Email");
		tabla.getColumns().addAll(nombre, apellido, email);*/
		

		TableColumn<Persona, String> nombre = new TableColumn<>("Nombre");//La columna es de tipo Persona y el contenido de la columna es de tipo String.
		nombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));//De esta manera es como obtiene los datos la columna, aquí ya hemos ligado el modelo con la columna.
		//Observa como es que el objeto PropertyValueFactory recibe un String con el atributo de la clase Persona al que queremos acceder y obtener.
		
		//Lo mismo para las otras columnas
		TableColumn<Persona, String> apellido = new TableColumn<>("Apellido");
		apellido.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellido"));
		
		TableColumn<Persona, String> edad = new TableColumn<>("Edad");
		edad.setCellValueFactory(new PropertyValueFactory<Persona, String>("edad"));
		
		tabla.getColumns().addAll(nombre, apellido, edad);
				
		
		//Creamos el objeto datos y le agregamos algunos objetos de tipo Persona
		final ObservableList<Persona> datos = FXCollections.observableArrayList(
		              new Persona("Jose 1", "Manuel", "28"),
		              new Persona("Jose 2", "Manuel", "20"),
		              new Persona("Jose 3", "Manuel", "28"));
		
		tabla.setItems(datos);//Agregamos los datos en la tabla, aquí la tabla ya muestra la información.

	}
	
	
	private BorderPane createSidebarContent(Stage stage) {// create some content
															// to put in the
															// sidebar.
		final Text lyric = new Text();
		// lyric.getStyleClass().add("lyric-text");

		final BorderPane lyricPane = new BorderPane();
		lyricPane.setCenter(lyric);

		///////////////////////////// EMPIEZA Boton Abrir Archivo
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

				abrirAbrirArchivo.setEffect(shadow);
				configureFileChooser(fileChooser);
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					ejecutar(file);
				}
				event.consume();
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
		lyricPane.setTop(toggleButtonAbrirArchivo);
		///////////////////////////// TERMINA Boton Abrir Archivo

		///////////////////////////// EMPIEZA Boton Abrir Base de Datos
		ImageView abrirBaseDeDatos = new ImageView("/imagenesFX/AbrirBaseDeDatos.png");
		ToggleButton toggleButtonAbrirBaseDeDatos = new ToggleButton("", abrirBaseDeDatos);
		Tooltip toolTipAbrirBaseDeDatos = new Tooltip("Abrir Base de Datos");
		toggleButtonAbrirBaseDeDatos.setTooltip(toolTipAbrirBaseDeDatos);

		toggleButtonAbrirBaseDeDatos.setBackground(null);
		toggleButtonAbrirBaseDeDatos.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			final FileChooser fileChooser = new FileChooser();

			@Override
			public void handle(MouseEvent event) {
				abrirAbrirArchivo.onMousePressedProperty();

				abrirAbrirArchivo.setEffect(shadow);
				configureFileChooser(fileChooser);
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					ejecutar(file);
				}
				event.consume();
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

		lyricPane.setLeft(toggleButtonAbrirBaseDeDatos);

		///////////////////////////// TERMINA Boton Abrir Base de Datos

		return lyricPane;
	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("View Pictures");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}

	private void ejecutar(File file) {
		Rengine re = new Rengine(new String[] { "--vanilla" }, false, null);

		String dir = agregarCuatroSparadores(file.getAbsolutePath());

		re.eval("source(\"" + dir + "\")");

		String hola = "";
		if (!texto.getText().isEmpty()) {

			// hola = re.eval(texto.getText()+"()").asString();
			hola = re.eval(texto.getText()).asString();
		}

		System.out.println(hola);

	}

	private String agregarCuatroSparadores(String dir) {

		String path = "";
		String[] numerosComoArray = dir.split("\\\\");

		for (int i = 0; i < numerosComoArray.length; i++) {
			if (i != numerosComoArray.length - 1)
				path += numerosComoArray[i] + "\\\\";
			else
				path += numerosComoArray[i];
		}

		return path;
	}
	
	
	

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/** Animates a node on and off screen to the left. */
	class SideBar extends VBox {
		/**
		 * @return a control button to hide and show the sidebar
		 */
		public Button getControlButton() {
			return controlButton;
		}

		private final Button controlButton;

		/**
		 * creates a sidebar containing a vertical alignment of the given nodes
		 */
		SideBar(final double expandedWidth, Node... nodes) {
			getStyleClass().add("sidebar");
			this.setPrefWidth(expandedWidth);
			this.setMinWidth(0);

			// create a bar to hide and show.
			setAlignment(Pos.CENTER);
			getChildren().addAll(nodes);

			// create a button to hide and show the sidebar.
			controlButton = new Button("Cerrar Menú");
			controlButton.getStyleClass().add("hide-left");

			// apply the animations when the button is pressed.
			controlButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					// create an animation to hide sidebar.
					final Animation hideSidebar = new Transition() {
						{
							setCycleDuration(Duration.millis(250));
						}

						protected void interpolate(double frac) {
							final double curWidth = expandedWidth * (1.0 - frac);
							setPrefWidth(curWidth);
							setTranslateX(-expandedWidth + curWidth);
						}
					};
					hideSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent actionEvent) {
							setVisible(false);
							controlButton.setText("Menú");
							controlButton.getStyleClass().remove("hide-left");
							controlButton.getStyleClass().add("show-right");
						}
					});

					// create an animation to show a sidebar.
					final Animation showSidebar = new Transition() {
						{
							setCycleDuration(Duration.millis(250));
						}

						protected void interpolate(double frac) {
							final double curWidth = expandedWidth * frac;
							setPrefWidth(curWidth);
							setTranslateX(-expandedWidth + curWidth);
						}
					};
					showSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent actionEvent) {
							controlButton.setText("Cerrar Menú");
							controlButton.getStyleClass().add("hide-left");
							controlButton.getStyleClass().remove("show-right");
						}
					});

					if (showSidebar.statusProperty().get() == Animation.Status.STOPPED
							&& hideSidebar.statusProperty().get() == Animation.Status.STOPPED) {
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
	}

}