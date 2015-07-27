package baseDatos.hibernate.consultas;

import org.hibernate.HibernateException;

import baseDatos.hibernate.tablas.Grafico;

public class GraficoDAO extends DAO {
	
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

}
