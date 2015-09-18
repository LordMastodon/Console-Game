package consolegame;

import engine.console.ConsoleOutputStream;
import consolegame.story.Character;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.PrintStream;

public class ConsoleGame extends JFrame {

    public static JTextArea consoleArea = new JTextArea();
    public static JTextField inputField = new JTextField();

    public static Character whoYouAre;

    public static int input = 0;

    private void addComponentsToPane() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(panel);
        setLayout(new BorderLayout());

        consoleArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(consoleArea);

        DefaultCaret caret = (DefaultCaret) consoleArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        inputField.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "submit");
        inputField.getActionMap().put("submit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (input != 0) {
                    print("[" + whoYouAre.name() + "] " + inputField.getText());
                    input++;
                    inputField.setText("");
                } else {
                    whoYouAre = new Character(inputField.getText());
                    input++;
                    print("You've selected the name \"" + inputField.getText() + "\".");
                    inputField.setText("");
                }
            }
        });

        getContentPane().add(inputField, BorderLayout.SOUTH);
        getContentPane().add(scrollPane);
    }

    public static void main(String[] args) {
        System.setOut(new PrintStream(new ConsoleOutputStream(consoleArea)));
        ConsoleGame frame = new ConsoleGame("Console Game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentsToPane();

        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        inputField.requestFocusInWindow();

        print("Please select a name!");
    }

    static void print(String message) {
        System.out.println(message);
    }

    public ConsoleGame(String title) {
        super(title);
    }

    public static void setCharacter(Character character) {
        whoYouAre = character;
    }

}
