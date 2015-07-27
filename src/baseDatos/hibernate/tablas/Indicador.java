package baseDatos.hibernate.tablas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Indicador")
public class Indicador implements Serializable {
	
	private static final long serialVersionUID = -8612794918676028856L;
	private BigDecimal id;
	private String codigo;
	private String nombre;
	private Integer idUnidadDeMedida;
	private Integer direccion;
	private String formula;
	private String fichaMetodologica;
	private Integer idGrafico;
	private Integer idResponsable;
	private String frecuencia;
	private String periodo;
	private Timestamp fechaUltimaActualizacion;
	private Integer idTipoIndicador;
	private String observaciones;
	
	public Indicador(){
		
	}
	
	@Id
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getIdUnidadDeMedida() {
		return idUnidadDeMedida;
	}
	public void setIdUnidadDeMedida(Integer idUnidadDeMedida) {
		this.idUnidadDeMedida = idUnidadDeMedida;
	}
	public Integer getDireccion() {
		return direccion;
	}
	public void setDireccion(Integer direccion) {
		this.direccion = direccion;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getFichaMetodologica() {
		return fichaMetodologica;
	}
	public void setFichaMetodologica(String fichaMetodologica) {
		this.fichaMetodologica = fichaMetodologica;
	}
	public Integer getIdGrafico() {
		return idGrafico;
	}
	public void setIdGrafico(Integer idGrafico) {
		this.idGrafico = idGrafico;
	}
	public Integer getIdResponsable() {
		return idResponsable;
	}
	public void setIdResponsable(Integer idResponsable) {
		this.idResponsable = idResponsable;
	}
	public String getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Timestamp getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	public void setFechaUltimaActualizacion(Timestamp fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}
	public Integer getIdTipoIndicador() {
		return idTipoIndicador;
	}
	public void setIdTipoIndicador(Integer idTipoIndicador) {
		this.idTipoIndicador = idTipoIndicador;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}
