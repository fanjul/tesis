package baseDatos.hibernate.consultas;

import org.hibernate.HibernateException;

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

}
