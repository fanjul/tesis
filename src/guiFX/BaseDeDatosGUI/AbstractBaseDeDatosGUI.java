package guiFX.BaseDeDatosGUI;

import baseDatos.hibernate.consultas.AbstractaConsulta;
import baseDatos.hibernate.consultas.FactoryConsultas;
import javafx.scene.layout.AnchorPane;

public interface AbstractBaseDeDatosGUI {
	
	void crearTablaBaseDeDatos();
	void mostrarTabla(AbstractaConsulta consulta, FactoryConsultas factoryConsultasDAO, AnchorPane centroInferior);
}
