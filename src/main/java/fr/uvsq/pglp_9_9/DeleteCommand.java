package fr.uvsq.pglp_9_9;

import java.io.IOException;

public class DeleteCommand implements Command{

	private Form forme;
	private FactoryDAO dao;
	public DeleteCommand(Form forme) {
		this.setForme(forme);
		dao= new FactoryDAO();
	}
	public Form getForme() {
		return forme;
	}
	public void setForme(Form forme) {
		this.forme = forme;
	}
	
	@Override
	public void execute() {
	
		if (forme instanceof Carre) {
			try {
				dao.getCarreDAO().delete( forme.getNom());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} else if (forme instanceof Cercle) {
			try {
				dao.getCercleDAO().delete( forme.getNom());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (forme instanceof Rectangle) {
			try {
				dao.getRectangleDAO().delete( forme.getNom());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (forme instanceof Triangle) {
			try {
				dao.getTriangleDAO().delete( forme.getNom());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (forme instanceof Groupe) {
			try {
				dao.getGroupeDAO().delete( forme.getNom());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
	}

}
