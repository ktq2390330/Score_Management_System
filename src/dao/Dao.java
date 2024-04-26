package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Dao {
	public Connection getConnection() throws Exception {
		InitialContext ic = new InitialContext();
		DataSource ds = (DataSource)ic.lookup(
				"java:/comp/env/jdbc/sm");
		Connection connection = ds.getConnection();
		return connection;
	}

}