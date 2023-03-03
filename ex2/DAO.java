package bd_carros;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO () {
		conexao = null;
	}
	
	public boolean conectar () {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "carros";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;
		
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}
		
		return status;
	}
	
	public boolean close () {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return status;
	}
	
	public boolean inserirCarro (Carro carro) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO \"carro\"" + "VALUES (" + carro.getId() + ", '" + carro.getNomeDono() + "', '" + carro.getModelo() + "', '" + carro.getPlaca() + "');");
			st.close();
			status = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return status;
	}
	
	public boolean atualizarCarro (Carro carro) {
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE \"carro\" SET \"nomeDono\" = '" + carro.getNomeDono() + "', \"modelo\" = '" + carro.getModelo() + "', \"placa\" = '" + carro.getPlaca() + "' WHERE id = " + carro.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}
	
	public boolean excluirCarro (int id) {
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM carro WHERE id = " + id; 
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}
	
	public Carro[] getCarros () {
		Carro[] carros = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM carro");
			if(rs.next()) {
				rs.last();
				carros = new Carro[rs.getRow()];
				rs.beforeFirst();
				
				for(int i=0; rs.next(); i++) {
					carros[i] = new Carro(rs.getInt("id"), rs.getString("nomeDono"), rs.getString("modelo"), rs.getString("placa"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return carros;
	}
	
	public Carro[] getCarroNome (String nome) {
		Carro[] carros = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM carro WHERE \"carro\".\"nomeDono\" LIKE '" + nome + "'");
			if(rs.next()) {
				rs.last();
				carros = new Carro[rs.getRow()];
				rs.beforeFirst();
				
				for(int i=0; rs.next(); i++) {
					carros[i] = new Carro(rs.getInt("id"), rs.getString("nomeDono"), rs.getString("modelo"), rs.getString("placa"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return carros;
	}
	
	public Carro[] getCarroModelo (String modelo) {
		Carro[] carros = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM carro WHERE modelo LIKE '" + modelo + "'");
			if(rs.next()) {
				rs.last();
				carros = new Carro[rs.getRow()];
				rs.beforeFirst();
				
				for(int i=0; rs.next(); i++) {
					carros[i] = new Carro(rs.getInt("id"), rs.getString("nomeDono"), rs.getString("modelo"), rs.getString("placa"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return carros;
	}
}
