package nate.console;

import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ConsolePane extends JPanel {
	
	JTextArea console;
	
	public ConsolePane() throws IOException {
		setLayout(new GroupLayout(this));
		
		console = new JTextArea();
		add(console);
			
//		console.setSize(800 - 20, 600 - 20);
//		console.setLocation(10, 10);
		
		console.setBounds(800 -20, 600 - 20, 10, 10);
		
		console.setEditable(false);
		
		console.append("Hello!");
		
	}

}
