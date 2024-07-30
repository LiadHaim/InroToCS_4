import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import exe.ex3.game.Game;
import exe.ex3.game.GhostCL;
import exe.ex3.game.PacManAlgo;
import exe.ex3.game.PacmanGame;

/**
 * ID : 212274054
 **/

/**
 * This is the major algorithmic class for Ex3 - the PacMan game:
 *
 * This code is a very simple example (random-walk algorithm).
 * Your task is to implement (here) your PacMan algorithm.
 */
public class Ex3Algo implements PacManAlgo{
	private int _count;
	public Ex3Algo() {_count=0;}

	@Override
	/**
	 *  Add a short description for the algorithm as a String.
	 */
	public String getInfo() {
		return "This algorithm calculate the distances using all distances function" +
				"to detect the shortest path ,then finding the direction to the next move";
	}

	@Override
	/**
	 * This ia the main method - that you should design, implement and test.
	 */
	/**
	 * Determines the next move for the Pacman in the game.
	 * @param game The Pacman game state.
	 * @return The direction of the next move.
	 */
/**
 * Determines the next move for the Pacman in the game.
 * @param game The Pacman game state.
 * @return The direction of the next move.
 */
	public int move(PacmanGame game) {
		// Perform certain actions only on specific counts
		if (_count == 0 || _count == 300) {
			int gameCode = 0;
			int[][] gameBoard = game.getGame(0);
			printBoard(gameBoard);
			int blue = Game.getIntColor(Color.BLUE, gameCode);
			int pink = Game.getIntColor(Color.PINK, gameCode);
			int black = Game.getIntColor(Color.BLACK, gameCode);
			int green = Game.getIntColor(Color.GREEN, gameCode);
			System.out.println("Blue=" + blue + ", Pink=" + pink + ", Black=" + black + ", Green=" + green);
			String pacmanPos = game.getPos(gameCode).toString();
			System.out.println("Pacman coordinate: " + pacmanPos);
			GhostCL[] ghosts = game.getGhosts(gameCode);
			printGhosts(ghosts);
			int up = Game.UP, left = Game.LEFT, down = Game.DOWN, right = Game.RIGHT;
		}

		// Increment the count
		_count++;

		// Determine the next move based on the game state
		int dir = endGame(game);

		// Return the direction of the next move
		return dir;
	}


	private static void printBoard(int[][] board) {
		// Iterate over the rows
		for (int y = 0; y < board.length; y++) {
			// Iterate over the columns
			for (int x = 0; x < board[0].length; x++) {
				// Get the value at the current position
				int value = board[y][x];
				System.out.print(value + "\t"); // Print the value
			}
			System.out.println(); // Move to the next line for the next row
		}
	}


	private static void printGhosts(GhostCL[] ghosts) {
		for (int i = 0; i < ghosts.length; i++) {
			GhostCL ghost = ghosts[i];
			System.out.println(i + ") Status: " + ghost.getStatus() + ", Type: " + ghost.getType() + ", Position: " + ghost.getPos(0) + ", Remaining Time as Eatable: " + ghost.remainTimeAsEatable(0));
		}
	}


	private static Pixel2D PinkNieghbours(Pixel2D pos, Map map, Map2D allDis) {
		Pixel2D closestPink = new Index2D(0, 0);
		int maxDistance = Integer.MAX_VALUE;
		int width = map.getWidth();
		int height = map.getHeight();

		for (int i = 0; i < width; i++) {
			for (int j = 1; j < height - 1; j++) {
				if (map.getPixel(i, j) == 3) { // Check if the pixel is pink
					if (allDis.getPixel(i, j) != -1) { // Check if the pixel has a valid distance
						int currentDistance = allDis.getPixel(i, j);
						if (maxDistance > currentDistance) {
							maxDistance = currentDistance;
							closestPink = new Index2D(i, j);
						}
					}
				}
			}
		}

		return closestPink;
	}


	private static int endGame(PacmanGame game) {
// Extract the game board and create a Map object
		int[][] board = game.getGame(0);
		Map map = new Map(board);

// Get Pacman's current position
		String pacmanPos = game.getPos(0);
		String[] posParts = pacmanPos.split(",");
		int x = Integer.parseInt(posParts[0]);
		int y = Integer.parseInt(posParts[1]);
		Pixel2D pos = new Index2D(x, y);
// Compute all distances from Pacman's position
		Map2D allDistances = map.allDistance(pos, 1);
// Find the closest pink pixel
		Pixel2D closestPink = PinkNieghbours(pos, map, allDistances);
// Compute the shortest path to the closest pink pixel
		Pixel2D[] path = map.shortestPath(pos, closestPink, 1);
// Determine the next direction to move
		int direction = findDir(pos, path[1]);
		return direction;
	}
	private static int findDir(Pixel2D pose,Pixel2D dest){
		//go down && up
		if (pose.getX() == dest.getX()){
			if (pose.getY()+1 == dest.getY()){return Game.UP;}
			if (pose.getY()-1 == dest.getY()){return Game.DOWN;}
			if (pose.getY() < dest.getY()){return Game.DOWN;}
			if (pose.getY() > dest.getY()){return Game.UP;}
			//go left && right
		} else if (pose.getY() == dest.getY()){
			if (pose.getX()+1 == dest.getX()){return Game.RIGHT;}
			if (pose.getX()-1 == dest.getX()){return Game.LEFT;}
			if (pose.getX() > dest.getX()){return Game.RIGHT;}
			if (pose.getX() < dest.getX()){return Game.LEFT;}
		}
		return -1;
	}
}