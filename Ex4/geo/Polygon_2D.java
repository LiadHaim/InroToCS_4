package geo;

import ex4.Ex4;
import ex4.Ex4_Const;

import java.security.spec.ECParameterSpec;
import java.util.ArrayList;

public class Polygon_2D implements GeoShape {
	// Constructs an empty Polygon_2D object.
	private ArrayList<Point_2D> arrayOfPoints;

	/**
	 * Constructs an empty Polygon_2D object.
	 */
	public Polygon_2D() {
		this.arrayOfPoints = new ArrayList<Point_2D>();
	}

	public Polygon_2D(ArrayList<Point_2D> poly2){
		// Constructs a new Polygon_2D object with the same points as the given polygon.
		arrayOfPoints = (ArrayList<Point_2D>) poly2.clone();
	}

	/**
	 * Constructs a new Polygon_2D object with the same points as the given polygon.
	 * @param po The Polygon_2D object to copy.
	 */
	public Polygon_2D(Polygon_2D po) {
		this.arrayOfPoints = new ArrayList<>(po.arrayOfPoints);
	}
	//Returns an array of all the points in the polygon.
	/**
	 * Returns an array of all the points in the polygon.
	 * @return An array of Point_2D objects representing the points in the polygon.
	 */
	public Point_2D[] getAllPoints() {
		Point_2D[] p1 = new Point_2D[this.arrayOfPoints.size()];
		p1 = this.arrayOfPoints.toArray(p1);
		return p1;

	}

	public double[] get_x(){
		double[] arr_x = new double[arrayOfPoints.size()];
		for(int i =0; i<arrayOfPoints.size(); i++){
			arr_x[i] = arrayOfPoints.get(i).x();
		}
		return arr_x;
	}

	public double[] get_y(){
		double[] arr_y = new double[arrayOfPoints.size()];
		for(int i =0; i<arrayOfPoints.size(); i++){
			arr_y[i] = arrayOfPoints.get(i).y();
		}
		return arr_y;
	}



	//Adds a point to the polygon.
	public void add(Point_2D p) {
		this.arrayOfPoints.add(p);
	}


	 // Calculates the string representation of the Polygon_2D object.

	@Override
	public String toString() {
		return "Poly_2D:" + arrayOfPoints.toString();
	}


	 // Checks if the polygon contains the given point.

	@Override
	public boolean contains(Point_2D ot) {
		double polygonArea = area();
		Triangle_2D t1 = new Triangle_2D(arrayOfPoints.get(0), arrayOfPoints.get(1), ot);
		Triangle_2D t2 = new Triangle_2D(arrayOfPoints.get(1), arrayOfPoints.get(2), ot);
		Triangle_2D t3 = new Triangle_2D(arrayOfPoints.get(2), arrayOfPoints.get(0), ot);
		double sumOfTriangleAreas = t1.area() + t2.area() + t3.area();
		return Math.abs(polygonArea - sumOfTriangleAreas) < Ex4_Const.EPS;
	}


	 // Calculates the area of the polygon.

	@Override
	public double area() {
		Point_2D[] points = getAllPoints();
		int numOfPoints = points.length;

		if (numOfPoints < 3) {
			return 0.0;
		}

		double totalArea = 0.0;
		for (int i = 1; i < numOfPoints - 1; i++) {
			double x1 = points[i].x() - points[0].x();
			double y1 = points[i].y() - points[0].y();
			double x2 = points[i + 1].x() - points[0].x();
			double y2 = points[i + 1].y() - points[0].y();

			double crossProduct = x1 * y2 - x2 * y1;
			totalArea += crossProduct;
		}

		double area = Math.abs(totalArea / 2.0);
		return area;
	}


	 // Calculates the perimeter of the polygon.

	@Override
	public double perimeter() {
		Point_2D[] points = getAllPoints();
		int numOfPoints = points.length;

		if (numOfPoints < 2) {
			return 0.0;
		}

		double perimeter = 0.0;
		for (int i = 0; i < numOfPoints - 1; i++) {
			perimeter += points[i].distance(points[i + 1]);
		}
		perimeter += points[numOfPoints - 1].distance(points[0]);

		return perimeter;
	}


	 //Translates the polygon by the given vector.

	@Override
	public void translate(Point_2D vec) {
		for (Point_2D point : arrayOfPoints) {
			point.move(vec);
		}
	}


	 // Creates a deep copy of the polygon.

	@Override
	public GeoShape copy() {
		////// add your code here //////
		return new Polygon_2D(this.arrayOfPoints);
		////////////////////////////////
	}


	 //Scales the polygon relative to the given center point by the given ratio.

	@Override
	public void scale(Point_2D center, double ratio) {
		for (int i = 0; i < arrayOfPoints.size(); i++) {
			arrayOfPoints.get(i).scale(center, ratio);
		}
	}


	 // Rotates the polygon around the given center point by the given angle in degrees.

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		for (int i = 0; i < arrayOfPoints.size(); i++) {
			arrayOfPoints.get(i).rotate(center, angleDegrees);
		}
	}

//	@Override
//	public Point_2D[] toArray() {
//		return new Point_2D[0];
	}


