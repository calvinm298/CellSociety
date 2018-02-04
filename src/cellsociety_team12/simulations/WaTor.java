package cellsociety_team12.simulations;

import cells.WaterCell;
import javafx.scene.Node;
import objects.wator_objects.Fish;
import objects.wator_objects.WaterAnimals;

public class WaTor extends Simulation {

	public WaTor(String xml_file_name) {
	}

	@Override
	protected void setupGrid() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void updateGrid() {
		// TODO Auto-generated method stub
		for (int x = 0; x < next_grid.length; x++) {
			for (int y = 0; y < next_grid[x].length; y++) {
				if (((WaterCell) curr_grid[x][y]).getAnimal()) { //if the cell contains a fishy
					/**SET EVERYTHING FOR NEXT GRID  
					 * 
					 * - check surrounding cells and pick a random one if available. set chosen cell's will
					 * 		be taken by fish to true
					//	- check number of chronons in current fish, if is max count, then reproduce in 
					 * 		previous position. (reproduce means to turn current cell's will be taken by fish
					 * 		on).  
					 */ 
				}
				
				if {} //water cell = shark
			}		/** - check chronon count of shark - if 0, then kill shark
						- shark moves towards fish - we need to check this by iterating all neighbors.
							if no fish, then chose a random square. turn square's will be taken by shark on
						- check number of chronon's in current shark, if max shark chronon, then set current
							cell to will be taken by shark
					*/
			/**
			 * Iterate through all of the cells and set animals correspondingly. if takenbyShark and takenbyFish
			 * are both true, then put shark in cell (shark has eaten the fish).
			 * 
			 * set curr grid to next grid
			 */
			
	}

	@Override
	protected Node getObject(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

}
