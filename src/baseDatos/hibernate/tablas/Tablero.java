package baseDatos.hibernate.tablas;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import baseDatos.hibernate.tablas.Indicador;
import baseDatos.hibernate.tablas.Persona;

@Entity
@Table(name = "Tablero")
public class Tablero implements Serializable {

	private static final long serialVersionUID = 1L;

	public Tablero() {
	}

	@Id
	private Integer id;
	private String nombre;
	private Integer idIndicador;
	private Integer idResponsable;
	private Timestamp fechaUltimaActualizacion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idIndicador", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
	private Indicador indicador;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idResponsable", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
	private Persona persona;

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

	public Integer getIdIndicador() {
		return idIndicador;
	}

	public void setIdIndicador(Integer param) {
		this.idIndicador = param;
	}

	public Integer getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(Integer param) {
		this.idResponsable = param;
	}

	public Timestamp getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Timestamp param) {
		this.fechaUltimaActualizacion = param;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador param) {
		this.indicador = param;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona param) {
		this.persona = param;
	}

}