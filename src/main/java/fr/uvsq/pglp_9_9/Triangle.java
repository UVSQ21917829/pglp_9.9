package fr.uvsq.pglp_9_9;

public class Triangle extends Form{
    private Point2D point1;
    private Point2D point2;
    private Point2D point3;
	public Point2D getPoint1() {
		return point1;
	}

	public void setPoint1(Point2D point1) {
		this.point1 = point1;
	}

	public Point2D getPoint2() {
		return point2;
	}

	public void setPoint2(Point2D point2) {
		this.point2 = point2;
	}

	public Point2D getPoint3() {
		return point3;
	}

	public void setPoint3(Point2D point3) {
		this.point3 = point3;
	}

	public Triangle(String nom,Point2D point1,Point2D point2,Point2D point3) {
		super(nom);
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
	}

	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deplacer(int x, int y) {
		this.point1.deplaccer(x, y);
		this.point2.deplaccer(x, y);
		this.point3.deplaccer(x, y);
		
	}
	@Override
	public void decaler(int x, int y) {
		this.point1.decaler(x, y);
		this.point2.decaler(x, y);
		this.point3.decaler(x, y);
		
		
	}

	
}
