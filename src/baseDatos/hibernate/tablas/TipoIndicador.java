package baseDatos.hibernate.tablas;

import java.io.Serializable;
import javax.persistence.*;

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

}