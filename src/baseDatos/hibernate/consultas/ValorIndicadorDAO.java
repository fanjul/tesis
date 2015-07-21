package baseDatos.hibernate.consultas;

import org.hibernate.HibernateException;

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
}
