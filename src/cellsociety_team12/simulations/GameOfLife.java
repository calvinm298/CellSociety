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
		cellArray = parser.getCells();
		/**XML parser needs to take this in, and create the grid and objects that will be made into 
		 *private instance variables. 
		 */
	}
	/**
	 * @author August
	 * sets up the grid
	 */
	protected void setupGrid() {
		curr_grid = new ConwayCell[parser.getSizeX()][parser.getSizeY()];
		for (int i = 0; i < parser.getSizeX(); i++) {
			for (int j = 0; j < parser.getSizeY(); j++) {
				curr_grid[i][j] = new ConwayCell();
			}
		}
		for (Point p : cellArray) {
			((ConwayCell) curr_grid[p.x][p.y]).setAlive();
		}
	}
	
	protected void updateGrid(){
		//ONLY EDIT NEXTGRID
		next_grid = curr_grid;
		for (int x = 0; x < next_grid.length; x++) {
			for (int y = 0; y < next_grid[x].length; y++) {
				int numNeighbors = getNumNeighbors(x, y);
				if (((ConwayCell) next_grid[x][y]).isAlive()) {
					if (numNeighbors < 2 || numNeighbors > 3) {
						((ConwayCell) next_grid[x][y]).setDead();
					}					
				}
				if (((ConwayCell) next_grid[x][y]).isDead()) {
					if (numNeighbors == 3) {
						((ConwayCell) next_grid[x][y]).setAlive();
					}
				}
			}
		}
		
		//NEED A METHOD OR SOMETHING THAT SAYS CURRGRID = NEXTGRID
		curr_grid = next_grid;
		
	}

	private int getNumNeighbors(int x, int y) {
		return 	checkIfAlive(x - 1, y - 1) +
				checkIfAlive(x - 1, y + 1) +
				checkIfAlive(x + 1, y - 1) +
				checkIfAlive(x + 1, y + 1) +
				checkIfAlive(x - 1, y) +
				checkIfAlive(x + 1, y) +
				checkIfAlive(x , y - 1) +
				checkIfAlive(x , y + 1);			
	}

	private int checkIfAlive(int xCoor, int yCoor) {
		int last = curr_grid.length;
		if (xCoor < 0 || xCoor > last || yCoor < 0 || yCoor > last) {
			return 0;
		}
		if (((ConwayCell) curr_grid[xCoor][yCoor]).isAlive()) {
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
