package baseDatos.hibernate.consultas;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import baseDatos.hibernate.tablas.ValorIndicador;

public class ValorIndicadorDAO extends DAO {

	public void guardar(ValorIndicador valorIndicador) {
		try {
			iniciaOperacion();
			sesion.save(valorIndicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}

	}

	public void actualizar(ValorIndicador valorIndicador) {
		try {
			iniciaOperacion();
			sesion.update(valorIndicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

	public void eliminar(ValorIndicador valorIndicador) {
		try {
			iniciaOperacion();
			sesion.delete(valorIndicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

	public List<ValorIndicador> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM ValorIndicador vi";
			Query query = sesion.createQuery(hql);
			List<ValorIndicador> results = query.list();
			return results;
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

}
