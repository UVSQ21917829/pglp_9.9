package fr.uvsq.pglp_9_9;



public class CreateCommand implements Command {

	private Form forme;
	private Groupe dessin;
	private FactoryDAO dao;

	public CreateCommand(Form forme, Groupe dessin) {

		this.setForme(forme);
		this.setDessin(dessin);
		dao = new FactoryDAO();
	}

	public Form getForme() {
		return forme;
	}

	public void setForme(Form forme) {
		this.forme = forme;
	}

	public Groupe getDessin() {
		return dessin;
	}

	public void setDessin(Groupe dessin) {
		this.dessin = dessin;
	}

	@Override
	public void execute() {
		if (!this.exist(this.forme)) {
			dessin.addForm(forme);
			/*if (forme instanceof Carre) {
				try {
					dao.getCarreDAO().create((Carre) forme);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (forme instanceof Cercle) {
				try {
					dao.getCercleDAO().create((Cercle) forme);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (forme instanceof Rectangle) {
				try {
					dao.getRectangleDAO().create((Rectangle) forme);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (forme instanceof Triangle) {
				try {
					dao.getTriangleDAO().create((Triangle) forme);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (forme instanceof Groupe) {
				try {
					dao.getGroupeDAO().create((Groupe) forme);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
		}

	}

	public boolean exist(Form forme2) {
		for (Form form : dessin.getForms()) {
			if (form.getNom().equals(forme2.getNom())) {
				return true;
			}
		}

		return false;
	}

}
