package ce204_hw3_App;

import ce204_hw3_lib.controller.editor_controller;
import ce204_hw3_lib.view.editor_GUI;

public class Main {
	
	
	public editor_controller controller;
	public editor_GUI view = new editor_GUI(this);
	
	public Main() {
		view = new editor_GUI(this);
		controller = new editor_controller(this);
		view.setVisible(true);
	}
	
	
	//Static field
	public static void main(String[] args) {
		new Main();
	}

}
