package baseDatos.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import baseDatos.hibernate.configuracion.HibernateUtil;

public class TipoIndicadorDAO {

	private Session sesion;
	private Transaction tx;

	// se conecta con la base de datos
	private void iniciaOperacion() throws HibernateException {
		sesion = HibernateUtil.createSessionFactory().openSession();
		tx = sesion.beginTransaction();
	}

	// cualquier excepcion, se hace un rollback deshaciendo las operaciones
	private void manejaExcepcion(HibernateException he)
			throws HibernateException {
		tx.rollback();
		throw new HibernateException(
				"Ocurrió un error en la capa de acceso a datos", he);
	}

	public Integer guardaIndicador(TipoIndicador tipoIndicador) {
		Integer id = 0;
		try {
			iniciaOperacion();
			id = (Integer) sesion.save(tipoIndicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		return id;
	}

}
