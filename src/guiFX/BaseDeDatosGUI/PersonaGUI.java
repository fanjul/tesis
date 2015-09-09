package guiFX.BaseDeDatosGUI;

import baseDatos.hibernate.tablas.Persona;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PersonaGUI extends TableView<Persona> {
	
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

	@SuppressWarnings("unchecked")
	public PersonaGUI() {
		super();
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


}
