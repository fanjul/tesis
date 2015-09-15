package baseDatos.hibernate.consultas;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import baseDatos.hibernate.tablas.Grafico;

public class GraficoDAO extends DAO implements AbstractaConsulta {
	
	public void guardar(Grafico grafico) {
		try {
			iniciaOperacion();
			sesion.save(grafico);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		
	}

	public void actualizar(Grafico grafico) {
		try {
			iniciaOperacion();
			sesion.update(grafico);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	public void eliminar(Grafico grafico) {
		try {
			iniciaOperacion();
			sesion.delete(grafico);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Grafico> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM Grafico gra";
			Query query = sesion.createQuery(hql);
			List<Grafico> results = query.list();
			return results;
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

}
