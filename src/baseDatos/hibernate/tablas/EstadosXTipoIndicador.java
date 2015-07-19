package baseDatos.hibernate.tablas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EstadosXTipoIndicador implements Serializable {

	private static final long serialVersionUID = 3669929335334558489L;
	private Integer idTipoIndicador;
	private Integer idEstado;
	private String estado;
	private String representacionCromatica;
	private String observaciones;

	public EstadosXTipoIndicador() {

	}

	@Id
	public Integer getIdTipoIndicador() {
		return idTipoIndicador;
	}

	public void setIdTipoIndicador(Integer idTipoIndicador) {
		this.idTipoIndicador = idTipoIndicador;
	}

	@Id
	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRepresentacionCromatica() {
		return representacionCromatica;
	}

	public void setRepresentacionCromatica(String representacionCromatica) {
		this.representacionCromatica = representacionCromatica;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
