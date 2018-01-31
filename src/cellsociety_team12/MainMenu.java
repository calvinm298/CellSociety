package cellsociety_team12;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * The main screen for "Cell Society."
 * 
 * @author Aditya Sridhar
 */
public class MainMenu extends Application {

    private static final String TITLE = "Cell Society";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 680;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final Paint BACKGROUND = Color.BLACK;
    private static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    private static Stage stage;
	
	// Additional setup for the game
    private Scene myScene;
    private Group root;

    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start(Stage stage) {
    	initialize(stage);
    	this.stage = stage;
    }
    
    private void initialize(Stage stage) {        
    	root = new Group();
        myScene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);

        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        stage.setResizable(false);
    }
    
    /**
     * Start the program.
     */
    public static void main(String[] args) {
        launch(args);
    }
}