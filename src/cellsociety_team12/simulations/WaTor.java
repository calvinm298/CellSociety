package cellsociety_team12.simulations;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import cells.WaterCell;
import javafx.scene.Node;
import objects.wator_objects.Fish;
import objects.wator_objects.Shark;
import objects.wator_objects.WaterAnimals;

public class WaTor extends Simulation {

	private ArrayList<Point> currAliveFishCell;
	private ArrayList<Point> currAliveSharkCell;

	public WaTor(String xml_file_name) {
	}

	protected void setupGrid() {
		// TODO Auto-generated method stub

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
				if (((WaterCell) curr_grid[i][j]).getAnimal().getAnimalTypeString().equals("Fish")) { // need to create
																										// this
																										// comparator
					currAliveFishCell.add(new Point(i, j));
				}
				if (((WaterCell) curr_grid[i][j]).getAnimal().getAnimalTypeString().equals("Shark")) { // need to create
																										// this
																										// comparator
					currAliveSharkCell.add(new Point(i, j));
				}
			}
		}
	}

	private void chooseNextLocation() {
		for (Point currLocation : currAliveFishCell) {
			Point nextLocation = getNextFishLocation(currLocation, "Fish");
			((WaterCell) next_grid[(int) nextLocation.getX()][(int) nextLocation.getY()]).markWillBeTakenByFish();
			((WaterCell) curr_grid[(int) currLocation.getX()][(int) currLocation.getY()]).getAnimal()
					.setNextLocation((int) currLocation.getX(), (int) currLocation.getY());
			// MARK THE NEXT LOCATION WITHIN THE OBJECT - this way we can actually update
			// the next_grid and move
			// that same object with all its attributes
		}
		for (Point currLocation : currAliveSharkCell) {
			Point nextLocation = getNextSharkLocation(currLocation, "Shark");
			((WaterCell) next_grid[(int) nextLocation.getX()][(int) nextLocation.getY()]).markWillBeTakenByShark();
			((WaterCell) curr_grid[(int) currLocation.getX()][(int) currLocation.getY()]).getAnimal()
					.setNextLocation((int) currLocation.getX(), (int) currLocation.getY());

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
		int xCoor = (int) currLocation.getX();
		int yCoor = (int) currLocation.getY();
		neighborList.add(new Point(xCoor - 1, yCoor + 1));
		neighborList.add(new Point(xCoor, yCoor + 1));
		neighborList.add(new Point(xCoor + 1, yCoor + 1));
		neighborList.add(new Point(xCoor + 1, yCoor));
		neighborList.add(new Point(xCoor + 1, yCoor - 1));
		neighborList.add(new Point(xCoor, yCoor - 1));
		neighborList.add(new Point(xCoor - 1, yCoor - 1));
		neighborList.add(new Point(xCoor - 1, yCoor));
		ArrayList<Point> possibleLocations = new ArrayList<>();
		if (animal == "Fish") {
			possibleLocations = getNextPossibleLocationForFish(neighborList);
		} else if (animal == "Shark") {
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
	//test
	private void changeGrid() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (((WaterCell) curr_grid[i][j]).isTaken()) {
					WaterAnimals currAnimal = ((WaterCell) curr_grid[i][j]).getAnimal();
					currAnimal.setChrononCount(currAnimal.getChrononCount() + 1);
					if (currAnimal.getChrononCount() == currAnimal.getMaxChrononCount()) {
						//reproduction
						((WaterCell)next_grid[i][j]).setAnimal(new WaterAnimals //WHATEVER THE CURRENT WATOR ANIMAL IS);
						currAnimal.setChrononCount(0);
					}
					if (currAnimal.getAnimalTypeString().equals("Shark")){
						((Shark)currAnimal).setEnergy(((Shark)currAnimal).getEnergy() - 1);
						if (((Shark)currAnimal).getEnergy() == 0) {
							continue;
						}
;					}
					Point nextLocation = ((WaterCell) curr_grid[i][j]).getAnimal().getNextLocation();
					((WaterCell)next_grid[nextLocation.x][nextLocation.y]).setAnimal(((WaterCell) curr_grid[i][j]).getAnimal());;
					
					/**
					 * stuck a little on this part, but set the next_grid location corresponding to
					 * nextLocation as the animal at curr_grid[i][j].
					 */
				}
			}
		}
		/**
		 * set the curr_grid = next_grid and reset next_grid
		 */
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

	@Override
	protected Node getObject(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

}
