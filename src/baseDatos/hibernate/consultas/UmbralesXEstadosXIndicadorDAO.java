package baseDatos.hibernate.consultas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.walking.spi.AttributeDefinition;

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
	
	@SuppressWarnings("unchecked")
	public List<UmbralesXEstadosXIndicador> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM UmbralesXEstadosXIndicador uxe";
			Query query = sesion.createQuery(hql);
			List<UmbralesXEstadosXIndicador> results = query.list();
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
					.getClassMetadata(UmbralesXEstadosXIndicador.class.getName()));
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
