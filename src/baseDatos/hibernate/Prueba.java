package baseDatos.hibernate;

public class Prueba {

	public static void main(String[] args) {
		TipoIndicadorDAO i = new TipoIndicadorDAO();
		TipoIndicador indicador = new TipoIndicador();
		indicador.setId(2);
		indicador.setObservaciones("el corvi no entiende nada. Fanjul menos... Guille Z");
		indicador.setTipo("De la vida");
		i.guardaIndicador(indicador);
	}

}
