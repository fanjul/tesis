package baseDatos.hibernate.tablas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import baseDatos.hibernate.tablas.primaryKey.EstadosXTipoIndicadorPK;

@Entity
@Table(name = "EstadosXTipoIndicador")
@IdClass(EstadosXTipoIndicadorPK.class)
public class EstadosXTipoIndicador implements Serializable {

	private static final long serialVersionUID = 1L;

	public EstadosXTipoIndicador() {
	}

	@Id
	private Integer idTipoIndicador;
	@Id
	private Integer idEstado;
	private String representacionCromatica;
	private String estado;
	private String observaciones;
	@ManyToOne(fetch = FetchType.LAZY)
	@Id
	@JoinColumn(name = "idTipoIndicador", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
	private TipoIndicador tipoIndicador;
	public Integer getIdTipoIndicador() {
		return idTipoIndicador;
	}

	public void setIdTipoIndicador(Integer idTipoIndicador) {
		this.idTipoIndicador = idTipoIndicador;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getRepresentacionCromatica() {
		return representacionCromatica;
	}

	public void setRepresentacionCromatica(String param) {
		this.representacionCromatica = param;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String param) {
		this.estado = param;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String param) {
		this.observaciones = param;
	}

	public TipoIndicador getTipoIndicador() {
	    return tipoIndicador;
	}

	public void setTipoIndicador(TipoIndicador param) {
	    this.tipoIndicador = param;
	}

}