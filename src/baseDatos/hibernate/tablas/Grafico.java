package baseDatos.hibernate.tablas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Grafico")
public class Grafico implements Serializable {

	private static final long serialVersionUID = 1L;

	public Grafico() {
	}

	@Id
	private Integer id;
	private String tipoGrafico;
	private String observaciones;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoGrafico() {
	    return tipoGrafico;
	}

	public void setTipoGrafico(String param) {
	    this.tipoGrafico = param;
	}

	public String getObservaciones() {
	    return observaciones;
	}

	public void setObservaciones(String param) {
	    this.observaciones = param;
	}

}