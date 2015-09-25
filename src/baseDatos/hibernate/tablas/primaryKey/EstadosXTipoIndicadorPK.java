package baseDatos.hibernate.tablas.primaryKey;

import java.io.Serializable;

import baseDatos.hibernate.tablas.EstadosXTipoIndicador;

public class EstadosXTipoIndicadorPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idTipoIndicador;
	private Integer idEstado;

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

}
