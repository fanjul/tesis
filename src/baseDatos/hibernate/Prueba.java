package baseDatos.hibernate;

import java.math.BigDecimal;
import java.sql.Timestamp;

import baseDatos.hibernate.consultas.EstadosXTipoIndicadorDAO;
import baseDatos.hibernate.consultas.GraficoDAO;
import baseDatos.hibernate.consultas.HSTumbralesXEstadosXIndicadorDAO;
import baseDatos.hibernate.consultas.IndicadorDAO;
import baseDatos.hibernate.consultas.PersonaDAO;
import baseDatos.hibernate.consultas.TipoIndicadorDAO;
import baseDatos.hibernate.consultas.UmbralesXEstadosXIndicadorDAO;
import baseDatos.hibernate.consultas.UnidadesDeMedidaDAO;
import baseDatos.hibernate.consultas.ValorIndicadorDAO;
import baseDatos.hibernate.tablas.EstadosXTipoIndicador;
import baseDatos.hibernate.tablas.Grafico;
import baseDatos.hibernate.tablas.HSTumbralesXEstadosXIndicador;
import baseDatos.hibernate.tablas.Indicador;
import baseDatos.hibernate.tablas.Persona;
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
		Persona persona = new Persona();
		TipoIndicador tipoIndicador = new TipoIndicador();
		UmbralesXEstadosXIndicador umbral = new UmbralesXEstadosXIndicador();
		UnidadesDeMedida unidad = new UnidadesDeMedida();
		ValorIndicador valor = new ValorIndicador();
		
		
		
		EstadosXTipoIndicadorDAO estadosDao = new EstadosXTipoIndicadorDAO();
		GraficoDAO graficoDao = new GraficoDAO();
		HSTumbralesXEstadosXIndicadorDAO hstDao = new HSTumbralesXEstadosXIndicadorDAO();
		IndicadorDAO indicadorDao = new IndicadorDAO();
		PersonaDAO personaDao = new PersonaDAO();
		TipoIndicadorDAO tipoIndicadorDao = new TipoIndicadorDAO();
		UmbralesXEstadosXIndicadorDAO umbralDao = new UmbralesXEstadosXIndicadorDAO();
		UnidadesDeMedidaDAO unidadDao = new UnidadesDeMedidaDAO();
		ValorIndicadorDAO valorDao = new ValorIndicadorDAO();
		
		
		//setear datos en las tablas
		estados.setIdEstado(3);
		estados.setIdTipoIndicador(4);
		
		grafico.setObservaciones("joya");
		
		hst.setIdIndicador(40);
		hst.setIdEstadoTipoIndicador(40);
		hst.setInicioUmbral(new Timestamp(0));
		hst.setFinUmbral(new Timestamp(0));
		
		indicador.setCodigo("asd");
		indicador.setId(new BigDecimal(4));
		
		persona.setApellido("Corvi");
		
		tipoIndicador.setId(2);
		tipoIndicador.setObservaciones("el corvi no entiende nada. Fanjul menos... Guille Z");
		tipoIndicador.setTipo("De la vida");
		
		umbral.setFinUmbral(new Timestamp(0));
		umbral.setIdEstadoTipoIndicador(3);
		umbral.setIdIndicador(3);
		
		unidad.setUnidadDeMedida("centimetro");
		
		valor.setIdIndicador(10);
		valor.setFecha(new Timestamp(0));

		//realizar las consultas de las tablas: GUARDAR
		estadosDao.guardar(estados);
		graficoDao.guardar(grafico);
		hstDao.guardar(hst);	
		indicadorDao.guardar(indicador);
		personaDao.guardar(persona);
		tipoIndicadorDao.guardar(tipoIndicador);
		umbralDao.guardar(umbral);
		unidadDao.guardar(unidad);
		valorDao.guardar(valor);
		
		//ELIMINAR
		estadosDao.eliminar(estados);
		graficoDao.eliminar(grafico);
		hstDao.eliminar(hst);	
		indicadorDao.eliminar(indicador);
		personaDao.eliminar(persona);
		tipoIndicadorDao.eliminar(tipoIndicador);
		umbralDao.eliminar(umbral);
		unidadDao.eliminar(unidad);
		valorDao.eliminar(valor);
		
	}

}
