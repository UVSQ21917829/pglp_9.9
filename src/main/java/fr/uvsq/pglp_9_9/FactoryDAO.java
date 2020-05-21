package fr.uvsq.pglp_9_9;

public class FactoryDAO {

	public DAO<Cercle> getCercleDAO() {
		return new CercleDAO();

	}

	public DAO<Carre> getCarreDAO() {
		return new CarreDAO();

	}

	public DAO<Rectangle> getRectangleDAO() {
		return new RectangleDAO();

	}

	public DAO<Triangle> getTriangleDAO() {
		return new TriangleDAO();

	}

	public DAO<Groupe> getGroupeDAO() {
		return new GroupeDAO();

	}
}