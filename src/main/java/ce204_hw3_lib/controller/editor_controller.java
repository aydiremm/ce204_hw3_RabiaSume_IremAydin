package ce204_hw3_lib.controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import ce204_hw3_App.Main;
import ce204_hw3_lib.model.Language;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class editor_controller {
	
	private Main main;
	public UndoManager undoManager;
	
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
		return;
	}
	

	public void functionCopy() {
		RSyntaxTextArea textArea = main.view.textArea;
		String selectedText = textArea.getSelectedText();
		setClipboard(selectedText);
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
	}
	
	public void fuctionUndo() {
		try {
			undoManager.undo();
		} catch (CannotUndoException e) {
			return;
		}
		
	}
	
	public void functionRedo() {
		try {
			undoManager.redo();
		} catch (CannotRedoException e) {
			return;
		}
		
	}
	
	public void functionCompile() throws Exception {
		String fileName = getClassName();
		
		Language selectedLanguage = (Language) main.view.comboBox.getSelectedItem();
		fileName += selectedLanguage.extension;
		
		File file = new File(fileName);
		if (!file.exists()) file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(main.view.textArea.getText());
		writer.close();
		
		ProcessBuilder processBuilder = new ProcessBuilder();

    	processBuilder.command("cmd.exe", "/c", selectedLanguage.compileCommand + fileName);
    	
    	Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);
	}
	
	
	public void functionRun() throws IOException {
		Runtime rt = Runtime.getRuntime();
		String fileName = "hw3";
		Language selectedLanguage = (Language) main.view.comboBox.getSelectedItem();
		rt.exec("cmd.exe /c start cmd.exe /k \"" + selectedLanguage.executeCommand + fileName);
		
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
