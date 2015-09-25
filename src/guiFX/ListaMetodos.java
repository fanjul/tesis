package guiFX;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import dialogos.Dialogo;
import dialogos.DialogoEstaSeguroDeEliminar;
import dialogos.DialogoGuardarArchivo;
import dialogos.DialogoRenombrarArchivo;
import dialogos.DialogoSeRenombroCorrectamente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ListaMetodos extends ListView<String> {
	static final String RUTA_METODOS = System.getProperty("user.dir") + "\\" + "Metodos Matematicos";
	static final String EXTENSION_ARCHIVOS = "met";

	public ListaMetodos(ObservableList<String> lista) {
		super(lista);
		crearBotonDerecho();
	}

	private void crearBotonDerecho() {
		final ContextMenu cm = new ContextMenu();

		cm.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.SECONDARY) {
					System.out.println("consuming right release button in cm filter");
					event.consume();
				}
			}
		});
		cm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// System.out.println("right gets consumed so this must be left
				// on "+
				// ((MenuItem)event.getTarget()).getText());
				if (((MenuItem) event.getTarget()).getText().equalsIgnoreCase("Renombrar")) {
					// System.out.println("Renombrar arch");
					renombrarArchivo();
			
				
				} else {
					if (((MenuItem) event.getTarget()).getText().equalsIgnoreCase("Eliminar")) {
						// System.out.println("Eliminar arch");
						Dialogo dialogoEstaSeguroDeEliminar = new DialogoEstaSeguroDeEliminar();
						dialogoEstaSeguroDeEliminar.crearDialogo();
						dialogoEstaSeguroDeEliminar.mostrarDialogo();
						((DialogoEstaSeguroDeEliminar) dialogoEstaSeguroDeEliminar).getBotonAceptar()
								.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								eliminarArchivo();
								dialogoEstaSeguroDeEliminar.cerrarDialogo();
							}
						});

						((DialogoEstaSeguroDeEliminar) dialogoEstaSeguroDeEliminar).getBotonCancelar()
								.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								dialogoEstaSeguroDeEliminar.cerrarDialogo();
							}
						});
						
						
						
					}
				}

			}
		});

		MenuItem menuItemRenombrar = new MenuItem("Renombrar");
		MenuItem menuItemEliminar = new MenuItem("Eliminar");
		// MenuItem menuItem3 = new MenuItem("line 3");

		cm.getItems().addAll(menuItemRenombrar, menuItemEliminar);

		this.setContextMenu(cm);
	}

	private void renombrarArchivo() {
		Dialogo dialogoRenombrarArchivo = new DialogoRenombrarArchivo();
		dialogoRenombrarArchivo.crearDialogo();
		dialogoRenombrarArchivo.mostrarDialogo();

		((DialogoRenombrarArchivo) dialogoRenombrarArchivo).getBotonAceptar()
				.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						File archivoARenombrar = new File(
								RUTA_METODOS + "\\" + getSeleccionado() + "." + EXTENSION_ARCHIVOS);

						if (!((DialogoRenombrarArchivo) dialogoRenombrarArchivo).getTextFieldNombreArchivo().getText()
								.equals("")) {
							File nuevoNombreArchivo = new File(
									RUTA_METODOS + "\\" + ((DialogoRenombrarArchivo) dialogoRenombrarArchivo)
											.getTextFieldNombreArchivo().getText() + "." + EXTENSION_ARCHIVOS);
							boolean correcto = archivoARenombrar.renameTo(nuevoNombreArchivo);
							
							actualizarListaMetodos();
							
							if (correcto) {
								System.out.println("El renombrado ha sido correcto");
								dialogoRenombrarArchivo.cerrarDialogo();

								@SuppressWarnings("unused")
								Dialogo dialogoSeRenombroCorrectamente = new DialogoSeRenombroCorrectamente();
								dialogoSeRenombroCorrectamente.crearDialogo();
								dialogoSeRenombroCorrectamente.mostrarDialogo();
								((DialogoSeRenombroCorrectamente) dialogoSeRenombroCorrectamente).getBotonAceptar()
										.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										dialogoSeRenombroCorrectamente.cerrarDialogo();
									}
								});

							} else {
								System.out.println("El renombrado no se ha podido realizar");
							}
						}

					}

				});

		((DialogoRenombrarArchivo) dialogoRenombrarArchivo).getBotonCancelar()
				.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						dialogoRenombrarArchivo.cerrarDialogo();
					}
				});

		//this.setItems(getMetodosMatematicosYaCreados());
	}

	
	
	
	private void eliminarArchivo() {
		File archivoAEliminar = new File(RUTA_METODOS + "\\" + getSeleccionado() + "." + EXTENSION_ARCHIVOS);
		if (archivoAEliminar.exists()) {
			archivoAEliminar.delete();
		}
		actualizarListaMetodos();
	}

	private ObservableList<String> getMetodosMatematicosYaCreados() {
		File carpetaDefecto = new File(RUTA_METODOS);
		ObservableList<String> listaMetodos = FXCollections.observableArrayList();

		if (carpetaDefecto.exists()) {
			File[] archivos = carpetaDefecto.listFiles();
			for (File archivo : archivos) {
				String extension = FilenameUtils.getExtension(archivo.getPath());
				if (extension.equals(EXTENSION_ARCHIVOS)) {
					listaMetodos.add(
							archivo.getName().substring(0, archivo.getName().lastIndexOf("." + EXTENSION_ARCHIVOS)));
				}
			}
		}

		return listaMetodos;
	}

	private String getSeleccionado() {
		return this.getSelectionModel().getSelectedItem().toString();
	}
	
	private void actualizarListaMetodos(){
		this.setItems(getMetodosMatematicosYaCreados());
	}

}
