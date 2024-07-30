package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Segment_2DTest {

    @Test
    void contains() {
        // Create two points for the segment
        Point_2D point1 = new Point_2D(1, 1);
        Point_2D point2 = new Point_2D(4, 4);

        // Create a segment using the points
        Segment_2D segment = new Segment_2D(point1, point2);

        // Create a translation vector
        Point_2D translationVector = new Point_2D(2, 3);

        // Translate the segment using the translate() method
        segment.translate(translationVector);

        // Create expected points after translation
        Point_2D expectedPoint1 = new Point_2D(3, 4);
        Point_2D expectedPoint2 = new Point_2D(6, 7);

        // Get the actual points after translation
        Point_2D actualPoint1 = segment.get_p1();
        Point_2D actualPoint2 = segment.get_p2();

        // Assert that the actual points match the expected points
        assertEquals(expectedPoint1, actualPoint1);
        assertEquals(expectedPoint2, actualPoint2);
    }

    @Test
    void area() {
        // Segment does not have an area, so this test is not applicable
    }

    @Test
    void perimeter() {
        // Create two points for the segment
        Point_2D point1 = new Point_2D(0, 0);
        Point_2D point2 = new Point_2D(3, 4);

        // Create a segment using the points
        Segment_2D segment = new Segment_2D(point1, point2);

        // Calculate the expected perimeter
        double expectedPerimeter = point1.distance(point2);

        // Get the actual perimeter using the perimeter() method
        double actualPerimeter = segment.perimeter();

        // Assert that the actual perimeter matches the expected perimeter
        assertEquals(expectedPerimeter, actualPerimeter, 0.001);
    }

    @Test
    void translate() {
        // Create two points for the segment
        Point_2D point1 = new Point_2D(1, 1);
        Point_2D point2 = new Point_2D(4, 4);

        // Create a segment using the points
        Segment_2D segment = new Segment_2D(point1, point2);

        // Create a translation vector
        Point_2D translationVector = new Point_2D(2, 3);

        // Translate the segment using the translate() method
        segment.translate(translationVector);

        // Create expected points after translation
        Point_2D expectedPoint1 = new Point_2D(3, 4);
        Point_2D expectedPoint2 = new Point_2D(6, 7);

        // Get the actual points after translation
        Point_2D actualPoint1 = segment.get_p1();
        Point_2D actualPoint2 = segment.get_p2();

        // Assert that the actual points match the expected points
        assertEquals(expectedPoint1, actualPoint1);
        assertEquals(expectedPoint2, actualPoint2);
    }

    @Test
    void copy() {
        Point_2D a = new Point_2D(1, 1);
        Point_2D b = new Point_2D(2, 2);
        Point_2D c = new Point_2D(3, 3);
        Triangle_2D triangle = new Triangle_2D(a, b, c);

        GeoShape copy = triangle.copy();

        // Assert that the copy is of type Triangle_2D
        assertEquals(Triangle_2D.class, copy.getClass());

        // Assert that the copy contains the same points as the original triangle
        Point_2D[] originalPoints = triangle.getAllPoints();
        Point_2D[] copyPoints = ((Triangle_2D) copy).getAllPoints();
        for (int i = 0; i < originalPoints.length; i++) {
            assertEquals(originalPoints[i], copyPoints[i]);
        }
    }

    @Test
    void scale() {
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(3, 4);

        Segment_2D segment = new Segment_2D(p1, p2);

        Point_2D center = new Point_2D(1, 1);
        double ratio = 2.0;
        segment.scale(center, ratio);

        Point_2D expectedP1 = new Point_2D(-1, -1);
        Point_2D expectedP2 = new Point_2D(5, 7);

        assertEquals(expectedP1, segment.get_p1());
        assertEquals(expectedP2, segment.get_p2());
    }

    @Test
    void rotate() {
        // Rotation test is not provided in the code snippet
    }
}