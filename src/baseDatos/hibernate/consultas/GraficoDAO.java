package baseDatos.hibernate.consultas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.walking.spi.AttributeDefinition;

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
	
	@SuppressWarnings("unchecked")
	public List<Grafico> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM Grafico gra";
			Query query = sesion.createQuery(hql);
			List<Grafico> results = query.list();
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
					.getClassMetadata(Grafico.class.getName()));
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
