package cellsociety_team12.simulations;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import cells.ConwayCell;
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
	private final String sharkIndicate = "Shark";
	private final String fishIndicate = "Fish";
	private WatorParser parser;
	private final Paint FISH_COLOR = Color.BLUE;
	private final Paint SHARK_COLOR = Color.RED;
	private final Paint WATER_COLOR = Color.WHITE;

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
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				curr_grid[i][j] = new WaterCell();
			}
		}
		
		//((WaterCell) curr_grid[0][0]).setAnimal(new Shark(5, 5, 5));
		
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				//System.out.println(((WaterCell) curr_grid[i][j]).getAnimalTypeString());
			}
		}
		
		
		System.out.println(((WaterCell) curr_grid[4][4]).getAnimalTypeString());
		for (Point p : parser.getCells("fish")) {
			System.out.println("fish " + p);
			((WaterCell) curr_grid[p.x][p.y]).setAnimal(new Fish(parser.getMaxChronCount(), parser.getFishEnergy()));
		}
		for (Point p : parser.getCells("shark")) {
			System.out.println("shark " + p);
			((WaterCell) curr_grid[p.x][p.y]).setAnimal(
					new Shark(parser.getSharkMaxEnergy(), parser.getSharkStartingEnergy(), parser.getMaxChronCount()));
		}

//		for (int i = 0; i < sizeX; i++) {
//			for (int j = 0; j < sizeY; j++) {
//				if (((WaterCell) curr_grid[i][j]).isTakenByShark()) {
//					System.out.println("Shark");
//				}
//			}
//
//		}
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
				if (((WaterCell) curr_grid[i][j]).getAnimalTypeString().equals(fishIndicate)) { // need to
																											// create
					// this
					// comparator
					currAliveFishCell.add(new Point(i, j));
				}
				if (((WaterCell) curr_grid[i][j]).getAnimalTypeString().equals(sharkIndicate)) { // need to
																												// create
					// this
					// comparator
					currAliveSharkCell.add(new Point(i, j));
				}
			}
		}
	}
//
	private void chooseNextLocation() {
		for (Point currLocation : currAliveFishCell) {
			Point nextLocation = getNextFishLocation(currLocation, fishIndicate);
			
			
			((WaterCell) next_grid[nextLocation.x][nextLocation.y]).markWillBeTakenByFish();
			((WaterCell) curr_grid[currLocation.x][currLocation.y]).getAnimal().setNextLocation(currLocation.x,
					currLocation.y);
			// MARK THE NEXT LOCATION WITHIN THE OBJECT - this way we can actually update
			// the next_grid and move
			// that same object with all its attributes
		}
		for (Point currLocation : currAliveSharkCell) {
			Point nextLocation = getNextSharkLocation(currLocation, sharkIndicate);
			System.out.println("next shark location = " + nextLocation);

			((WaterCell) next_grid[nextLocation.x][nextLocation.y]).markWillBeTakenByShark();
			((WaterCell) curr_grid[currLocation.x][currLocation.y]).getAnimal().setNextLocation(currLocation.x,
					currLocation.y);

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
			if (x >= 0 && x < sizeX && y >= 0 && y < sizeY && (((WaterCell) curr_grid[x][y]).isNotTaken())
					&& (((WaterCell) curr_grid[x][y]).getWillBeTakenByFish() == false)) {
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
					&& (((WaterCell) curr_grid[x][y]).getWillBeTakenByShark() == false)) {
				fishNeighbors.add(neighbor);
			} else if (x >= 0 && x < sizeX && y >= 0 && y < sizeY && (((WaterCell) curr_grid[x][y]).isNotTaken())
					&& (((WaterCell) curr_grid[x][y]).getWillBeTakenByShark() == false)) {
				emptyNeighbors.add(neighbor);
			}
		}
		if (fishNeighbors.size() > 0) {
			return fishNeighbors;
		}
		return emptyNeighbors;
	}

	// test
	private void changeGrid() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (((WaterCell) curr_grid[i][j]).isTaken()) {
					WaterAnimals currAnimal = ((WaterCell) curr_grid[i][j]).getAnimal();
					currAnimal.setChrononCount(currAnimal.getChrononCount() + 1);
					Point nextLocation = ((WaterCell) curr_grid[i][j]).getAnimal().getNextLocation();
					if (currAnimal.getChrononCount() == currAnimal.getMaxChrononCount()) {
						// reproduction
						if (((WaterCell) next_grid[i][j]).isTakenByFish()) {
							((WaterCell) next_grid[i][j])
									.setAnimal(new Fish(parser.getMaxChronCount(), parser.getFishEnergy()));
						}
						if (((WaterCell) next_grid[i][j]).isTakenByShark()) {
							((WaterCell) next_grid[i][j]).setAnimal(new Shark(parser.getSharkMaxEnergy(),
									parser.getSharkStartingEnergy(), parser.getMaxChronCount()));
						}
						currAnimal.setChrononCount(0);
					}
					if (currAnimal.getAnimalTypeString().equals(sharkIndicate)) {
						((Shark) currAnimal).changeEnergy(-1);
						if (((Shark) currAnimal).getEnergy() == 0) {
							continue;
						}
						((WaterCell) next_grid[nextLocation.x][nextLocation.y])
								.setAnimal(((WaterCell) curr_grid[i][j]).getAnimal());

					}

					if (currAnimal.getAnimalTypeString().equals(fishIndicate)) {
						if (((WaterCell) next_grid[nextLocation.x][nextLocation.y]).getWillBeTakenByShark()) {
							((Shark) ((WaterCell) next_grid[nextLocation.x][nextLocation.y]).getAnimal())
									.changeEnergy(((Fish) currAnimal).giveEnergy());
							if (((Shark) ((WaterCell) next_grid[nextLocation.x][nextLocation.y]).getAnimal())
									.getEnergy() > ((Shark) ((WaterCell) next_grid[nextLocation.x][nextLocation.y])
											.getAnimal()).getMaxEnergy()) {
								((Shark) ((WaterCell) next_grid[nextLocation.x][nextLocation.y]).getAnimal()).setEnergy(
										((Shark) ((WaterCell) next_grid[nextLocation.x][nextLocation.y]).getAnimal())
												.getMaxEnergy());
							}
							continue; // if a space will be taken by both a fish and shark, the shark "eats" the fish
										// and the fish will not go to this possition

						}
					}

				}
			}
		}
		/**
		 * set the curr_grid = next_grid and reset next_grid
		 */
		curr_grid = next_grid;
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				((WaterCell) next_grid[i][j]).reset();
			}
		}
	}

	// {
	// for (int y = 0; y < next_grid[x].length; y++) {
	// if (((WaterCell)
	// curr_grid[x][y]).getAnimal().getAnimalTypeString().equals("Fish")) { //if the
	// cell contains a fishy
	// Point nextLocation = getRandomNeighbor(x, y);/**SET EVERYTHING FOR NEXT GRID
	// *
	// * - check surrounding cells and pick a random one if available. set chosen
	// cell's will
	// * be taken by fish to true
	// // - check number of chronons in current fish, if is max count, then
	// reproduce in
	// * previous position. (reproduce means to turn current cell's will be taken by
	// fish
	// * on).
	// */
	// }
	//
	// if {} //water cell = shark
	// } /** - check chronon count of shark - if 0, then kill shark
	// - shark moves towards fish - we need to check this by iterating all
	// neighbors.
	// if no fish, then chose a random square. turn square's will be taken by shark
	// on
	// - check number of chronon's in current shark, if max shark chronon, then set
	// current
	// cell to will be taken by shark
	// */
	// /**
	// * Iterate through all of the cells and set animals correspondingly. if
	// takenbyShark and takenbyFish
	// * are both true, then put shark in cell (shark has eaten the fish).
	// *
	// * set curr grid to next grid
	// */
	//
	// }

	
	protected Node getObject(int row, int col) {

		Rectangle tempRect = new Rectangle();
		tempRect.setWidth(cell_sizeX);
		tempRect.setHeight(cell_sizeY);

		if (((WaterCell) curr_grid[row][col]).getAnimalTypeString().equals(fishIndicate)) {
			//System.out.println(row + " " + col);
			tempRect.setFill(FISH_COLOR);
			return tempRect;
		}
		else if (((WaterCell) curr_grid[row][col]).getAnimalTypeString().equals(sharkIndicate)) {
			//System.out.println(row + " " + col);

			tempRect.setFill(SHARK_COLOR);
			return tempRect;
		}
		tempRect.setFill(WATER_COLOR);
		return tempRect;

	}

}
