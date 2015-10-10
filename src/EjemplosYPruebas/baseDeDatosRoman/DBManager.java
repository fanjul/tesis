package EjemplosYPruebas.baseDeDatosRoman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import EjemplosYPruebas.baseDeDatosRoman.properties.*;

public class DBManager {

	private static DBManager dbManager = null;

	private Connection conexion;

	private DBManager() {
		try {
			PropertyManager pManager = PropertyManager.instance();
			Class.forName(pManager.getProperty(Parameters.DB_DRIVER.toString()));
			try {
				conexion = DriverManager.getConnection(pManager
						.getProperty(Parameters.DB_NAME.toString()));
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conexion;
	}

	public static DBManager instance() {

		if (dbManager == null) {
			dbManager = new DBManager();
		}

		return dbManager;
	}

	public ResultSet executeQuery(String query) {
		Statement statement;
		ResultSet result = null;
		try {
			statement = conexion.createStatement();
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}

	public void executeUpdate(String updateStatement) {
		Statement statement;
		try {
			statement = conexion.createStatement();
			statement.executeUpdate(updateStatement);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public void insert(String insertStatement) {
		Statement statement;
		try {
			statement = conexion.createStatement();
			statement.execute(insertStatement);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void finalize() {
		try {
			if (conexion != null) {
				conexion.close();
				dbManager = null;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
