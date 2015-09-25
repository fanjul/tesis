package baseDatos.hibernate.tablas;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Persona")
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	public Persona() {
	}

	@Id
	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private String tipoDocumento;
	private String nroDocumento;
	private String cargo;
	private String observaciones;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String param) {
		this.nombre = param;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String param) {
		this.apellido = param;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String param) {
		this.email = param;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String param) {
		this.tipoDocumento = param;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String param) {
		this.nroDocumento = param;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String param) {
		this.cargo = param;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String param) {
		this.observaciones = param;
	}

}