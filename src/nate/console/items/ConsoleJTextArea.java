package nate.console.items;

import java.io.IOException;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ConsoleJTextArea extends JTextArea {
	
	public ConsoleJTextArea(int x, int y, int width, int height) throws IOException {
		this.setBounds(x, y, width, height);
	}

}
