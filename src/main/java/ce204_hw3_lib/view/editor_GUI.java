package ce204_hw3_lib.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.undo.UndoManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import ce204_hw3_App.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JTextField;

import ce204_hw3_lib.controller.*;
import ce204_hw3_lib.model.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class editor_GUI extends JFrame {
	
	private Main main;

	public JPanel contentPane;
	public JTextField textField;
	public RSyntaxTextArea textArea;
	public JComboBox<Language> comboBox;


	/**
	 * Create the frame.
	 */
	
	Colorize_Syntax colSyntax =new Colorize_Syntax();
	
	public editor_GUI(Main main) {
		this.main = main;
		setResizable(false);
		setTitle("Text Code Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 722, 385);
		contentPane.add(scrollPane);
		
		textArea = new RSyntaxTextArea();
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		scrollPane.setViewportView(textArea);
		
		
		JButton btnPaste = new JButton("Paste");
		btnPaste.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPaste.setBounds(120, 1, 100, 39);
		btnPaste.setToolTipText("");
		btnPaste.setIcon(new ImageIcon(editor_GUI.class.getResource("/ce204_hw3_lib/view/003-paste.png")));
		btnPaste.addActionListener(e -> main.controller.functionPaste());
		contentPane.add(btnPaste);
		
		
		JButton btnCopy = new JButton("Copy");
		btnCopy.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCopy.setBounds(10, 1, 100, 39);
		btnCopy.addActionListener(e -> main.controller.functionCopy());
		btnCopy.setIcon(new ImageIcon(editor_GUI.class.getResource("/ce204_hw3_lib/view/007-copy-two-paper-sheets-interface-symbol.png")));
		contentPane.add(btnCopy);
		
		JButton btnCut = new JButton("Cut");
		btnCut.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCut.setBounds(230, 1, 100, 39);
		btnCut.addActionListener(e -> main.controller.functionCut());
		btnCut.setIcon(new ImageIcon(editor_GUI.class.getResource("/ce204_hw3_lib/view/008-cut-with-scissors.png")));
		contentPane.add(btnCut);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUndo.setBounds(20, 441, 100, 39);
		btnUndo.addActionListener(e -> main.controller.fuctionUndo());
		btnUndo.setIcon(new ImageIcon(editor_GUI.class.getResource("/ce204_hw3_lib/view/004-undo-circular-arrow.png")));
		contentPane.add(btnUndo);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRedo.setBounds(120, 441, 100, 39);
		btnRedo.setIcon(new ImageIcon(editor_GUI.class.getResource("/ce204_hw3_lib/view/002-redo-arrow-symbol.png")));
		btnRedo.addActionListener(e -> main.controller.functionRedo());
		contentPane.add(btnRedo);
			
		comboBox = new JComboBox(Language.values());
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox.setBounds(495, 1, 125, 35);
		contentPane.add(comboBox);
		
		JButton btnCompile = new JButton("Compile");
		btnCompile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCompile.setBounds(541, 446, 86, 29);
		btnCompile.addActionListener(e -> {
			try {
				main.controller.functionCompile();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		contentPane.add(btnCompile);
		
		JButton btnRun = new JButton("Run");
		btnRun.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRun.addActionListener(e -> {
			try {
				main.controller.functionRun();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		contentPane.add(btnCompile);
		btnRun.setBounds(637, 446, 86, 29);
		contentPane.add(btnRun);
		
		JButton btnApply = new JButton("Apply");
		btnApply.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnApply.setBounds(633, 1, 92, 35);
		contentPane.add(btnApply);
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("Java")) {
					colSyntax.javaSyntax(textArea);
				}
				
				else if (comboBox.getSelectedItem().equals("C#")) {
					colSyntax.csSyntax(textArea);
				}
				
				
				else if (comboBox.getSelectedItem().equals("C++")) {
					colSyntax.cppSyntax(textArea);
				}
			}
		});
		

	}
}