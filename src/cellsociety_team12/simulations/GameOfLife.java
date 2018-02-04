package cellsociety_team12.simulations;

import cells.ConwayCell;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import util.ConwayParser;

import java.awt.Point;
import java.util.ArrayList;

public class GameOfLife extends Simulation {
	
//	private static String XMLfileName;
	private ArrayList<Point> currAliveCell;
	private ArrayList<Point> nextAliveCell;
	private ArrayList<Point> nextDeadCell;
	private ConwayParser parser;
	private static final Paint ALIVE_COLOR = Color.GREEN;
	private static final Paint DEAD_COLOR = Color.WHITE;

	public GameOfLife(String xml_file_name) {
		parser = new ConwayParser(xml_file_name);
		sizeX = parser.getSizeX();
		sizeY = parser.getSizeY();
		this.initializeCellLists();
		this.setupGrid();
		/**XML parser needs to take this in, and create the grid and objects that will be made into 
		 *private instance variables. 
		 */
	}
	private void initializeCellLists() {
		currAliveCell = new ArrayList<>();
		nextAliveCell = new ArrayList<>();
		nextDeadCell = new ArrayList<>();
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
		for (Point p : parser.getCells("cell")) {
			((ConwayCell) curr_grid[p.x][p.y]).setAlive();
		}
		
	}
	
	protected void updateGrid(){
		this.setupCellLists();
		this.checkCells();
		this.changeGrid();
//		//ONLY EDIT NEXTGRID
//		next_grid = curr_grid;
//		for (int x = 0; x < next_grid.length; x++) {
//			for (int y = 0; y < next_grid[x].length; y++) {
//				int numNeighbors = getNumNeighbors(x, y);
//				if (((ConwayCell) next_grid[x][y]).isAlive()) {
//					if (numNeighbors < 2 || numNeighbors > 3) {
//						((ConwayCell) next_grid[x][y]).setDead();
//					}					
//				}
//				if (((ConwayCell) next_grid[x][y]).isDead()) {
//					if (numNeighbors == 3) {
//						((ConwayCell) next_grid[x][y]).setAlive();
//					}
//				}
//			}
//		}
//		
//		//NEED A METHOD OR SOMETHING THAT SAYS CURRGRID = NEXTGRID
//		curr_grid = next_grid;
		
	}
//
//	private int getNumNeighbors(int x, int y) {
//		return 	checkIfAlive(x - 1, y - 1) +
//				checkIfAlive(x - 1, y + 1) +
//				checkIfAlive(x + 1, y - 1) +
//				checkIfAlive(x + 1, y + 1) +
//				checkIfAlive(x - 1, y) +
//				checkIfAlive(x + 1, y) +
//				checkIfAlive(x , y - 1) +
//				checkIfAlive(x , y + 1);			
//	}
//
//	private int checkIfAlive(int xCoor, int yCoor) {
//		int last = curr_grid.length;
//		if (xCoor < 0 || xCoor > (last - 1) || yCoor < 0 || yCoor > (last - 1)) {
//			return 0;
//		}
//		if (((ConwayCell) curr_grid[xCoor][yCoor]).isAlive()) {
//			return 1;
//		}
//		return 0;
//	}
	
	private void setupCellLists() {
		this.currAliveCell.clear();
		this.nextAliveCell.clear();
		this.nextDeadCell.clear();
		
		next_grid = curr_grid;

		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j <sizeY; j++) {
				if (((ConwayCell) next_grid[i][j]).isAlive()) {
					this.currAliveCell.add(new Point(i, j));
				}
			}
		}
	}
	
	private void checkCells() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				ArrayList<Point> pointsToCheck = this.createNeighborsList(i, j);
				int numNeighbors = this.calcNumNeighbors(pointsToCheck);
				this.calcNextTurnState(numNeighbors, i, j, ((ConwayCell) next_grid[i][j]).isAlive());
			}
		}
	}

	private int calcNumNeighbors(ArrayList<Point> points) {
		int numNeighbors = 0;
		for (Point p : points) {
			if (this.currAliveCell.contains(p)) {
				numNeighbors++;
			}
		}

		return numNeighbors;
	}
	
	private ArrayList<Point> createNeighborsList(int i, int j) {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(i - 1, j + 1));
		points.add(new Point(i, j + 1));
		points.add(new Point(i + 1, j + 1));
		points.add(new Point(i + 1, j));
		points.add(new Point(i + 1, j - 1));
		points.add(new Point(i, j - 1));
		points.add(new Point(i - 1, j - 1));
		points.add(new Point(i - 1, j));
		return points;
	}
	
	private void calcNextTurnState(int num, int i, int j, boolean isAlive) {
		if (num > 3 || num < 2) {
			this.nextDeadCell.add(new Point(i,j));
		} if (num == 2 && isAlive) {
			this.nextAliveCell.add(new Point(i,j));
		} if (num == 2 && !isAlive) {
			this.nextDeadCell.add(new Point(i,j));
		} if (num == 3) {
			this.nextAliveCell.add(new Point(i,j));
		}
	}
	
	private void changeGrid() {
		for (Point p : this.nextAliveCell) {
			((ConwayCell) next_grid[p.x][p.y]).setAlive();
		}
		for (Point p : this.nextDeadCell) {
			((ConwayCell) next_grid[p.x][p.y]).setDead();
		}
		curr_grid = next_grid;
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
