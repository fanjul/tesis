package baseDatos.hibernate.tablas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import baseDatos.hibernate.tablas.EstadosXTipoIndicador;
import java.util.Collection;
import javax.persistence.OneToMany;
import baseDatos.hibernate.tablas.Indicador;
import baseDatos.hibernate.tablas.UmbralesXEstadosXIndicador;
import baseDatos.hibernate.tablas.HSTumbralesXEstadosXIndicador;

@Entity
@Table(name = "TipoIndicador")
public class TipoIndicador implements Serializable {

	private static final long serialVersionUID = 1L;

	public TipoIndicador() {
	}

	@Id
	private Integer id;
	private String tipo;
	private String observaciones;
	@OneToMany(mappedBy = "tipoIndicador")
	private Collection<EstadosXTipoIndicador> estadosXTipoIndicador;
	@OneToMany(mappedBy = "tipoIndicador")
	private Collection<Indicador> indicador;
	@OneToMany(mappedBy = "tipoIndicador")
	private Collection<UmbralesXEstadosXIndicador> umbralesXEstadosXIndicador;
	@OneToMany(mappedBy = "tipoIndicador")
	private Collection<HSTumbralesXEstadosXIndicador> hSTumbralesXEstadosXIndicador;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String param) {
		this.tipo = param;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String param) {
		this.observaciones = param;
	}

	public Collection<EstadosXTipoIndicador> getEstadosXTipoIndicador() {
	    return estadosXTipoIndicador;
	}

	public void setEstadosXTipoIndicador(Collection<EstadosXTipoIndicador> param) {
	    this.estadosXTipoIndicador = param;
	}

	public Collection<Indicador> getIndicador() {
	    return indicador;
	}

	public void setIndicador(Collection<Indicador> param) {
	    this.indicador = param;
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

}