package nate.console;

import java.awt.Container;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class ConsoleWindow {
	public static void addComponentsToPane(Container pane) {
		pane.setLayout(null);
		
		JTextArea console = new JTextArea();
		JTextField input = new 
		
		pane.add(console);
		
		Insets insets = pane.getInsets();
		console.setBounds(10 + insets.left, 10 + insets.top, 800 - 20, 600 - 40);
		console.setEditable(false);
	}
	
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Console Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		addComponentsToPane(frame.getContentPane());
	
		Insets insets = frame.getInsets();
		frame.setSize(800 + insets.left + insets.right, 600 + insets.top + insets.bottom);
	
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
