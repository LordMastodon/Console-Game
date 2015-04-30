package nate.console;

import java.awt.Container;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import nate.console.items.ConsoleJTextArea;

@SuppressWarnings("serial")
public class Main extends JFrame {
	
	public Main(String title) throws IOException {
		super(title);
		
		onCreation();
	}

//	public Main(String title) {
//		// TODO Auto-generated constructor stub
//	}

//	public Main() {
//		EventQueue.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
//				JFrame frame = new JFrame("Console Game");
//				
//				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//				frame.setResizable(false);
//				
//				try {
//					frame.add(new ConsolePane());
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				
//				frame.pack();
//				frame.setSize(800, 600);
//				frame.setVisible(true);
//			}
//		 
//		});
//	}
	
	ConsoleJTextArea console;
	
	void addComponents(Container pane) {
		pane.setLayout(null);
		
		JTextArea console = new JTextArea();
		
		pane.add(console);
		
		Insets insets = pane.getInsets();
		console.setBounds(10 + insets.left, 10 + insets.top, this.getWidth() - 20, this.getHeight() - 20);
		
		console.append("Hello!");
	}
	
	void onCreation() throws IOException {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);

		addComponents(this.getContentPane());
		
		this.pack();
		
		Insets insets = this.getInsets();
		this.setSize(800 + insets.left + insets.right, 600 + insets.top + insets.bottom);
		this.setVisible(true);
	}
	
//	public static void main(String[] args) throws IOException {
//		new Main("Console Game");
//	}

}
