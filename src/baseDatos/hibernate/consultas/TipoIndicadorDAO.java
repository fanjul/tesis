package baseDatos.hibernate.consultas;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import baseDatos.hibernate.tablas.TipoIndicador;


public class TipoIndicadorDAO extends DAO {

	public void guardar(TipoIndicador tipoIndicador) {
		try {
			iniciaOperacion();
			sesion.save(tipoIndicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		
	}

	public void actualizar(TipoIndicador tipoIndicador) {
		try {
			iniciaOperacion();
			sesion.update(tipoIndicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	public void eliminar(TipoIndicador tipoIndicador) {
		try {
			iniciaOperacion();
			sesion.delete(tipoIndicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoIndicador> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM TipoIndicador ti";
			Query query = sesion.createQuery(hql);
			List<TipoIndicador> results = query.list();
			return results;
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
}
