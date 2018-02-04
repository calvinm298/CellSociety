package cellsociety_team12.simulations;

import cells.FireCell;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import util.FireParser;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author August Ning
 *
 */
public class SpreadingOfFire extends Simulation {
//	private static FireCell[][] currGrid;
//	private static FireCell[][] nextGrid;
	// private static ArrayList<Point> fireCellArray;
	// private static ArrayList<Point> treeCellArray;
	private static FireParser parser;
	private double probCatch;
	private ArrayList<Point> burningList;
	private Random rand = new Random();
	private final String BURNING = "burning";
	private final String TREE = "tree";
	// private final String EMPTY = "empty";
	

	public SpreadingOfFire(String xml_file_name) {
		parser = new FireParser(xml_file_name);
		probCatch = parser.getProbCatch();
		this.burningList = new ArrayList<>();
		this.setupGrid();
	}
	@Override
	protected void setupGrid() {
		sizeX = parser.getSizeX();
		sizeY = parser.getSizeY();
		curr_grid = new FireCell[sizeX][sizeY];
		for (int i = 0; i < parser.getSizeX(); i++) {
			for (int j = 0; j < parser.getSizeY(); j++) {
				curr_grid[i][j] = new FireCell();
			}
		}
		for (Point p : parser.getCells(BURNING)) {
			((FireCell)curr_grid[p.x][p.y]).setBurning();
		}
//		for (Point p : parser.getCells(TREE)) {
//			((FireCell)curr_grid[p.x][p.y]).setTree();
//		}
	}
	@Override
	public void updateGrid() {
		this.updateBurningCells();
		this.updateTreeCells();
	}

	private void updateBurningCells() {
		next_grid = curr_grid;
		this.burningList.clear();
		for (int i = 0; i < parser.getSizeX(); i++) {
			for (int j = 0; j < parser.getSizeY(); j++) {
				if (((FireCell)next_grid[i][j]).isBurning()) {
					this.burningList.add(new Point(i, j));
				}
			}
		}
		for (Point p : this.burningList) {
			((FireCell)next_grid[p.x][p.y]).setEmpty();
		}
	}

	private void updateTreeCells() {
		for (int i = 0; i < parser.getSizeX(); i++) {
			for (int j = 0; j < parser.getSizeY(); j++) {
				if (((FireCell)next_grid[i][j]).isTree() && this.caughtFire(i, j)) {
					((FireCell)next_grid[i][j]).isBurning();
				}
			}
		}
		curr_grid = next_grid;
	}

	private ArrayList<Point> createNeighborPoints(int i, int j) {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(i - 1, j));
		points.add(new Point(i + 1, j));
		points.add(new Point(i, j - 1));
		points.add(new Point(i, j + 1));
		return points;
	}

	private boolean caughtFire(int i, int j) {
		ArrayList<Point> neighborPoints = this.createNeighborPoints(i, j);
		for (Point p : neighborPoints) {
			if (this.burningList.contains(p) && rand.nextDouble() <= this.probCatch) {
				return true;
			}
		}
		return false;
	}
	@Override
	protected Node getObject(int row, int col) {
		// TODO Auto-generated method stub
		Rectangle rectNode = new Rectangle(cell_sizeX, cell_sizeY);
		rectNode.setFill(((FireCell)curr_grid[row][col]).getColor());
		return rectNode;
	}
}
