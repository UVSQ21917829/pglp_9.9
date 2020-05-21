package fr.uvsq.pglp_9_9;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CercleDAO extends DAO<Cercle> {

	@Override
	public Cercle create(Cercle cercle) throws IOException {
		this.createConnection();
		try (PreparedStatement prepareCarre = this.connection
				.prepareStatement("INSERT INTO cercle (nom, x,y,rayon) VALUES(?, ?, ?, ?)");) {

			prepareCarre.setString(1, cercle.getNom());
			prepareCarre.setInt(2, cercle.getCentre().getX());
			prepareCarre.setInt(3, cercle.getCentre().getY());
			prepareCarre.setInt(4, cercle.getRayon());
			prepareCarre.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		this.closeConnexion();
		return cercle;
	}

	@Override
	public Cercle read(String nom) throws ClassNotFoundException, IOException {
		Cercle cercle = null;
		this.createConnection();
		try (PreparedStatement carrPr = this.connection.prepareStatement("SELECT * FROM cercle WHERE nom = ?")) {
			carrPr.setString(1, nom);
			try (ResultSet res = carrPr.executeQuery()) {
				if (res.next()) {
					cercle = new Cercle(res.getString("nom"),
							new Point2D(Integer.parseInt(res.getString("x")), Integer.parseInt(res.getString("y"))),
							Integer.parseInt(res.getString("rayon")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.closeConnexion();
		;

		return cercle;
	}

	@Override
	public Cercle update(Cercle cerc) throws ClassNotFoundException, IOException {
		Cercle cercle = null;
		this.createConnection();
		try (PreparedStatement statementCercle = this.connection
				.prepareStatement("UPDATE cercle SET x=?, y=?, rayon=? WHERE nom=?");) {

			statementCercle.setInt(1, cerc.getCentre().getX());
			statementCercle.setInt(2, cerc.getCentre().getY());
			statementCercle.setInt(3, cerc.getRayon());
			statementCercle.setString(4, cerc.getNom());
			statementCercle.executeUpdate();
			cercle = this.read(cerc.getNom());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cercle;
	}

	@Override
	public void delete(String nom) throws IOException {
		this.createConnection();
		try (PreparedStatement statementCercle = this.connection
				.prepareStatement("DELETE FROM cercle  WHERE nom = ?");) {
			statementCercle.setString(1, nom);
			statementCercle.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnexion();

	}

}
