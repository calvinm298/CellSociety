package cellsociety_team12.simulations;
import java.awt.Point;
import java.util.ArrayList;
import cells.WaterCell;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import objects.wator_objects.Fish;
import objects.wator_objects.Shark;
import objects.wator_objects.WaterAnimals;
import util.WatorParser;

public class WaTor extends Simulation {

	private ArrayList<Point> currAliveFishCell;
	private ArrayList<Point> currAliveSharkCell;
	private final static String sharkIndicate = "Shark";
	private final static String fishIndicate = "Fish";
	private WatorParser parser;
	private final static Paint FISH_COLOR = Color.DARKGREEN;
	private final static Paint SHARK_COLOR = Color.DEEPSKYBLUE;
	private final static Paint WATER_COLOR = Color.WHITE;

	public WaTor(String xml_file_name) {
		parser = new WatorParser(xml_file_name);
		sizeX = parser.getSizeX();
		sizeY = parser.getSizeY();
		this.initializeCellLists();
		this.setupGrid();
	}
	private void initializeCellLists() {
		currAliveFishCell = new ArrayList<>();
		currAliveSharkCell = new ArrayList<>();
	}
	protected void setupGrid() {
		curr_grid = new WaterCell[sizeX][sizeY];
		next_grid = new WaterCell[sizeX][sizeY];
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				curr_grid[i][j] = new WaterCell();
				next_grid[i][j] = new WaterCell();
			}
		}
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
			}
		}
		for (Point p : parser.getCells("fish")) {
			((WaterCell) curr_grid[p.x][p.y]).setFish(new Fish(parser.getMaxChronCount(), parser.getFishEnergy()));
		}
		for (Point p : parser.getCells("shark")) {
			((WaterCell) curr_grid[p.x][p.y]).setShark(
					new Shark(parser.getSharkMaxEnergy(), parser.getSharkStartingEnergy(), parser.getMaxChronCount()));
		}
	}
	protected void updateGrid() {
		this.setupCellLists();
		this.chooseNextLocation();
		this.changeGrid();
	}
	private void setupCellLists() {
		this.currAliveFishCell.clear();
		this.currAliveSharkCell.clear();
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (((WaterCell) curr_grid[i][j]).getAnimalTypeString().equals(fishIndicate)) { 
					currAliveFishCell.add(new Point(i, j));
				}
				if (((WaterCell) curr_grid[i][j]).getAnimalTypeString().equals(sharkIndicate)) { 
					currAliveSharkCell.add(new Point(i, j));
				}
			}
		}
	}
	private void chooseNextLocation() {
		for (Point currLocation : currAliveSharkCell) {
			Point nextLocation = getNextSharkLocation(currLocation, sharkIndicate);
			Shark curr_shark = (Shark) ((WaterCell) curr_grid[currLocation.x][currLocation.y]).getAnimal();
			if(curr_shark.getEnergy() == 0) {
				continue;
			}
			if (curr_shark.checkReproduction() && !(currLocation.equals(nextLocation))) {
				((WaterCell) next_grid[currLocation.x][currLocation.y]).setShark(new Shark(parser.getSharkMaxEnergy(),
						parser.getSharkStartingEnergy(), parser.getMaxChronCount()));
			}
			((WaterCell) next_grid[nextLocation.x][nextLocation.y]).setShark(curr_shark);
			((WaterCell) curr_grid[nextLocation.x][nextLocation.y]).markWillBeTakenByShark();
			curr_shark.increaseChronon();
//			curr_shark.changeEnergy(-1);
			curr_shark.setEnergy(Math.min(curr_shark.getEnergy() - 1, curr_shark.getMaxEnergy()));
		}
		for (Point currLocation : currAliveFishCell) {
			Point nextLocation = getNextFishLocation(currLocation, fishIndicate);
			Fish curr_fish = (Fish) ((WaterCell) curr_grid[currLocation.x][currLocation.y]).getAnimal();
			if (((WaterCell) curr_grid[nextLocation.x][nextLocation.y]).getWillBeTakenByShark()) {
				((Shark) ((WaterCell) next_grid[nextLocation.x][nextLocation.y]).getAnimal()).changeEnergy(curr_fish.giveEnergy());
				Shark curr_shark = ((Shark) ((WaterCell) next_grid[nextLocation.x][nextLocation.y]).getAnimal());
				curr_shark.setEnergy(Math.min(curr_shark.getEnergy() + curr_fish.giveEnergy(), curr_shark.getMaxEnergy()));
				continue;
			} else {
				if (curr_fish.checkReproduction() && !(currLocation.equals(nextLocation))) {
					((WaterCell) next_grid[currLocation.x][currLocation.y])
							.setFish(new Fish(parser.getMaxChronCount(), parser.getFishEnergy()));
				}
				((WaterCell) next_grid[nextLocation.x][nextLocation.y]).setFish(curr_fish);
			}
			((WaterCell) curr_grid[nextLocation.x][nextLocation.y]).markWillBeTakenByFish();
			curr_fish.increaseChronon();
		}
	}
	private Point getNextFishLocation(Point currLocation, String animal) {
		ArrayList<Point> nextPossibleFishLocations = getNextPossibleNeighbors(currLocation, animal);
		Point nextFishLocation = getRandomLocation(nextPossibleFishLocations);
		if (nextFishLocation == null) {
			return currLocation;
		}
		return nextFishLocation;
	}
	private Point getNextSharkLocation(Point currLocation, String animal) {
		ArrayList<Point> nextPossibleSharkLocations = getNextPossibleNeighbors(currLocation, animal);
		Point nextSharkLocation = getRandomLocation(nextPossibleSharkLocations);
		if (nextSharkLocation == null) {
			return currLocation;
		}
		return nextSharkLocation;
	}
	private Point getRandomLocation(ArrayList<Point> nextPossibleFishLocations) {
		int maxNum = nextPossibleFishLocations.size();
		if (maxNum == 0) {
			return null;
		}
		int rand = (int) (Math.random() * maxNum);
		return nextPossibleFishLocations.get(rand);
	}
	private ArrayList<Point> getNextPossibleNeighbors(Point currLocation, String animal) {
		ArrayList<Point> neighborList = new ArrayList<>();
		int xCoor = currLocation.x;
		int yCoor = currLocation.y;
		neighborList.add(new Point(xCoor, yCoor + 1));
		neighborList.add(new Point(xCoor + 1, yCoor));
		neighborList.add(new Point(xCoor, yCoor - 1));
		neighborList.add(new Point(xCoor - 1, yCoor));
		ArrayList<Point> possibleLocations = new ArrayList<>();
		if (animal == fishIndicate) {
			possibleLocations = getNextPossibleLocationForFish(neighborList);
		} else if (animal == sharkIndicate) {
			possibleLocations = getNextPossibleLocationForShark(neighborList);
		}
		return possibleLocations;
	}
	private ArrayList<Point> getNextPossibleLocationForFish(ArrayList<Point> neighborList) {
		ArrayList<Point> emptyNeighbors = new ArrayList<>();
		for (Point neighbor : neighborList) {
			int x = (int) neighbor.getX();
			int y = (int) neighbor.getY();
			if (x >= 0 && x < sizeX && y >= 0 && y < sizeY && !(((WaterCell) curr_grid[x][y]).isTakenByFish())
					&& !(((WaterCell) curr_grid[x][y]).isTakenByShark())
					&& (!((WaterCell) curr_grid[x][y]).getWillBeTakenByFish())) {
				emptyNeighbors.add(neighbor);
			}
		}
		return emptyNeighbors;
	}
	private ArrayList<Point> getNextPossibleLocationForShark(ArrayList<Point> neighborList) {
		ArrayList<Point> fishNeighbors = new ArrayList<>();
		ArrayList<Point> emptyNeighbors = new ArrayList<>();
		for (Point neighbor : neighborList) {
			int x = (int) neighbor.getX();
			int y = (int) neighbor.getY();
			if (x >= 0 && x < sizeX && y >= 0 && y < sizeY && (((WaterCell) curr_grid[x][y]).isTakenByFish())
					&& (!((WaterCell) curr_grid[x][y]).getWillBeTakenByShark())) {
				fishNeighbors.add(neighbor);
			} else if (x >= 0 && x < sizeX && y >= 0 && y < sizeY && (((WaterCell) curr_grid[x][y]).isNotTaken())
					&& (!((WaterCell) curr_grid[x][y]).getWillBeTakenByShark())) {
				emptyNeighbors.add(neighbor);
			}
		}
		if (fishNeighbors.size() > 0) {
			return fishNeighbors;
		}
		return emptyNeighbors;
	}
	private void changeGrid() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				curr_grid[i][j] = next_grid[i][j];
			}
		}
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				next_grid[i][j] = new WaterCell();
	}}}
	protected Node getObject(int row, int col) {
		Rectangle tempRect = new Rectangle();
		tempRect.setWidth(cell_sizeX);
		tempRect.setHeight(cell_sizeY);

		if (((WaterCell) curr_grid[row][col]).getAnimalTypeString().equals(fishIndicate)) {
			tempRect.setFill(FISH_COLOR);
			return tempRect;
		} else if (((WaterCell) curr_grid[row][col]).getAnimalTypeString().equals(sharkIndicate)) {
			tempRect.setFill(SHARK_COLOR);
			return tempRect;
		}
		tempRect.setFill(WATER_COLOR);
		return tempRect;
	}
}