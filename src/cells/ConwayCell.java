package cells;

/**
 * This is a subclass made for the Conway Game. This class will have functions determining
 * if the cell is dead or not. There are also functions setting the cell to dead or alive.
 * @author Calvin Ma
 *
 */

public class ConwayCell extends Cell {
	
	private static boolean isAlive = false;
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
		return this.isAlive;
	}
	/**
	 * returns whether the cell is dead
	 * @return
	 */	
	public boolean isDead() {
		return !this.isAlive;
	}
	/**
	 * sets the cell to alive
	 * 
	 */	
	public void setAlive() {
		this.isAlive = true;
	}
	/**
	 * sets the cell to dead
	 * 
	 */	
	public void setDead() {
		this.isAlive = false;
	}
	
}
