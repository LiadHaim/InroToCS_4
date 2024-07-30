package geo;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Triangle_2DTest {

    @Test
    void contains() {
        // Create the triangle points
        Point_2D p1 = new Point_2D(1, 0);
        Point_2D p2 = new Point_2D(0, 1);
        Point_2D p3 = new Point_2D(-1, 0);

        // Create a triangle
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

        // Point inside the triangle
        Point_2D pointInside = new Point_2D(0, 0);

        // Check if the triangle contains the point inside
        Assertions.assertTrue(triangle.contains(pointInside));

        // Point outside the triangle
        Point_2D pointOutside = new Point_2D(2, 2);

        // Check if the triangle doesn't contain the point outside
        Assertions.assertFalse(triangle.contains(pointOutside));
    }

    @Test
    void area() {
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(2, 0);
        Point_2D p3 = new Point_2D(1, 2);
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

        // Calculate the expected area
        double expectedArea = 2.0;

        // Calculate the actual area
        double actualArea = triangle.area();

        // Check if the actual area matches the expected area with a tolerance of 0.001
        assertEquals(expectedArea, actualArea, 0.001);
    }

    @Test
    void perimeter() {
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(3, 0);
        Point_2D p3 = new Point_2D(0, 4);
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

        // Calculate the expected perimeter
        double expectedPerimeter = 12.0;

        // Calculate the actual perimeter
        double actualPerimeter = triangle.perimeter();

        // Check if the actual perimeter matches the expected perimeter with a tolerance of 0.001
        assertEquals(expectedPerimeter, actualPerimeter, 0.001);
    }

    @Test
    void translate() {
        // Create the triangle points
        Point_2D p1 = new Point_2D(1, 0);
        Point_2D p2 = new Point_2D(0, 1);
        Point_2D p3 = new Point_2D(-1, 0);

        // Create a triangle
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

        // Create the translation vector
        Point_2D translationVector = new Point_2D(2, 2);

        // Translate the triangle using the translate method
        triangle.translate(translationVector);

        // Check if the triangle points have moved according to the translation
        Point_2D expectedP1 = new Point_2D(3, 2);
        Point_2D expectedP2 = new Point_2D(2, 3);
        Point_2D expectedP3 = new Point_2D(1, 2);

        Assertions.assertEquals(expectedP1, triangle.getAllPoints()[0]);
        Assertions.assertEquals(expectedP2, triangle.getAllPoints()[1]);
        Assertions.assertEquals(expectedP3, triangle.getAllPoints()[2]);
    }

    @Test
    void copy() {
        // Create the triangle points
        Point_2D p1 = new Point_2D(1, 0);
        Point_2D p2 = new Point_2D(0, 1);
        Point_2D p3 = new Point_2D(-1, 0);

        // Create a triangle
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

        // Copy the triangle using the copy method
        Triangle_2D copiedTriangle = (Triangle_2D) triangle.copy();

        // Check if the points of the copied triangle are identical to the points of the original triangle
        Assertions.assertEquals(triangle.getAllPoints()[0], copiedTriangle.getAllPoints()[0]);
        Assertions.assertEquals(triangle.getAllPoints()[1], copiedTriangle.getAllPoints()[1]);
        Assertions.assertEquals(triangle.getAllPoints()[2], copiedTriangle.getAllPoints()[2]);
    }

    @Test
    void scale() {
        // Create triangle points
        Point_2D p1 = new Point_2D(0, 0);
        Point_2D p2 = new Point_2D(2, 0);
        Point_2D p3 = new Point_2D(0, 2);
        Triangle_2D triangle = new Triangle_2D(p1, p2, p3);

        // Perform scaling on the triangle
        double ratio = 2; // Scaling ratio
        Point_2D center = new Point_2D(1, 1); // Scaling center point
        triangle.scale(center, ratio);

        // Check the triangle points after scaling
        Point_2D expectedP1 = new Point_2D(-1, -1);
        Point_2D expectedP2 = new Point_2D(3, -1);
        Point_2D expectedP3 = new Point_2D(-1, 3);
        Point_2D[] actualPoints = triangle.getAllPoints();

        Assert.assertEquals(expectedP1, actualPoints[0]);
        Assert.assertEquals(expectedP2, actualPoints[1]);
        Assert.assertEquals(expectedP3, actualPoints[2]);
    }

    @Test
    void rotate() {
        Triangle_2D triangle = new Triangle_2D(new Point_2D(1, 1), new Point_2D(2, 3), new Point_2D(4, 2));

        // Rotate the triangle 90 degrees counterclockwise around the center (2, 2)
        triangle.rotate(new Point_2D(2, 2), 90);

        // Check the new coordinates of the triangle points after rotation
        Point_2D[] expectedPoints = {
                new Point_2D(3, 1),
                new Point_2D(1, 2),
                new Point_2D(2, 4)
        };
        Point_2D[] actualPoints = triangle.getAllPoints();

        for (int i = 0; i < expectedPoints.length; i++) {
            assertEquals(expectedPoints[i], actualPoints[i]);
        }
    }
}