package cellsociety_team12.simulations;

import cells.ConwayCell;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import util.ConwayParser;
import util.XMLParser;

import java.awt.Point;
import java.util.ArrayList;

public class GameOfLife extends Simulation {
	
	private static String XMLfileName;
	private static ArrayList<Point> cellArray;
	private static ConwayParser parser;
	private static final Paint ALIVE_COLOR = Color.GREEN;
	private static final Paint DEAD_COLOR = Color.WHITE;

	public GameOfLife(String xml_file_name) {
		parser = new ConwayParser(xml_file_name);
		cellArray = parser.getCells("cell");
		sizeX = parser.getSizeX();
		sizeY = parser.getSizeY();
		/**XML parser needs to take this in, and create the grid and objects that will be made into 
		 *private instance variables. 
		 */
	}
	/**
	 * @author August
	 * sets up the grid
	 */
	protected void setupGrid() {
		curr_grid = new ConwayCell[sizeX][sizeY];
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				curr_grid[i][j] = new ConwayCell();
			}
		}
		for (Point p : cellArray) {
			((ConwayCell) curr_grid[p.x][p.y]).setAlive();
		}
		
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				System.out.print((((ConwayCell) curr_grid[i][j]).isAlive() ? 1 : 0) + " ");
			}
			System.out.println();
		}
		System.out.println();

		//ONLY EDIT NEXTGRID
		next_grid = new ConwayCell[curr_grid.length][curr_grid[0].length];
		for(int i = 0; i < curr_grid.length; i++) {
			for(int j = 0; j < curr_grid[0].length; j++) {
				next_grid[i][j] = curr_grid[i][j];
			}
		}		

	
	}
	
	protected void updateGrid(){
//		for (int i = 0; i < sizeX; i++) {
//			for (int j = 0; j < sizeY; j++) {
//				System.out.print((((ConwayCell) curr_grid[i][j]).isAlive() ? 1 : 0) + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();

		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				int numNeighbors = getNumNeighbors(x, y);
				System.out.println("(" + y + ", " + x + "): " + numNeighbors);
				if (((ConwayCell) curr_grid[x][y]).isAlive()) {
					if (numNeighbors < 2 || numNeighbors > 3) {
						((ConwayCell) next_grid[x][y]).setDead();
					}
				}
				else {
					if (numNeighbors == 3) {
						((ConwayCell) next_grid[x][y]).setAlive();
					}
				}
			}
		}
		
//		for (int i = 0; i < sizeX; i++) {
//			for (int j = 0; j < sizeY; j++) {
//				System.out.print((((ConwayCell) curr_grid[i][j]).isAlive() ? 1 : 0) + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("Iteration over");

		//NEED A METHOD OR SOMETHING THAT SAYS CURRGRID = NEXTGRID
		for(int i = 0; i < curr_grid.length; i++) {
			for(int j = 0; j < curr_grid[0].length; j++) {
				curr_grid[i][j] = next_grid[i][j];
			}
		}		
	}

	private int getNumNeighbors(int x, int y) {
		int a = x + 1;
		int b = x - 1;
		int c = y + 1;
		int d = y - 1;
		return (checkIfAlive(b, d) +
				checkIfAlive(b, c) +
				checkIfAlive(a, d) +
				checkIfAlive(a, c) +
				checkIfAlive(b, y) +
				checkIfAlive(a, y) +
				checkIfAlive(x, d) +
				checkIfAlive(x, c));
	}

	private int checkIfAlive(int xCoor, int yCoor) {
		if (xCoor < 0 || xCoor > (sizeX - 1) || yCoor < 0 || yCoor > (sizeY - 1)) {
			System.out.println("Made it here 1, " + yCoor + " and " + xCoor);
			return 0;
		}
		if (((ConwayCell) curr_grid[xCoor][yCoor]).isAlive()) {
			System.out.println("Made it here 2");
			return 1;
		}
		return 0;
	}

	protected Node getObject(int row, int col) {
		Rectangle tempRect = new Rectangle();
		tempRect.setWidth(cell_sizeX);
		tempRect.setHeight(cell_sizeY);
		
		if (((ConwayCell) curr_grid[row][col]).isAlive()) {
			tempRect.setFill(ALIVE_COLOR);
			return tempRect;
		}
		tempRect.setFill(DEAD_COLOR);
		return tempRect;
	}	
}

