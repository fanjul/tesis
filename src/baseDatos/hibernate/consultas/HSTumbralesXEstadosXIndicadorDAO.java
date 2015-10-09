package baseDatos.hibernate.consultas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.walking.spi.AttributeDefinition;

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

	@SuppressWarnings("unchecked")
	public List<HSTumbralesXEstadosXIndicador> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM HSTumbralesXEstadosXIndicador hstu";
			Query query = sesion.createQuery(hql);
			List<HSTumbralesXEstadosXIndicador> results = query.list();
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
					.getClassMetadata(HSTumbralesXEstadosXIndicador.class.getName()));
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
