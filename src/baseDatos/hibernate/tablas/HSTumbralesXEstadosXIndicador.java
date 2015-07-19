package baseDatos.hibernate.tablas;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HSTumbralesXEstadosXIndicador implements Serializable {

	private static final long serialVersionUID = 9218812442649637983L;
	private Integer idIndicador;
	private Integer idEstadoTipoIndicador;
	private Timestamp inicioUmbral;
	private Timestamp finUmbral;
	private String operadorUmbralSuperior;
	private String operadorUmbralInferior;
	private Double valorUmbralSuperior;
	private Double valorUmbralInferior;
	private String observaciones;
	
	public HSTumbralesXEstadosXIndicador(){
		
	}
	
	@Id
	public Integer getIdIndicador() {
		return idIndicador;
	}

	public void setIdIndicador(Integer idIndicador) {
		this.idIndicador = idIndicador;
	}
	
	@Id
	public Integer getIdEstadoTipoIndicador() {
		return idEstadoTipoIndicador;
	}

	public void setIdEstadoTipoIndicador(Integer idEstadoTipoIndicador) {
		this.idEstadoTipoIndicador = idEstadoTipoIndicador;
	}
	
	@Id
	public Timestamp getInicioUmbral() {
		return inicioUmbral;
	}

	public void setInicioUmbral(Timestamp inicioUmbral) {
		this.inicioUmbral = inicioUmbral;
	}

	@Id
	public Timestamp getFinUmbral() {
		return finUmbral;
	}

	public void setFinUmbral(Timestamp finUmbral) {
		this.finUmbral = finUmbral;
	}

	public String getOperadorUmbralSuperior() {
		return operadorUmbralSuperior;
	}

	public void setOperadorUmbralSuperior(String operadorUmbralSuperior) {
		this.operadorUmbralSuperior = operadorUmbralSuperior;
	}

	public String getOperadorUmbralInferior() {
		return operadorUmbralInferior;
	}

	public void setOperadorUmbralInferior(String operadorUmbralInferior) {
		this.operadorUmbralInferior = operadorUmbralInferior;
	}

	public Double getValorUmbralSuperior() {
		return valorUmbralSuperior;
	}

	public void setValorUmbralSuperior(Double valorUmbralSuperior) {
		this.valorUmbralSuperior = valorUmbralSuperior;
	}

	public Double getValorUmbralInferior() {
		return valorUmbralInferior;
	}

	public void setValorUmbralInferior(Double valorUmbralInferior) {
		this.valorUmbralInferior = valorUmbralInferior;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	

}
