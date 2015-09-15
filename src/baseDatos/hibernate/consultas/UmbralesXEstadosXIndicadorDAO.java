package baseDatos.hibernate.consultas;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import baseDatos.hibernate.tablas.UmbralesXEstadosXIndicador;

public class UmbralesXEstadosXIndicadorDAO extends DAO implements AbstractaConsulta {
	public void guardar(UmbralesXEstadosXIndicador umbrales) {
		try {
			iniciaOperacion();
			sesion.save(umbrales);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		
	}

	public void actualizar(UmbralesXEstadosXIndicador umbrales) {
		try {
			iniciaOperacion();
			sesion.update(umbrales);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	public void eliminar(UmbralesXEstadosXIndicador umbrales) {
		try {
			iniciaOperacion();
			sesion.delete(umbrales);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UmbralesXEstadosXIndicador> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM UmbralesXEstadosXIndicador uxe";
			Query query = sesion.createQuery(hql);
			List<UmbralesXEstadosXIndicador> results = query.list();
			return results;
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

}
