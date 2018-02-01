package cellsociety_team12;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import gui_elements.ComboBoxes;
import gui_elements.Labels;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * This class displays the main menu screen for "Cell Society." Here, we choose a simulation 
 * and select an XML file to read in. Based on these configurations, a simulation will be 
 * chosen thereafter. It calls the "Simulation" class, found in the "simulations" folder, and 
 * the frame will switch to the simulation corresponding to the configurations. This class has 
 * a main method, so this is the program to run to begin any simulation.
 * 
 * @author Aditya Sridhar
 */
public class MainMenu extends Application {

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final Paint BACKGROUND = Color.BLACK;
    private static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    private static final String PROPERTY_FILENAME = "data/mainmenu.properties";
    private static final String TITLE_PROPERTY = "title";
    private static final String WIDTH_PROPERTY = "width";
    private static final String HEIGHT_PROPERTY = "height";
    private static final String IMAGE_PROPERTY = "image";
    private static final String IMAGE_WIDTH_PROPERTY = "imgWidth";
    private static final String IMAGE_HEIGHT_PROPERTY = "imgHeight";
    private static final String IMAGE_XLOC_PROPERTY = "imgXLoc";
    private static final String IMAGE_YLOC_PROPERTY = "imgYLoc";
    private static String title;
    private static String image_name;
    private static int screen_width;
    private static int screen_height;
    private static int image_width;
    private static int image_height;
    private static int image_xloc;
    private static int image_yloc;
    private static Stage stage;
   	private static Properties menu_properties;
	private static InputStream input;
	private static Image image;
	private static ImageView imageView;
	
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
    	setImage();
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
    		image_name = menu_properties.getProperty(IMAGE_PROPERTY);
    		image_width = Integer.parseInt(menu_properties.getProperty(IMAGE_WIDTH_PROPERTY));
    		image_height = Integer.parseInt(menu_properties.getProperty(IMAGE_HEIGHT_PROPERTY));
    		image_xloc = Integer.parseInt(menu_properties.getProperty(IMAGE_XLOC_PROPERTY));
    		image_yloc = Integer.parseInt(menu_properties.getProperty(IMAGE_YLOC_PROPERTY));
     	
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
    	ChooseSimulation simChoice = new ChooseSimulation(root);
    }
        
    /**
     * Sets the image for the main menu.
     */
    private void setImage() {
        image = new Image(getClass().getClassLoader().getResourceAsStream(image_name));
        imageView = new ImageView(image);
        imageView.setX(image_xloc);
        imageView.setY(image_yloc);
        imageView.setFitWidth(image_width);
        imageView.setFitHeight(image_height);
        root.getChildren().add(imageView);
    }
    
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

    /**
     * Starts the program.
     */
    public static void main(String[] args) {
        launch(args);
    }
}