package geo;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point_2DTest {

    @Test
    void add() {
        // Create two points
        Point_2D p1 = new Point_2D(1, 2);
        Point_2D p2 = new Point_2D(3, 4);

        // Add p2 to p1
        Point_2D result = p1.add(p2);

        // Assert the expected result
        Assert.assertEquals(4.0, result.x(), 0.001);
        Assert.assertEquals(6.0, result.y(), 0.001);
    }

    @Test
    void testToString() {
        // Create a point
        Point_2D point = new Point_2D(1.5, 2.8);

        // Call the toString method
        String result = point.toString();

        // Assert the expected result
        Assert.assertEquals("1.5,2.8", result);
    }

    @Test
    void distance() {
        // Create a point
        Point_2D point = new Point_2D(3, 4);

        // Call the distance method
        double result = point.distance();

        // Assert the expected result
        Assert.assertEquals(5.0, result, 0.001);
    }

    @Test
    void testDistance() {
        // Create two points
        Point_2D point1 = new Point_2D(1, 2);
        Point_2D point2 = new Point_2D(4, 6);

        // Call the distance method
        double result = point1.distance(point2);

        // Assert the expected result
        Assert.assertEquals(5.0, result, 0.001);
    }

    @Test
    void testEquals() {
        // Create two points with the same coordinates
        Point_2D point1 = new Point_2D(3, 4);
        Point_2D point2 = new Point_2D(3, 4);

        // Call the equals method
        boolean result = point1.equals(point2);

        // Assert the expected result
        Assert.assertTrue(result);
    }
    @Test
    void testEquals2(){
        // Create two points with different coordinates
        Point_2D point1 = new Point_2D(1, 2);
        Point_2D point2 = new Point_2D(3, 4);

        // Call the equals method
        boolean result = point1.equals(point2);

        // Assert the expected result
        Assert.assertFalse(result);
    }

    @Test
    void close2equals() {
        // Create two points with close coordinates
        Point_2D point1 = new Point_2D(1, 2);
        Point_2D point2 = new Point_2D(1.1, 2.2);

        // Set the epsilon value
        double epsilon = 0.3;

        // Call the close2equals method
        boolean result = point1.close2equals(point2, epsilon);

        // Assert the expected result
        Assert.assertTrue(result);
    }
    @Test
    void close2equals2(){
        // Create two points with distant coordinates
        Point_2D point1 = new Point_2D(1, 2);
        Point_2D point2 = new Point_2D(4, 6);

        // Set the epsilon value
        double epsilon = 1.0;

        // Call the close2equals method
        boolean result = point1.close2equals(point2, epsilon);

        // Assert the expected result
        Assert.assertFalse(result);
    }

    @Test
    void vector() {
        // Create two points
        Point_2D point1 = new Point_2D(1, 2);
        Point_2D point2 = new Point_2D(4, 6);

        // Call the vector method
        Point_2D result = point1.vector(point2);

        // Assert the expected result
        Assert.assertEquals(3.0, result.x(), 0.001);
        Assert.assertEquals(4.0, result.y(), 0.001);
    }

    @Test
    void move() {
        // Create a point
        Point_2D point = new Point_2D(1, 2);

        // Create a vector to move the point
        Point_2D vector = new Point_2D(3, 4);

        // Call the move method
        point.move(vector);

        // Assert the expected result
        Assert.assertEquals(4.0, point.x(), 0.001);
        Assert.assertEquals(6.0, point.y(), 0.001);
    }

    @Test
    void scale() {
        // Create a center point
        Point_2D center = new Point_2D(1, 1);

        // Create a point to scale
        Point_2D point = new Point_2D(2, 3);

        // Set the scaling ratio
        double ratio = 2.0;

        // Call the scale method
        point.scale(center, ratio);

        // Assert the expected result
        Assert.assertEquals(3.0, point.x(), 0.001);
        Assert.assertEquals(5.0, point.y(), 0.001);
    }

    @Test
    void rotate() {
        // יצירת נקודה לבדיקה
        Point_2D point = new Point_2D(1, 1);

        // מרכז הסיבוב
        Point_2D center = new Point_2D(0, 0);

        // הסיבוב במעלות
        double angleDegrees = 90;

        // ביצוע הסיבוב
        point.rotate(center, angleDegrees);

        // אמת אמתות: נקודת המבחן צריכה להיות (1, -1) לאחר הסיבוב
        Assertions.assertEquals(-1.0, point.x(), 0.0001);
        Assertions.assertEquals(1.0000000000000002, point.y(), 0.0001);

    }
}