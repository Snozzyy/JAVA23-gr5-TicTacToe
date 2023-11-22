import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

        private JButton[] buttons = new JButton[9];
        private CardLayout cardLayout = new CardLayout();
        private JPanel cardPanel = new JPanel(cardLayout);

        public GUI() {
            super("Tic Tac Toe");
            setSize(1080, 640);
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);


            add(cardPanel);

            JPanel gamePanel = createGamePanel();

            JPanel menuPanel = createMenuPanel();

            JPanel creditPanel = createCreditPanel();

            cardPanel.add(menuPanel, "Menu");
            cardPanel.add(gamePanel, "Game");
            cardPanel.add(creditPanel,"Credit");

            cardLayout.show(cardPanel, "Menu");

            setVisible(true);
        }
        //Spelet
        public JButton mainMenuButton(){
            JButton mainMenuButton = new JButton("Main Menu");
            mainMenuButton.setPreferredSize(new Dimension(200,50));
            mainMenuButton.addActionListener(e -> cardLayout.show(cardPanel, "Menu"));
            return mainMenuButton;
        }
        private JPanel createGamePanel() {

            JPanel mainPanel = new JPanel(new BorderLayout());

            JPanel gameBoard = new JPanel(new GridLayout(3, 3, 10, 10));
            gameBoard.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            playButtons(gameBoard);
            mainPanel.add(gameBoard, BorderLayout.CENTER);

            JLabel scoreName = new JLabel("Scoreboard:", SwingConstants.LEFT);
            scoreName.setFont(new Font("Serif", Font.BOLD, 20));

            JLabel whosTurn = new JLabel("Your turn: ", SwingConstants.RIGHT);
            whosTurn.setFont(new Font("Serif", Font.BOLD, 20));

            JPanel scorePanel = new JPanel(new BorderLayout());
            scorePanel.add(scoreName, BorderLayout.WEST);
            scorePanel.add(whosTurn, BorderLayout.EAST);
            scorePanel.setPreferredSize(new Dimension(200, 100));
            scorePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            scorePanel.setBackground(Color.WHITE);
            mainPanel.add(scorePanel, BorderLayout.NORTH);
            mainPanel.add(mainMenuButton(),BorderLayout.SOUTH);

            return mainPanel;
        }
        //Main Menu
        private JPanel createMenuPanel() {
            JPanel menuPanel = new JPanel();
            menuPanel.setLayout(new GridLayout(4, 2, 10, 10));

            JLabel mainMenuLabel = new JLabel("Tic Tac Toe", SwingConstants.CENTER);
            mainMenuLabel.setFont(new Font("Serif",Font.BOLD,100));
            mainMenuLabel.setForeground(new Color(115, 108, 237));
            menuPanel.add(mainMenuLabel,BorderLayout.NORTH);

            JButton startButton = createStyledButton("Start Game");
            JButton creditButton = createStyledButton("Credit");
            JButton exitButton = createStyledButton("Exit Game");

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
        private JButton createStyledButton(String text) {
            JButton button = new JButton(text);
            button.setFont(new Font("Times New Roman", Font.BOLD, 30));
            button.setBackground(new Color(212, 193, 236));
            button.setForeground(Color.BLACK);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(200, 50));
            return button;
        }
        private JPanel createCreditPanel(){
            JPanel creditPanel = new JPanel(new BorderLayout());

            //Headern
            JLabel creditHeader = new JLabel("Credits", SwingConstants.CENTER);
            creditHeader.setFont(new Font("Serif", Font.BOLD, 100));
            creditPanel.add(creditHeader,BorderLayout.NORTH);

            // Information om vem som har gjort programmet
            JTextField creditLabel = new JTextField("Credits: Adam Barnell\n, Oliver Schuller\n, Carl Sundberg\n, Reidar Thorsen\n");
            creditLabel.setEnabled(false);
            creditLabel.setFont(new Font("Serif", Font.BOLD, 30));
            creditPanel.add(creditLabel, BorderLayout.CENTER);

            creditPanel.add(mainMenuButton(), BorderLayout.SOUTH);

            return creditPanel;
        }

        private void playButtons(JPanel gameBoard) {
            for (int i = 0; i < 9; i++) {
                buttons[i] = new JButton(String.valueOf(i+1));
                buttons[i].addActionListener(this);
                buttons[i].setFont(new Font("Serif", Font.BOLD,40));
                buttons[i].setBackground(Color.lightGray);
                buttons[i].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                gameBoard.add(buttons[i]);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Skickar vald knapp till Players.setBoard()
            Player.setBoard(Integer.parseInt(e.getActionCommand())-1);

            // Endast test atm, bör göras som en funktion sen
            // Om getRandomPlayer == True är det X tur, annars 0 tur
            if (Player.getPlayerTurn()) {
                buttons[Integer.parseInt(e.getActionCommand())-1].setText("X");
                Player.setPlayerTurn(false);
            } else {
                buttons[Integer.parseInt(e.getActionCommand())-1].setText("O");
                Player.setPlayerTurn(true);
            }
        }
    }
