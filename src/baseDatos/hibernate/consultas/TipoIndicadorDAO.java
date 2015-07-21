package baseDatos.hibernate.consultas;

import org.hibernate.HibernateException;

import baseDatos.hibernate.tablas.TipoIndicador;


public class TipoIndicadorDAO extends DAO{

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
}
