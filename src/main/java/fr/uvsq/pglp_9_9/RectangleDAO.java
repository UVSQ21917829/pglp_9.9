package fr.uvsq.pglp_9_9;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RectangleDAO extends DAO<Rectangle> {

	@Override
	public Rectangle create(Rectangle ractangle) throws IOException {
		this.createConnection();
		try (PreparedStatement prepareRec = this.connection
				.prepareStatement("INSERT INTO rectangle (nom, x,y,largeur,longueur) VALUES(?, ?, ?, ?, ?)");) {

			prepareRec.setString(1, ractangle.getNom());
			prepareRec.setInt(2, ractangle.getCentreGravite().getX());
			prepareRec.setInt(3, ractangle.getCentreGravite().getY());
			prepareRec.setInt(4, ractangle.getLargeur());
			prepareRec.setInt(5, ractangle.getLongueur());
			prepareRec.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		this.closeConnexion();
		return null;
	}

	@Override
	public Rectangle read(String nom) throws ClassNotFoundException, IOException {
		Rectangle rect = null;
		this.createConnection();
		try (PreparedStatement statmRectangle = this.connection
				.prepareStatement("SELECT * FROM rectangle  WHERE nom = ?")) {
			statmRectangle.setString(1, nom);
			try (ResultSet res = statmRectangle.executeQuery()) {
				if (res.next()) {
					rect = new Rectangle(res.getString("nom"),
							new Point2D(Integer.parseInt(res.getString("x")), Integer.parseInt(res.getString("y"))),
							Integer.parseInt(res.getString("largeur")), Integer.parseInt(res.getString("longueur")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.closeConnexion();
		;

		return rect;
	}

	@Override
	public Rectangle update(Rectangle rec) throws ClassNotFoundException, IOException {
		Rectangle rectangle = null;
		this.createConnection();
		try (PreparedStatement udateStatemnt = this.connection
				.prepareStatement("UPDATE rectangle SET x=?, y=?, largeur=?, longueur=? WHERE nom=?");) {

			udateStatemnt.setInt(1, rec.getCentreGravite().getX());
			udateStatemnt.setInt(2, rec.getCentreGravite().getY());
			udateStatemnt.setInt(3, rec.getLargeur());
			udateStatemnt.setInt(4, rec.getLongueur());
			udateStatemnt.setString(5, rec.getNom());
			udateStatemnt.executeUpdate();
			rectangle = this.read(rec.getNom());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rectangle;
	}

	@Override
	public void delete(String nom) throws IOException {
		this.createConnection();
		try (PreparedStatement deleteRect = this.connection.prepareStatement("DELETE FROM rectangle  WHERE nom = ?");) {
			deleteRect.setString(1, nom);
			deleteRect.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnexion();

	}

}
