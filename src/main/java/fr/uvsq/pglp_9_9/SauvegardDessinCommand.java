package fr.uvsq.pglp_9_9;

import java.io.IOException;

public class SauvegardDessinCommand implements Command{

	private Groupe groupe;
	
	private FactoryDAO dao;
	public SauvegardDessinCommand(Groupe groupe) {
		super();
		this.groupe = groupe;
		this.dao = new FactoryDAO();
	}
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	public FactoryDAO getDao() {
		return dao;
	}
	public void setDao(FactoryDAO dao) {
		this.dao = dao;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
			dao.getGroupeDAO().create(this.groupe);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
