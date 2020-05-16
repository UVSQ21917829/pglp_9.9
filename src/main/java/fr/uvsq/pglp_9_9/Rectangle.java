package fr.uvsq.pglp_9_9;

public class Rectangle extends Form{
    private Point2D centreGravite;
    private int longueur;
    private int largeur;
	public Rectangle(String nom,Point2D centreGravite,int longueur,int largeur) {
		super(nom);
		this.centreGravite = centreGravite;
		this.longueur = longueur;
		this.largeur = largeur;
		
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longeur) {
		this.longueur = longeur;
	}

	public Point2D getCentreGravite() {
		return centreGravite;
	}

	public void setCentreGravite(Point2D centreGravite) {
		this.centreGravite = centreGravite;
	}

	@Override
	public void afficher() {
		System.out.println("Rectangle nom: "+ this.getNom() + "centre gravitie"+this.centreGravite+"largeur : " +this.largeur+ "longueur : "+ this.longueur);
		
	}

	@Override
	public void deplacer(int x, int y) {
		this.centreGravite.deplaccer(x, y);
		
	}

	@Override
	public void decaler(int x, int y) {
		this.centreGravite.decaler(x, y);
		
	}

	

}
