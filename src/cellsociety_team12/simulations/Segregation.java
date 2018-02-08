package cellsociety_team12.simulations;

import java.awt.Point;
import java.util.*;

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
	private double bluePercentage;
	private double redPercentage;
	private List<Point> currEmptyList;
	private List<Point> currBlueList;
	private List<Point> currRedList;
	private List<Point> nextEmptyList;
	private List<Point> nextBlueList;
	private List<Point> nextRedList;
	private final String BLUE = "blue";
	private final String RED = "red";

	public Segregation(String xml_file_name) {
		parser = new SegregationParser(xml_file_name);
		similarPercentage = parser.getSimilar();
		this.initializeCellLists();
		this.setupGrid();
	}

	@Override
	protected void setupGrid() {
		try {
		sizeX = parser.getSizeX();
		sizeY = parser.getSizeY();
		Random rand = new Random();
		this.similarPercentage = parser.getSimilar();
		this.bluePercentage = parser.getBluePercentage();
		this.redPercentage = parser.getRedPercentage();

		curr_grid = new SegregationCell[sizeX][sizeY];
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				curr_grid[i][j] = new SegregationCell();
				double randomboi = rand.nextDouble();
				if (randomboi < this.bluePercentage) {
					((SegregationCell)curr_grid[i][j]).setBlue();
				} else if (randomboi > 1 - this.redPercentage) {
					((SegregationCell)curr_grid[i][j]).setRed();
				}
			}
		}
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	@Override
	protected void updateGrid() {
		this.setupCellLists();
//		System.out.println("Blue: " + this.currRedList.size());
//		System.out.println("Red: " + this.currBlueList.size());
//		System.out.println("Empty: " + this.currEmptyList.size());
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
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
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
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (!((SegregationCell) next_grid[i][j]).isEmpty()) {
					List<Point> neighborsToCheck = this.createNeighborsList(i, j);
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

	private List<Point> createNeighborsList(int i, int j) {
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

	private int checkBlueNeighbors(List<Point> points) {
		int numNeighbors = 0;
		for (Point p : points) {
			if (this.currBlueList.contains(p)) {
				numNeighbors++;
			}
		}
		return numNeighbors;
	}

	private int checkRedNeighbors(List<Point> points) {
		int numNeighbors = 0;
		for (Point p : points) {
			if (this.currRedList.contains(p)) {
				numNeighbors++;
			}
		}
		return numNeighbors;
	}
	
	private int calcNumNeighbors(List<Point> neighbors) {
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
		if (this.currEmptyList.isEmpty()) return;
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
