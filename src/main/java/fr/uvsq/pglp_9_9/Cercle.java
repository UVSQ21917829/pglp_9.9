package fr.uvsq.pglp_9_9;

public class Cercle extends Form{
    private Point2D centre;
	public Cercle(String nom,Point2D centre) {
		super(nom);
		this.setCentre(centre);
	}

	public Point2D getCentre() {
		return centre;
	}

	public void setCentre(Point2D centre) {
		this.centre = centre;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deplacer(int x, int y) {
		centre.deplaccer(x, y);
		
	}
	@Override
	public void decaler(int x, int y) {
		this.centre.decaler(x, y);
		
	}

	

}
