package baseDatos.hibernate.consultas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;

import baseDatos.hibernate.configuracion.HibernateUtil;

public abstract class DAO {
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
	
	
	public List<String> getAllTables() {
		this.iniciaOperacion();
	    List<String> tableNames = new ArrayList<String>();
	    SessionFactory sessionFactory = sesion.getSessionFactory();
	    Map<String, ClassMetadata>  map = (Map<String, ClassMetadata>) sessionFactory.getAllClassMetadata();
	    for(String entityName : map.keySet()){
	        SessionFactoryImpl sfImpl = (SessionFactoryImpl) sessionFactory;
	        String tableName = ((AbstractEntityPersister)sfImpl.getEntityPersister(entityName)).getTableName();
	        tableNames.add(tableName);
	    }
	    return tableNames;
	}
	
	public abstract List<String> getColumnas();
	
}
