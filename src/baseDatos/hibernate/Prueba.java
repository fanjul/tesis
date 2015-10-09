package baseDatos.hibernate;

import baseDatos.hibernate.consultas.EstadosXTipoIndicadorDAO;
import baseDatos.hibernate.consultas.GraficoDAO;
import baseDatos.hibernate.consultas.HSTumbralesXEstadosXIndicadorDAO;
import baseDatos.hibernate.consultas.IndicadorDAO;
import baseDatos.hibernate.consultas.PersonaDAO;
import baseDatos.hibernate.consultas.TableroDAO;
import baseDatos.hibernate.consultas.TipoIndicadorDAO;
import baseDatos.hibernate.consultas.UmbralesXEstadosXIndicadorDAO;
import baseDatos.hibernate.consultas.UnidadesDeMedidaDAO;
import baseDatos.hibernate.consultas.ValorIndicadorDAO;
import baseDatos.hibernate.tablas.EstadosXTipoIndicador;
import baseDatos.hibernate.tablas.Grafico;
import baseDatos.hibernate.tablas.HSTumbralesXEstadosXIndicador;
import baseDatos.hibernate.tablas.Indicador;
import baseDatos.hibernate.tablas.Persona;
import baseDatos.hibernate.tablas.Tablero;
import baseDatos.hibernate.tablas.TipoIndicador;
import baseDatos.hibernate.tablas.UmbralesXEstadosXIndicador;
import baseDatos.hibernate.tablas.UnidadesDeMedida;
import baseDatos.hibernate.tablas.ValorIndicador;

public class Prueba {

	
	public static void main(String[] args) {		
		EstadosXTipoIndicador estados = new EstadosXTipoIndicador();
		Grafico grafico = new Grafico();
		HSTumbralesXEstadosXIndicador hst = new HSTumbralesXEstadosXIndicador();
		Indicador indicador = new Indicador(); 
		Indicador indicador2 = new Indicador();

		Persona persona = new Persona();
		TipoIndicador tipoIndicador = new TipoIndicador();
		UmbralesXEstadosXIndicador umbral = new UmbralesXEstadosXIndicador();
		UnidadesDeMedida unidad = new UnidadesDeMedida();
		ValorIndicador valor = new ValorIndicador();
		Tablero tablero = new Tablero();


		EstadosXTipoIndicadorDAO estadosDao = new EstadosXTipoIndicadorDAO();
		GraficoDAO graficoDao = new GraficoDAO();
		HSTumbralesXEstadosXIndicadorDAO hstDao = new HSTumbralesXEstadosXIndicadorDAO();
		IndicadorDAO indicadorDao = new IndicadorDAO();
		PersonaDAO personaDao = new PersonaDAO();
		TipoIndicadorDAO tipoIndicadorDao = new TipoIndicadorDAO();
		UmbralesXEstadosXIndicadorDAO umbralDao = new UmbralesXEstadosXIndicadorDAO();
		UnidadesDeMedidaDAO unidadDao = new UnidadesDeMedidaDAO();
		ValorIndicadorDAO valorDao = new ValorIndicadorDAO();
		TableroDAO tableroDao = new TableroDAO();

		//setear datos en las tablas
		grafico.setId(1);
		grafico.setObservaciones("asd");
		graficoDao.guardar(grafico);
		
		grafico.setId(2);
		graficoDao.actualizar(grafico);
//		
//		indicador.setCodigo("asd");
//		indicador.setId(1);
//		indicador.setGrafico(grafico);
//		indicador2.setCodigo("asdf2");
//		indicador2.setId(5);
//		
//		indicadorDao.guardar(indicador);
//		indicadorDao.guardar(indicador2);

//		tipoIndicador.setId(2);
//		tipoIndicador.setObservaciones("el corvi no entiende nada. Fanjul menos... Guille Z");
//		tipoIndicador.setTipo("De la vida");
//		tipoIndicadorDao.guardar(tipoIndicador);
//
//		estados.setIdEstado(2);
//		estados.setIdTipoIndicador(2);
//	estadosDao.guardar(estados);
//
//		
	//	umbral.setFinUmbral(new Timestamp(0));
	//	umbral.setIdEstadoTipoIndicador(4);
	//	umbral.setIdIndicador(1);
	//	umbral.setEstadosXTipoIndicador(estados);
	//	umbral.setIndicador(indicador);
//		
	//	umbralDao.guardar(umbral);
//		
//		umbral.setFinUmbral(new Timestamp(0));
//		umbral.setIdEstadoTipoIndicador(4);
//		umbral.setIdIndicador(5);
//		//umbral.setEstadosXTipoIndicador(estados);
//		umbral.setIndicador(indicador2);
//		umbralDao.guardar(umbral);

//		
//		
//
//		grafico.setObservaciones("joya");
//		
//		hst.setIdIndicador(40);
//		hst.setIdEstadoTipoIndicador(40);
//
//		hst.setInicioUmbral(new Timestamp(0));
//		hst.setFinUmbral(new Timestamp(0));
//
//		
//
//		persona.setApellido("Corvi");
//

//
//		
//
//		unidad.setUnidadDeMedida("centimetro");
//
//		
//		valor.setIdIndicador(10);
//		valor.setFecha(new Timestamp(0));


		//realizar las consultas de las tablas: GUARDAR
//		graficoDao.guardar(grafico);
//		hstDao.guardar(hst);	
//		tableroDao.guardar(tablero);

	


//		personaDao.guardar(persona);
//		tipoIndicadorDao.guardar(tipoIndicador);
//		unidadDao.guardar(unidad);
//		valorDao.guardar(valor);

		//ELIMINAR
//		estadosDao.eliminar(estados);
//		graficoDao.eliminar(grafico);
//		hstDao.eliminar(hst);	
//		indicadorDao.eliminar(indicador);
//		personaDao.eliminar(persona);
//		tipoIndicadorDao.eliminar(tipoIndicador);
//		umbralDao.eliminar(umbral);
//		unidadDao.eliminar(unidad);
//		valorDao.eliminar(valor);

	}

}
