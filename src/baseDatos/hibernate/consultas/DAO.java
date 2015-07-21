package baseDatos.hibernate.consultas;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import baseDatos.hibernate.configuracion.HibernateUtil;

public class DAO {
	protected Session sesion;
	protected Transaction tx;

	// se conecta con la base de datos
	protected void iniciaOperacion() throws HibernateException {
		sesion = HibernateUtil.createSessionFactory().openSession();
		tx = sesion.beginTransaction();
	}

	// cualquier excepcion, se hace un rollback deshaciendo las operaciones
	protected void manejaExcepcion(HibernateException he)
			throws HibernateException {
		tx.rollback();
		throw new HibernateException(
				"Ocurrió un error en la capa de acceso a datos", he);
	}
	
	
	
	
}
