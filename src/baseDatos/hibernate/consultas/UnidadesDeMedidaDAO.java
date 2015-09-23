package baseDatos.hibernate.consultas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.persister.entity.AbstractEntityPersister;

import baseDatos.hibernate.tablas.UnidadesDeMedida;

public class UnidadesDeMedidaDAO extends DAO {
	public void guardar(UnidadesDeMedida unidadesDeMedida) {
		try {
			iniciaOperacion();
			sesion.save(unidadesDeMedida);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		
	}

	public void actualizar(UnidadesDeMedida unidadesDeMedida) {
		try {
			iniciaOperacion();
			sesion.update(unidadesDeMedida);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	public void eliminar(UnidadesDeMedida unidadesDeMedida) {
		try {
			iniciaOperacion();
			sesion.delete(unidadesDeMedida);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UnidadesDeMedida> getTodos() {
		try {
			this.iniciaOperacion();
			String hql = "FROM UnidadesDeMedida udms";
			Query query = sesion.createQuery(hql);
			List<UnidadesDeMedida> results = query.list();
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
					.getClassMetadata(UnidadesDeMedida.class.getName()));
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
