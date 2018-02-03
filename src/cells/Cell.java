package cells;

import javafx.scene.paint.Paint;

public class Cell {
	/**
	 * Superclass for Cells class 
	 * 
	 * @author Calvin Ma
	 */
	
	private static Object gameObject = null;
	private Paint color;
	
	public Cell() {
		
	}
	
	public void setColor(Paint color) {
		this.color = color;
	}
	
	public Cell(Object newObject) {
		this.gameObject = newObject;
	}
	
	public boolean isTaken() {
		return (this.gameObject != null);
	}
	
	public boolean isNotTaken() {
		return (this.gameObject == null);

	}

	

}
