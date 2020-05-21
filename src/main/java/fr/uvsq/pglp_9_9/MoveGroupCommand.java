package fr.uvsq.pglp_9_9;

public class MoveGroupCommand implements Command {

	private int x, y;
	private Groupe gr;

	public MoveGroupCommand(int x, int y, Groupe gr) {
		this.x = x;
		this.y = y;
		this.setGr(gr);

	}

	public Groupe getGr() {
		return gr;
	}

	public void setGr(Groupe gr) {
		this.gr = gr;
	}

	@Override
	public void execute() {
		// gr.decaler(x, y);
		for (Form forme : gr.getForms()) {
			DaplacerCommand de = new DaplacerCommand(x, y, forme);
			de.execute();
		}
		/*
		 * if (forme instanceof Carre) { try { dao.getCarreDAO().update((Carre) forme);
		 * } catch (ClassNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * } else if (forme instanceof Cercle) { try {
		 * dao.getCercleDAO().update((Cercle) forme); } catch (ClassNotFoundException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * } else if (forme instanceof Rectangle) { try {
		 * dao.getRectangleDAO().update((Rectangle) forme); } catch
		 * (ClassNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * } else if (forme instanceof Triangle) { try {
		 * dao.getTriangleDAO().update((Triangle) forme); } catch
		 * (ClassNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * } else if (forme instanceof Groupe) { try {
		 * dao.getGroupeDAO().update((Groupe) forme); } catch (ClassNotFoundException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
		 */
	}

}
