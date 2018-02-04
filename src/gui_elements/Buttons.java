package gui_elements;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Buttons {
	
	private static final int OK_X = 270;
	private static final int OK_Y = 610;
	private static final int START_X = 270;
	private static final int START_Y = 610;
	private static final int STOP_X = 270;
	private static final int STOP_Y = 610;
	private static final int RESET_X = 270;
	private static final int RESET_Y = 610;
	private static final int STEP_X = 270;
	private static final int STEP_Y = 610;
	private static final int WIDTH = 80;
	private static final String OK_TEXT = "OK";
	private static final String START_TEXT = "Start";
	private static final String STOP_TEXT = "Stop";
	private static final String RESET_TEXT = "Reset";
	private static final String STEP_TEXT = "Step";
	private static final String STYLE = "-fx-background-color: #0000ff";
	private static final Paint COLOR = Color.YELLOW;
	private static Button ok, start_or_resume, stop, reset, step;

	
	private Button createGeneralButton(String text) {
		Button tempButton = new Button(text);
		tempButton.setTextFill(COLOR);
		tempButton.setStyle(STYLE);
		tempButton.setPrefWidth(WIDTH);
		return tempButton;
	}
	
	public Button createOkButton() {
   		ok = createGeneralButton(OK_TEXT);
        ok.setLayoutX(OK_X);
        ok.setLayoutY(OK_Y);
        return ok;
	}
	
	public Button createStartButton() {
   		start_or_resume = createGeneralButton(START_TEXT);
        start_or_resume.setLayoutX(START_X);
        start_or_resume.setLayoutY(START_Y);
		return start_or_resume;
	}

	public Button createStopButton() {
   		stop = createGeneralButton(STOP_TEXT);
        stop.setLayoutX(STOP_X);
        stop.setLayoutY(STOP_Y);
		return stop;
	}

	public Button createResetButton() {
   		reset = createGeneralButton(RESET_TEXT);
        reset.setLayoutX(RESET_X);
        reset.setLayoutY(RESET_Y);
		return reset;
	}

	public Button createStepButton() {
   		step = createGeneralButton(STEP_TEXT);
        step.setLayoutX(STEP_X);
        step.setLayoutY(STEP_Y);
		return step;
	}
}