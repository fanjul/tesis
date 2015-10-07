package baseDatos.hibernate.tablas.primaryKey;

import java.io.Serializable;

import baseDatos.hibernate.tablas.EstadosXTipoIndicador;
import javax.persistence.Basic;

public class EstadosXTipoIndicadorPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idTipoIndicador;
	private Integer idEstado;
	@Basic
	private Integer tipoIndicador;

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

	@Override
	public boolean equals(Object estado) {
		return ( this.idTipoIndicador.equals(((EstadosXTipoIndicador)estado).getIdTipoIndicador()) && 
				this.idEstado.equals(((EstadosXTipoIndicador)estado).getIdEstado()));
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public Integer getTipoIndicador() {
	    return tipoIndicador;
	}

	public void setTipoIndicador(Integer param) {
	    this.tipoIndicador = param;
	}

}
