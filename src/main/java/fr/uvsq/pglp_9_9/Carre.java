package fr.uvsq.pglp_9_9;

public class Carre extends Form {

	private Point2D centreGravite;
	private int cote;

	@Override
	public void afficher() {
		System.out.println(this.getNom() + "= Carre((" + this.getCentreGravite().getX() + ","
				+ this.getCentreGravite().getY() + ")," + this.getCote() + ")");

	}

	@Override
	public void deplacer(int x, int y) {
		centreGravite.deplaccer(x, y);

	}

	@Override
	public void decaler(int x, int y) {
		this.centreGravite.decaler(x, y);

	}

	public Carre(String nom, Point2D centreGravite, int cote) {
		super(nom);
		this.centreGravite = centreGravite;
		this.cote = cote;
	}

	public Point2D getCentreGravite() {
		return centreGravite;
	}

	public void setCentreGravite(Point2D centreGravite) {
		this.centreGravite = centreGravite;
	}

	public int getCote() {
		return cote;
	}

	public void setCote(int cote) {
		this.cote = cote;
	}

}
