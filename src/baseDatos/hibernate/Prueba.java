package baseDatos.hibernate;

import java.math.BigDecimal;

import baseDatos.hibernate.consultas.IndicadorDAO;
import baseDatos.hibernate.consultas.TipoIndicadorDAO;
import baseDatos.hibernate.tablas.Indicador;
import baseDatos.hibernate.tablas.TipoIndicador;

public class Prueba {

	public static void main(String[] args) {
		TipoIndicadorDAO i = new TipoIndicadorDAO();
		TipoIndicador indicador = new TipoIndicador();
		indicador.setId(2);
		indicador.setObservaciones("el corvi no entiende nada. Fanjul menos... Guille Z");
		indicador.setTipo("De la vida");
		i.guardaIndicador(indicador);
		
		Indicador ind = new Indicador();
		ind.setCodigo("asd");
		ind.setId(new BigDecimal(1));
		IndicadorDAO indDao = new IndicadorDAO();
		indDao.guardaIndicador(ind);
	}

}
