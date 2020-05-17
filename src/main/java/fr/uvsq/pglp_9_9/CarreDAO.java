package fr.uvsq.pglp_9_9;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarreDAO extends DAO<Carre> {

	@Override
	public Carre create(Carre carre) throws IOException {
		this.createConnection();
		try (PreparedStatement prepareCarre = this.connection
				.prepareStatement("INSERT INTO carre (nom, x,y,cote) VALUES(?, ?, ?, ?)");) {

			prepareCarre.setString(1, carre.getNom());
			prepareCarre.setInt(2, carre.getCentreGravite().getX());
			prepareCarre.setInt(3, carre.getCentreGravite().getY());
			prepareCarre.setInt(4, carre.getCote());
			prepareCarre.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		this.closeConnexion();
		return null;
	}

	@Override
	public Carre read(String nom) throws ClassNotFoundException, IOException {
		Carre carre = null;
		this.createConnection();
		try (PreparedStatement carrPr = this.connection.prepareStatement("SELECT * FROM carre R WHERE nom = ?")) {
			carrPr.setString(1, nom);
			try (ResultSet res = carrPr.executeQuery()) {
				if (res.next()) {
					carre = new Carre(res.getString("nom"),
							new Point2D(Integer.parseInt(res.getString("x")), Integer.parseInt(res.getString("y"))),
							Integer.parseInt(res.getString("cote")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.closeConnexion();
		;

		return carre;
	}

	@Override
	public Carre update(Carre obj) throws ClassNotFoundException, IOException {
		Carre carre = null;
		this.createConnection();
		try (PreparedStatement udateStatemnt = this.connection
				.prepareStatement("UPDATE PERSONNEL SET fonction=? WHERE id=?");) {

			udateStatemnt.setInt(1, obj.getCentreGravite().getX());
			udateStatemnt.setInt(2, obj.getCentreGravite().getY());
			udateStatemnt.setInt(3, obj.getCote());
			udateStatemnt.executeUpdate();
			carre = this.read(obj.getNom());
		} catch (SQLException e) {
			// TODO: handle exception

		}
		return carre;
	}

	@Override
	public void delete(String nom) throws IOException {
		this.createConnection();
		try (PreparedStatement deleteCarre = this.connection.prepareStatement("DELETE FROM carre  WHERE nom = ?");) {
			deleteCarre.setString(1, nom);
			deleteCarre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnexion();

	}

}
