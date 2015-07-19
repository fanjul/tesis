package baseDatos.hibernate.consultas;

import java.math.BigDecimal;

import org.hibernate.HibernateException;

import baseDatos.hibernate.tablas.Indicador;

public class IndicadorDAO extends DAOConexion{

	

	public BigDecimal guardaIndicador(Indicador indicador) {
		BigDecimal id = new BigDecimal(0);
		try {
			iniciaOperacion();
			id = (BigDecimal) sesion.save(indicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		return id;
	}
}
