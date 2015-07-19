package baseDatos.hibernate.tablas;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ValorIndicador implements Serializable {

	private static final long serialVersionUID = -8423220578940394325L;
	private Integer idIndicador;
	private Timestamp fecha;
	private Double valor;
	private String estado;
	private Double variacion;
	private Integer signoVariacion;
	private String observaciones;
	
	public ValorIndicador(){
		
	}
	
	@Id
	public Integer getIdIndicador() {
		return idIndicador;
	}

	public void setIdIndicador(Integer idIndicador) {
		this.idIndicador = idIndicador;
	}

	@Id
	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getVariacion() {
		return variacion;
	}

	public void setVariacion(Double variacion) {
		this.variacion = variacion;
	}

	public Integer getSignoVariacion() {
		return signoVariacion;
	}

	public void setSignoVariacion(Integer signoVariacion) {
		this.signoVariacion = signoVariacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	

}
