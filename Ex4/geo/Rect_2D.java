package geo;

import geo.GeoShape;
import geo.Point_2D;

/**
 * This class represents a 2D axis parallel rectangle.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect_2D implements GeoShape {
	private Point_2D p1;
	private Point_2D p2;
	private Point_2D p3;
	private Point_2D p4;
	public Rect_2D(Point_2D p1, Point_2D p2) {
		////// add your code here //////
		this.p1 = new Point_2D(p1);
		this.p2 = new Point_2D(p2);
		this.p3 = new Point_2D(this.p1.x(),this.p2.y());
		this.p4 = new Point_2D(this.p2.x(), this.p1.y());
		////////////////////////////////
	}
	public Rect_2D(Rect_2D t1) {
		////// add your code here //////
		this.p1 = new Point_2D(t1.getP1());
		this.p2 = new Point_2D(t1.getP2());
		this.p3 = new Point_2D(t1.getP3());
		this.p4 = new Point_2D(t1.getP4());
		////////////////////////////////
	}

	public Rect_2D(Point_2D p1, Point_2D p2, Point_2D p3, Point_2D p4) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
	}

	@Override
	public String toString() {
		return "Rect_2D [" + "p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + ", p4=" + p4 + ']';
	}

	public Point_2D getP1() {
		return this.p1;
	}

	public Point_2D getP2() {
		return this.p2;
	}
	public Point_2D getP3() {
		return this.p3;
	}
	public Point_2D getP4() {
		return this.p4;
	}
	public double getHeight(){return this.p2.distance(this.p4);}
	public double getWidth(){return this.p1.distance(this.p2);}
	@Override
	public boolean contains(Point_2D ot) {
		Triangle_2D t1 = new Triangle_2D(p1,p2,ot);
		Triangle_2D t2 = new Triangle_2D(p1,p3,ot);
		Triangle_2D t3 = new Triangle_2D(p3,p4,ot);
		Triangle_2D t4 = new Triangle_2D(p4,p2,ot);

		double areaSum = t1.area() + t2.area() + t3.area() + t4.area();

		if(this.area()-areaSum<=0.0001){return true;}
		return false;
	}

	@Override
	public double area() {
		double height = getHeight();
		double width = getWidth();

		return height * width;
	}

	@Override
	public double perimeter() {
		double height = getHeight();
		double width = getWidth();
		return 2 * (height + width);
	}

	@Override
	public void translate(Point_2D vec) {
		this.p1.move(vec);
		this.p2.move(vec);
		this.p3.move(vec);
		this.p4.move(vec);
	}

	@Override
	public GeoShape copy() {
		return new Rect_2D(this.getP1(),this.getP2(),this.getP3(),this.getP4());
	}
	@Override
	public void scale(Point_2D center, double ratio) {
		this.p1.scale(center,ratio);
		this.p2.scale(center,ratio);
		this.p3.scale(center,ratio);
		this.p4.scale(center,ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this.p1.rotate(center,angleDegrees);
		this.p2.rotate(center,angleDegrees);
		this.p3.rotate(center,angleDegrees);
		this.p4.rotate(center,angleDegrees);
	}

	@Override
	public Point_2D[] getAllPoints() {
		Point_2D[] point_arr = new Point_2D[2];
		point_arr[0] = new Point_2D(this.p1);
		point_arr[1] = new Point_2D(this.p2);
		return point_arr;
	}

	public double[] getx(){
		double[] x_arr = new double[4];
		x_arr[0] = p1.x();
		x_arr[1] = p3.x();
		x_arr[2] = p2.x();
		x_arr[3] = p4.x();
		return x_arr;
	}

	public double[] gety(){
		double[] y_arr = new double[4];
		y_arr[0] = p1.y();
		y_arr[1] = p3.y();
		y_arr[2] = p2.y();
		y_arr[3] = p4.y();
		return y_arr;
	}

}
