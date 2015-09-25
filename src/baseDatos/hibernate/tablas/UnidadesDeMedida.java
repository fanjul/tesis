package baseDatos.hibernate.tablas;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "UnidadesDeMedida")
public class UnidadesDeMedida implements Serializable {

	private static final long serialVersionUID = 1L;

	public UnidadesDeMedida() {
	}

	@Id
	private Integer id;
	private String unidadDeMedida;
	private String observaciones;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida(String param) {
		this.unidadDeMedida = param;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String param) {
		this.observaciones = param;
	}

}