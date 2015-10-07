package baseDatos.hibernate.tablas;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import baseDatos.hibernate.tablas.ValorIndicador;

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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTipoIndicador", referencedColumnName = "id",insertable = false, updatable = false, nullable = false)
	private TipoIndicador tipoIndicador;
	@OneToMany(mappedBy = "indicador")
	private Collection<UmbralesXEstadosXIndicador> umbralesXEstadosXIndicador;
	@OneToMany(mappedBy = "indicador")
	private Collection<HSTumbralesXEstadosXIndicador> hSTumbralesXEstadosXIndicador;
	@OneToMany(mappedBy = "indicador")
	private Collection<Tablero> tablero;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUnidadDeMedida", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
	private UnidadesDeMedida unidadesDeMedida;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idResponsable", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
	private Persona persona;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idGrafico", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
	private Grafico grafico;
	@OneToMany(mappedBy = "indicador")
	private Collection<ValorIndicador> valorIndicador;
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

	public TipoIndicador getTipoIndicador() {
	    return tipoIndicador;
	}

	public void setTipoIndicador(TipoIndicador param) {
	    this.tipoIndicador = param;
	}

	public Collection<UmbralesXEstadosXIndicador> getUmbralesXEstadosXIndicador() {
	    return umbralesXEstadosXIndicador;
	}

	public void setUmbralesXEstadosXIndicador(Collection<UmbralesXEstadosXIndicador> param) {
	    this.umbralesXEstadosXIndicador = param;
	}

	public Collection<HSTumbralesXEstadosXIndicador> getHSTumbralesXEstadosXIndicador() {
	    return hSTumbralesXEstadosXIndicador;
	}

	public void setHSTumbralesXEstadosXIndicador(Collection<HSTumbralesXEstadosXIndicador> param) {
	    this.hSTumbralesXEstadosXIndicador = param;
	}

	public Collection<Tablero> getTablero() {
	    return tablero;
	}

	public void setTablero(Collection<Tablero> param) {
	    this.tablero = param;
	}

	public UnidadesDeMedida getUnidadesDeMedida() {
	    return unidadesDeMedida;
	}

	public void setUnidadesDeMedida(UnidadesDeMedida param) {
	    this.unidadesDeMedida = param;
	}

	public Persona getPersona() {
	    return persona;
	}

	public void setPersona(Persona param) {
	    this.persona = param;
	}

	public Grafico getGrafico() {
	    return grafico;
	}

	public void setGrafico(Grafico param) {
	    this.grafico = param;
	}

	public Collection<ValorIndicador> getValorIndicador() {
	    return valorIndicador;
	}

	public void setValorIndicador(Collection<ValorIndicador> param) {
	    this.valorIndicador = param;
	}


}