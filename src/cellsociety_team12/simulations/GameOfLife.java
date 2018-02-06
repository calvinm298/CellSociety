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
		try {
			curr_grid = new ConwayCell[sizeX][sizeY];
			for (int i = 0; i < sizeX; i++) {
				for (int j = 0; j < sizeY; j++) {
					curr_grid[i][j] = new ConwayCell();
				}
			}
			for (Point p : parser.getCells("cell")) {
				((ConwayCell) curr_grid[p.x][p.y]).setAlive();
			}
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	protected void updateGrid(){
		this.setupCellLists();
		this.checkCells();
		this.changeGrid();
	}
	
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
			if(!(p.x < 0 || p.x > sizeX || p.y < 0 || p.y > sizeY) && (this.currAliveCell.contains(p))) {
				numNeighbors++;
			}
		}
		return numNeighbors;
	}
	
	private ArrayList<Point> createNeighborsList(int i, int j) {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(i - 1, j - 1));
		points.add(new Point(i - 1, j));
		points.add(new Point(i - 1, j + 1));
		points.add(new Point(i, j - 1));
		points.add(new Point(i, j + 1));
		points.add(new Point(i + 1, j - 1));
		points.add(new Point(i + 1, j));
		points.add(new Point(i + 1, j + 1));
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
