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


public class editor_GUI extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 749, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 722, 385);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("Paste");
		btnNewButton.setBounds(120, 1, 100, 39);
		btnNewButton.setToolTipText("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\casper\\Desktop\\png\\003-paste.png"));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Copy");
		btnNewButton_1.setBounds(10, 1, 100, 39);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\casper\\Desktop\\png\\007-copy-two-paper-sheets-interface-symbol.png"));
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cut");
		btnNewButton_2.setBounds(230, 1, 100, 39);
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\casper\\Desktop\\png\\008-cut-with-scissors.png"));
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Undo");
		btnNewButton_3.setBounds(20, 441, 100, 39);
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\casper\\Desktop\\png\\004-undo-circular-arrow.png"));
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Redo");
		btnNewButton_4.setBounds(120, 441, 100, 39);
		btnNewButton_4.setIcon(new ImageIcon("C:\\Users\\casper\\Desktop\\png\\002-redo-arrow-symbol.png"));
		contentPane.add(btnNewButton_4);
		
		String arr[]= {"Csharp", "Java", "C++" };	
		JComboBox comboBox = new JComboBox(arr);
		comboBox.setBounds(556, 1, 176, 35);
		contentPane.add(comboBox);
		
		JButton btnNewButton_5 = new JButton("Compile&Run");
		btnNewButton_5.setBounds(586, 441, 146, 39);
		contentPane.add(btnNewButton_5);
	}
}
