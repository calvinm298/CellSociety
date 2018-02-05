package gui_elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Buttons {
	
	private static final int OK_X = 270;
	private static final int OK_Y = 610;
	private static final int START_X = 100;
	private static final int START_Y = 470;
	private static final int STOP_X = 260;
	private static final int STOP_Y = 470;
	private static final int STEP_X = 420;
	private static final int STEP_Y = 470;
	private static final int SPEED_PLUS_X = 515;
	private static final int SPEED_PLUS_Y = 270;
	private static final int SPEED_MINUS_X = 515;
	private static final int SPEED_MINUS_Y = 320;
	private static final int WIDTH = 80;
	private static final String STYLE = "-fx-background-color: #0000ff";
	private static final String BUTTON_CONTROLS_FILENAME = "data/control_button_texts.properties";
	private static final String OK_PROPERTY = "ok";
	private static final String START_PROPERTY = "start";
	private static final String STOP_PROPERTY = "stop";
	private static final String STEP_PROPERTY = "step";
	private static final String SPEED_PLUS_PROPERTY = "speed_plus";
	private static final String SPEED_MINUS_PROPERTY = "speed_minus";
	private static final String RESUME_PROPERTY = "resume";	
	private static final Paint COLOR = Color.YELLOW;
	private static String ok_text;
	private static String start_text;
	private static String stop_text;
	private static String step_text;
	private static String speed_plus_text;
	private static String speed_minus_text;
	private static String resume_text;
	private static Button ok, start_or_resume, stop, reset, step, speed_plus, speed_minus;
	private static Properties properties;
	private static InputStream input;
	
	public Buttons() {
		initialize();
	}
	
	private void initialize() {
		properties = new Properties();
		input = null;
    	try {
	  		input = new FileInputStream(BUTTON_CONTROLS_FILENAME);
	  		properties.load(input);
	  		ok_text = properties.getProperty(OK_PROPERTY);
	  		start_text = properties.getProperty(START_PROPERTY);
	  		stop_text = properties.getProperty(STOP_PROPERTY);
	  		step_text = properties.getProperty(STEP_PROPERTY);
	  		speed_plus_text = properties.getProperty(SPEED_PLUS_PROPERTY);
	  		speed_minus_text = properties.getProperty(SPEED_MINUS_PROPERTY);
	  		resume_text = properties.getProperty(RESUME_PROPERTY);
	   	} catch (IOException ex) {
	  		ex.printStackTrace();
	  	} finally {
	  		if (input != null) {
	  			try {
	  				input.close();
	  			} catch (IOException e) {
	  				e.printStackTrace();
	  			}
	  		}
	  	}	
	}
	
	private static Button createGeneralButton(String text) {
		Button tempButton = new Button(text);
		tempButton.setTextFill(COLOR);
		tempButton.setStyle(STYLE);
		tempButton.setPrefWidth(WIDTH);
		return tempButton;
	}
	
	public static Button createOkButton() {
   		ok = createGeneralButton(ok_text);
        ok.setLayoutX(OK_X);
        ok.setLayoutY(OK_Y);
        return ok;
	}
	
	public static Button createStartButton() {
   		start_or_resume = createGeneralButton(start_text);
        start_or_resume.setLayoutX(START_X);
        start_or_resume.setLayoutY(START_Y);
		return start_or_resume;
	}

	public static Button createStopButton() {
   		stop = createGeneralButton(stop_text);
        stop.setLayoutX(STOP_X);
        stop.setLayoutY(STOP_Y);
		return stop;
	}

	public static Button createStepButton() {
   		step = createGeneralButton(step_text);
        step.setLayoutX(STEP_X);
        step.setLayoutY(STEP_Y);
		return step;
	}
	public static Button createSpeedPlusButton() {
   		speed_plus = createGeneralButton(speed_plus_text);
        speed_plus.setLayoutX(SPEED_PLUS_X);
        speed_plus.setLayoutY(SPEED_PLUS_Y);
		return speed_plus;
	}
	
	public static Button createSpeedMinusButton() {
   		speed_minus = createGeneralButton(speed_minus_text);
        speed_minus.setLayoutX(SPEED_MINUS_X);
        speed_minus.setLayoutY(SPEED_MINUS_Y);
		return speed_minus;
	}
	
	public static void setResumeText(Button button) {
		button.setText(resume_text);
	}
}