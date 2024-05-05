package JDBCProjet.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBCProjet.beans.Client;
import JDBCProjet.connexion.Connexion;
import JDBCProjet.dao.IDao;

public class ClientService implements IDao<Client> {

	@Override
	public boolean create(Client c) {
		try {
			Connection conn = Connexion.getConnection();
			Statement stmt = conn.createStatement();

			try {
				String query = "INSERT INTO client(nom,prenom) VALUES( '" + c.getNom() + "','" + c.getPrenom() + "')";

				int cl = stmt.executeUpdate(query);

				return cl > 0;
			} catch (SQLException e) {
				System.err.println("Error executing query: " + e.getMessage());
				return false;
			}
		} catch (SQLException e) {
			System.err.println("Error creating SQL statement: " + e.getMessage());

			return false;

		}
	}

	@Override
	public List<Client> findAll() {
		try {
			Connection conn = Connexion.getConnection();
			Statement stmt = conn.createStatement();
			try {
				List<Client> l = new ArrayList<>();
				String query = "SELECT * from client";
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					l.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3)));

				}
				return l;
			} catch (SQLException e) {
				System.err.println("Error executing query: " + e.getMessage());
				return null;
			}

		} catch (SQLException e) {
			System.err.println("Error creating SQL statement: " + e.getMessage());
			return null;
		}
	}

	@Override
	public Client findById(int id) {
		try {
			Connection conn = Connexion.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * from client WHERE id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return new Client(rs.getInt(1), rs.getString(2), rs.getString(3));
		} catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
			return null;
		}

	}

	

	@Override
	public boolean delete(Client c) {
		try {
			Connection conn = Connexion.getConnection();
			PreparedStatement stmt = conn.prepareStatement("delete from client WHERE id=?");
			stmt.setInt(1, c.getId());
			stmt.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(Client c) {
		try {
			Connection conn = Connexion.getConnection();
			PreparedStatement stmt = conn.prepareStatement("update client set nom=? where id = ?");
			stmt.setInt(2, c.getId());
			stmt.setString(1,c.getNom());
			stmt.executeUpdate();
			return true;
		}
		catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
			return false;
		}
	}

}
