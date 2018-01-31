package gui_elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import cellsociety_team12.MainMenu;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * This class maintains the GUI for labels by creating and returning labels for all classes of 
 * "Cell Society." Classes will use the "Labels" class by calling any of its getter methods.
 * 
 * @author Aditya Sridhar
 */
public class Labels {
	
    private static final String MM_PROPERTY_FILE = "data/mainmenu.properties";
    private static final String HEADING_PROPERTY = "heading";
    private static final String BODY_PROPERTY = "body";
    private static final String SIM_PROPERTY = "chooseSim";
    private static final String FILE_PROPERTY = "chooseFile";
    private static final String MM_HEADING_FONT_STYLE = "Arial";
    private static final String MM_BODY_FONT_STYLE = "TimesRoman";
    private static final String MM_SIM_FONT_STYLE = "Arial";
    private static final String MM_FILE_FONT_STYLE = "Arial";
    private static final int MM_HEADING_FONT_SIZE = 40;
    private static final int MM_HEADING_XLOC = new MainMenu().getScreenWidth() / 2 - 220;
    private static final int MM_HEADING_YLOC = 30;
    private static final int MM_BODY_FONT_SIZE = 20;
    private static final int MM_BODY_XLOC = 20;
    private static final int MM_BODY_YLOC = 340;
    private static final int MM_SIM_FONT_SIZE = 20;
    private static final int MM_SIM_XLOC = new MainMenu().getScreenWidth() / 2 - 220;
    private static final int MM_SIM_YLOC = 520;
    private static final int MM_FILE_FONT_SIZE = 20;
    private static final int MM_FILE_XLOC = new MainMenu().getScreenWidth() / 2 - 159;
    private static final int MM_FILE_YLOC = 560;
    private static final Paint MM_HEADING_FONT_COLOR = Color.YELLOW;
    private static final Paint MM_BODY_FONT_COLOR = Color.GREENYELLOW;
    private static final Paint MM_SIM_FONT_COLOR = Color.WHITE;
    private static final Paint MM_FILE_FONT_COLOR = Color.WHITE;
    private static String mm_heading_name;
    private static String mm_body_name;
    private static String mm_sim_name;
    private static String mm_file_name;
    private static Properties menu_properties;
	private static InputStream input;
	private static Label mm_heading, mm_body, mm_sim, mm_file;

    /**
     * Constructor sets up all label properties by calling 
     * properties files.
     */
	public Labels() {
		getMainMenuProperties();
	}
	
    /**
     * Creates and returns the main menu screen's heading.
     */
	public Label getMainMenuHeading() {
		mm_heading = new Label(mm_heading_name);
   		mm_heading.setTextFill(MM_HEADING_FONT_COLOR);
   		mm_heading.setFont(new Font(MM_HEADING_FONT_STYLE, MM_HEADING_FONT_SIZE));
   		mm_heading.setLayoutX(MM_HEADING_XLOC);
   		mm_heading.setLayoutY(MM_HEADING_YLOC);
   		
   		return mm_heading;
	}

    /**
     * Creates and returns the main menu screen's body.
     */
	public Label getMainMenuBody() {
		mm_body = new Label(mm_body_name);
   		mm_body.setTextFill(MM_BODY_FONT_COLOR);
   		mm_body.setFont(new Font(MM_BODY_FONT_STYLE, MM_BODY_FONT_SIZE));
   		mm_body.setLayoutX(MM_BODY_XLOC);
   		mm_body.setLayoutY(MM_BODY_YLOC);

   		return mm_body;
	}
	
	public Label getMainMenuSim() {
		mm_sim = new Label(mm_sim_name);
   		mm_sim.setTextFill(MM_SIM_FONT_COLOR);
   		mm_sim.setFont(new Font(MM_SIM_FONT_STYLE, MM_SIM_FONT_SIZE));
   		mm_sim.setLayoutX(MM_SIM_XLOC);
   		mm_sim.setLayoutY(MM_SIM_YLOC);

   		return mm_sim;
	}

	public Label getMainMenuFile() {
		mm_file = new Label(mm_file_name);
   		mm_file.setTextFill(MM_FILE_FONT_COLOR);
   		mm_file.setFont(new Font(MM_FILE_FONT_STYLE, MM_FILE_FONT_SIZE));
   		mm_file.setLayoutX(MM_FILE_XLOC);
   		mm_file.setLayoutY(MM_FILE_YLOC);
   		
   		return mm_file;
	}

    /**
     * Retrieves the main menu properties.
     */
	private void getMainMenuProperties() {
    	menu_properties = new Properties();
    	input = null;
     	try {
    		input = new FileInputStream(MM_PROPERTY_FILE);
    		menu_properties.load(input);
    		mm_heading_name = menu_properties.getProperty(HEADING_PROPERTY);
    		mm_body_name = menu_properties.getProperty(BODY_PROPERTY);     	
    		mm_sim_name = menu_properties.getProperty(SIM_PROPERTY);
    		mm_file_name = menu_properties.getProperty(FILE_PROPERTY);     	
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
}