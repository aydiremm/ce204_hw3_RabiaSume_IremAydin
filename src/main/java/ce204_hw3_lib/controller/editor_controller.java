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

public class editor_controller {
	
	private Main main;
	public UndoManager undoManager;
	Runtime runtime = Runtime.getRuntime();
	
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
	
	public void functionLanguageChange() {
		Language selectedLanguage = (Language) main.view.comboBox.getSelectedItem();
		RSyntaxTextArea textArea = main.view.textArea;
		Colorize_Syntax colSyntax = main.view.colSyntax;
		
		setNotification("Language set to " + selectedLanguage.displayName, 3);
		switch (selectedLanguage) {
			case JAVA -> colSyntax.javaSyntax(textArea);
			case C_SHARP -> colSyntax.csSyntax(textArea);
			case C_PLUS_PLUS -> colSyntax.cppSyntax(textArea);
			default -> colSyntax.javaSyntax(textArea);
		}
	}
	
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
		setNotification("Paste button clicked", 3);
		return;
	}
	

	public void functionCopy() {
		RSyntaxTextArea textArea = main.view.textArea;
		String selectedText = textArea.getSelectedText();
		setClipboard(selectedText);
		setNotification("Copy button clicked", 3);
	}
	
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
		setNotification("Cut button clicked", 3);
	}
	
	public void fuctionUndo() {
		try {
			undoManager.undo();
		} catch (CannotUndoException e) {}
		setNotification("Undo button clicked", 3);
	}
	
	public void functionRedo() {
		try {
			undoManager.redo();
		} catch (CannotRedoException e) {}
		setNotification("Redo button clicked", 3);
		
	}
	
	public void functionCompile() {
		try {
			
			String fileName = getClassName();
			
			Language selectedLanguage = (Language) main.view.comboBox.getSelectedItem();
			fileName += selectedLanguage.extension;
			
			System.out.println(fileName + " =? " + selectedLanguage.extension);
			if (fileName.equals(selectedLanguage.extension)) {
				setNotification("Class name cannot be null", 6);
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
	
	
	public void functionRun() {
		try {
			String fileName = getClassName();
			Language selectedLanguage = (Language) main.view.comboBox.getSelectedItem();
			
			runtime.exec("cmd.exe /c start cmd.exe /k \"" + selectedLanguage.executeCommand + fileName);
		} catch (Exception e) {}	
	}
	
	public void setNotification(String text, int seconds) {
		new Thread(() -> {
			
			main.view.notificationLabel.setText(text);
			try {
				Thread.sleep(Duration.ofSeconds(seconds).toMillis());
			} catch (InterruptedException e) {}
			main.view.notificationLabel.setText("");
		}).start();
	}
	
	
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
