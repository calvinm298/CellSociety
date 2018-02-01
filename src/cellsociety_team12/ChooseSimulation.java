package cellsociety_team12;

import java.util.Arrays;

import gui_elements.ComboBoxes;
import gui_elements.Labels;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * This class sets up the simulation choices for "Cell Society." The user can select a new 
 * simulation at any time (whether from the main screen or from another running simulation). 
 * It is called from the "MainMenu" class.
 * 
 * @author Aditya Sridhar
 */
public class ChooseSimulation {
	
	private static Group root;
	private static String simulation_name;
	private static String xml_file;
	private static ComboBox<String> main_menu_sim_cb, main_menu_file_cb;

    /**
     * Constructor for the simulation setup. 
     */
	public ChooseSimulation(Group root) {
		this.root = root;
		initialize();
	}
	
    /**
     * Calls methods to set labels and comboBoxes.
     */
	private void initialize() {    	
    	Label file_label = setLabels();	// Returns label for choosing an XML file
    	setComboBoxes(file_label);
	}
	
    /**
     * Sets the labels for the main menu, except for 
     * the label for choosing XML file (which appears 
     * only after a simulation is chosen).
     */
    private Label setLabels() {
    	Label[] main_menu_labels = {new Labels().getMainMenuHeading(),
    	                            new Labels().getMainMenuBody(),
    	                            new Labels().getMainMenuSim(),
    	                            new Labels().getMainMenuFile()};
    	for(int label_element = 0; label_element < 3; label_element++) {
        	root.getChildren().add(main_menu_labels[label_element]);
        }
    	
    	return main_menu_labels[3];
    }

    /**
     * Sets the drop-down menus for the main menu.
     */
    private void setComboBoxes(Label file_label) {
    	main_menu_sim_cb = new ComboBoxes(root).getSim();
    	main_menu_sim_cb.setOnAction((ActionEvent ev) -> {
            simulation_name = main_menu_sim_cb.getSelectionModel().getSelectedItem().toString();
            if(!root.getChildren().contains(file_label)) {
            	root.getChildren().add(file_label);
            }
            main_menu_file_cb = new ComboBoxes(root).getXMLFile(simulation_name);
        });
    	if(root.getChildren().contains(file_label)) {
	        main_menu_file_cb.setOnAction((ActionEvent ev) -> {
	        	xml_file = main_menu_file_cb.getSelectionModel().getSelectedItem().toString();
	        });
    	}
    }
}