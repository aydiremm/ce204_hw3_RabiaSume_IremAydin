package ce204_hw3_lib.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JTextField;


public class editor_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editor_GUI frame = new editor_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public editor_GUI() {
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
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnPaste = new JButton("Paste");
		btnPaste.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPaste.setBounds(120, 1, 100, 39);
		btnPaste.setToolTipText("");
		btnPaste.setIcon(new ImageIcon(editor_GUI.class.getResource("/ce204_hw3_lib/view/003-paste.png")));
		contentPane.add(btnPaste);
		
		JButton btnCopy = new JButton("Copy");
		btnCopy.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCopy.setBounds(10, 1, 100, 39);
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCopy.setIcon(new ImageIcon(editor_GUI.class.getResource("/ce204_hw3_lib/view/007-copy-two-paper-sheets-interface-symbol.png")));
		contentPane.add(btnCopy);
		
		JButton btnCut = new JButton("Cut");
		btnCut.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCut.setBounds(230, 1, 100, 39);
		btnCut.setIcon(new ImageIcon(editor_GUI.class.getResource("/ce204_hw3_lib/view/008-cut-with-scissors.png")));
		contentPane.add(btnCut);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUndo.setBounds(20, 441, 100, 39);
		btnUndo.setIcon(new ImageIcon(editor_GUI.class.getResource("/ce204_hw3_lib/view/004-undo-circular-arrow.png")));
		contentPane.add(btnUndo);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRedo.setBounds(120, 441, 100, 39);
		btnRedo.setIcon(new ImageIcon(editor_GUI.class.getResource("/ce204_hw3_lib/view/002-redo-arrow-symbol.png")));
		contentPane.add(btnRedo);
		
		String arr[]= {"Csharp", "Java", "C++" };	
		JComboBox comboBox = new JComboBox(arr);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox.setBounds(495, 1, 125, 35);
		contentPane.add(comboBox);
		
		JButton btnCompile = new JButton("Compile");
		btnCompile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCompile.setBounds(541, 446, 86, 29);
		contentPane.add(btnCompile);
		
		JButton btnRun = new JButton("Run");
		btnRun.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRun.setBounds(637, 446, 86, 29);
		contentPane.add(btnRun);
		
		JButton btnApply = new JButton("Apply");
		btnApply.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnApply.setBounds(633, 1, 92, 35);
		contentPane.add(btnApply);
		
		textField = new JTextField();
		textField.setBounds(354, 12, 117, 24);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
