package baseDatos.hibernate.tablas;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import baseDatos.hibernate.tablas.primaryKey.HSTumbralesPK;
import baseDatos.hibernate.tablas.TipoIndicador;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import baseDatos.hibernate.tablas.Indicador;

@Entity
@IdClass(HSTumbralesPK.class)
@Table(name = "HSTumbralesXEstadosXIndicador")
public class HSTumbralesXEstadosXIndicador implements Serializable {

	private static final long serialVersionUID = 1L;

	public HSTumbralesXEstadosXIndicador() {
	}

	@Id
	private Integer idIndicador;
	@Id
	private Integer idEstadoTipoIndicador;
	@Id
	private Timestamp inicioUmbral;
	@Id
	private Timestamp finUmbral;
	private String operadorUmbralSuperior;
	private Double valorUmbralSuperior;
	private Double valorUmbralInferior;
	private String operadorUmbralInferior;
	private String observaciones;
	@ManyToOne(fetch=FetchType.LAZY)
	@Id
	@JoinColumn(name = "idEstadoTipoIndicador", referencedColumnName = "id",insertable = false, updatable = false, nullable = false)
	private TipoIndicador tipoIndicador;
	@ManyToOne(fetch=FetchType.LAZY)
	@Id
	@JoinColumn(name = "idIndicador", referencedColumnName = "id",insertable = false, updatable = false, nullable = false)
	private Indicador indicador;

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

	public void setOperadorUmbralSuperior(String param) {
		this.operadorUmbralSuperior = param;
	}

	public Double getValorUmbralSuperior() {
		return valorUmbralSuperior;
	}

	public void setValorUmbralSuperior(Double param) {
		this.valorUmbralSuperior = param;
	}

	public Double getValorUmbralInferior() {
		return valorUmbralInferior;
	}

	public void setValorUmbralInferior(Double param) {
		this.valorUmbralInferior = param;
	}

	public String getOperadorUmbralInferior() {
		return operadorUmbralInferior;
	}

	public void setOperadorUmbralInferior(String param) {
		this.operadorUmbralInferior = param;
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

	public Indicador getIndicador() {
	    return indicador;
	}

	public void setIndicador(Indicador param) {
	    this.indicador = param;
	}

}