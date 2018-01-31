package gui_elements;

import javafx.scene.control.ComboBox;

/**
 * This class maintains the GUI for drop-down menus by creating and returning comboBoxes for all classes 
 * of "Cell Society." Classes will use the "ComboBoxes" class by calling any of its getter methods.
 * 
 * @author Aditya Sridhar
 */
public class ComboBoxes {

    /**
     * Creates and returns a drop-down menu of
     * simulations from the main menu screen.
     */
	public ComboBox getMainMenuSim() {
		return new ComboBox();
	}

    /**
     * Creates and returns a drop-down menu of
     * XML configuration files for the selected 
     * simulation from the main menu screen.
     */
	public ComboBox getMainMenuFile() {
		return new ComboBox();		
	}
}
