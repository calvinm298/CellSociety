package cells;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * @author August Ning
 *
 */
public class RPSCell extends Cell {
	private final Paint ROCK_COLOR = Color.RED;
	private final Paint PAPER_COLOR = Color.BLUE;
	private final Paint SCISSORS_COLOR = Color.YELLOW;
	private Paint EMPTY_COLOR = Color.WHITE;
	private Paint cellColor;
	private final String ROCK = "rock";
	private final String PAPER = "paper";
	private final String SCISSORS = "scissors";
	private final int LOSE = -1;
	private final int WIN = -2;
	private final int REPRODUCE = -3;
	private final int NOTHING = -4;
	// private final String EMPTY = "empty";

	public RPSCell() {
		// TODO Auto-generated constructor stub
		this("");
	}

	public RPSCell(String type) {
		if (type.equals(ROCK)) {
			cellColor = ROCK_COLOR;
		} else if (type.equals(PAPER)) {
			cellColor = PAPER_COLOR;
		} else if (type.equals(SCISSORS)) {
			cellColor = SCISSORS_COLOR;
		} else {
			cellColor = EMPTY_COLOR;
		}
	}

	public int checkStatus(RPSCell neighbor) {
		if (neighbor.cellColor.equals(EMPTY_COLOR)) {
			return REPRODUCE;
		} else if (this.cellColor.equals(neighbor.cellColor)) {
			return NOTHING;
		}
		if (this.cellColor.equals(ROCK_COLOR)) {
			if (neighbor.cellColor.equals(PAPER_COLOR)) {
				return LOSE;
			} else {
				return WIN;
			}
		} else if (this.cellColor.equals(PAPER_COLOR)) {
			if (neighbor.cellColor.equals(SCISSORS_COLOR)) {
				return LOSE;
			} else {
				return WIN;
			}
		} else if (this.cellColor.equals(SCISSORS_COLOR)) {
			if (neighbor.cellColor.equals(ROCK_COLOR)) {
				return LOSE;
			} else {
				return WIN;
			}
		}
		return NOTHING;
	}

	public Paint getColor() {
		return this.cellColor;
	}

}
