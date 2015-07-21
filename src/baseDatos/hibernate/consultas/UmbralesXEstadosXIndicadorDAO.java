package baseDatos.hibernate.consultas;

import org.hibernate.HibernateException;

import baseDatos.hibernate.tablas.UmbralesXEstadosXIndicador;

public class UmbralesXEstadosXIndicadorDAO extends DAO {
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

}
