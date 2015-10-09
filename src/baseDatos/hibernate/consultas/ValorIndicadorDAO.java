package baseDatos.hibernate.consultas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.walking.spi.AttributeDefinition;

import baseDatos.hibernate.tablas.ValorIndicador;

public class ValorIndicadorDAO extends DAO {

	public void guardar(ValorIndicador valorIndicador) {
		try {
			iniciaOperacion();
			sesion.save(valorIndicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}

	}

	public void actualizar(ValorIndicador valorIndicador) {
		try {
			iniciaOperacion();
			sesion.update(valorIndicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

	public void eliminar(ValorIndicador valorIndicador) {
		try {
			iniciaOperacion();
			sesion.delete(valorIndicador);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ValorIndicador> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM ValorIndicador vi";
			Query query = sesion.createQuery(hql);
			List<ValorIndicador> results = query.list();
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
					.getClassMetadata(ValorIndicador.class.getName()));
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
