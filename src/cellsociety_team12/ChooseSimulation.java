package cellsociety_team12;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import cellsociety_team12.simulations.GameOfLife;
import cellsociety_team12.simulations.Segregation;
import cellsociety_team12.simulations.Simulation;
import cellsociety_team12.simulations.SpreadingOfFire;
import cellsociety_team12.simulations.WaTor;
import gui_elements.Buttons;
import gui_elements.ComboBoxes;
import gui_elements.Labels;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sun.font.CreatedFontTracker;

/**
 * This class sets up the simulation choices for "Cell Society." The user can select a new 
 * simulation at any time (whether from the main screen or from another running simulation). 
 * It is called from the "MainMenu" and "Simulation" classes.
 * 
 * @author Aditya Sridhar
 */
public class ChooseSimulation {
	
	private static final String SIMULATION_WORD = "simulation";
    private static final String SIMULATION_PROPERTIES_FILENAME = "data/simulation_names.properties";
    private static final String NUM_SIMULATIONS_PROPERTY = "numSims";
    private static final String XML_FILE_HEADING = "data\\XMLFiles\\";
	private static int number_of_simulations;
	private static boolean setIntroLabels;
    private static Properties simulation_properties;
    private static InputStream input;
    private static Stage stage;
	private static Group root;
	private static String simulation_name;
	private static String xml_file_name;
	private static ComboBox<String> main_menu_sim_cb, main_menu_file_cb;
	private static Label file_label;
	private static Button ok_button;
	
    /**
     * Constructor for the simulation setup. 
     */
	public ChooseSimulation(Stage stage, Group root, boolean setIntroLabels) {
		this.stage = stage;
		this.root = root;
		this.setIntroLabels = setIntroLabels;
		initialize();
	}
	
    /**
     * Calls methods to set labels and comboBoxes.
     */
	private void initialize() {    	
    	file_label = setLabels();	// Returns label for choosing an XML file
    	setComboBoxes(file_label);
	}
	
    /**
     * Sets the labels for the main menu, except for 
     * the label for choosing XML file (which appears 
     * only after a simulation is chosen).
     */
    private Label setLabels() {
    	Label[] main_menu_labels;
    	if(setIntroLabels) {
    		main_menu_labels = new Label[] {new Labels().getMainMenuHeading(),
                        					new Labels().getMainMenuBody(),
                        					new Labels().getMainMenuSim(),
                        					new Labels().getMainMenuFile()};
    	}
    	else {
    		main_menu_labels = new Label[] {new Labels().getMainMenuSim(),
											new Labels().getMainMenuFile()};    		
    	}
    				           
    	for(int label_element = 0; label_element < main_menu_labels.length - 1; label_element++) {
        	rootAdd(main_menu_labels[label_element]);
        }
    	
    	return main_menu_labels[main_menu_labels.length - 1];
    }

    /**
     * Sets the drop-down menus for the main menu.
     */
    private void setComboBoxes(Label file_label) {
    	main_menu_sim_cb = new ComboBoxes(root).getSim();
    	main_menu_sim_cb.setOnAction((ActionEvent ev) -> {
    		rootRemove(ok_button);
            simulation_name = main_menu_sim_cb.getSelectionModel().getSelectedItem().toString();
            rootAdd(file_label);
            main_menu_file_cb = new ComboBoxes(root).getXMLFile(simulation_name);
    		main_menu_file_cb.setOnAction((ActionEvent ev2) -> {
	        	xml_file_name = main_menu_file_cb.getSelectionModel().getSelectedItem().toString();
	        	if(ok_button == null) {
	        		ok_button = new Buttons().createOkButton();
	        	}
	        	rootAdd(ok_button);
	        	createSimulation(ok_button);
	        });
    	});
    }
    
    private void createSimulation(Button ok_button) {
    	ok_button.setOnAction(value -> {
    		stage.close();
    		Simulation sim = assignSimulation(simulation_name, xml_file_name);
    		sim.start(new Stage());
    	});
    }
    
    private Simulation assignSimulation(String simulation_name, String xml_file_name) {
    	System.out.println(xml_file_name);
    	Simulation[] simulations = listOfSimulations(xml_file_name);
    	simulation_properties = new Properties();
    	input = null;

    	try {
	  		input = new FileInputStream(SIMULATION_PROPERTIES_FILENAME);
	  		simulation_properties.load(input);

	  		number_of_simulations = Integer.parseInt(simulation_properties.getProperty(NUM_SIMULATIONS_PROPERTY));
	  		
	  		for(int simulation_number = 1; simulation_number <= number_of_simulations; simulation_number++) {
	  			String file_simulation_name = simulation_properties.getProperty(SIMULATION_WORD + simulation_number);
	  			if(simulation_name.equals(file_simulation_name)) {
	  				return simulations[simulation_number - 1];
	  			}
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
		throw new NullPointerException("The simulation requested does not exist!");
    }
    
    private Simulation[] listOfSimulations(String xml_file_name) {
    	String full_xml_file_name = XML_FILE_HEADING + xml_file_name;
    	return new Simulation[] {
//    			new GameOfLife(full_xml_file_name),
//    			new GameOfLife(full_xml_file_name),
//    			new GameOfLife(full_xml_file_name),
//    			new GameOfLife(full_xml_file_name),
//    			new SpreadingOfFire(full_xml_file_name),
//    			new SpreadingOfFire(full_xml_file_name),
//    			new SpreadingOfFire(full_xml_file_name),
//    			new SpreadingOfFire(full_xml_file_name),
    			new Segregation(full_xml_file_name),
    			new Segregation(full_xml_file_name),
    			new Segregation(full_xml_file_name),
    			new Segregation(full_xml_file_name),
    	};
    }
    
    private void rootAdd(Object obj) {
		if(!root.getChildren().contains(obj)) {
			root.getChildren().add((Node) obj);
		}
    }

    private void rootRemove(Object obj) {
		if(root.getChildren().contains(obj)) {
			root.getChildren().remove(obj);
		}
    }
}