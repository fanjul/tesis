package baseDatos.hibernate.consultas;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import baseDatos.hibernate.tablas.Indicador;


public class IndicadorDAO extends DAO {

	public void guardar(Indicador indicador) {
		try {
			iniciaOperacion();
			sesion.save(indicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		
	}

	public void actualizar(Indicador indicador) {
		try {
			iniciaOperacion();
			sesion.update(indicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	public void eliminar(Indicador indicador) {
		try {
			iniciaOperacion();
			sesion.delete(indicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	public List<Indicador> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM Indicador ind";
			Query query = sesion.createQuery(hql);
			List<Indicador> results = query.list();
			return results;
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
}
