package baseDatos.hibernate.consultas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.walking.spi.AttributeDefinition;

import baseDatos.hibernate.tablas.Indicador;


public class IndicadorDAO extends DAO {

	public void guardar(Indicador indicador) {
		try {
			iniciaOperacion();
			sesion.save(indicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		
	}

	public void actualizar(Indicador indicador) {
		try {
			iniciaOperacion();
			sesion.update(indicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	public void eliminar(Indicador indicador) {
		try {
			iniciaOperacion();
			sesion.delete(indicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Indicador> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM Indicador ind";
			Query query = sesion.createQuery(hql);
			List<Indicador> results = query.list();
			return results;
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	public List<String> getColumnas() {
		try {
			this.iniciaOperacion();
			AbstractEntityPersister aep = ((AbstractEntityPersister) sesion.getSessionFactory()
					.getClassMetadata(Indicador.class.getName()));
			List<String> columnNames = new ArrayList<String>();
			for (String[] arrayS : aep.getContraintOrderedTableKeyColumnClosure()) {
				columnNames.addAll(Arrays.asList(arrayS));

			}
			
			for(AttributeDefinition element : aep.getAttributes() ){
				if(!element.getType().isAssociationType() && !element.getType().isComponentType()){
					columnNames.add(element.getName());
				}
				
			}
			
			return columnNames;
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
}
