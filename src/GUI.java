import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

public class GUI extends JFrame implements ActionListener {

    private JButton[] buttons = new JButton[9];
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private JLabel whosTurn;

    public GUI() {
        super("Tic Tac Toe");
        setSize(1080, 640);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Image icon = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
        this.setIconImage(icon);
        add(cardPanel);

        JPanel gamePanel = gamePanel();

        JPanel menuPanel = menuPanel();

        JPanel creditPanel = creditPanel();

        cardPanel.add(menuPanel, "Menu");
        cardPanel.add(gamePanel, "Game");
        cardPanel.add(creditPanel,"Credit");
        //Visar menyn när programmet startar
        cardLayout.show(cardPanel, "Menu");

        setVisible(true);
    }
    //Menuknappen
    public JButton mainMenuButton(){
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setPreferredSize(new Dimension(200,50));
        mainMenuButton.addActionListener(e -> cardLayout.show(cardPanel, "Menu"));
        mainMenuButton.setBackground(new Color(242, 204, 195));
        mainMenuButton.setFont(new Font("Serif",Font.BOLD,30));
        return mainMenuButton;
    }
    private JLabel scoreLabel;
    //Spelpanelen som skapar gridlayout till spelet och en panel för information
    private JPanel gamePanel() {

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel gameBoard = new JPanel(new GridLayout(3, 3, 10, 10));
        gameBoard.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        playButtons(gameBoard);
        mainPanel.add(gameBoard, BorderLayout.CENTER);

        scoreLabel = new JLabel(Player.getScore(), SwingConstants.LEFT);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 20));

        whosTurn = new JLabel("Your turn: " + Player.getPlayerString(), SwingConstants.RIGHT);
        whosTurn.setFont(new Font("Serif", Font.BOLD, 20));

        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.add(scoreLabel, BorderLayout.WEST);
        scorePanel.add(whosTurn, BorderLayout.EAST);
        scorePanel.setPreferredSize(new Dimension(200, 100));
        scorePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        scorePanel.setBackground(Color.WHITE);
        mainPanel.add(scorePanel, BorderLayout.NORTH);
        mainPanel.add(mainMenuButton(),BorderLayout.SOUTH);

        return mainPanel;
    }
    //MainMenu sidan som visar de olika alternativen man har i menyn.
    private JPanel menuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel mainMenuLabel = new JLabel("Tic Tac Toe", SwingConstants.CENTER);
        mainMenuLabel.setFont(new Font("Serif",Font.BOLD,100));
        mainMenuLabel.setForeground(new Color(115, 108, 237));
        menuPanel.add(mainMenuLabel,BorderLayout.NORTH);

        JButton startButton = mainMenuButton("Start Game");
        JButton creditButton = mainMenuButton("Credit");
        JButton exitButton = mainMenuButton("Exit Game");

        startButton.addActionListener(e -> cardLayout.show(cardPanel, "Game"));
        exitButton.addActionListener(e -> System.exit(0));
        creditButton.addActionListener(e -> cardLayout.show(cardPanel,"Credit"));
        //Lägger till knapparna
        menuPanel.add(startButton);
        menuPanel.add(creditButton);
        menuPanel.add(exitButton);

        menuPanel.setBackground(new Color(242, 223, 215));

        return menuPanel;
    }
    private JButton mainMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Times New Roman", Font.BOLD, 30));
        button.setBackground(new Color(212, 193, 236));
        button.setForeground(Color.BLACK);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50));
        return button;
    }
    //Credit sidan som visar vilka som har gjort programmet
    private JPanel creditPanel(){
        JPanel creditPanel = new JPanel(new BorderLayout());

        //Headern
        JLabel creditHeader = new JLabel("Credits", SwingConstants.CENTER);
        creditHeader.setFont(new Font("Serif", Font.BOLD, 100));
        creditPanel.add(creditHeader,BorderLayout.NORTH);

        // Information om vem som har gjort programmet
        JTextArea creditTextField = new JTextArea(" Adam Barnell\n Oliver Schuller\n Carl Sundberg\n Reidar Thorsen");
        creditTextField.setEditable(false);
        creditTextField.setLineWrap(true);
        creditTextField.setFont(new Font("Serif", Font.BOLD, 50));
        creditTextField.setBackground(new Color(242, 223, 215));
        creditTextField.setForeground(new Color(115, 108, 237));
        creditPanel.add(creditTextField, BorderLayout.CENTER);

        creditPanel.add(mainMenuButton(), BorderLayout.SOUTH);

        return creditPanel;
    }

    private void playButtons(JPanel gameBoard) {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(String.valueOf(i + 1));
            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font("Serif", Font.BOLD, 40));
            buttons[i].setBackground(Color.lightGray);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.black, 2));
            gameBoard.add(buttons[i]);
        }
    }
    private void resetGame() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(String.valueOf(i + 1));
            Player.resetBoard();
        }
        whosTurn.setText("Your turn: " + Player.getPlayerString());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Audio.btnSound();

        // Kollar ifall knappen är X/O, om den är printa invalid
        if (e.getActionCommand().equals("X") || e.getActionCommand().equals("O")) {
            System.out.println("Invalid");
        } else {
            int buttonToInt = Integer.parseInt(e.getActionCommand())-1;
            // Ifall getPlayerTurn() == True så sätt dit X och växla till andra spelaren
            if (Player.getPlayerTurn()) {
                Player.setBoard(buttonToInt);
                buttons[buttonToInt].setText("X");
                Player.setPlayerTurn(false);
                whosTurn.setText("Your turn: " + Player.getPlayerString());
            } else {
                // Ifall getPlayerTurn() == False så sätt dit O och växla till andra spelaren
                Player.setBoard(buttonToInt);
                buttons[buttonToInt].setText("O");
                Player.setPlayerTurn(true);
                whosTurn.setText("Your turn: " + Player.getPlayerString());
            }
        }

        String winner = Player.checkForWin();
        if (!winner.isEmpty()) {
            scoreLabel.setText(Player.getScore());
            Audio.winningSound();
            JOptionPane.showMessageDialog(null, "Game over!\n " + winner + " wins!");
            resetGame();
        } else if (Player.isBoardFull()) {
            Audio.playerDrawSound();
            JOptionPane.showMessageDialog(null, "It's a draw!");
            resetGame();
        }
    }
}
