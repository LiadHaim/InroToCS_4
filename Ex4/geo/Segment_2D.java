package geo;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
import java.awt.*;


public class Segment_2D implements GeoShape {

    private Point_2D a;
    private Point_2D b;

    // Create a new segment with the given start and end points
    public Segment_2D(Point_2D a, Point_2D b) {
        // Initialize the start point of the segment
        this.a = new Point_2D(a);

        // Initialize the end point of the segment
        this.b = new Point_2D(b);
    }

    // Create a copy of the given segment
    public Segment_2D(Segment_2D t1) {
        // Copy the start point of the segment
        this.a = t1.a;

        // Copy the end point of the segment
        this.b = t1.b;
    }

    // Get the start point of the segment
    public Point_2D get_p1() {
        return a;
    }

    // Get the end point of the segment
    public Point_2D get_p2() {
        return b;
    }

    // Check if the segment contains the given point
    @Override
    public boolean contains(Point_2D ot) {
        return this.get_p1().distance(ot) + this.get_p2().distance(ot) == this.get_p1().distance(this.get_p2());
    }
    // Calculate the area of the segment (which is always 0)
    @Override
    public double area() {
        return 0;
    }

    // Calculate the perimeter of the segment
    @Override
    public double perimeter() {
        // Calculate the distance between the start point and end point of the segment
        double perimeter = a.distance(b);
        return perimeter;
    }

    // Translate the segment by the given vector
    @Override
    public void translate(Point_2D vec) {
        // Move the start point of the segment by the given vector
        a.move(vec);

        // Move the end point of the segment by the given vector
        b.move(vec);
    }

    // Create a copy of the segment
    @Override
    public GeoShape copy() {
        return new Segment_2D(this.get_p1(),this.get_p2());
    }

    // Scale the segment relative to the given center point and ratio
    @Override
    public void scale(Point_2D center, double ratio) {
        // Scale the start point of the segment relative to the given center point and ratio
        a.scale(center, ratio);

        // Scale the end point of the segment relative to the given center point and ratio
        b.scale(center, ratio);
    }

    // Rotate the segment around the given center point by the specified angle in degrees
    @Override
    public void rotate(Point_2D center, double angleDegrees) {
        // Rotate the start point of the segment around the given center point by the specified angle in degrees
        a.rotate(center, angleDegrees);

        // Rotate the end point of the segment around the given center point by the specified angle in degrees
        b.rotate(center, angleDegrees);
    }

//    @Override
//    public Point_2D[] toArray() {
//        return new Point_2D[0];
//    }

    public Point_2D[] getAllPoints() {
        Point_2D[] arr = new Point_2D[2];
        arr[0] = new Point_2D(this.a);
        arr[1] = new Point_2D(this.b);
        return arr;
    }

}
