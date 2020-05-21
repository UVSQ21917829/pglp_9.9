package fr.uvsq.pglp_9_9;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupeDAO extends DAO<Groupe> {

	@Override
	public Groupe create(Groupe groupe) throws IOException {
		List<Form> formesList = groupe.getForms();
		FactoryDAO dao = new FactoryDAO();

		this.createConnection();
		try (PreparedStatement statementGroupe = this.connection.prepareStatement("INSERT INTO groupe(nom) values(?)");
				PreparedStatement statementCarre = this.connection
						.prepareStatement("INSERT INTO estCarre(gr_nom, nom) VALUES(?, ?)");
				PreparedStatement statementTriangle = this.connection
						.prepareStatement("INSERT INTO estTriangle(gr_nom, nom) VALUES(?, ?)");
				PreparedStatement statementEstGroupe = this.connection
						.prepareStatement("INSERT INTO estGroupe(gr_nom, nom) VALUES(?, ?)");
				PreparedStatement statamentCercle = this.connection
						.prepareStatement("INSERT INTO estCercle(gr_nom, nom) VALUES(?, ?)");
				PreparedStatement statementRectangle = this.connection
						.prepareStatement("INSERT INTO estRectangle(gr_nom, nom) VALUES(?, ?)")) {
			// on commence par insertion du groupe
			if (dao.getGroupeDAO().read(groupe.getNom()) != null)
				dao.getGroupeDAO().update((Groupe) groupe);
			else {
				statementGroupe.setString(1, groupe.getNom());
				statementGroupe.executeUpdate();
				for (Form form : formesList) {

					if (form instanceof Carre) {

						dao.getCarreDAO().create((Carre) form);
						statementCarre.setString(1, groupe.getNom());
						statementCarre.setString(2, form.getNom());
						statementCarre.executeUpdate();
					} else if (form instanceof Cercle) {
						dao.getCercleDAO().create((Cercle) form);
						statamentCercle.setString(1, groupe.getNom());
						statamentCercle.setString(2, form.getNom());
						statamentCercle.executeUpdate();
					} else if (form instanceof Rectangle) {
						dao.getRectangleDAO().create((Rectangle) form);
						statementRectangle.setString(1, groupe.getNom());
						statementRectangle.setString(2, form.getNom());
						statementRectangle.executeUpdate();
					} else if (form instanceof Triangle) {
						dao.getTriangleDAO().create((Triangle) form);
						statementTriangle.setString(1, groupe.getNom());
						statementTriangle.setString(2, form.getNom());
						statementTriangle.executeUpdate();
					} else if (form instanceof Groupe) {
						dao.getGroupeDAO().create((Groupe) form);
						statementEstGroupe.setString(1, groupe.getNom());
						statementEstGroupe.setString(2, form.getNom());
						statementEstGroupe.executeUpdate();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnexion();
		;
		return groupe;
	}

	@Override
	public Groupe read(String nom) throws ClassNotFoundException, IOException {
		Groupe groupe = null;
		this.createConnection();

		try (PreparedStatement statementGr = this.connection.prepareStatement("SELECT * FROM groupe  WHERE nom = ?");

				PreparedStatement statementCarre = this.connection
						.prepareStatement("SELECT * FROM estCarre  WHERE gr_nom = ?");
				PreparedStatement statementTriangle = this.connection
						.prepareStatement("SELECT * FROM estTriangle  WHERE gr_nom = ?");
				PreparedStatement statementCercle = this.connection
						.prepareStatement("SELECT * FROM estCercle  WHERE gr_nom = ?");
				PreparedStatement statementRectangle = this.connection
						.prepareStatement("SELECT * FROM estRectangle  WHERE gr_nom = ?");
				PreparedStatement statementGroupe = this.connection
						.prepareStatement("SELECT * FROM estGroupe WHERE gr_nom = ?");) {
			statementGr.setString(1, nom);
			statementTriangle.setString(1, nom);
			statementCarre.setString(1, nom);
			statementRectangle.setString(1, nom);
			statementCercle.setString(1, nom);
			statementGroupe.setString(1, nom);
			try (ResultSet res = statementGr.executeQuery();
					ResultSet carres = statementCarre.executeQuery();
					ResultSet rectangles = statementRectangle.executeQuery();
					ResultSet cercles = statementCercle.executeQuery();
					ResultSet triangles = statementTriangle.executeQuery();
					ResultSet groupes = statementGroupe.executeQuery();) {

				if (res.next()) {
					groupe = new Groupe(res.getString("nom"), new ArrayList<Form>());
				}
				FactoryDAO dao = new FactoryDAO();
				while (carres.next()) {
					
					groupe.addForm((Carre) dao.getCarreDAO().read(carres.getString("nom")));
				}
				while (triangles.next()) {
					
					groupe.addForm((Triangle) dao.getTriangleDAO().read(triangles.getString("nom")));
				}

				while (cercles.next()) {
					groupe.addForm((Cercle) dao.getCercleDAO().read(cercles.getString("nom")));
				}

				while (rectangles.next()) {
					groupe.addForm((Rectangle) dao.getRectangleDAO().read(rectangles.getString("nom")));
				}

				while (groupes.next()) {
					groupe.addForm((Groupe) dao.getGroupeDAO().read(groupes.getString("nom")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.closeConnexion();
		return groupe;
	}

	@Override
	public Groupe update(Groupe groupe) throws ClassNotFoundException, IOException {
		List<Form> formesList = groupe.getForms();

		this.createConnection();
		try (// PreparedStatement udateStatemnt=this.connection.prepareStatement("UPDATE
				// PERSONNEL SET fonction=? WHERE id=?");

				PreparedStatement statementCarre = this.connection
						.prepareStatement("UPDATE carre SET x=?, y=?, cote=? WHERE nom=?");
				PreparedStatement statementTriangle = this.connection
						.prepareStatement("UPDATE triangle SET x1=?, y2=?, x2=?, y1=?,x3=?, y3=? WHERE nom=?");
				PreparedStatement statementRectangle = this.connection
						.prepareStatement("UPDATE rectangle SET x=?, y=?, largeur=?, longueur=? WHERE nom=?");
				PreparedStatement statamentCercle = this.connection
						.prepareStatement("UPDATE cercle SET x=?, y=?, rayon=? WHERE nom=?")) {
			// on commence par insertion du groupe

			for (Form form : formesList) {

				if (form instanceof Carre) {
					DAO<Carre> dao = new CarreDAO();
					if (dao.read(form.getNom()) != null)
						dao.update((Carre) form);
					else
						dao.create((Carre) form);
					// statementCarre.setString(1, groupe.getNom());
					// statementCarre.setString(2, form.getNom());
					// statementCarre.executeUpdate();
				} else if (form instanceof Cercle) {
					DAO<Cercle> dao = new CercleDAO();
					if (dao.read(form.getNom()) != null)
						dao.update((Cercle) form);
					else
						dao.create((Cercle) form);
					// statamentCercle.setString(1, groupe.getNom());
					// statamentCercle.setString(2, form.getNom());
					// statamentCercle.executeUpdate();
				} else if (form instanceof Rectangle) {
					DAO<Rectangle> dao = new RectangleDAO();
					if (dao.read(form.getNom()) != null)
						dao.update((Rectangle) form);
					else
						dao.create((Rectangle) form);
					// statementRectangle.setString(1, groupe.getNom());
					// statementRectangle.setString(2, form.getNom());
					// statementRectangle.executeUpdate();
				} else if (form instanceof Triangle) {
					DAO<Triangle> dao = new TriangleDAO();
					if (dao.read(form.getNom()) != null)
						dao.update((Triangle) form);
					else
						dao.create((Triangle) form);
					// statementTriangle.setString(1, groupe.getNom());
					// statementTriangle.setString(2, form.getNom());
					// statementTriangle.executeUpdate();
				} else if (form instanceof Groupe) {
					DAO<Groupe> dao = new GroupeDAO();
					if (dao.read(form.getNom()) != null)
						dao.update((Groupe) form);
					else
						dao.create((Groupe) form);
					// statementEstGroupe.setString(1, groupe.getNom());
					// statementEstGroupe.setString(2, form.getNom());
					// statementEstGroupe.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnexion();
		;
		return groupe;
	}

	@Override
	public void delete(String nom) throws IOException {
		// suppression en cascade
		this.createConnection();
		try (PreparedStatement deleteGroupe = this.connection.prepareStatement("DELETE FROM groupe  WHERE nom = ?");) {
			deleteGroupe.setString(1, nom);
			deleteGroupe.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnexion();
	}

}
