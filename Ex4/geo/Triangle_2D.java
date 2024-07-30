package geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle_2D implements GeoShape {
	private Point_2D p1;
	private Point_2D p2;
	private Point_2D p3;

	// Create a new triangle with the given three points
	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		// Initialize the first point of the triangle
		this.p1 = p1;

		// Initialize the second point of the triangle
		this.p2 = p2;

		// Initialize the third point of the triangle
		this.p3 = p3;
	}

	// Create a copy of the given triangle
	public Triangle_2D(Triangle_2D t1) {
		// Copy the first point of the triangle
		this.p1 = t1.p1;

		// Copy the second point of the triangle
		this.p2 = t1.p2;

		// Copy the third point of the triangle
		this.p3 = t1.p3;
	}

	// Get an array containing all the points of the triangle
	public Point_2D[] getAllPoints() {
		Point_2D[] points = {this.p1, this.p2, this.p3};
		return points;
	}

	// Check if the triangle contains the given point
	@Override
	public boolean contains(Point_2D ot) {
		double fullArea = area();
		double t1 = areaByPoints(ot, p1, p2);
		double t2 = areaByPoints(ot, p2, p3);
		double t3 = areaByPoints(ot, p1, p3);
		if(Math.abs((t1+t2+t3)-fullArea)<=0.00001){
			return true;
		}
		return false;

//		Triangle_2D T = new Triangle_2D(p1,p2,p3);
//		Triangle_2D t1 = new Triangle_2D(p1,p2,ot);
//		Triangle_2D t2 = new Triangle_2D(p1,ot,p3);
//		Triangle_2D t3 = new Triangle_2D(ot,p2,p3);
//
//		return T.area() - (t1.area() + t2.area() + t3.area()) == 0;
	}

	// Calculate the area of the triangle
	@Override
	public double area() {
		return areaByPoints(p1, p2, p3);
	}

	public double areaByPoints(Point_2D p1, Point_2D p2, Point_2D p3){
		return 0.5*Math.abs(p1.x()*(p3.y()-p2.y()) + p2.x()*(p1.y()-p3.y())+ p3.x()*(p2.y()-p1.y()));
	}

	// Calculate the perimeter of the triangle
	@Override
	public double perimeter() {
		double d1 = p1.distance(p2);
		double d2 = p2.distance(p3);
		double d3 = p3.distance(p1);
		double perimeter = d1 + d2 + d3;
		return perimeter;
	}

	// Translate the triangle by the given vector
	@Override
	public void translate(Point_2D vec) {
		// Move each point of the triangle by the given vector
		p1.move(vec);
		p2.move(vec);
		p3.move(vec);
	}

	// Create a copy of the triangle
	@Override

	public GeoShape copy() {
		return new Triangle_2D(p1,p2,p3);
	}

	// Scale the triangle relative to the given center point and ratio
	@Override
	public void scale(Point_2D center, double ratio) {
		// Scale each point of the triangle relative to the given center point and ratio
		p1.scale(center, ratio);
		p2.scale(center, ratio);
		p3.scale(center, ratio);
	}

	// Rotate the triangle around the given center point by the specified angle in degrees
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		// Rotate each point of the triangle around the given center point by the specified angle in degrees
		p1.rotate(center, angleDegrees);
		p2.rotate(center, angleDegrees);
		p3.rotate(center, angleDegrees);
	}

//	@Override
//	public Point_2D[] toArray() {
//		return new Point_2D[0];
//	}
}







