package gui_elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import cellsociety_team12.MainMenu;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * This class maintains the GUI for drop-down menus by creating and returning comboBoxes for all classes 
 * of "Cell Society." Classes will use the "ComboBoxes" class by calling any of its getter methods.
 * 
 * @author Aditya Sridhar
 */
public class ComboBoxes {

    private static final String SIMULATION_PROPERTIES = "data/simulation_names.properties";
	private static final String SIMULATION_WORD = "simulation";
	private static final String NUM_SIMULATIONS_PROPERTY = "numSims";
	private static final String XML_FILE_WORD = "XML File";
	private static final int SIM_XLOC = 280;
	private static final int SIM_YLOC = 520;
	private static final int SIM_WIDTH = 240;
	private static final int SIM_HEIGHT = 30;
	private static final int FILE_XLOC = 280;
	private static final int FILE_YLOC = 558;
	private static final int FILE_WIDTH = 240;
	private static final int FILE_HEIGHT = 30;
	private static String simulation_string;
	private static String simulation_xml_files;
    private static Properties simulation_properties;
	private static Properties xml_file_properties;
    private static InputStream input;
	private static ComboBox<String> cb_sim, cb_file;
	private static HBox hbox_sim, hbox_file;
	private static Group root;
	private static int simulation_number = 1;
	private static int number_of_simulations;

    /**
     * Constructor sets up all comboBox properties by calling 
     * properties files.
     */
	public ComboBoxes(Group root) {
		this.root = root;
	}
	
    /**
     * Creates and returns a drop-down menu of simulations.
     */
	public ComboBox<String> getSim() {
		cb_sim = new ComboBox<String>();
		simulation_properties = new Properties();
		input = null;
		try {
	  		input = new FileInputStream(SIMULATION_PROPERTIES);
	  		simulation_properties.load(input);

	  		number_of_simulations = Integer.parseInt(simulation_properties.getProperty(NUM_SIMULATIONS_PROPERTY));
	  		
	  		for(int simulation_number = 1; simulation_number <= number_of_simulations; simulation_number++) {
	  			simulation_string = simulation_properties.getProperty(SIMULATION_WORD + simulation_number);
	  			cb_sim.getItems().add(simulation_string);
	  		}
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
		
		hbox_sim = new HBox(cb_sim);
		addComboBox(hbox_sim, SIM_XLOC, SIM_YLOC, SIM_WIDTH, SIM_HEIGHT);

		return cb_sim;
	}

    /**
     * Creates and returns a drop-down menu of XML configuration 
     * files for the selected simulation.
     */
	public ComboBox<String> getXMLFile(String simulation_name) {
		cb_file = new ComboBox<String>();
		xml_file_properties = new Properties();
		input = null;
		try {
	  		input = new FileInputStream(simulation_name + " " + XML_FILE_WORD);
	  		xml_file_properties.load(input);
	  		
//
//	  		number_of_simulations = Integer.parseInt(simulation_properties.getProperty(NUM_SIMULATIONS_PROPERTY));
//	  		
//	  		for(int simulation_number = 1; simulation_number <= number_of_simulations; simulation_number++) {
//	  			simulation_string = simulation_properties.getProperty(SIMULATION_WORD + simulation_number);
//	  			cb_sim.getItems().add(simulation_string);
//	  		}
//	   	} catch (IOException ex) {
//	  		ex.printStackTrace();
//	  	} finally {
//	  		if (input != null) {
//	  			try {
//	  				input.close();
//	  			} catch (IOException e) {
//	  				e.printStackTrace();
//	  			}
//	  		}
//	  	}
//				
		hbox_file = new HBox(cb_file);
		addComboBox(hbox_file, FILE_XLOC, FILE_YLOC, FILE_WIDTH, FILE_HEIGHT);
		
		return cb_file;
	}
	
	public void addComboBox(HBox hbox, int xloc, int yloc, int width, int height) {
      hbox.setLayoutX(xloc);
      hbox.setLayoutY(yloc);
      hbox.setPrefWidth(width);
      hbox.setPrefHeight(height);
      root.getChildren().add(hbox);
	}
}
