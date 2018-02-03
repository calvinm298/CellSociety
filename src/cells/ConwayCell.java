package cells;

import objects.conway.conwayObject;

/**
 * This is a subclass made for the Conway Game. This class will have functions determining
 * if the cell is dead or not. There are also functions setting the cell to dead or alive.
 * @author Calvin Ma
 *
 */

public class ConwayCell extends Cell {
	
	private static conwayObject conway= null;
	/**
	 * Constructor to make a Conway Cell, there is no need for a constructor parameter because
	 * we will be setting the cell as alive/dead with methods.
	 */
	public ConwayCell () {
		
	}
	/**
	 * returns whether the cell is alive
	 * @return
	 */
	public boolean isAlive() {
		return this.conway != null;
	}
	/**
	 * returns whether the cell is dead
	 * @return
	 */	
	public boolean isDead() {
		return this.conway == null;
	}
	/**
	 * sets the cell to alive
	 * 
	 */	
	public void setAlive() {
		this.conway = new conwayObject();
	}
	/**
	 * sets the cell to dead
	 * 
	 */	
	public void setDead() {
		this.conway = null;
	}
	
}
