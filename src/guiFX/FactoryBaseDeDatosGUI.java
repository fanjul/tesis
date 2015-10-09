package guiFX;

import guiFX.BaseDeDatosGUI.AbstractBaseDeDatosGUI;
import guiFX.BaseDeDatosGUI.EstadosXTipoIndicadorGUI;
import guiFX.BaseDeDatosGUI.GraficoGUI;
import guiFX.BaseDeDatosGUI.HSTumbralesXEstadosXIndicadorGUI;
import guiFX.BaseDeDatosGUI.IndicadorGUI;
import guiFX.BaseDeDatosGUI.PersonaGUI;
import guiFX.BaseDeDatosGUI.TableroGUI;
import guiFX.BaseDeDatosGUI.TipoIndicadorGUI;
import guiFX.BaseDeDatosGUI.UmbralesXEstadosXIndicadorGUI;
import guiFX.BaseDeDatosGUI.UnidadesDeMedidaGUI;
import guiFX.BaseDeDatosGUI.ValorIndicadorGUI;

public class FactoryBaseDeDatosGUI {

	public AbstractBaseDeDatosGUI getBaseDeDatos(String tabla) {

		if ("ValorIndicador".equalsIgnoreCase(tabla)) {
			return new ValorIndicadorGUI();
		} else if ("UnidadesDeMedida".equalsIgnoreCase(tabla)) {
			return new UnidadesDeMedidaGUI();
		} else if ("UmbralesXEstadosXIndicador".equalsIgnoreCase(tabla)) {
			return new UmbralesXEstadosXIndicadorGUI();
		} else if ("TipoIndicador".equalsIgnoreCase(tabla)) {
			return new TipoIndicadorGUI();
		} else if ("Persona".equalsIgnoreCase(tabla)) {
			return new PersonaGUI();
		} else if ("Indicador".equalsIgnoreCase(tabla)) {
			return new IndicadorGUI();
		} else if ("HSTumbralesXEstadosXIndicador".equalsIgnoreCase(tabla)) {
			return new HSTumbralesXEstadosXIndicadorGUI();
		} else if ("Grafico".equalsIgnoreCase(tabla)) {
			return new GraficoGUI();
		} else if ("EstadosXTipoIndicador".equalsIgnoreCase(tabla)) {
			return new EstadosXTipoIndicadorGUI();
		} else if ("Tablero".equalsIgnoreCase(tabla)) {
			return new TableroGUI();
		} else 
			return null;
	}
}
