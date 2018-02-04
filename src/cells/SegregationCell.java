package cells;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * @author August Ning
 *
 */
public class SegregationCell extends Cell {
	private final Paint BLUE_COLOR = Color.RED;
	private final Paint RED_COLOR = Color.BLUE;
	private Paint EMPTY_COLOR = Color.WHITE;
	private Paint cellColor;
	private final String BLUE = "blue";
	private final String RED = "red";	
//	private final String EMPTY = "empty";
	
	public SegregationCell() {
		this("");
	}
	
	public SegregationCell(String type) {
		if (type.equals(BLUE)) {
			cellColor = BLUE_COLOR;
		} else if (type.equals(RED)) {
			cellColor = RED_COLOR;
		} else {
			cellColor = EMPTY_COLOR;
		}
	}
	public boolean isSame(SegregationCell cell) {
		return this.cellColor.equals(cell.getColor());
	}
	public boolean isBlue() {
		return cellColor.equals(BLUE_COLOR);
	}
	public boolean isRed() {
		return cellColor.equals(RED_COLOR);
	}
	public boolean isEmpty() {
		return cellColor.equals(EMPTY_COLOR);
	}
	public void setBlue() {
		cellColor = BLUE_COLOR;
	}
	public void setRed() {
		cellColor = RED_COLOR;
	}
	public void setEmpty() {
		cellColor = EMPTY_COLOR;
	}
	public Paint getColor() {
		return this.cellColor;
	}
}
