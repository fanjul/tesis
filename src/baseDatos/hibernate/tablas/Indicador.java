package baseDatos.hibernate.tablas;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import baseDatos.hibernate.tablas.Grafico;

@Entity
@Table(name = "Indicador")
public class Indicador implements Serializable {

	private static final long serialVersionUID = 1L;

	public Indicador() {
	}

	@Id
	private Integer id;
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
	@OneToMany( mappedBy = "indicador")
	private Collection<UmbralesXEstadosXIndicador> umbralesXEstadosXIndicador;
	@OneToMany
	private Collection<Grafico> grafico;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String param) {
		this.codigo = param;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String param) {
		this.nombre = param;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String param) {
		this.formula = param;
	}

	public String getFichaMetodologica() {
		return fichaMetodologica;
	}

	public void setFichaMetodologica(String param) {
		this.fichaMetodologica = param;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String param) {
		this.frecuencia = param;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String param) {
		this.periodo = param;
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

	public void setObservaciones(String param) {
		this.observaciones = param;
	}

	public Collection<UmbralesXEstadosXIndicador> getUmbralesXEstadosXIndicador() {
	    return umbralesXEstadosXIndicador;
	}

	public void setUmbralesXEstadosXIndicador(Collection<UmbralesXEstadosXIndicador> param) {
	    this.umbralesXEstadosXIndicador = param;
	}

	public Collection<Grafico> getGrafico() {
	    return grafico;
	}

	public void setGrafico(Collection<Grafico> param) {
	    this.grafico = param;
	}

}