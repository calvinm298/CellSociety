package cells;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


/**
 * This is a subclass made for the Conway Game. This class will have functions determining
 * if the cell is dead or not. There are also functions setting the cell to dead or alive.
 * @author Calvin Ma
 *
 */

public class ConwayCell extends Cell {
	
	private Paint color;
	private static Paint ALIVE_COLOR = Color.GREEN;
	private static Paint DEAD_COLOR = Color.WHITE;
	/**
	 * Constructor to make a Conway Cell, there is no need for a constructor parameter because
	 * we will be setting the cell as alive/dead with methods.
	 */
	public ConwayCell () {
		this.setDead();
	}
	/**
	 * returns whether the cell is alive
	 * @return
	 */
	public boolean isAlive() {
		return (color.equals(ALIVE_COLOR));
	}
	/**
	 * returns whether the cell is dead
	 * @return
	 */	
	public boolean isDead() {
		return this.color.equals(DEAD_COLOR);
	}
	/**
	 * sets the cell to alive
	 * 
	 */	
	public void setAlive() {
		color = ALIVE_COLOR;
	}
	/**
	 * sets the cell to dead
	 * 
	 */	
	public void setDead() {
		color = DEAD_COLOR;
	}	
}