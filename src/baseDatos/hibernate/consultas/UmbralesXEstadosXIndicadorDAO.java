package baseDatos.hibernate.consultas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.persister.entity.AbstractEntityPersister;

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
			String[] properties = aep.getPropertyNames();
			List<String> columnNames = new ArrayList<String>();
			for (String[] arrayS : aep.getContraintOrderedTableKeyColumnClosure()) {
				columnNames.addAll(Arrays.asList(arrayS));

			}

			for (int nameIndex = 0; nameIndex != properties.length; nameIndex++) {
				String[] columns = aep.getPropertyColumnNames(nameIndex);
				for (int columnIndex = 0; columnIndex != columns.length; columnIndex++) {
					columnNames.add(columns[columnIndex]);
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
