package baseDatos;

import java.awt.Color; 
import java.awt.Paint;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//import org.jfree.data.category.DefaultCategoryDataset;
import org.joda.time.LocalDate;

public class DBAnalisis {
	
	private int idIndicador;
	private LocalDate inicio_periodo;
	private LocalDate fin_periodo;
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public DBAnalisis(int idIndicador, LocalDate inicio_periodo, LocalDate fin_periodo) {
		this.idIndicador = idIndicador;
		this.inicio_periodo = inicio_periodo;
		this.fin_periodo = fin_periodo;
	}
	
	
	public String getDescriptionByColor(String color) {
		DBManager dbM = DBManager.instance();
		PreparedStatement stat1 = null;
		try {	
			stat1 = dbM.getConnection().prepareStatement("SELECT Estado FROM EstadosPorTipoIndicador WHERE IdTipoIndicador = ? And RepresentacionCromatica = ?");
			stat1.setInt(1, idIndicador);
			stat1.setString(2, color);
			stat1.execute();			
			ResultSet res = stat1.getResultSet();
			res.next();
			return res.getString("Estado");
		} catch(Exception e){e.printStackTrace();}
		return null;
	}
	
	
	
	
	public Paint[] getBarColors() {
		DBManager dbM = DBManager.instance();
		PreparedStatement stat1 = null;
		PreparedStatement stat2 = null;
		try {	
			stat1 = dbM.getConnection().prepareStatement("SELECT Estado FROM ValorIndicador WHERE IdIndicador = ? And Fecha >= ? And Fecha <= ? ORDER BY Fecha");
			stat1.setInt(1, idIndicador);
			stat1.setDate(2, java.sql.Date.valueOf(inicio_periodo.toString()));
			stat1.setDate(3, java.sql.Date.valueOf(fin_periodo.toString()));
			stat1.execute();
			stat2 = dbM.getConnection().prepareStatement("SELECT Count(*) AS Cuenta FROM ValorIndicador WHERE IdIndicador = ? And Fecha >= ? And Fecha <= ?");
			stat2.setInt(1, idIndicador);
			stat2.setDate(2, java.sql.Date.valueOf(inicio_periodo.toString()));
			stat2.setDate(3, java.sql.Date.valueOf(fin_periodo.toString()));
			stat2.execute();
			ResultSet count = stat2.getResultSet();
			count.next();
			int size = count.getInt("Cuenta");
			ResultSet res = stat1.getResultSet();
			Paint[] colores = new Paint[size];
			int i = 0;
			while(res.next() && i < size) {
				switch(res.getString("Estado")) {
				case "VERDE":
					colores[i] = Color.green;
				break;
				case "ROJO":
					colores[i] = Color.red;
				break;
				case "AMARILLO":
					colores[i] = Color.yellow;
				break;
				case "NARANJA":
					colores[i] = Color.orange;
				break;
				}
				
				i++;
			}
			return colores;
		} catch(Exception e){e.printStackTrace();}
		return null;
	}
	
	public TablaResultados recuperarDatos() {
		DBManager dbM = DBManager.instance();
		PreparedStatement stat1 = null;
		PreparedStatement stat2 = null;
		try {	
			stat1 = dbM.getConnection().prepareStatement("SELECT Fecha,Valor,Estado FROM ValorIndicador WHERE IdIndicador = ? And Fecha >= ? And Fecha <= ? ORDER BY Fecha");
			stat1.setInt(1, idIndicador);
			stat1.setDate(2, java.sql.Date.valueOf(inicio_periodo.toString()));
			stat1.setDate(3, java.sql.Date.valueOf(fin_periodo.toString()));
			stat1.execute();
			stat2 = dbM.getConnection().prepareStatement("SELECT Count(*) AS Cuenta FROM ValorIndicador WHERE IdIndicador = ? And Fecha >= ? And Fecha <= ?");
			stat2.setInt(1, idIndicador);
			stat2.setDate(2, java.sql.Date.valueOf(inicio_periodo.toString()));
			stat2.setDate(3, java.sql.Date.valueOf(fin_periodo.toString()));
			stat2.execute();
			ResultSet count = stat2.getResultSet();
			count.next();
			int size = count.getInt("Cuenta");
			ResultSet res = stat1.getResultSet();
			String tabla[][] = new String[size][3];
			int i = 0;
			while(res.next() && i < size) {
				tabla[i][0] = df.format(res.getDate(1));
				tabla[i][1] = String.valueOf(res.getInt(2));
				tabla[i][2] = res.getString(3);
				i++;
			}
			return new TablaResultados(tabla,size);
		} catch(Exception e){e.printStackTrace();}
		return null;
	}
	
	
	
	
	


	/*public DefaultCategoryDataset getChartDataSet() {
		try {
			ResultSet rs = getValoresPeriodo();
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			while(rs.next()) {
				dataset.setValue(rs.getInt("Valor"), "Valor", rs.getDate("Fecha").toString());	
			}
			return dataset;
		} catch(Exception e){e.printStackTrace();}
		return null;
	}*/
	
	public ResultSet getValoresPeriodo() {
		DBManager dbM = DBManager.instance();
		PreparedStatement ps = null;
		try {
			ps = dbM.getConnection().prepareStatement("SELECT Valor,Fecha FROM ValorIndicador WHERE IdIndicador = ? And Fecha >= ? And Fecha <= ? ORDER BY Fecha");
			ps.setInt(1, idIndicador);
			ps.setDate(2, java.sql.Date.valueOf(inicio_periodo.toString()));
			ps.setDate(3, java.sql.Date.valueOf(fin_periodo.toString()));
			ps.execute();
			return ps.getResultSet();
		} catch(Exception e){e.printStackTrace();}
		return null;
		
	}
	
	public int getValorActualIndicador(int id) {
		DBManager dbM = DBManager.instance();
		PreparedStatement ps = null;
		try {
			ps = dbM.getConnection().prepareStatement("SELECT Valor FROM ValorIndicador WHERE IdIndicador = ? And Fecha = (SELECT MAX(Fecha) FROM ValorIndicador WHERE IdIndicador = ? And Fecha >= ? And Fecha <= ?)");
			ps.setInt(1, id);
			ps.setInt(2, id);
			ps.setDate(3, java.sql.Date.valueOf(inicio_periodo.toString()));
			ps.setDate(4, java.sql.Date.valueOf(fin_periodo.toString()));
			ps.execute();
			ResultSet rs = ps.getResultSet();
			rs.next();
			return rs.getInt("Valor");
		} catch(Exception e){e.printStackTrace();}
		return -1;
	}
	
	public String getColorIndicador(int id) {
		DBManager dbM = DBManager.instance();
		PreparedStatement ps = null;
		try {		
			ps = dbM.getConnection().prepareStatement("SELECT Estado FROM ValorIndicador WHERE IdIndicador = ? And Fecha = (SELECT MAX(Fecha) FROM ValorIndicador WHERE IdIndicador = ? And Fecha >= ? And Fecha <= ?)");
			ps.setInt(1, id);
			ps.setInt(2, id);
			ps.setDate(3, java.sql.Date.valueOf(inicio_periodo.toString()));
			ps.setDate(4, java.sql.Date.valueOf(fin_periodo.toString()));
			ps.execute();
			ResultSet rs = ps.getResultSet();
			rs.next();
			return rs.getString("Estado");
		} catch(Exception e){e.printStackTrace();}
		return null;
	}

}
