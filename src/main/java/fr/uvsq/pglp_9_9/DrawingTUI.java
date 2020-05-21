package fr.uvsq.pglp_9_9;

import java.io.IOException;
import java.util.Scanner;

public class DrawingTUI {
	Scanner scanner;
	Groupe dessin;
	FactoryDAO dao;
	Command command;

	public DrawingTUI() {

		this.scanner = new Scanner(System.in);
		this.dao = new FactoryDAO();
		this.dessin = new Groupe("Dessin");
		try {
			if(dao.getGroupeDAO().read(dessin.getNom())!=null)
				this.dessin.setForms(dao.getGroupeDAO().read(dessin.getNom()).getForms() );
		} catch (ClassNotFoundException | IOException |RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public Command nextCommand() {

		String line;
		line = this.scanner.nextLine();
		String nom = null;
		try {
			// enlever les espaces
			line = line.replaceAll("\\s+", "");
			String commandS = line.substring(0, line.indexOf("("));

			// String[] split = line.split(",");
			System.out.print(line);
			if (line.contains("=")) {
				String[] split2 = line.split("=");
				nom = line.substring(0, line.indexOf("="));

				String type = split2[1].substring(0, split2[1].indexOf("("));
				if (type.equals("Cercle")) {
					String[] splitpartie2 = split2[1].split("Cercle");
					String[] carreSplit = splitpartie2[1].split(",");
					if (carreSplit.length == 3) {

						int x = Integer.parseInt(line.substring(line.lastIndexOf("(") + 1, line.indexOf(",")));
						int y = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(")")));
						Point2D point = new Point2D(x, y);
						int rayon = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1, line.lastIndexOf(")")));
						Cercle cercle = new Cercle(nom, point, rayon);
						command = new CreateCommand(cercle, dessin);
					} else {
						System.out.print("nombre d'arguements invalide");
					}
				} else if (type.equals("Rectangle")) {
					System.out.print("reeeec");
					String[] rectangleS = line.split(",");

					if (rectangleS.length == 4) {
						String s = rectangleS[0].substring(rectangleS[0].lastIndexOf("(") + 1);

						int x = Integer.parseInt(s.replaceAll("[\\D]", ""));
						s = rectangleS[1].substring(0, 2);
						int y = Integer.parseInt(s.replaceAll("[\\D]", ""));
						s = rectangleS[3].substring(0, rectangleS[3].indexOf(")") - 1);
						int largeur = Integer.parseInt(s.replaceAll("[\\D]", ""));
						s = rectangleS[2];
						int longueur = Integer.parseInt(s.replaceAll("[\\D]", ""));
						Rectangle rec = new Rectangle(nom, new Point2D(x, y), longueur, largeur);
						System.out.print(rec.getNom());
						command = new CreateCommand(rec, dessin);
					} else {
						System.out.print("nombre d'arguements invalide");
					}

				} else if (type.equals("Carre")) {
					String[] carreSplit = line.split(",");
					if (carreSplit.length == 3) {
						int x = Integer.parseInt(carreSplit[0].substring(carreSplit[0].lastIndexOf("(") + 1));
						int y = Integer.parseInt(carreSplit[1].substring(0, carreSplit[1].indexOf(")")));
						Point2D point = new Point2D(x, y);
						int cote = Integer.parseInt(carreSplit[2].substring(0, carreSplit[2].length() - 1));
						Carre carre = new Carre(nom, point, cote);
						System.out.print(carre.getNom());
						command = new CreateCommand(carre, dessin);

					} else {
						System.out.print("nombre d'arguements invalide");
					}
				} else if (type.equals("Triangle")) {
					String[] triangleSplit = line.split(",");
					if (triangleSplit.length == 6) {

						String s = triangleSplit[0].substring(triangleSplit[0].lastIndexOf("(") + 1);
						int x1 = Integer.parseInt(s.replaceAll("[\\D]", ""));
						s = triangleSplit[1];
						s = s.replaceAll("[\\D]", "");
						int y1 = Integer.parseInt(s);
						s = triangleSplit[2];
						int x2 = Integer.parseInt(s.replaceAll("[\\D]", ""));
						s = triangleSplit[3];
						int y2 = Integer.parseInt(s.replaceAll("[\\D]", ""));
						s = triangleSplit[4].substring(1);
						int x3 = Integer.parseInt(s.replaceAll("[\\D]", ""));
						s = triangleSplit[5];
						int y3 = Integer.parseInt(s.replaceAll("[\\D]", ""));

						Triangle triangle = new Triangle(nom, new Point2D(x1, y1), new Point2D(x2, y2),
								new Point2D(x3, y3));
						command = new CreateCommand(triangle, dessin);
					} else {
						System.out.print("nombre d'arguements invalide");
					}
				}
			} else if (commandS.equals("move")) {
				String[] moveSp = line.split(",");
				if (moveSp.length == 3) {
					nom = line.substring(line.indexOf("(") + 1, line.indexOf(","));
					int x = Integer.parseInt(line.substring(line.lastIndexOf("(") + 1, line.lastIndexOf(",")));
					int y = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1, line.indexOf(")")));
					for (Form form : dessin.getForms()) {
						if (form.getNom().contentEquals(nom)) {
							command = new DaplacerCommand(x, y, (Form) form);
							break;
						}
					}
				} else {
					System.out.print("nombre d'arguements invalide");
				}

			} else if (commandS.matches("moveDessin")) {

				String[] moveSp = line.split(",");
				if (moveSp.length == 3) {
					int x = Integer.parseInt(line.substring(line.lastIndexOf("(") + 1, line.lastIndexOf(",")));
					int y = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1, line.indexOf(")")));
					command = new MoveGroupCommand(x, y, dessin);

				} else {
					System.out.print("nombre d'arguements invalide");
				}
			}
			else if (commandS.matches("Sauvegarde")) {

				//nom = line.substring(line.indexOf("(") + 1, line.indexOf(")"));

				command = new SauvegardDessinCommand(dessin);
			}

			else if (commandS.matches("delete")) {

				nom = line.substring(line.indexOf("(") + 1, line.indexOf(")"));

				command = new DeleteCommand(nom, dessin);

			} else if (commandS.matches("exit")) {
				System.out.print("quiteer");
				command = new QuitCommand();

			}
		} catch (RuntimeException   e) {
			// TODO: handle exception
		}
		return command;
	}

	public void execute() {
		command = nextCommand();
	}

	public void afficher() {
		
		dessin.afficher();

	}

}