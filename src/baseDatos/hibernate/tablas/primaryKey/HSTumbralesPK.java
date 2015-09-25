package baseDatos.hibernate.tablas.primaryKey;

import java.io.Serializable;
import java.sql.Timestamp;

import baseDatos.hibernate.tablas.HSTumbralesXEstadosXIndicador;

public class HSTumbralesPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idIndicador;
	private Integer idEstadoTipoIndicador;
	private Timestamp inicioUmbral;
	private Timestamp finUmbral;

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

	public Timestamp getInicioUmbral() {
		return inicioUmbral;
	}

	public void setInicioUmbral(Timestamp inicioUmbral) {
		this.inicioUmbral = inicioUmbral;
	}

	public Timestamp getFinUmbral() {
		return finUmbral;
	}

	public void setFinUmbral(Timestamp finUmbral) {
		this.finUmbral = finUmbral;
	}

	@Override
	public boolean equals(Object hst) {
		return (this.getFinUmbral().equals(((HSTumbralesXEstadosXIndicador) hst).getFinUmbral())
				&& this.getIdEstadoTipoIndicador()
						.equals(((HSTumbralesXEstadosXIndicador) hst).getIdEstadoTipoIndicador())
				&& this.getIdIndicador().equals(((HSTumbralesXEstadosXIndicador) hst).getFinUmbral())
				&& this.getInicioUmbral().equals(((HSTumbralesXEstadosXIndicador) hst).getFinUmbral()));
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
