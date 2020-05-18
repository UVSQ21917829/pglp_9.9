package fr.uvsq.pglp_9_9;

public class Cercle extends Form{
    private Point2D centre;
    private int rayon;
	public Cercle(String nom,Point2D centre,int rayon) {
		super(nom);
		this.setCentre(centre);
		this.setRayon(rayon);
	}

	public Point2D getCentre() {
		return centre;
	}

	public void setCentre(Point2D centre) {
		this.centre = centre;
	}

	public int getRayon() {
		return rayon;
	}

	public void setRayon(int rayon) {
		this.rayon = rayon;
	}

	@Override
	public void afficher() {
		System.out.println(this.getNom() +"= Cercle(("+getCentre().getX()+"," +getCentre().getY()+")," +getRayon()+")");
		
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
