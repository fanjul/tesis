package baseDatos.hibernate.tablas;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import baseDatos.hibernate.tablas.primaryKey.UmbralesPK;
import baseDatos.hibernate.tablas.TipoIndicador;

@Entity
@IdClass(UmbralesPK.class)
@Table(name = "UmbralesXEstadosXIndicador")
public class UmbralesXEstadosXIndicador implements Serializable {

	private static final long serialVersionUID = 1L;

	public UmbralesXEstadosXIndicador() {
	}

	@Id
	private Integer idIndicador;
	@Id
	private Integer idEstadoTipoIndicador;
	private Timestamp inicioUmbral;
	private Timestamp finUmbral;
	private String operadorUmbralSuperior;
	private String operadorUmbralInferior;
	private Double valorUmbralSuperior;
	private Double valorUmbralInferior;
	private String observaciones;
	@ManyToOne(fetch=FetchType.LAZY)
	@Id
	@JoinColumn(name = "idIndicador", referencedColumnName = "id",insertable = false, updatable = false, nullable = false)
	private Indicador indicador;
	@ManyToOne(fetch=FetchType.LAZY)
	@Id
	@JoinColumn(name = "idEstadoTipoIndicador", referencedColumnName = "id",insertable = false, updatable = false, nullable = false)
	private TipoIndicador tipoIndicador;
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

	public Indicador getIndicador() {
	    return indicador;
	}

	public void setIndicador(Indicador param) {
	    this.indicador = param;
	}

	public TipoIndicador getTipoIndicador() {
	    return tipoIndicador;
	}

	public void setTipoIndicador(TipoIndicador param) {
	    this.tipoIndicador = param;
	}

}