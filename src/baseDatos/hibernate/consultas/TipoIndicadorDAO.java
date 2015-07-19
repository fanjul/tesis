package baseDatos.hibernate.consultas;

import org.hibernate.HibernateException;

import baseDatos.hibernate.tablas.TipoIndicador;

public class TipoIndicadorDAO extends DAOConexion{

	public Integer guardaIndicador(TipoIndicador tipoIndicador) {
		Integer id = 0;
		try {
			iniciaOperacion();
			id = (Integer) sesion.save(tipoIndicador);
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
