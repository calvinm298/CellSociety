package cellsociety_team12.simulations;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.Random;

import cells.SegregationCell;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import util.SegregationParser;

/**
 * @author August Ning
 *
 */
public class Segregation extends Simulation {
	private static SegregationParser parser;
	private double similarPercentage;
	private ArrayList<Point> currEmptyList;
	private ArrayList<Point> currBlueList;
	private ArrayList<Point> currRedList;
	private ArrayList<Point> nextEmptyList;
	private ArrayList<Point> nextBlueList;
	private ArrayList<Point> nextRedList;
//	private Random rand = new Random();
	private final String BLUE = "blue";
	private final String RED = "red";
//	private final String EMPTY = "empty";

	public Segregation(String xml_file_name) {
		parser = new SegregationParser(xml_file_name);
		similarPercentage = parser.getSimilar();
		this.initializeCellLists();
		this.setupGrid();
	}

	@Override
	protected void setupGrid() {
		sizeX = parser.getSizeX();
		sizeY = parser.getSizeY();
		curr_grid = new SegregationCell[sizeX][sizeY];
		for (int i = 0; i < parser.getSizeX(); i++) {
			for (int j = 0; j < parser.getSizeY(); j++) {
				curr_grid[i][j] = new SegregationCell();
			}
		}
		for (Point p : parser.getCells(BLUE)) {
			((SegregationCell) curr_grid[p.x][p.y]).setBlue();
		}
		for (Point p : parser.getCells(RED)) {
			((SegregationCell) curr_grid[p.x][p.y]).setRed();
		}
	}
	
	@Override
	protected void updateGrid() {
		this.setupCellLists();
		this.checkCells();
		this.changeGrid();
	}
	private void initializeCellLists() {
		currEmptyList = new ArrayList<>();
		currBlueList = new ArrayList<>();
		currRedList = new ArrayList<>();
		nextEmptyList = new ArrayList<>();
		nextBlueList = new ArrayList<>();
		nextRedList = new ArrayList<>();
	}
	private void setupCellLists() {
		next_grid = curr_grid;
		this.currEmptyList.clear();
		this.currBlueList.clear();
		this.currRedList.clear();
		this.nextEmptyList.clear();
		this.nextBlueList.clear();
		this.nextRedList.clear();
		for (int i = 0; i < parser.getSizeX(); i++) {
			for (int j = 0; j < parser.getSizeY(); j++) {
				if (((SegregationCell) next_grid[i][j]).isEmpty()) {
					this.currEmptyList.add(new Point(i, j));
				} else if (((SegregationCell) next_grid[i][j]).isBlue()) {
					this.currBlueList.add(new Point(i, j));
				} else if (((SegregationCell) next_grid[i][j]).isRed()) {
					this.currRedList.add(new Point(i, j));
				}
			}
		}
	}

	private void checkCells() {
		for (int i = 0; i < parser.getSizeX(); i++) {
			for (int j = 0; j < parser.getSizeY(); j++) {
				if (!((SegregationCell) next_grid[i][j]).isEmpty()) {
					ArrayList<Point> neighborsToCheck = this.createNeighborsList(i, j);
					int numSameNeighbors = -1;
					String cellType = "";
					if (((SegregationCell) next_grid[i][j]).isBlue()) {
						cellType = BLUE;
						numSameNeighbors = this.checkBlueNeighbors(neighborsToCheck);
					} else if (((SegregationCell) next_grid[i][j]).isRed()) {
						cellType = RED;
						numSameNeighbors = this.checkRedNeighbors(neighborsToCheck);
					}
					int numTotalNeighbors = calcNumNeighbors(neighborsToCheck);
					double simPercent = (1.0 * numSameNeighbors)/numTotalNeighbors;
					
					if (!isSatisfied(simPercent)) {
						this.moveCell(cellType, i, j);
					}
				}
			}
		}
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

	private int checkBlueNeighbors(ArrayList<Point> points) {
		int numNeighbors = 0;
		for (Point p : points) {
			if (this.currBlueList.contains(p)) {
				numNeighbors++;
			}
		}
		return numNeighbors;
	}

	private int checkRedNeighbors(ArrayList<Point> points) {
		int numNeighbors = 0;
		for (Point p : points) {
			if (this.currRedList.contains(p)) {
				numNeighbors++;
			}
		}
		return numNeighbors;
	}
	
	private int calcNumNeighbors(ArrayList<Point> neighbors) {
		int numNeighbors = 0;
		for (Point p : neighbors) {
			if (this.currBlueList.contains(p) || this.currRedList.contains(p)) {
				numNeighbors++;
			}
		}
		return numNeighbors;
	}
	
	private boolean isSatisfied(double percentage) {
		return (percentage >= this.similarPercentage);
	}
	private void moveCell(String type, int i, int j) {
		Point currPoint = new Point(i, j);
		this.nextEmptyList.add(currPoint);
		Collections.shuffle(currEmptyList);
		Point newPoint = currEmptyList.get(0);
		currEmptyList.remove(0);
		if (type.equals(BLUE)) {
			this.nextBlueList.add(newPoint);
		} else if (type.equals(RED)) {
			this.nextRedList.add(newPoint);
		}
	}
	
	private void changeGrid() {
		for (Point p : this.nextEmptyList) {
			((SegregationCell) next_grid[p.x][p.y]).setEmpty();
		}
		for (Point p : this.nextBlueList) {
			((SegregationCell) next_grid[p.x][p.y]).setBlue();
		}
		for (Point p : this.nextRedList) {
			((SegregationCell) next_grid[p.x][p.y]).setRed();
		}
		curr_grid = next_grid;
	}
	@Override
	protected Node getObject(int row, int col) {
		// TODO Auto-generated method stub
		Rectangle rectNode = new Rectangle(cell_sizeX, cell_sizeY);
		rectNode.setFill(((SegregationCell) curr_grid[row][col]).getColor());
		return rectNode;
	}

}
