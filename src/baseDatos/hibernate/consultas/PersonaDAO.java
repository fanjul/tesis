package baseDatos.hibernate.consultas;

import org.hibernate.HibernateException;

import baseDatos.hibernate.tablas.Persona;

public class PersonaDAO extends DAO {
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
}
