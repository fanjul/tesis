package baseDatos.hibernate.tablas.primaryKey;

import java.io.Serializable;
import java.sql.Timestamp;

import baseDatos.hibernate.tablas.ValorIndicador;
import javax.persistence.Basic;

public class ValorIndicadorPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idIndicador;
	private Timestamp fecha;
	@Basic
	private Integer indicador;

	public Integer getIdIndicador() {
		return idIndicador;
	}

	public void setIdIndicador(Integer idIndicador) {
		this.idIndicador = idIndicador;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	@Override
	public boolean equals(Object valorIndicador) {
		return (this.getFecha().equals(((ValorIndicador) valorIndicador).getFecha())
				&& this.getIdIndicador().equals(((ValorIndicador) valorIndicador).getIdIndicador()));
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public Integer getIndicador() {
	    return indicador;
	}

	public void setIndicador(Integer param) {
	    this.indicador = param;
	}

}
