package ce204_hw3_App;

import ce204_hw3_lib.controller.editor_controller;
import ce204_hw3_lib.view.editor_GUI;

/**
 * @class Main
 * @brief This class defines the entry point for a Java application.
 */
public class Main {
	
	/**
     * @brief A controller for the editor.
     */	
	public editor_controller controller;
	/**
     * @brief A view for the editor.
     */
	public editor_GUI view = new editor_GUI(this);
	
	/**
     * @brief Constructor for the Main class.
     */
	public Main() {
		view = new editor_GUI(this);
		controller = new editor_controller(this);
		view.setVisible(true);
	}
	 /**
     * @brief The main function, which creates an instance of the Main class.
     * @param args Arguments passed to the main function.
     */
		
	//Static field
	public static void main(String[] args) {
		new Main();
	}

}
