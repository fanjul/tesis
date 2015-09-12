package baseDatos.hibernate.consultas;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

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
	
	public List<UnidadesDeMedida> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM UnidadesDeMedida udms";
			Query query = sesion.createQuery(hql);
			List<UnidadesDeMedida> results = query.list();
			return results;
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
}
