package ce204_hw3_test;

import static org.junit.Assert.*;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.awt.datatransfer.StringSelection;
import javax.swing.undo.UndoManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.junit.Test;

import ce204_hw3_App.Main;
import ce204_hw3_lib.controller.editor_controller;
import ce204_hw3_lib.model.Language;
import ce204_hw3_lib.view.editor_GUI;


public class editor_controller_test {
	
	
	private Main main = new Main();
	private RSyntaxTextArea textArea = new RSyntaxTextArea();
	public editor_controller controller= new editor_controller(main);
	
	@Test
	public void functionPaste() {
		textArea.setText("Hello world! ");
		textArea.getCaret().setDot(13);
		
        main.view.textArea = textArea;
		
		String clipboard = "Irem";
		
		StringSelection clipboardSelection = new StringSelection(clipboard);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(clipboard), null);
        
        
		controller.functionPaste();
		String text = main.view.textArea.getText();
		
		assertEquals("Hello world! Irem", text);
	}
	
	@Test
	public void functionCopy() {
		textArea.setText("Hello world! ");
		String selectedText = "world!";
		StringSelection selection = new StringSelection(selectedText);
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        controller.functionCopy();
        
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	Transferable copied = clipboard.getContents(null);
    	
    	String copiedStr = null;
    	
    	try {
    		copiedStr = (String)copied.getTransferData(DataFlavor.stringFlavor);
		} catch (Exception e) 
    	{
			fail(e.getMessage());
		}
        
       assertFalse(!copiedStr.equalsIgnoreCase(selectedText));
	}
	
	
	@Test
	public void functionCut() {
		textArea.setText("Hello world!");
		textArea.setCaretPosition(6); 
		textArea.moveCaretPosition(12);
		main.view.textArea = textArea;
		
		String selectedText = "world!";
		StringSelection selection = new StringSelection(selectedText);
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		controller.functionCut();
		
		String text = main.view.textArea.getText();
		
		assertEquals("Hello ", text);
	}

	
	@Test
	public void functionCompile()  {
		textArea.setText("public class Test {\r\npublic static void main(String[] args) {\r\nSystem.out.println(\"Hello, World!\");\r\n}\r\n}");
		main.view.textArea = textArea;
		main.view.comboBox.setSelectedItem(Language.JAVA);
		
		try {
			controller.functionCompile();
		} catch (Exception e) {
			fail(e.getMessage());
		}	
		
		String fileName = "Test.java";
		assertTrue( new File(fileName).exists());
			
	}
	
	@Test
	public void functionRun() {
		textArea.setText("public class Test {\r\npublic static void main(String[] args) {\r\nSystem.out.println(\"Hello, World!\");\r\n}\r\n}");
		main.view.textArea = textArea;
		main.view.comboBox.setSelectedItem(Language.JAVA);
		
		try {
			controller.functionRun();
		} catch (Exception e) {
			fail(e.getMessage());
		}	
        
		
		
		
		
		try {
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
		} catch (Exception e) {
			fail(e.getMessage());
		}	
	}
	
	
	
}
