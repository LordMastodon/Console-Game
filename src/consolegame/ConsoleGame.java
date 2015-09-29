package consolegame;

import consolegame.formatting.COS;
import consolegame.story.Character;
import consolegame.story.characters.Assistant;
import consolegame.story.characters.ColonelSanders;
import consolegame.story.characters.Popeye;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.text.DefaultCaret;

public class ConsoleGame extends JFrame {

    public static JTextArea consoleArea = new JTextArea();
    public static JTextField inputField = new JTextField();

    boolean isWaitingForInput = false;

    Character character;

    String input;

    JPanel cards;

    static boolean running = false;

    static int popeyesValue = 0;
    static int kfcValue = 0;

    public static JLabel kfcValueLabel = new JLabel("KFC's Value: " + kfcValue);
    public static JLabel popeyesValueLabel = new JLabel("Popeye's Value: " + popeyesValue);

    private void addComponentsToPane(Container pane) throws InterruptedException {
        JPanel consoleCard = new JPanel();
        consoleCard.setBorder(new EmptyBorder(10, 10, 10, 10));
        consoleCard.setLayout(new BorderLayout());
        consoleCard.setName("console");

        JPanel characterSelectCard = new JPanel();
        characterSelectCard.setBorder(new EmptyBorder(10, 10, 10, 10));
        characterSelectCard.setLayout(new BorderLayout());
        characterSelectCard.setName("characterSelect");

        JPanel marketingCard = new JPanel();
        marketingCard.setBorder(new EmptyBorder(10, 10, 10, 10));
        marketingCard.setLayout(new BorderLayout());
        marketingCard.setName("marketing");

        cards = new JPanel(new CardLayout());

        System.setOut(new PrintStream(new COS(consoleArea)));
        consoleArea.setEditable(false);
        consoleArea.setLineWrap(true);
        consoleArea.setWrapStyleWord(true);

        //Marketing Card Declaration
//        JLabel

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

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());

        JButton marketing = new JButton("Marketing Page");

        marketing.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, "marketing");
        });

        infoPanel.add(kfcValueLabel, BorderLayout.EAST);
        infoPanel.add(popeyesValueLabel, BorderLayout.WEST);
        infoPanel.add(marketing, BorderLayout.CENTER);

        consoleCard.add(inputField, BorderLayout.SOUTH);
        consoleCard.add(scrollPane);
        consoleCard.add(infoPanel, BorderLayout.NORTH);

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

            kfcStoryPart1();
        });

        popeyes.addActionListener(e -> {
            character = new Popeye();

            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, "console");

            popeyesStoryPart1();
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

    public static void main(String[] args) throws InterruptedException {
        running = true;

        ConsoleGame frame = new ConsoleGame("Console Game");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addComponentsToPane(frame.getContentPane());

        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        inputField.requestFocusInWindow();

        while(running) {
            kfcValueLabel.setText("KFC's value: " + kfcValue);
            popeyesValueLabel.setText("Popeye's value: " + popeyesValue);

            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        running = false;
    }

    public void kfcStoryPart1() {

    }

    public void popeyesStoryPart1() {
        print("You are Popeye, the head of Popeye's, a chain of fast food restaurants from Louisiana specializing in fried chicken.");
        print("Colonel Sanders from KFC plans to take over your chain by buying it. You must stop him and save Popeye's! After all, it's Louisiana Fast!");
        print("Popeye's is going under. You must use clever marketing strategies to get people to stop going to KFC and go back to Popeye's.");
        print("Remember that this is turn-based. Every time you do something, KFC is also going to do something.");
        print("This means that after a certain number of turns, KFC will have amassed the funds to buy out Popeye's and you will lose.");
        print("Also remember that every time you draw a customer who spends money you drive up the value of Popeye's, meaning it will take longer for KFC to buy you out.");
        print("Winning means your value is higher than KFC's and you buy THEM out.\n");

        Runnable wait = () -> {
            wait(TimeUnit.MILLISECONDS, 1000);
            popeyesStoryPart2();
        };

        SwingUtilities.invokeLater(wait);
    }

    public void popeyesStoryPart2() {
        Assistant assist = new Assistant();

        assist.formattedPrint("Hello Popeye!");
    }

    void print(String message) {
        System.out.println(message);
    }

    public ConsoleGame(String title) { super(title); }

    boolean waitingForInput() { return isWaitingForInput; }

    void setIsWaitingForInput(boolean isWaitingForInput) { this.isWaitingForInput = isWaitingForInput; }

    void wait(TimeUnit unit, int time) {
        try {
            unit.sleep(time);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
