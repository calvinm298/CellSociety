package cellsociety_team12.simulations;

import cells.ConwayCell;
import util.ConwayParser;
import util.XMLParser;

import java.awt.Point;
import java.util.ArrayList;

public class GameOfLife extends Simulation {
	
	private static ConwayCell[][] currGrid;
	private static ConwayCell[][] nextGrid;
	private static ArrayList<Point> cellArray;
	private static ConwayParser parser;



	public GameOfLife(String simulation_name, String xml_file_name) {
		super(simulation_name, xml_file_name);
		ConwayParser parser = new ConwayParser(xml_file_name);
		 cellArray = parser.getCells();
		/**XML parser needs to take this in, and create the grid and objects that will be made into 
		 *private instance variables. 
		 */
	}

	
	private void updateGrids(ConwayCell[][] currGrid){
		//ONLY EDIT NEXTGRID
		ConwayCell[][] nextGrid = currGrid;
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
