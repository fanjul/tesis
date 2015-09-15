package baseDatos.hibernate.consultas;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import baseDatos.hibernate.tablas.Persona;

public class PersonaDAO extends DAO implements AbstractaConsulta {
	public void guardar(Persona persona) {
		try {
			iniciaOperacion();
			sesion.save(persona);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		
	}

	public void actualizar(Persona persona) {
		try {
			iniciaOperacion();
			sesion.update(persona);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	public void eliminar(Persona persona) {
		try {
			iniciaOperacion();
			sesion.delete(persona);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Persona> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM Persona per";
			Query query = sesion.createQuery(hql);
			List<Persona> results = query.list();
			return results;
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
}
