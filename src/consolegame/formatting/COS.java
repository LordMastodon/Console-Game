package consolegame.formatting;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class COS extends OutputStream {

    private JTextArea textArea;

    public COS(JTextArea area) { textArea = area; }

    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char)b));
    }
}
