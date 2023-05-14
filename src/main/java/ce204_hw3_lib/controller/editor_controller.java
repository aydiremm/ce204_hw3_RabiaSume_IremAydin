package ce204_hw3_lib.controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import ce204_hw3_App.Main;
import ce204_hw3_lib.model.Colorize_Syntax;
import ce204_hw3_lib.model.Language;

import java.io.*;
import java.time.Duration;

/**
 * This class serves as the controller for the text editor component of the application. it handles user
 * interactions with the editor and delegates the appropriate action to the corresponding model and view.
 */
public class editor_controller {
	
	private Main main;
	public UndoManager undoManager;
	Runtime runtime = Runtime.getRuntime();

	/**
	 * initializes a new instance of the editor_controller class.
	 *
	 * @param main The main application instance
	 */
	public editor_controller(Main main) {
		this.main = main;
		this.undoManager = new UndoManager();

		main.view.textArea.getDocument().addUndoableEditListener(e -> {
			undoManager.addEdit(e.getEdit());
		});
		main.view.textArea.setText("public class HelloWorld {\r\n"
				+ "    public static void main(String[] args) {\r\n"
				+ "        System.out.println(\"Hello, World!\");\r\n"
				+ "    }\r\n"
				+ "}");
	}
	
	/**
	 * Handles the user's selection of a new language from the language drop-down menu.
	 */
	public void functionLanguageChange() {
		Language selectedLanguage = (Language) main.view.comboBox.getSelectedItem();
		RSyntaxTextArea textArea = main.view.textArea;
		Colorize_Syntax colSyntax = main.view.colSyntax;
		
		setNotification("Language Set To " + selectedLanguage.displayName, 2);
		switch (selectedLanguage) {
			case JAVA:
				colSyntax.javaSyntax(textArea);
				break;
			case C_SHARP:
				colSyntax.csSyntax(textArea);
				break;
			case C_PLUS_PLUS:
				colSyntax.cppSyntax(textArea);
				break;
			default:
				colSyntax.javaSyntax(textArea);
		}
	}
	
	
	/**
	 * @brief Function that is called when the user clicks the "Paste" button.
	 * Pastes the c contents into the editor.
	 */
	public void functionPaste() {
		RSyntaxTextArea textArea = main.view.textArea;
		String input = textArea.getText();
		String selectedText = textArea.getSelectedText();
		String clipboard = getClipboard();
		if (clipboard == null) return;

		int dot = textArea.getCaret().getDot();
		int mark = textArea.getCaret().getMark();
		
		if (dot == mark) {
			input = input.substring(0, dot) + clipboard + input.substring(dot, input.length());
		}
		else if (dot != mark) {
			int begin = dot < mark ? dot : mark;
			int end = dot > mark ? dot : mark;
			input = input.substring(0, begin) + clipboard + input.substring(end, input.length());
		}
		else if (input.length() == mark) {
				input += clipboard;
		}
		
		textArea.setText(input);
		setNotification("Paste Button Clicked", 2);
		return;
	}
	

	/**
	 * @brief Function for copying selected text to the c
	 */
	public void functionCopy() {
		RSyntaxTextArea textArea = main.view.textArea;
		String selectedText = textArea.getSelectedText();
		setClipboard(selectedText);
		setNotification("Copy Button Clicked", 2);
	}
	
	/**
     * @brief Cuts the selected text from the RSyntaxTextArea object and puts it in the c.
     */
	public void functionCut() {
		RSyntaxTextArea textArea = main.view.textArea;
		String selectedText = textArea.getSelectedText();
		setClipboard(selectedText);

		int dot = textArea.getCaret().getDot();
		int mark = textArea.getCaret().getMark();
		String input = textArea.getText();
		int begin = dot < mark ? dot : mark;
		int end = dot > mark ? dot : mark;
		input = input.substring(0, begin) + input.substring(end, input.length());
		textArea.setText(input);
		setNotification("Cut Button Clicked", 2);
	}
	
	/**
     * @brief Undoes the last edit operation.
     */
	public void fuctionUndo() {
		try {
			undoManager.undo();
		} catch (CannotUndoException e) {}
		setNotification("Undo Button Clicked", 2);
	}
	
	/**
     * @brief Red the last edit operation that was undone.
     */
	public void functionRedo() {
		try {
			undoManager.redo();
		} catch (CannotRedoException e) {}
		setNotification("Redo Button Clicked", 2);
		
	}
	
	/**
     * @brief Compiles the code written in the RSyntaxTextArea object and runs it.
     */
	public void functionCompile() {
		try {
			
			String fileName = getClassName();
			
			Language selectedLanguage = (Language) main.view.comboBox.getSelectedItem();
			fileName += selectedLanguage.extension;
			
			System.out.println(fileName + " =? " + selectedLanguage.extension);
			if (fileName.equals(selectedLanguage.extension)) {
				setNotification("Class Name Cannot Be Null", 5);
				return;
			}
			
			File file = new File(fileName);
			if (!file.exists()) file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(main.view.textArea.getText());
			writer.close();
			Process process = runtime.exec("cmd.exe /c start cmd.exe /k \"" + selectedLanguage.compileCommand + fileName + " && echo done. && pause && exit()");
		} catch (Exception e) {
		}
	}
	
	/**
     * @brief Runs the code in the currently active file using the selected programming language.
     */
	public void functionRun() {
		try {
			String fileName = getClassName();
			Language selectedLanguage = (Language) main.view.comboBox.getSelectedItem();
			
			Runtime runtime = Runtime.getRuntime();
			runtime.exec("cmd.exe /c start cmd.exe /k \"" + selectedLanguage.executeCommand + fileName);
		} catch (Exception e) {}	
	}
	
	/**
     * @brief Sets a notification message to display on the program's notification label for a set amount of time.
     * @param text The text of the notification message.
     * @param seconds The number of seconds to display the message for.
     */
	public void setNotification(String text, int seconds) {
		new Thread(() -> {
			
			main.view.notificationLabel.setText(text);
			try {
				Thread.sleep(Duration.ofSeconds(seconds).toMillis());
			} catch (InterruptedException e) {}
			main.view.notificationLabel.setText("");
		}).start();
	}
	
	/**
     * @brief Gets the text currently stored in the system c.
     * @return The text currently stored in the system c, or null if no text is available.
     */
	private String getClipboard() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	Transferable copied = clipboard.getContents(null);
    	if ( copied.isDataFlavorSupported(DataFlavor.stringFlavor))
        {
			try {
				return (String)copied.getTransferData(DataFlavor.stringFlavor);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    	
        return null;
	}
	
	/**
     * @brief Sets the text in the system c to the given selected text.
     * @param selectedText The text to set as the new system c contents.
     */
	private void setClipboard(String selectedText) {
		if (selectedText != null && !selectedText.isEmpty()) {
            StringSelection selection = new StringSelection(selectedText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
        }
	}
	
	private String getClassName() {
		String fileName = "";
		String fileText = main.view.textArea.getText();
		int beginOfClass = fileText.indexOf("public class ");
		int beginOfClassName = beginOfClass + 13;
		for (int i = beginOfClassName; i < fileText.length(); i++) {
			char c = fileText.charAt(i);
			if (c == ' ') break;
			fileName += c;
		}
		return fileName;
	}
	
	
	
	
}
