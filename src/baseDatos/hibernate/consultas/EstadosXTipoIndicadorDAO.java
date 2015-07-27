package baseDatos.hibernate.consultas;

import org.hibernate.HibernateException;

import baseDatos.hibernate.tablas.EstadosXTipoIndicador;

public class EstadosXTipoIndicadorDAO extends DAO {
	
	public void guardar(EstadosXTipoIndicador estadosX) {
		try {
			iniciaOperacion();
			sesion.save(estadosX);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		
	}

	public void actualizar(EstadosXTipoIndicador estadosX) {
		try {
			iniciaOperacion();
			sesion.update(estadosX);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	public void eliminar(EstadosXTipoIndicador estadosX) {
		try {
			iniciaOperacion();
			sesion.delete(estadosX);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

}
