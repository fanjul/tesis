package guiFX.BaseDeDatosGUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import baseDatos.hibernate.consultas.AbstractaConsulta;
import baseDatos.hibernate.consultas.FactoryConsultas;
import baseDatos.hibernate.consultas.PersonaDAO;
import baseDatos.hibernate.tablas.Persona;
import guiFX.PanelDerecho;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

import javafx.scene.layout.AnchorPane;

public class PersonaGUI extends TableView<Persona> implements AbstractBaseDeDatosGUI {
	
	private TableView<Persona> tablaPersona;
	private TableColumn<Persona, Integer> columnaId;
	private TableColumn<Persona, String> columnaNombre;
	private TableColumn<Persona, String> columnaApellido;
	private TableColumn<Persona, String> columnaEmail;
	private TableColumn<Persona, String> columnaTipoDocumento;
	private TableColumn<Persona, String> columnaNroDocumento;
	private TableColumn<Persona, String> columnaCargo;
	private TableColumn<Persona, String> columnaObsevaciones;
	
	private ObservableList<Persona> data; 

	public PersonaGUI() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public void mostrarTabla(){
		tablaPersona = new TableView<Persona>();
		
		columnaId = new TableColumn<Persona, Integer>("Id");
		columnaId.setCellValueFactory(new PropertyValueFactory<Persona,Integer>("id"));
		
		columnaNombre = new TableColumn<Persona, String>("Nombre");
		columnaNombre.setCellValueFactory(new PropertyValueFactory<Persona,String>("nombre"));
		
		columnaApellido = new TableColumn<Persona, String>("Apellido");
		columnaApellido.setCellValueFactory(new PropertyValueFactory<Persona,String>("apellido"));
		
		columnaEmail = new TableColumn<Persona, String>("Email");
		columnaEmail.setCellValueFactory(new PropertyValueFactory<Persona,String>("email"));
		
		columnaCargo = new TableColumn<Persona, String>("Cargo");
		columnaCargo.setCellValueFactory(new PropertyValueFactory<Persona,String>("cargo"));
		
		columnaTipoDocumento = new TableColumn<Persona, String>("Tipo Documento");
		columnaTipoDocumento.setCellValueFactory(new PropertyValueFactory<Persona,String>("tipoDocumento"));
		
		columnaNroDocumento = new TableColumn<Persona, String>("Nro Documento");
		columnaNroDocumento.setCellValueFactory(new PropertyValueFactory<Persona,String>("nroDocumento"));
		
		columnaObsevaciones = new TableColumn<Persona, String>("Observaciones");
		columnaObsevaciones.setCellValueFactory(new PropertyValueFactory<Persona,String>("observaciones"));
		 
		tablaPersona.getColumns().addAll(columnaId,columnaNombre,columnaApellido,columnaEmail,columnaCargo,columnaTipoDocumento,columnaNroDocumento,columnaObsevaciones);
	
		tablaPersona.getStyleClass().add("tablas");
		this.agregarListenerEvent();
	}

	public TableView<Persona> getTablaPersona() {
		return tablaPersona;
	}

	public TableColumn<Persona, Integer> getColumnaId() {
		return columnaId;
	}

	public TableColumn<Persona, String> getColumnaNombre() {
		return columnaNombre;
	}

	public TableColumn<Persona, String> getColumnaApellido() {
		return columnaApellido;
	}

	public TableColumn<Persona, String> getColumnaEmail() {
		return columnaEmail;
	}

	public TableColumn<Persona, String> getColumnaTipoDocumento() {
		return columnaTipoDocumento;
	}

	public TableColumn<Persona, String> getColumnaNroDocumento() {
		return columnaNroDocumento;
	}

	public TableColumn<Persona, String> getColumnaCargo() {
		return columnaCargo;
	}

	public TableColumn<Persona, String> getColumnaObsevaciones() {
		return columnaObsevaciones;
	}

	
	public ObservableList<Persona> getData() {
		return data;
	}

	public void setData(ObservableList<Persona> data) {
		this.data = data;
	}

	@Override
	public void crearTablaBaseDeDatos() {
		this.mostrarTabla();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void mostrarTabla(AbstractaConsulta consulta, FactoryConsultas factoryConsultasDAO,
			AnchorPane centroInferior) {
		
		if(!centroInferior.getChildren().isEmpty()){
			centroInferior.getChildren().remove(0);
		}
		this.setData(FXCollections.observableArrayList());
		List<Persona> lista = (List<Persona>) factoryConsultasDAO.getLista("Persona");
		lista = ((PersonaDAO)consulta).getTodos();
		for (Persona vi : lista) { 
			this.getData().add(vi);

		}
		this.getTablaPersona().setItems(this.getData());
		tablaPersona.setPrefSize(centroInferior.getMaxWidth(), centroInferior.getMaxHeight());
		centroInferior.getChildren().add(0,this.getTablaPersona());			
	}

	private void agregarListenerEvent() {
		// Para que se pueda seleccionar varias rows de la tabla
		tablaPersona.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Drag & Drop de tabla al editor. Si queres pone el cursor donde
		// quieras y arrastras.
		tablaPersona.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent me) {
				final Dragboard db = tablaPersona.startDragAndDrop(TransferMode.COPY);
				final ClipboardContent content = new ClipboardContent();

				String selected = "";
				Set<Persona> selec = new HashSet<Persona>(
						tablaPersona.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();

				for (int i = 0; i < arr.length; i++) {
					selected += (((Persona) arr[i]).getId().toString());
					selected += " ";

				}
				content.putString(selected.toString());

				// content.putString("Drag Me!");
				db.setContent(content);
				me.consume();
			}
		});
		tablaPersona.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(final DragEvent de) {

			}
		});

		tablaPersona.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(final DragEvent de) {
				de.acceptTransferModes(TransferMode.COPY);
				de.consume();
			}
		});

		PanelDerecho.getInstance().getEditorTexto().setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
			}
		});

		PanelDerecho.getInstance().getEditorTexto().setOnDragDropped(new EventHandler<DragEvent>() {
			 
			@Override
			public void handle(DragEvent event) {
				Clipboard clipboard = Clipboard.getSystemClipboard();
				ClipboardContent content = new ClipboardContent();

				String selected = "";
				Set<Persona> selec = new HashSet<Persona>(
						tablaPersona.getSelectionModel().getSelectedItems());
				Object[] arr = selec.toArray();

				for (int i = 0; i < arr.length; i++) {
					selected += (((Persona) arr[i]).getId().toString());
					selected += " ";

				}
				content.putString(selected);

				clipboard.setContent(content);

				PanelDerecho.getInstance().getEditorTexto().insertText(
						PanelDerecho.getInstance().getEditorTexto().getCaretPosition(), clipboard.getString());
			}

		});
	}


}
