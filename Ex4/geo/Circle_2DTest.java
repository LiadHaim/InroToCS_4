package geo;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class Circle_2DTest {

    @Test
    void contains() {
        Point_2D center = new Point_2D(0, 0);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);

        Point_2D pointInside = new Point_2D(3, 3);
        assertTrue(circle.contains(pointInside));

        Point_2D pointOnBoundary = new Point_2D(5, 0);
        assertTrue(circle.contains(pointOnBoundary));

        Point_2D pointOutside = new Point_2D(10, 10);
        assertFalse(circle.contains(pointOutside));
    }

    @Test
    void area() {
        double radius = 5;
        double expectedArea = Math.PI * Math.pow(radius, 2);
        double actualArea = 78.5398;

        assertEquals(expectedArea, actualArea, 0.0001);
    }

    @Test
    void perimeter() {
        double radius = 5;
        double expected= Math.PI*2*radius;
        double actual = 10*Math.PI;
        assertEquals(expected,actual,0.0001);
    }

    @Test
    void translate() {
        Point_2D center = new Point_2D(0, 0);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);

        Point_2D translationVector = new Point_2D(2, 3);
        circle.translate(translationVector);

        Point_2D expectedCenter = new Point_2D(2, 3);
        Point_2D actualCenter = circle.getCenter();

        assertEquals(expectedCenter, actualCenter);
    }

    @Test
    void copy() {
        Point_2D center = new Point_2D(0, 0);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);
        GeoShape copy = circle.copy();

        // Assert that the copy is an instance of Circle_2D
        assertEquals(Circle_2D.class, copy.getClass());

        // Assert that the copy has the same center and radius
        Circle_2D circleCopy = (Circle_2D) copy;
        assertEquals(circle.getCenter(), circleCopy.getCenter());
        assertEquals(circle.getRadius(), circleCopy.getRadius());

        // Assert that the original object and the copy are not the same object
        assertNotSame(circle, copy);
    }

    @org.junit.jupiter.api.Test
    void scale() {
        Point_2D center = new Point_2D(0, 0);
        double radius = 5;
        Circle_2D circle = new Circle_2D(center, radius);

        Point_2D newCenter = new Point_2D(2, 3);
        double ratio = 2;
        circle.scale(newCenter, ratio);

        double expectedRadius = radius * ratio;
        double actualRadius = circle.getRadius();

        assertEquals(expectedRadius, actualRadius, 0.0001);

        Point_2D expectedCenter = new Point_2D(2, 3);
        Point_2D actualCenter = circle.getCenter();

        assertEquals(expectedCenter, actualCenter);
    }

    @Test
    void rotate() {
        Point_2D center = new Point_2D(15, 23);
        Circle_2D circle = new Circle_2D(center, 5);

        // Rotate the circle by 90 degrees
        circle.rotate(center, 90);

        // Verify the new center coordinates after rotation
        Point_2D newCenter = circle.getCenter();
        assertEquals(15.0, newCenter.x(), 0.000001);
        assertEquals(23.0, newCenter.y(), 0.000001);

        // Verify the radius remains the same
        assertEquals(5.0, circle.getRadius(), 0.000001);
    }
}
