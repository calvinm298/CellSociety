package gui_elements;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Buttons {
	
	private static final int OK_X = 270;
	private static final int OK_Y = 610;
	private static final int OK_WIDTH = 80;
	private static final String OK_TEXT = "OK";
	private static final String OK_STYLE = "-fx-background-color: #0000ff";
	private static final Paint OK_COLOR = Color.YELLOW;
	private static Button ok, start_or_resume, stop, reset, step;
	
	public Button createOkButton() {
   		ok = new Button(OK_TEXT);
   		ok.setTextFill(OK_COLOR);
        ok.setStyle(OK_STYLE);
        ok.setLayoutX(OK_X);
        ok.setLayoutY(OK_Y);
        ok.setPrefWidth(OK_WIDTH);
        
        return ok;
	}
	
	public Button createStartButton() {
		return start_or_resume;
	}

	public Button createStopButton() {
		return stop;
	}

	public Button createResetButton() {
		return reset;
	}

	public Button createStepButton() {
		return step;
	}
}
