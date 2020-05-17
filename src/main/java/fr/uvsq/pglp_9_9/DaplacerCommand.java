package fr.uvsq.pglp_9_9;

import java.io.IOException;

public class DaplacerCommand implements Command{
	private int x,y;
	private Form forme;
	private FactoryDAO dao;
	public DaplacerCommand(int x,int y,Form forme) {
		this.x=x;
		this.y=y;
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
		forme.decaler(x, y);
		if (forme instanceof Carre) {
			try {
				dao.getCarreDAO().update((Carre) forme);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} else if (forme instanceof Cercle) {
			try {
				dao.getCercleDAO().update((Cercle) forme);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (forme instanceof Rectangle) {
			try {
				dao.getRectangleDAO().update((Rectangle) forme);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (forme instanceof Triangle) {
			try {
				dao.getTriangleDAO().update((Triangle) forme);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (forme instanceof Groupe) {
			try {
				dao.getGroupeDAO().update((Groupe) forme);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
	}
}
