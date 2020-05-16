package fr.uvsq.pglp_9_9;

public abstract class Form {
	private String nom;
	public Form(String nom) {
		this.nom=nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	// pour afficher les forme
	public abstract void afficher();

	// pour deplacer les formes
	public abstract void decaler(int x, int y);
	public abstract void deplacer(int x, int y);

}
