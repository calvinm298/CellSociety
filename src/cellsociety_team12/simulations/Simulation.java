package cellsociety_team12.simulations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import cells.Cell;
import cellsociety_team12.ChooseSimulation;
import gui_elements.Buttons;
import gui_elements.ComboBoxes;
import gui_elements.Labels;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Aditya Sridhar
 */
public abstract class Simulation extends Application {

    private static final int FRAMES_PER_SECOND = 2;
    private static final int GRID_SIZEX = 400;
    private static final int GRID_SIZEY = 400;
    private static final int GRID_XLOC = 100;
    private static final int GRID_YLOC = 50;
    private static final int INITIAL_TIME_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final Paint BACKGROUND = Color.BLACK;
    private static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    private static final String PROPERTY_FILENAME = "data/mainmenu.properties";
    private static final String TITLE_PROPERTY = "title";
    private static final String WIDTH_PROPERTY = "width";
    private static final String HEIGHT_PROPERTY = "height";
    private static String title, image_name, simulation_name, xml_file_name;
    private static int screen_width, screen_height;
    private static int time_delay = INITIAL_TIME_DELAY;
    private static boolean setIntroLabels = false;
	private static boolean setNewToOldChoice = true;
	private static boolean initialButtonsCreated = false;
    private static Stage stage;
    private static Timeline animation;
    private static Properties menu_properties;
	private static InputStream input;
	private static GridPane visual_grid;
	protected ChooseSimulation simChoice, newSimChoice;
	protected Cell[][] curr_grid, next_grid;
	protected int sizeX, sizeY, cell_sizeX, cell_sizeY;
	private static Button start_button, stop_button, reset_button, step_button, speed_plus_button, speed_minus_button;

	
	// Additional setup for the main menu
    private Scene myScene;
    private Group root;
    
    /**
     * Initializes the stage for the main menu.
     */
    @Override
    public void start(Stage stage) {
    	this.stage = stage;
    	initialButtonsCreated = false;
    	initialize();
    }

    /**
     * Sets the scene and initializes the screen properties.
     */
    private void initialize() {
    	time_delay = INITIAL_TIME_DELAY;
    	root = new Group();
    	setProperties();
        myScene = new Scene(root, screen_width, screen_height, BACKGROUND);
        setStage();
        chooseSimulation();
        setupGrid();
        initializeGUI();
        
        KeyFrame frame = new KeyFrame(Duration.millis(INITIAL_TIME_DELAY),
                                      e -> step());
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        this.animation = animation;
        ChooseSimulation.setAnimation(animation);
    }

    private void step() {
    	updateGrid();
    	updateGUI();
    }
    
    private void initializeGUI() {
    	visual_grid = new GridPane();
    	visual_grid.setPrefSize(GRID_SIZEX, GRID_SIZEY);
    	visual_grid.setLayoutX(GRID_XLOC);
    	visual_grid.setLayoutY(GRID_YLOC);
    	if(!initialButtonsCreated) {
    		createButtons();
    		initialButtonsCreated = true;
    	}
    	cell_sizeX = GRID_SIZEX / curr_grid.length;
    	cell_sizeY = GRID_SIZEY / curr_grid[0].length;
    	initializeVisualGrid();
    }
    
    private void initializeVisualGrid() {
       	for(int i = 0; i < curr_grid.length; i++) {
    		for(int j = 0; j < curr_grid[0].length; j++) {
    			visual_grid.add(getObject(i, j), i, j);
    		}
    	}
    	root.getChildren().add(visual_grid);
    }
        
    /**
     * Reads in properties from a property file and sets the  
     * screen properties.
     */
    private void setProperties() {
    	menu_properties = new Properties();
    	input = null;
     	try {
    		input = new FileInputStream(PROPERTY_FILENAME);
    		menu_properties.load(input);
    		title = menu_properties.getProperty(TITLE_PROPERTY);
    		screen_width = Integer.parseInt(menu_properties.getProperty(WIDTH_PROPERTY));
    		screen_height = Integer.parseInt(menu_properties.getProperty(HEIGHT_PROPERTY));     	
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

    /**
     * Sets the stage for the main menu.
     */
    private void setStage() {
    	stage.setScene(myScene);
    	stage.setTitle(title);
    	stage.show();
    	stage.setResizable(false);
    }

    /**
     * Calls methods to set contents of the main menu, 
     * including labels, drop-down menus, and the image.
     */
    private void chooseSimulation() {
    	if(simChoice == null) {
    		newSimChoice = new ChooseSimulation(stage, root, setIntroLabels, animation, 
    				simChoice == null ? ChooseSimulation.getOldSimChoice() : simChoice, setNewToOldChoice);
    	}
		simChoice = newSimChoice;
    }
    
    protected abstract void setupGrid();
    protected abstract void updateGrid();
    
    private void updateGUI() {
    	root.getChildren().remove(visual_grid);
    	initializeGUI();
    }
    
    private void createButtons() {
    	function_start_button();
    	function_stop_button();
    	function_step_button();
    	function_speed_plus_button();
    	function_speed_minus_button();
    }
    
    private void function_start_button() {
    	start_button = Buttons.createStartButton();
    	root.getChildren().add(start_button);
    	start_button.setOnAction(value -> {
    		animation.play();
    		root.getChildren().remove(start_button);
    		root.getChildren().add(stop_button);
    	});    	
    }

    private void function_stop_button() {
    	stop_button = Buttons.createStopButton();
    	stop_button.setOnAction(value -> {
    		animation.pause();
    		root.getChildren().remove(stop_button);
    		root.getChildren().add(start_button);
    		Buttons.setResumeText(start_button);
    	});
    }

    private void function_step_button() {
    	step_button = Buttons.createStepButton();
    	root.getChildren().add(step_button);
    	step_button.setOnAction(value -> {
    		animation.pause();
    		if(!root.getChildren().contains(start_button))
    			root.getChildren().add(start_button);
       		Buttons.setResumeText(start_button);
    		if(root.getChildren().contains(stop_button))
    			root.getChildren().remove(stop_button);
    		step();
    	});
    }

    private void function_speed_plus_button() {
    	speed_plus_button = Buttons.createSpeedPlusButton();
    	root.getChildren().add(speed_plus_button);
    	speed_plus_button.setOnAction(value -> {
    		if(time_delay != 0) {
	    		animation.stop();
	    		time_delay -= 100 / FRAMES_PER_SECOND;
	            KeyFrame frame = new KeyFrame(Duration.millis(time_delay),
	                    e -> step());
	            Timeline animation = new Timeline();
				animation.setCycleCount(Timeline.INDEFINITE);
				animation.getKeyFrames().add(frame);
				this.animation = animation;
				ChooseSimulation.setAnimation(animation);
				animation.play();
    		}
    	});
    }

    private void function_speed_minus_button() {
    	speed_minus_button = Buttons.createSpeedMinusButton();
    	root.getChildren().add(speed_minus_button);
    	speed_minus_button.setOnAction(value -> {
    		animation.stop();
    		time_delay += 200 / FRAMES_PER_SECOND;
            KeyFrame frame = new KeyFrame(Duration.millis(time_delay),
                    e -> step());
            Timeline animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			this.animation = animation;
			ChooseSimulation.setAnimation(animation);
			animation.play();
    	});
    }

    protected abstract Node getObject(int row, int col);
    
    /**
     * Returns the width of the main menu screen.
     */
    public int getScreenWidth() {
    	return screen_width;
    }

    /**
     * Returns the height of the main menu screen.
     */
    public int getScreenHeight() {
    	return screen_height;
    }
}