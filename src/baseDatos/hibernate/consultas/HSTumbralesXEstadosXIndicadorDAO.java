package baseDatos.hibernate.consultas;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import baseDatos.hibernate.tablas.HSTumbralesXEstadosXIndicador;

public class HSTumbralesXEstadosXIndicadorDAO extends DAO {
	
	public void guardar(HSTumbralesXEstadosXIndicador hstUmbrales) {
		try {
			iniciaOperacion();
			sesion.save(hstUmbrales);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		
	}

	public void actualizar(HSTumbralesXEstadosXIndicador hstUmbrales) {
		try {
			iniciaOperacion();
			sesion.update(hstUmbrales);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	public void eliminar(HSTumbralesXEstadosXIndicador hstUmbrales) {
		try {
			iniciaOperacion();
			sesion.delete(hstUmbrales);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

	public List<HSTumbralesXEstadosXIndicador> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM HSTumbralesXEstadosXIndicador hstu";
			Query query = sesion.createQuery(hql);
			List<HSTumbralesXEstadosXIndicador> results = query.list();
			return results;
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
}
