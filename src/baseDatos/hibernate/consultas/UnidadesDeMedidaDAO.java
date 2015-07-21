package baseDatos.hibernate.consultas;

import org.hibernate.HibernateException;

import baseDatos.hibernate.tablas.UnidadesDeMedida;

public class UnidadesDeMedidaDAO extends DAO {
	public void guardar(UnidadesDeMedida unidadesDeMedida) {
		try {
			iniciaOperacion();
			sesion.save(unidadesDeMedida);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		
	}

	public void actualizar(UnidadesDeMedida unidadesDeMedida) {
		try {
			iniciaOperacion();
			sesion.update(unidadesDeMedida);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	public void eliminar(UnidadesDeMedida unidadesDeMedida) {
		try {
			iniciaOperacion();
			sesion.delete(unidadesDeMedida);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
}
