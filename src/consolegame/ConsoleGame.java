package consolegame;

import consolegame.formatting.COS;
import consolegame.story.Character;
import consolegame.story.characters.ColonelSanders;
import consolegame.story.characters.Popeye;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.DefaultCaret;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ConsoleGame extends JFrame {

    public static JTextArea consoleArea = new JTextArea();
    public static JTextField inputField = new JTextField();

    boolean isWaitingForInput = false;

    Character character;

    String input;

    JPanel cards;

    private void addComponentsToPane(Container pane) {
        JPanel consoleCard = new JPanel();
        consoleCard.setBorder(new EmptyBorder(10, 10, 10, 10));
        consoleCard.setLayout(new BorderLayout());

        JPanel characterSelectCard = new JPanel();
        characterSelectCard.setBorder(new EmptyBorder(10, 10, 10, 10));
        characterSelectCard.setLayout(new BorderLayout());

        cards = new JPanel(new CardLayout());

        System.setOut(new PrintStream(new COS(consoleArea)));
        consoleArea.setEditable(false);
        consoleArea.setLineWrap(true);
        consoleArea.setWrapStyleWord(true);

        //Console Card Declaration
        JScrollPane scrollPane = new JScrollPane(consoleArea);

        DefaultCaret caret = (DefaultCaret) consoleArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        inputField.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "submit");
        inputField.getActionMap().put("submit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (waitingForInput()) {
                    print("[" + new Popeye().name() + "] " + inputField.getText());
                    inputField.setText("");

                    setIsWaitingForInput(false);
                }
            }
        });

        consoleCard.add(inputField, BorderLayout.SOUTH);
        consoleCard.add(scrollPane);

        //Character Select Card Declaration
        JPanel southPanel = new JPanel();
        JPanel northPanel = new JPanel();

        JPanel kfcPanel = new JPanel();
        JPanel popeyesPanel = new JPanel();

        JLabel label = new JLabel("Which slogan sounds cooler?");

        JButton kfc = new JButton("It's finger-lickin' good.");
        JButton popeyes = new JButton("Louisiana Fast!");

        kfc.addActionListener(e -> {
            character = new ColonelSanders();

            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, "console");
        });

        popeyes.addActionListener(e -> {
            character = new Popeye();

            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, "console");
        });

        kfcPanel.add(kfc, BorderLayout.EAST);
        popeyesPanel.add(popeyes, BorderLayout.WEST);

        southPanel.add(kfcPanel, BorderLayout.EAST);
        southPanel.add(popeyesPanel, BorderLayout.WEST);

        northPanel.add(label, BorderLayout.NORTH);

        characterSelectCard.add(southPanel, BorderLayout.SOUTH);
        characterSelectCard.add(northPanel, BorderLayout.NORTH);

        cards.add(characterSelectCard, "characterSelect");
        cards.add(consoleCard, "console");

        pane.add(cards, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        ConsoleGame frame = new ConsoleGame("Console Game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentsToPane(frame.getContentPane());

        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        inputField.requestFocusInWindow();

//        frame.print("Before we start, you need to choose your character. Which slogan sounds cooler?");
//        frame.print("1.) It's finger-lickin' good.");
//        frame.print("2.) Louisiana Fast!");
//        frame.setIsWaitingForInput(true);
//
//        while(frame.waitingForInput()) {
//
//        }
//
//        if (frame.input.equals("1") || frame.input.equals("1.)") || frame.input.equalsIgnoreCase()) {
//
//        }

//        frame.print("You are Popeye, the head of Popeye's, a chain of fast food restaurants from Louisiana specializing in fried chicken.");
//        frame.print("Colonel Sanders from KFC plans to take over your chain by buying it! You must stop him and save Popeye's! After all, ")
    }

    void print(String message) {
        System.out.println(message);
    }

    public ConsoleGame(String title) { super(title); }

    static void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    boolean waitingForInput() { return isWaitingForInput; }

    void setIsWaitingForInput(boolean isWaitingForInput) { this.isWaitingForInput = isWaitingForInput; }

}
