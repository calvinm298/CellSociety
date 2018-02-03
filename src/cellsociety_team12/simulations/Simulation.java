package cellsociety_team12.simulations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import cells.Cell;
import cellsociety_team12.ChooseSimulation;
import gui_elements.ComboBoxes;
import gui_elements.Labels;
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

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final int GRID_SIZEX = 400;
    private static final int GRID_SIZEY = 400;
    private static final int GRID_XLOC = 150;
    private static final int GRID_YLOC = 50;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final Paint BACKGROUND = Color.BLACK;
    private static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    private static final String PROPERTY_FILENAME = "data/mainmenu.properties";
    private static final String TITLE_PROPERTY = "title";
    private static final String WIDTH_PROPERTY = "width";
    private static final String HEIGHT_PROPERTY = "height";
    private static String title, image_name, simulation_name, xml_file_name;
    private static int screen_width, screen_height;
    private static boolean setIntroLabels = false;
    private static Stage stage;
    private static Timeline animation;
    private static Properties menu_properties;
	private static InputStream input;
	private static GridPane visual_grid;
	protected Cell[][] curr_grid, next_grid;
	protected int sizeX, sizeY, cell_sizeX, cell_sizeY;
	 
	
	// Additional setup for the main menu
    private Scene myScene;
    private Group root;
    
    /**
     * Initializes the stage for the main menu.
     */
    @Override
    public void start(Stage stage) {
    	this.stage = stage;
    	initialize();
    }

    /**
     * Sets the scene and initializes the screen properties.
     */
    private void initialize() {
    	root = new Group();
    	setProperties();
        myScene = new Scene(root, screen_width, screen_height, BACKGROUND);
        setStage();
        chooseSimulation();
        initializeGUI();
        
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step());
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		this.animation = animation;
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
    	cell_sizeX = GRID_SIZEX / curr_grid.length;
    	cell_sizeY = GRID_SIZEY / curr_grid[0].length;
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
    	ChooseSimulation simChoice = new ChooseSimulation(stage, root, setIntroLabels);
    }
    
    protected abstract void setupGrid();
    protected abstract void updateGrid();
    
    private void updateGUI() {
    	for(int i = 0; i < curr_grid.length; i++) {
    		for(int j = 0; j < curr_grid[0].length; j++) {
    			visual_grid.getChildren().set(i*curr_grid[0].length + j, getObject(i, j));
    		}
    	}
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