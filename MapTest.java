import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


class MapTest {
    public static final int[][] map1 = {{-2, -2, -1, -2},
            {-2, -2, -2, -2},
            {-1, -2, -1, -2}};
   public static final int[][] map3=
           {{-2, -2, -1, -2},
           {-2, -2, -1, -2},
           {-1, -2, -1, -2}};
    public static final int[][] pixels = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}};

    static final int[][] map2 = {{-1, -1, -1, -1, -1, -1}, {-1, -2, -2, -2, -2, -1}, {-1, -2, -2, -1, -2, -1}, {-1, -2, -1, -2, -2, -1}, {-1, -2, -1, -1, -1, -1},
            {-1, -2, -2, -2, -2, -2}, {-1, -1, -1, -1, -1, -1}};
    static final int[][] map4 = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0}};


    @Test
    void init() {
        // Create a sample map
        Map sampleMap = new Map(3, 3, 1);
        // Initialize the map
        sampleMap.init(3, 3, 1);
        // Get the map using getMap
        int[][] result = sampleMap.getMap();
        // Assert that all elements in the map are equal to the specified value
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(1, result[i][j]);
            }
        }

    }

    @Test
    void testInit() {
        int[][] input = {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };

        // Create a sample map
        Map sampleMap = new Map(0, 1, 0);
        sampleMap.init(input);

        // Get the map using getMap
        int[][] result = sampleMap.getMap();

        // Assert that the result matches the input array
        assertArrayEquals(input, result);

    }


    @Test
    void getMap() {
        int[][] map = {
                {0, 0, 0},
                {1, 1, 1},
                {2, 2, 2}
        };
        Map sampleMap = new Map(map);

        // Get the map using getMap
        int[][] result = sampleMap.getMap();

        // Assert that the result matches the original map
        Assert.assertArrayEquals(map, result);
    }

    @Test
    void testGetWidth() {
        int [][] arr = {{2,3},{4,5}};
        Map x = new Map(arr);
        int z = x.getWidth();
        assertEquals(z, 2);
    }



    @Test
    void testGetHeight() {
        int [][] arr = {{2,3},{4,5}};
        Map x = new Map(arr);
        int z = x.getHeight();
        assertEquals(z, 2);
    }


    @Test
    void testGetPixelIntInt() {
        int [][] ans = {{1,2,3},{4,5,6},{7,8,9}};
        Map a = new Map(ans);
        int value = a.getPixel(1, 1);
        assertEquals(value, 5);
        int value2 = a.getPixel(2, 2);
        assertEquals(value2, 9);
        assertThrows(RuntimeException.class, () -> a.getPixel(4, 4));
    }


    @Test
    void testGetPixelPixel2D() {
        int [][] ans = {{1,2,3},{4,5,6},{7,8,9}};
        Map a = new Map(ans);
        Pixel2D r = new Index2D(0,0);
        int value = a.getPixel(r);
        assertEquals(value, 1);
        Pixel2D d = new Index2D(2,2);
        int value2 = a.getPixel(d);
        assertEquals(value2, 9);
    }


    @Test
    void testSetPixelIntIntInt() {
        int [][] ans1 = {{1,2,3},{4,5,6},{7,8,9}};
        Map expert = new Map(ans1);
        int v = 5;
        Pixel2D t = new Index2D(1,1);
        expert.setPixel(t, v);
        int [][] ans2 = expert.getMap();
        assertArrayEquals(ans2, ans1);
        int v2 = 9;
        Pixel2D e = new Index2D(2,2);
        Map expert2 = new Map(ans1);
        expert2.setPixel(e, v2);
        int [][] ans3 = expert2.getMap();
        assertArrayEquals(ans3, ans1);
    }



    @Test
    void testSetPixelPixel2DInt() {
        int [][] ans1 = {{1,2,3},{4,5,6},{7,8,9}};
        Map expert = new Map(ans1);
        int v1 = 9;
        expert.setPixel(2, 2, v1);
        int v2 = 2;
        expert.setPixel(0, 1,v2);
        int [][] ans2 = expert.getMap();
        assertArrayEquals(ans1, ans2);
    }



    @Test
    void fill() {
        Map2D map = new Map(pixels);

        int filledPixels = map.fill(new Index2D(1, 1), 2);

        Assert.assertEquals(9, filledPixels);
        Assert.assertEquals(2, map.getPixel(1, 1));
        Assert.assertEquals(2, map.getPixel(0, 0));
        Assert.assertEquals(2, map.getPixel(0, 1));
        Assert.assertEquals(2, map.getPixel(0, 2));
        Assert.assertEquals(2, map.getPixel(1, 0));
        Assert.assertEquals(2, map.getPixel(1, 2));
        Assert.assertEquals(2, map.getPixel(2, 0));
        Assert.assertEquals(2, map.getPixel(2, 1));
        Assert.assertEquals(2, map.getPixel(2, 2));


    }

    @Test
    void testFill() {
        int [][] ans = {
                {1,1,1,1,1,1,1,1}
                , {1,1,0,0,0,0,0,1}
                , {1,0,1,0,0,0,0,1}
                , {1,0,0,1,0,0,0,1}
                , {1,0,0,0,1,0,0,1}
                , {1,0,0,0,0,1,0,1}
                , {1,0,0,0,0,0,1,1}
                , {1,1,1,1,1,1,1,1}
        };
        Pixel2D r = new Index2D(2,5);
        Map m = new Map(ans);
        int k = m.fill(r, 5);
        int [][] result = m.getMap();
        int [][] expected = {
                {1,1,1,1,1,1,1,1}
                , {1,1,5,5,5,5,5,1}
                , {1,0,1,5,5,5,5,1}
                , {1,0,0,1,5,5,5,1}
                , {1,0,0,0,1,5,5,1}
                , {1,0,0,0,0,1,5,1}
                , {1,0,0,0,0,0,1,1}
                , {1,1,1,1,1,1,1,1}
        };
        assertArrayEquals(result, expected);
        assertEquals(k, 15);
    }


    @Test
    public void testShortestPath1() {
        Map2D map3 = new Map(map1);
        map3.setCyclic(true);
        Pixel2D zero = new Index2D(2, 1);
        Pixel2D step1 = new Index2D(1, 1);
        Pixel2D step2 = new Index2D(1, 2);
        Pixel2D step3 = new Index2D(1, 3);
        Pixel2D aim = new Index2D(2, 3);
        Pixel2D[] exp = {zero, step1, step2, step3, aim};
        Pixel2D [] shortest = map3.shortestPath(zero, aim, -1);
        System.out.println("leng"+shortest.length);
        assertEquals(shortest[3].getX(),1);


    }


    @Test
    void isInside() {
        // Create a map with dimensions 3x3
        Map map = new Map(3, 3, 0);

        // Test for pixels within the map boundaries
        assertTrue(map.isInside(new Index2D(0, 0)));
        assertTrue(map.isInside(new Index2D(1, 1)));
        assertTrue(map.isInside(new Index2D(2, 2)));

        // Test for a pixel outside the map boundaries
        assertFalse(map.isInside(new Index2D(3, 3)));
    }
    @Test
    void testAllDistance() {
        int[][] exp1 = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        Map res = new Map(exp1);
        Pixel2D n = new Index2D(1, 1);
        Map2D result = res.allDistance(new Index2D(1, 1), 1);
        int[][] result2 = result.getMap();
        int[][] expected = {
                {2, 1, 2},
                {1, 0, 1},
                {2, 1, 2}
        };
        assertArrayEquals(result2, expected);

    }


    @Test
    void allDistance4() {
        int[][] data = {
                {0, -1, -2},
                {-2, -2, -1},
                {-2, -1, -2}};
      System.out.println(Arrays.deepToString(data));
        Map map = new Map(data);
        Pixel2D start = new Index2D(0, 0);
        int obsColor = -1;
map.setCyclic(true);
        Map2D result = map.allDistance(start, obsColor);

        int[][] resultArray = result.getMap();
       System.out.println("7" + Arrays.deepToString(resultArray));
        int[][] expected = {
                {0, -1, 1},
                {1, 2,-1},
                {1, -1 ,2}
        };
//        System.out.println(Arrays.deepToString(expected));
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                assertEquals(expected[i][j], resultArray[i][j]);
            }
        }
    }

}
