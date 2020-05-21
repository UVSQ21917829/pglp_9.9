package fr.uvsq.pglp_9_9;

import java.io.IOException;
import java.util.Iterator;

public class DeleteCommand implements Command {

	private String nom;
	private Form forme;
	private Groupe dessin;
	private FactoryDAO dao;

	public DeleteCommand(String nom, Groupe dessin) {
		this.dessin = dessin;
		dao = new FactoryDAO();
		this.forme=getForm();
		this.nom = nom;
	}

	

	public boolean exist() {
		for (Form form : dessin.getForms()) {
			if (form.getNom().equals(nom)) {
				// System.out.println("show:"+form.getNom()+" " +nom);
				return true;
			}
		}

		return false;
	}

	public Form getForm() {
		for (Form form : dessin.getForms()) {
			System.out.println("show:" + form.getNom() + " " + nom);
			if (form.getNom().equals(nom)) {
				return form;
			}
		}

		return null;
	}

	@Override
	public void execute() {

		if (this.exist() || !nom.equals(dessin.getNom())) {

			for (Iterator<Form> iter = dessin.getForms().listIterator(); iter.hasNext();) {
				Form a = iter.next();
				// System.out.println("show:"+a.getNom());
				if (a.getNom().equals(nom)) {
					iter.remove();
				}

			}

			if (forme instanceof Carre) {
				try {

					dao.getCarreDAO().delete(nom);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if (forme instanceof Cercle) {
				try {
					dao.getCercleDAO().delete(nom);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if (forme instanceof Rectangle) {
				try {
					dao.getRectangleDAO().delete(nom);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if (forme instanceof Triangle) {
				try {
					dao.getTriangleDAO().delete(nom);
				} catch (IOException e) { // TODO
					e.printStackTrace();
				}

			} else if (forme instanceof Groupe) {
				try {
					dao.getGroupeDAO().delete(nom);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
