package cellsociety_team12.simulations;
import cells.FireCell;
import util.FireParser;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author August Ning
 *
 */
public class SpreadingOfFire extends Simulation {
	private static FireCell[][] currGrid;
	private static FireCell[][] nextGrid;
//	private static ArrayList<Point> fireCellArray;
//	private static ArrayList<Point> treeCellArray;
	private static FireParser parser;
	private double probCatch;
	private ArrayList<Point> burningList;
	private Random rand = new Random();
	private final String BURNING = "burning";
	private final String TREE = "tree";
	private final String EMPTY = "empty";	

	
	public SpreadingOfFire(String xml_file_name) {
		parser = new FireParser(xml_file_name);
		probCatch = parser.getProbCatch();
		this.burningList = new ArrayList<>();
		this.setupGrid();
	}
	private void setupGrid() {
		currGrid = new FireCell[parser.getSizeX()][parser.getSizeY()];
		for (int i = 0; i < parser.getSizeX(); i++) {
			for (int j = 0; j < parser.getSizeY(); j++) {
				currGrid[i][j] = new FireCell();
			}
		}
		for (Point p : parser.getCells(BURNING)) {
			currGrid[p.x][p.y].setBurning();
		}
		for (Point p : parser.getCells(TREE)) {
			currGrid[p.x][p.y].setTree();
		}
	}
	public void updateGrid() {
		this.updateBurningCells();
		this.updateTreeCells();
	}
	private void updateBurningCells() {
		nextGrid = currGrid;
		this.burningList.clear();
		for (int i = 0; i < parser.getSizeX(); i++) {
			for (int j = 0; j < parser.getSizeY(); j++) {
				if (nextGrid[i][j].isBurning()) {
					this.burningList.add(new Point(i,j));
				}
			}
		}
		for (Point p : this.burningList) {
			nextGrid[p.x][p.y].setEmpty();
		}
	}
	private void updateTreeCells() {
		for (int i = 0; i < parser.getSizeX(); i++) {
			for (int j = 0; j < parser.getSizeY(); j++) {
				if (nextGrid[i][j].isTree()) {
					ArrayList<Point> pointsToCheck = this.createNeighborPoints(i, j);
					if (caughtFire(pointsToCheck)) {
						nextGrid[i][j].isBurning();
					}
				}
			}
		}
		currGrid = nextGrid;
	}
	private ArrayList<Point> createNeighborPoints(int i, int j) {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(i-1, j));
		points.add(new Point(i+1, j));
		points.add(new Point(i, j-1));
		points.add(new Point(i, j+1));
		return points;
	}
	private boolean caughtFire(ArrayList<Point> points) {
		return (rand.nextDouble() < this.probCatch && this.burningList.contains(points));
	}

}
