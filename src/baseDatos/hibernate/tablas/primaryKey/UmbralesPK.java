package baseDatos.hibernate.tablas.primaryKey;

import java.io.Serializable;

import baseDatos.hibernate.tablas.UmbralesXEstadosXIndicador;

public class UmbralesPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idIndicador;
	private Integer idEstadoTipoIndicador;

	public Integer getIdIndicador() {
		return idIndicador;
	}

	public void setIdIndicador(Integer idIndicador) {
		this.idIndicador = idIndicador;
	}

	public Integer getIdEstadoTipoIndicador() {
		return idEstadoTipoIndicador;
	}

	public void setIdEstadoTipoIndicador(Integer idEstadoTipoIndicador) {
		this.idEstadoTipoIndicador = idEstadoTipoIndicador;
	}

	@Override
	public boolean equals(Object umbrales) {
		return (this.getIdEstadoTipoIndicador()
				.equals(((UmbralesXEstadosXIndicador) umbrales).getIdEstadoTipoIndicador())
				&& this.getIdIndicador().equals(((UmbralesXEstadosXIndicador) umbrales).getIdIndicador()));
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
