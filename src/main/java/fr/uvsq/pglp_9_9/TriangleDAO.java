package fr.uvsq.pglp_9_9;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TriangleDAO extends DAO<Triangle> {

	@Override
	public Triangle create(Triangle traingle) throws IOException {
		this.createConnection();
		try (PreparedStatement statemnt = this.connection
				.prepareStatement("INSERT INTO triangle (nom, x1,y1,x2,y2,x3,y3) VALUES(?, ?, ?, ?, ?,?)");) {

			statemnt.setString(1, traingle.getNom());
			statemnt.setInt(2, traingle.getPoint1().getX());
			statemnt.setInt(3, traingle.getPoint1().getY());
			statemnt.setInt(4, traingle.getPoint2().getX());
			statemnt.setInt(5, traingle.getPoint2().getY());
			statemnt.setInt(6, traingle.getPoint3().getX());
			statemnt.setInt(7, traingle.getPoint3().getY());
			
			statemnt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		this.closeConnexion();
		return traingle;
	}

	@Override
	public Triangle read(String nom) throws ClassNotFoundException, IOException {
		Triangle traingle = null;
		this.createConnection();
		try (PreparedStatement statmment = this.connection
				.prepareStatement("SELECT * FROM triangle  WHERE nom = ?")) {
			statmment.setString(1, nom);
			try (ResultSet res = statmment.executeQuery()) {
				if (res.next()) {
					traingle = new Triangle(res.getString("nom"),
							new Point2D(Integer.parseInt(res.getString("x1")), Integer.parseInt(res.getString("y1"))),
							new Point2D(Integer.parseInt(res.getString("x2")), Integer.parseInt(res.getString("y2"))),
							new Point2D(Integer.parseInt(res.getString("x3")), Integer.parseInt(res.getString("y3"))));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.closeConnexion();
		;

		return traingle;
	}

	@Override
	public Triangle update(Triangle traingle) throws ClassNotFoundException, IOException {
		Triangle tria = null;
		this.createConnection();
		try (PreparedStatement statemnt = this.connection
				.prepareStatement("UPDATE triangle SET x1=?, y2=?, x2=?, y1=?,x3=?, y3=? WHERE nom=?");) {

			statemnt.setInt(1, traingle.getPoint1().getX());
			statemnt.setInt(2, traingle.getPoint1().getY());
			statemnt.setInt(3, traingle.getPoint2().getX());
			statemnt.setInt(4, traingle.getPoint2().getY());
			statemnt.setInt(5, traingle.getPoint3().getX());
			statemnt.setInt(6, traingle.getPoint3().getY());
			statemnt.setString(7, traingle.getNom());
			statemnt.executeUpdate();
			tria = this.read(traingle.getNom());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tria;
	}

	@Override
	public void delete(String nom) throws IOException {
		this.createConnection();
		try (PreparedStatement statement = this.connection.prepareStatement("DELETE FROM triangle  WHERE nom = ?");) {
			statement.setString(1, nom);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnexion();
		
	}

}
