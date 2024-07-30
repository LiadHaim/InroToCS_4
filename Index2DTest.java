import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Index2DTest {

    @Test
    void distance2D() {
        Index2D t = new Index2D(1, 0); // creating new a new point to check
        Index2D o = new Index2D(0, 0); // creating the origin point
        double expected = 1; // the expected value
        double ans1 = t.distance2D(o); // checking whether it's symmetric
        double ans2 = o.distance2D(t); // checking whether it's symmetric
        double ans = t.distance2D(t); // checking whether it's reflixive
        Index2D x = null; // creating a null object
        assertThrows(RuntimeException.class, () -> t.distance2D(x)); // giving the function a null object and checking whether it would throw Exception or not
        assertEquals(ans1, expected); // asserting the symmetric value
        assertEquals(ans2, expected); // asserting the symmetric value
        assertEquals(ans, 0); // asserting the reflixivity of the distance
    }

    @Test
    void testEquals() {
        Index2D z = new Index2D(0, 0); // creating new points to check the function
        Index2D v = new Index2D(0, 0);
        Index2D c = new Index2D(0, 0);
        Index2D x = new Index2D(3, 3);
        Index2D d = new Index2D(0, 5);
        Index2D e = new Index2D(5, 0);
        boolean issame1 = v.equals(v); // checking whether the funcutin is reflixive
        boolean issame2 = z.equals(v); //checking whether the function is symmetric
        boolean issame3 = v.equals(z); // checking whether the function is symmetric
        boolean issame4 = d.equals(e); // giving the function an invalid situation
        boolean issame5 = v.equals(c); // checking whether the function is transitive
        boolean issame6 = z.equals(c); // checking whether the function is transitive
        if (!issame1) {
            fail();
        } // making the function fail if it's not reflixive
        if (!issame2) {
            fail();
        } // making the function fail if it's not symmetric
        if (!issame3) {
            fail();
        } // making the function fail if it's not symmetric
        if (issame4) {
            assertFalse(issame4);
        } // checking the validity of the function when swaping the coordinates of x,y
        assertFalse(!issame5); // making the function fail if it's not transitive
        assertFalse(!issame6); // making the function fail if it's not transitive
    }
}