package baseDatos.hibernate.consultas;

import java.util.ArrayList;
import java.util.List;

import baseDatos.hibernate.tablas.EstadosXTipoIndicador;
import baseDatos.hibernate.tablas.Grafico;
import baseDatos.hibernate.tablas.HSTumbralesXEstadosXIndicador;
import baseDatos.hibernate.tablas.Indicador;
import baseDatos.hibernate.tablas.Persona;
import baseDatos.hibernate.tablas.TipoIndicador;
import baseDatos.hibernate.tablas.UmbralesXEstadosXIndicador;
import baseDatos.hibernate.tablas.UnidadesDeMedida;
import baseDatos.hibernate.tablas.ValorIndicador;

public class FactoryConsultas {

	public Object getConsultaDAO(String nombre) {

		if ("ValorIndicador".equalsIgnoreCase(nombre)) {
			return new ValorIndicadorDAO();
		} else if ("UnidadesDeMedida".equalsIgnoreCase(nombre)) {
			return new UnidadesDeMedidaDAO();
		} else if ("UmbralesXEstadosXIndicador".equalsIgnoreCase(nombre)) {
			return new UmbralesXEstadosXIndicadorDAO();
		} else if ("TipoIndicador".equalsIgnoreCase(nombre)) {
			return new TipoIndicadorDAO();
		} else if ("Persona".equalsIgnoreCase(nombre)) {
			return new PersonaDAO();
		} else if ("Indicador".equalsIgnoreCase(nombre)) {
			return new IndicadorDAO();
		} else if ("HSTumbralesXEstadosXIndicador".equalsIgnoreCase(nombre)) {
			return new HSTumbralesXEstadosXIndicadorDAO();
		} else if ("Grafico".equalsIgnoreCase(nombre)) {
			return new GraficoDAO();
		} else if ("EstadosXTipoIndicador".equalsIgnoreCase(nombre)) {
			return new EstadosXTipoIndicadorDAO();
		} else
			return null;
	}

	public List<?> getLista(String nombre) {
		if ("ValorIndicador".equalsIgnoreCase(nombre)) {
			return new ArrayList<ValorIndicador>();
		} else if ("UnidadesDeMedida".equalsIgnoreCase(nombre)) {
			return new ArrayList<UnidadesDeMedida>();
		} else if ("UmbralesXEstadosXIndicador".equalsIgnoreCase(nombre)) {
			return new ArrayList<UmbralesXEstadosXIndicador>();
		} else if ("TipoIndicador".equalsIgnoreCase(nombre)) {
			return new ArrayList<TipoIndicador>();
		} else if ("Persona".equalsIgnoreCase(nombre)) {
			return new ArrayList<Persona>();
		} else if ("Indicador".equalsIgnoreCase(nombre)) {
			return new ArrayList<Indicador>();
		} else if ("HSTumbralesXEstadosXIndicador".equalsIgnoreCase(nombre)) {
			return new ArrayList<HSTumbralesXEstadosXIndicador>();
		} else if ("Grafico".equalsIgnoreCase(nombre)) {
			return new ArrayList<Grafico>();
		} else if ("EstadosXTipoIndicador".equalsIgnoreCase(nombre)) {
			return new ArrayList<EstadosXTipoIndicador>();
		} else
			return null;
	}
}
