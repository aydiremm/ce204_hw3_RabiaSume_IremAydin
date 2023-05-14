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

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

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
	public UndoManager undoManager = new UndoManager();
	
	@Test
	public void testFunctionPaste() {
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
	public void testFunctionCopy() {
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
	public void testFunctionCut() {
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
	public void testFuctionUndo() {
		String text = "Hello ";
		String clipboard = "Irem";
		
		textArea.setText(text);
		textArea.getCaret().setDot(6);
        main.view.textArea = textArea;
        
        StringSelection clipboardSelection = new StringSelection(clipboard);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(clipboard), null);
        
        controller.functionPaste();
		String textFirst = main.view.textArea.getText();
		assertEquals("Hello Irem", textFirst);
		
		controller.fuctionUndo();
		
		String textLast = main.view.textArea.getText();
		
		assertEquals("Hello Irem", textLast);
	}
	
	@Test
	public void testFunctionRedo() {
		textArea.setText("Hello world!");
		textArea.setCaretPosition(6); 
		textArea.moveCaretPosition(12);
		main.view.textArea = textArea;
		
		String selectedText = "world!";
		StringSelection selection = new StringSelection(selectedText);
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		controller.functionCut();
		
		String textFirst = main.view.textArea.getText();
		
		assertEquals("Hello ", textFirst);
		
		controller.functionRedo();
		
		String textLast = main.view.textArea.getText();
		
		assertEquals("Hello ", textLast);
			
	}
	
	
	@Test
	public void testFunctionCompile()  {
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
	public void testFunctionRun() {
		textArea.setText("public class Test {\r\npublic static void main(String[] args) {\r\nSystem.out.println(\"Hello, World!\");\r\n}\r\n}");
		main.view.textArea = textArea;
		main.view.comboBox.setSelectedItem(Language.JAVA);
		
		try {
			controller.functionRun();
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
		} catch (Exception e) {
			fail(e.getMessage());
		}	

	}
	
	
}