package br.com.javacrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.model.Contato;
import br.com.javacrud.jdbc.ConnectionFactory;
import br.com.javacrud.model.Cliente;

public class ClienteDao {

	private static Connection connection;

	public ClienteDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public static void create(Cliente cliente) {
		// String do banco de dados.
		String sql = "insert into clientes(nome,cpf,nascimento,situacao)values(?,?,?,?)";
		try {
			PreparedStatement psmt = connection.prepareStatement(sql);
			psmt.setString(1, cliente.getNome());
			psmt.setString(2, cliente.getCpf());
			psmt.setString(3, cliente.getNascimento());
			psmt.setString(4, cliente.getSituacao());
			// Executa a query
			psmt.execute();
			psmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void delete(Long clienteId) {
		try {
			PreparedStatement psmt = connection.prepareStatement("delete from clientes where id=?");
			psmt.setLong(1, clienteId);
			psmt.execute();
			psmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cliente> find() {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			PreparedStatement psmt = this.connection.prepareStatement("select * from clientes");
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				Cliente contato = new Cliente();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setCpf(rs.getString("email"));
				contato.setNascimento(rs.getString("endereco"));
				contato.setSituacao(rs.getString("endereco"));

				// Adicionando objetos a lista
				clientes.add(contato);
			}
			rs.close();
			psmt.close();
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static Cliente findByPk(Long clienteId) {
		return null;
	}

	public static void update(Cliente cliente) {

	}
}
