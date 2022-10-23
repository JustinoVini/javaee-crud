package br.com.javacrud.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection(){
		try {
			return DriverManager.getConnection(
					"jdbc:mysql://localhost/javacrud", "root", "@Viking569038");
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados!" + e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
