package guiFX.BaseDeDatosGUI;

import baseDatos.hibernate.consultas.FactoryConsultas;
import javafx.scene.layout.AnchorPane;

public interface AbstractBaseDeDatosGUI {
	
	void crearTablaBaseDeDatos();
	void mostrarTabla(Object consulta, FactoryConsultas factoryConsultasDAO, AnchorPane centroInferior);
}
