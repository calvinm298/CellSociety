package cellsociety_team12.simulations;

import cells.ConwayCell;
import javafx.scene.Group;
import util.ConwayParser;
import util.XMLParser;

import java.awt.Point;
import java.util.ArrayList;

public class GameOfLife extends Simulation {
	
	private static String XMLfileName;
	private static ConwayCell[][] currGrid;
	private static ConwayCell[][] nextGrid;
	private static ArrayList<Point> cellArray;
	private static ConwayParser parser;
	private static Group root;



	public GameOfLife(String xml_file_name) {
		parser = new ConwayParser(xml_file_name);
		cellArray = parser.getCells();
		root = getRoot();
		/**XML parser needs to take this in, and create the grid and objects that will be made into 
		 *private instance variables. 
		 */
	}
	/**
	 * @author August
	 * sets up the grid
	 */
	private void setupGrid() {
		currGrid = new ConwayCell[parser.getSizeX()][parser.getSizeY()];
		for (int i = 0; i < parser.getSizeX(); i++) {
			for (int j = 0; j < parser.getSizeY(); j++) {
				currGrid[i][j] = new ConwayCell();
			}
		}
		for (Point p : cellArray) {
			currGrid[p.x][p.y].setAlive();
		}
	}
	
	private void updateGrids(){
		//ONLY EDIT NEXTGRID
		nextGrid = currGrid;
		for (int x = 0; x < nextGrid.length; x++) {
			for (int y = 0; y < nextGrid[x].length; y++) {
				int numNeighbors = getNumNeighbors(x, y);
				if (nextGrid[x][y].isAlive()) {
					if (numNeighbors < 2 || numNeighbors > 3) {
						nextGrid[x][y].setDead();
					}					
				}
				if (nextGrid[x][y].isDead()) {
					if (numNeighbors == 3) {
						nextGrid[x][y].setAlive();
					}
				}
			}
		}
		
		//NEED A METHOD OR SOMETHING THAT SAYS CURRGRID = NEXTGRID
		currGrid = nextGrid;
		
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
		int last = currGrid.length;
		if (xCoor < 0 || xCoor > last || yCoor < 0 || yCoor > last) {
			return 0;
		}
		if (currGrid[xCoor][yCoor].isAlive()) {
			return 1;
		}
		return 0;
	}
	
	
	
}
