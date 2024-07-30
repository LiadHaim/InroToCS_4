package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rect_2DTest {

    @Test
    void contains() {
        // Create a rectangle
        Rect_2D rect = new Rect_2D(new Point_2D(1, 1), new Point_2D(4, 4));

        // Check points that are inside the rectangle
        Point_2D insidePoint1 = new Point_2D(2, 2);
        Point_2D insidePoint2 = new Point_2D(3, 3);
        assertTrue(rect.contains(insidePoint1));
        assertTrue(rect.contains(insidePoint2));

        // Check points that are outside the rectangle
        Point_2D outsidePoint1 = new Point_2D(0, 0);
        Point_2D outsidePoint2 = new Point_2D(5, 5);
        assertFalse(rect.contains(outsidePoint1));
        assertFalse(rect.contains(outsidePoint2));

        // Check points on the boundary of the rectangle
        Point_2D borderPoint1 = new Point_2D(1, 1);
        Point_2D borderPoint2 = new Point_2D(4, 4);
        assertTrue(rect.contains(borderPoint1));
        assertTrue(rect.contains(borderPoint2));
    }

    @Test
    public void testArea() {
        // Create a rectangle
        Rect_2D rect = new Rect_2D(new Point_2D(1, 1), new Point_2D(4, 4));

        // Check the area
        double expectedArea = 9.0;
        double actualArea = rect.area();
        assertEquals(expectedArea, actualArea);
    }

    @Test
    void testPerimeter() {
        // Create a rectangle
        Rect_2D rect = new Rect_2D(new Point_2D(1, 1), new Point_2D(4, 4));

        // Check the perimeter
        double expectedPerimeter = 14.48528137423857;
        double actualPerimeter = rect.perimeter();
        assertEquals(expectedPerimeter, actualPerimeter);
    }

    @Test
    void translate() {
        // Create a rectangle with start point (0, 0) and end point (2, 2)
        Rect_2D rect = new Rect_2D(new Point_2D(0, 0), new Point_2D(2, 2));

        // Translate the rectangle by vector (1, 1)
        rect.translate(new Point_2D(1, 1));

        // Check the expected result
        assertTrue(rect.contains(new Point_2D(1, 1))); // Updated start point of the rectangle
        assertTrue(rect.contains(new Point_2D(3, 3))); // Updated end point of the rectangle
    }

    @Test
    void copy() {
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(3, 3);
        Rect_2D rect1 = new Rect_2D(p1, p2);

        // Perform a copy operation
        Rect_2D rect2 = (Rect_2D) rect1.copy();

        // Check if the copied rectangle is equal to the original rectangle
        assertEquals(rect1.area(), rect2.area(), 0.0001);
        assertEquals(rect1.perimeter(), rect2.perimeter(), 0.0001);
        assertTrue(rect1.contains(p1));
        assertTrue(rect1.contains(p2));
    }

    @Test
    void scale() {
        // Create a rectangle
        Point_2D p1 = new Point_2D(1, 1);
        Point_2D p2 = new Point_2D(3, 3);
        Rect_2D rect1 = new Rect_2D(p1, p2);

        // Perform a copy operation
        Rect_2D rect2 = (Rect_2D) rect1.copy();

        // Check if the copied rectangle is equal to the original rectangle
        assertEquals(rect1.area(), rect2.area(), 0.0001);
        assertEquals(rect1.perimeter(), rect2.perimeter(), 0.0001);
        assertTrue(rect1.contains(p1));
        assertTrue(rect1.contains(p2));
    }

    @Test
    void rotate() {
        Point_2D center = new Point_2D(0, 0);

        // Create the _min and _max points
        Point_2D _min = new Point_2D(-1, -1);
        Point_2D _max = new Point_2D(1, 1);

        // Rotate in degrees
        double angleDegrees = 90;

        // Perform the rotation on the _min and _max points
        _min.rotate(center, angleDegrees);
        _max.rotate(center, angleDegrees);

        // Check the expected results: the test point should be (1, -1) and (1, -1) after rotation
        Assertions.assertEquals(1.0, _min.x(), 0.0000001);
        Assertions.assertEquals(-1.0, _min.y(), 0.0000001);
        Assertions.assertEquals(-1.0, _max.x(), 0.0000001);
        Assertions.assertEquals(1.0, _max.y(), 0.0000001);
    }
}
