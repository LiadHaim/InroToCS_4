import java.util.*;

/**
 * This class represents a 2D map as a "screen" or a raster matrix or maze over integers.
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D {
    public static void main(String[] args) {

    }
    private int[][] _map;
    private boolean _cyclicFlag = true;

    /**
     * Constructs a w*h 2D raster map with an init value v.
     *
     * @param w;
     * @param h;
     * @param v;
     */
    public Map(int w, int h, int v) {
        init(w, h, v);
    }

    /**
     * Constructs a square map (size*size).
     *
     * @param size;
     */
    public Map(int size) {
        this(size, size, 0);
    }

    /**
     * Constructs a map from a given 2D array.
     *
     * @param data;
     */
    public Map(int[][] data) {
        init(data);
    }

    @Override
    public void init(int w, int h, int v) {
        // Create a new map with dimensions (width: w, height: h)

        this._map = new int[w][h];// Iterate over each row

        for (int i = 0; i < w; i++) {// Iterate over each column
            for (int j = 0; j < h; j++) {
                this._map[i][j] = v;// Set the value of the current pixel to v
            }
        }
    }

    @Override
    public void init(int[][] arr) throws RuntimeException {
        // Check if the input array is null

        if (arr == null) {
            throw new RuntimeException("RuntimeException, arr can't be null");
        } else {
            // Create a new map with the same dimensions as the input array
            _map = new int[arr.length][arr[0].length];        // Iterate over each row in the input array
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) { // Copy the value from the input array to the map
                    _map[i][j] = arr[i][j];

                }

            }
        }
    }

    @Override
    public int[][] getMap() {    // Initialize the answer array as null
       int[][] ans = null;
        // Create a copy of the map
        int[][] copyMap = new int[this._map.length][this._map[0].length];
        //System.out.println( "ds" +Arrays.deepToString(copyMap));
        // Return the answer array (currently null)
        for (int i = 0; i < this._map.length; i++) {
            for (int j = 0; j < this._map[0].length; j++) {
                copyMap[i][j] =  this._map[i][j];
            }

        }
        return copyMap;
    }

    @Override
    public int getWidth() {    // Return the length of the first dimension of the map

        return _map.length;
    }

    @Override
    public int getHeight() {    // Return the length of the second dimension of the map
        return _map[0].length;
    }

    @Override
    public int getPixel(int x, int y) {
        // Adjust coordinates if map is cyclic
        return _map[x][y];    // Return the value at the specified coordinates in the map
    }

    @Override
    public int getPixel(Pixel2D p) {
        // Call the overloaded getPixel(int x, int y) method with the coordinates of the given pixel
        return this.getPixel(p.getX(), p.getY());
    }

    @Override
    public void setPixel(int x, int y, int v) {
        // Adjust coordinates if map is cyclic
        if (_cyclicFlag) {        // Calculate adjusted coordinates using modulo operator to wrap around the map

            x = (x + _map.length) % _map.length;
            y = (y + _map[0].length) % _map[0].length;
        }// Set the value v at the specified coordinates in the map

        _map[x][y] = v;


    }


    @Override
    public void setPixel(Pixel2D p, int v) {
        // Set the value v at the coordinates specified by the given Pixel2D object p
        _map[p.getX()][p.getY()] = v;
    }
    @Override
    public int fill(Pixel2D xy, int new_v) {
        int ans = 0;
        int original_color = getPixel(xy);

        if (original_color != new_v) {
            Queue<Pixel2D> queue = new LinkedList<>();
            queue.offer(xy);

            while (!queue.isEmpty()) {
                Pixel2D cPixel = queue.poll();
                int x = cPixel.getX();
                int y = cPixel.getY();

                if (getPixel(cPixel) == original_color) {
                    setPixel(cPixel, new_v);
                    ans++;

                    Pixel2D left = new Index2D(x - 1, y);
                    Pixel2D right = new Index2D(x + 1, y);
                    Pixel2D up = new Index2D(x, y - 1);
                    Pixel2D down = new Index2D(x, y + 1);

                    if (isInside(left) && getPixel(left) == original_color) {
                        queue.offer(left);
                    }
                    if (isInside(right) && getPixel(right) == original_color) {
                        queue.offer(right);
                    }
                    if (isInside(up) && getPixel(up) == original_color) {
                        queue.offer(up);
                    }
                    if (isInside(down) && getPixel(down) == original_color) {
                        queue.offer(down);
                    }
                }
            }
        }

        return ans;
    }
    @Override
    public Pixel2D[] shortestPath(Pixel2D start, Pixel2D end, int obsColor) {
        Pixel2D[] ans = null;  // the result.

        Map2D allD = this.allDistance(start, obsColor);
        int target = allD.getPixel(end);

        // Check if the target pixel is unreachable or an obstacle.
        if ((target == -1) || (target == -2)) {
            return null;
        }

        // Check if the target pixel is the starting pixel.
        if (target == 0) {
            ans = new Pixel2D[1];
            ans[0] = end;
            return ans;
        }

        ans = new Pixel2D[target + 1];
        ArrayList<Pixel2D> track = new ArrayList<>();
        track.add(end);
        ans[target] = end;

        int trackIndex = 0;
        while (trackIndex < track.size()) {
            Pixel2D temp = track.get(trackIndex);
            trackIndex++;

            // Check if we have reached the starting pixel.
            if (temp.equals(start)) {
                break;
            }

            Pixel2D[] neighbors = getNeighbors(temp);
            for (Pixel2D neighbor : neighbors) {
                // Check if the neighbor is a valid pixel and its distance is one less than the current pixel.
                if ((this.isCyclic() || isInside(neighbor)) && allD.getPixel(neighbor) == allD.getPixel(temp) - 1) {
                    track.add(neighbor);
                    ans[allD.getPixel(temp) - 1] = neighbor;
                    break;
                }
            }
        }
        return ans;
    }


    private Pixel2D[] getNeighbors(Pixel2D p) {
        int x = p.getX();
        int y = p.getY();
        Pixel2D[] neighbors = new Pixel2D[4];
        // Calculate the indices of the neighboring pixels
        // Taking into account the cyclic nature of the map
        // Up neighbor
        neighbors[0] = new Index2D((x - 1 + getWidth()) % getWidth(), y);
        // Down neighbor
        neighbors[1] = new Index2D((x + 1) % getWidth(), y);
        // Right neighbor
        neighbors[2] = new Index2D(x, (y + 1) % getHeight());
        // Left neighbor
        neighbors[3] = new Index2D(x, (y - 1 + getHeight()) % getHeight());
        return neighbors;
    }

    @Override
    public boolean isInside(Pixel2D p) {
        // Check if the given pixel is inside the map boundaries
        if (p.getX() >= 0 && p.getX() < _map.length && p.getY() >= 0 && p.getY() < _map[0].length) {
            return true;// The pixel is inside the map
        }

        return false;// The pixel is outside the map
    }


    @Override
    public boolean isCyclic() {// Return the value of the cyclic flag
        return _cyclicFlag;

    }


    @Override
    public void setCyclic(boolean cy) {// Set the value of the cyclic flag to the given value
        _cyclicFlag = cy;
        ;
    }

    @Override
    public Map2D allDistance(Pixel2D start, int obsColor) {
        // Create a map to store the distances
        Map2D distanceMap = new Map(getWidth(), getHeight(), -1);
        distanceMap.setCyclic(this.isCyclic());

        // Create a queue to perform breadth-first search
        Queue<Pixel2D> queue = new LinkedList<>();
        queue.offer(start);

        // Set the initial distance of the starting pixel to 0
        int distance = 0;
        distanceMap.setPixel(start, distance);

        // Perform breadth-first search
        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all pixels at the current distance level
            for (int i = 0; i < size; i++) {
                Pixel2D current = queue.poll();
                Pixel2D[] neighbors = getNeighbors(current);

                // Visit each neighbor pixel
                for (Pixel2D neighbor : neighbors) {
                    // Check if the neighbor pixel is unvisited and not an obstacle
                    if (distanceMap.getPixel(neighbor) == -1 && getPixel(neighbor) != obsColor) {
                        // Update the distance of the neighbor pixel and enqueue it
                        distanceMap.setPixel(neighbor, distance + 1);
                        queue.offer(neighbor);
                    }
                }
            }

            // Increment the distance level
            distance++;
        }

        // Return the distance map
        return distanceMap;
    }

}