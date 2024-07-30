package geo;

import static java.lang.Math.PI;

/**
 * This class represents a 2D circle in the plane.
 * Please make sure you update it according to the GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle_2D implements GeoShape {
	private Point_2D _center;
	private double _radius;

	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
	}

	public double getRadius() {
		return this._radius;
	}

	public Point_2D getCenter() {
		return _center;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ":" + _center.toString() + this.getRadius();
	}

	@Override
	public boolean contains(Point_2D ot) {
		// Check if the distance between the given point and the center is within the radius
		if (this._radius >= ot.distance(this._center)) {
			return true;
		}
		return false;
	}

	@Override
	public double area() {
		// Calculate and return the area of the circle using the formula: π * r^2
		double ans = Math.PI * Math.pow(_radius, 2);
		return ans;
	}

	@Override
	public double perimeter() {
		// Calculate and return the perimeter of the circle using the formula: 2 * π * r
		double ans = 2 * Math.PI * _radius;
		return ans;
	}

	@Override
	public void translate(Point_2D vec) {
		// Translate the center of the circle by adding the corresponding coordinates of the given vector
		_center = new Point_2D(_center.x() + vec.x(), _center.y() + vec.y());
	}


	@Override
	public GeoShape copy() {
		////// add your code here //////
		return new Circle_2D(_center, _radius);
		////////////////////////////////
	}


	@Override
	public void scale(Point_2D center, double ratio) {
		// Calculate the new radius based on the given ratio
		double newRadius = _radius * ratio;

		// Calculate the translation of the circle to the new center
		double dx = center.x() - _center.x();
		double dy = center.y() - _center.y();

		// Translate the center of the circle to the new center
		_center = new Point_2D(_center.x() + dx, _center.y() + dy);

		// Update the radius to the new radius
		_radius = newRadius;
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		// Rotate the center of the circle around the given center point by the specified angle
		_center.rotate(center, angleDegrees);
	}

//	@Override
//	public Point_2D[] toArray() {
//		return new Point_2D[0];
//	}

	public Point_2D[] getAllPoints() {
		Point_2D[] ans = new Point_2D[1];
		ans[0] =new Point_2D(this._center);
		return ans;
	}
}


