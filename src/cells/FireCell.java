package cells;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class FireCell extends Cell {
	private final Paint BURNING_COLOR = Color.RED;
	private final Paint TREE_COLOR = Color.FORESTGREEN;
	private final Paint EMPTY_COLOR = Color.PALEGOLDENROD;
	private Paint cellColor;
	private final String BURNING = "burning";
	private final String TREE = "tree";	
	/**
	 * August Ning
	 * @param type
	 */
	public FireCell() {
		this("");
	}
	public FireCell(String type) {
		if (type.equals(BURNING)) {
			this.setBurning();
		} else if (type.equals(TREE)) {
			this.setTree();
		} else {
			this.setEmpty();
		}
	}
	public boolean isBurning() {
		return this.cellColor.equals(BURNING_COLOR);
	}
	public boolean isTree() {
		return this.cellColor.equals(TREE_COLOR);

	}
	public boolean isEmpty() {
		return this.cellColor.equals(EMPTY_COLOR);
	}
	public void setBurning() {
		this.cellColor = BURNING_COLOR;
	}
	public void setTree() {
		this.cellColor = TREE_COLOR;
	}
	public void setEmpty() {
		this.cellColor = EMPTY_COLOR;
	}
	public Paint getColor() {
		return this.cellColor;
	}
}