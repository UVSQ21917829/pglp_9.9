package fr.uvsq.pglp_9_9;

import java.io.IOException;

public class DeleteCommand implements Command {

	private String nom;
	private Form forme;
	private Groupe dessin;
	private FactoryDAO dao;

	public DeleteCommand(String npm, Groupe dessin) {
		this.setForme(forme);
		this.dessin = dessin;
		dao = new FactoryDAO();
	}

	public Form getForme() {
		return forme;
	}

	public void setForme(Form forme) {
		this.forme = forme;
	}

	public boolean exist(Form forme2) {
		for (Form form : dessin.getForms()) {
			if (form.getNom().equals(forme2.getNom())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void execute() {

		if (!this.exist(this.forme)) {

			for (int i = 0; i < dessin.getForms().size(); i++) {
				if (dessin.getForms().get(i).getNom().equals(nom)) {
					dessin.getForms().remove(i);
				}
			}
			if (forme instanceof Carre) {
				try {

					dao.getCarreDAO().delete(nom);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (forme instanceof Cercle) {
				try {
					dao.getCercleDAO().delete(nom);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (forme instanceof Rectangle) {
				try {
					dao.getRectangleDAO().delete(nom);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (forme instanceof Triangle) {
				try {
					dao.getTriangleDAO().delete(nom);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (forme instanceof Groupe) {
				try {
					dao.getGroupeDAO().delete(nom);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
