package baseDatos;

import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBIndicador {

	public DBIndicador() {

	}
	
	
	
	
	
	

//	public Object[] getIndicatorNames() {
//		DBManager dbM = DBManager.instance();
//		ResultSet rs = dbM.executeQuery("SELECT Nombre FROM Indicador");
//		ArrayList<String> result = new ArrayList<>();
//		try {
//			while (rs.next()) {
//				result.add(rs.getString("Nombre"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result.toArray();
//	}
	
	public Object[] getIndicatorNames() {
		DBManager dbM = DBManager.instance();
		ResultSet rs = dbM.executeQuery("SELECT Codigo,Nombre FROM Indicador");
		ArrayList<Item<String>> result = new ArrayList<Item<String>>();
		try {
			while (rs.next()) {
				result.add(new Item(rs.getString("Codigo"),rs.getString("Nombre")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toArray();
	}

	public int getIdByName(String name) {
		DBManager dbM = DBManager.instance();
		ResultSet rs = dbM
				.executeQuery("SELECT Id FROM Indicador WHERE Nombre = " + "'"
						+ name + "'");
		int result = -1;
		try {
			rs.next();
			result = rs.getInt("Id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String getPeriodo(int idIndicador) {
		DBManager dbM = DBManager.instance();
		ResultSet rs = dbM
				.executeQuery("SELECT Periodo FROM Indicador WHERE Id = " + "'"
						+ idIndicador + "'");
		String result = "";
		try {
			rs.next();
			result = rs.getString("Periodo");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int getDireccion(int idIndicador) {
		DBManager dbM = DBManager.instance();
		ResultSet rs = dbM
				.executeQuery("SELECT Direccion FROM Indicador WHERE Id = " + "'"
						+ idIndicador + "'");
		int result = -1;
		try {
			rs.next();
			result = rs.getInt("Direccion");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
