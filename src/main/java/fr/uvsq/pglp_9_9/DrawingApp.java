package fr.uvsq.pglp_9_9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DrawingApp {

	private DrawingTUI draw;
	private Command command;
	private Connection conncetion;

	public DrawingApp() {

		try {
			

			String create = "CREATE TABLE groupe(nom varchar(25) PRIMARY KEY NOT NULL)";
			createTable(conncetion, "groupe", create);
			create = "CREATE TABLE Carre( nom varchar(25) PRIMARY KEY NOT NULL, x int, y int, cote int)";
			// statement.execute(create);
			createTable(conncetion, "carre", create);
			create = "CREATE TABLE Cercle( nom varchar(25) PRIMARY KEY NOT NULL, x int, y int, rayon int)";
			// statement.execute(create);
			createTable(conncetion, "cercle", create);
			create = "CREATE TABLE Rectangle( nom varchar(25) PRIMARY KEY NOT NULL, x int,"
					+ " y int, largeur int, longueur int)";
			// statement.execute(create);
			createTable(conncetion, "rectangle", create);
			create = "CREATE TABLE Triangle( nom varchar(25) PRIMARY KEY NOT NULL, x1 int,"
					+ " y1 int, x2 int, y2 int, x3 int, y3 int)";
			// statement.execute(create);
			createTable(conncetion, "triangle", create);
			create = "CREATE TABLE estCarre(gr_nom varchar(25), nom varchar(25), PRIMARY KEY(gr_nom,nom), "
					+ "FOREIGN KEY (gr_nom) REFERENCES Groupe(nom) ON DELETE CASCADE, "
					+ "FOREIGN KEY (nom) REFERENCES Carre(nom) ON DELETE CASCADE)";
			// statement.execute(create);
			createTable(conncetion, "estcarre", create);
			create = "CREATE TABLE estCercle(gr_nom varchar(25), nom varchar(25), PRIMARY KEY(gr_nom,nom),"
					+ " FOREIGN KEY (gr_nom) REFERENCES Groupe(nom) ON DELETE CASCADE,"
					+ " FOREIGN KEY (nom) REFERENCES Cercle(nom) ON DELETE CASCADE)";
			// statement.execute(create);
			createTable(conncetion, "estcercle", create);
			create = "CREATE TABLE estRectangle(gr_nom varchar(25), nom varchar(25), PRIMARY KEY(gr_nom,nom),"
					+ " FOREIGN KEY (gr_nom) REFERENCES Groupe(nom) ON DELETE CASCADE,"
					+ " FOREIGN KEY (nom) REFERENCES Rectangle(nom) ON DELETE CASCADE)";
			// statement.execute(create);
			createTable(conncetion, "estrectangle", create);
			create = "CREATE TABLE estTriangle(gr_nom varchar(25), nom varchar(25), PRIMARY KEY(gr_nom,nom),"
					+ " FOREIGN KEY (gr_nom) REFERENCES Groupe(nom) ON DELETE CASCADE,"
					+ " FOREIGN KEY (nom) REFERENCES Triangle(nom) ON DELETE CASCADE)";
			// statement.execute(create);
			createTable(conncetion, "esttriangle", create);
			create = "CREATE TABLE estGroupe(gr_nom varchar(25), nom varchar(25), PRIMARY KEY(gr_nom,nom)"
					+ ", FOREIGN KEY (gr_nom) REFERENCES Groupe(nom) ON DELETE CASCADE, "
					+ "FOREIGN KEY (nom) REFERENCES Groupe(nom) ON DELETE CASCADE)";
			// statement.execute(create);
			createTable(conncetion, "estgroupe", create);

			conncetion.close();
		} catch (  SQLException e) {
			System.out.println("");
			
			e.printStackTrace();

		}
		this.draw = new DrawingTUI();
	}

	public void createTable(Connection connection, String name, String createtTable) throws SQLException {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conncetion = DriverManager.getConnection("jdbc:derby:db_dessi;create=true");
		ResultSet res = conncetion.getMetaData().getTables(null, "APP", name.toUpperCase(), null);
		Statement statement;
		if (!res.next()) {
			statement = conncetion.createStatement();
			statement.execute(createtTable);
		}
		conncetion.close();
	}

	public static void main(String[] args) {
		DrawingApp app = new DrawingApp();
		app.run();
	}

	public void run() {
		System.out.println("\n *************Bienvenu dans l'application de Dessin *************");
		System.out.println("\n Dessin exemple:");
		System.out.println("\n c1 = Cercle((0, 0), 25)");
		System.out.println("\n c3 = Carre((0, 0), 25)");
		System.out.println("\n t3 = Triangle((1, 2),(3, 6),(4, 5))");
		System.out.println("\n r1 = Rectangle((0, 0),40,30)");
		System.out.println("\n Deplacer exemple:");
		System.out.println("\n move(c1, (10, 20))");
		System.out.println("\n moveGroup()");
		System.out.println("\n Supprimer  exemple:");
		System.out.println("\n delete(c1)");
		System.out.println("\n Pour sauvegarder votre dessin tapez: Sauvegarde()");
		System.out.println("\n Pour quitter l'application tapez: exit()");
		System.out.println("\n ****************************************************************");
		while (true) {
			command = this.draw.nextCommand();
            try {
            	command.execute();
			} catch (NullPointerException e) {
				System.out.println("Erreur lors de l'execustion de la commande. Veuillez verifier le syntaxe");
			}
			

			this.draw.afficher();
		}
	}
}
