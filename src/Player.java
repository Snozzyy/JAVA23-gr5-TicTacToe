import java.util.*;

public class Player {
    private static boolean playerTurn;
    private static String[] gameBoard = new String[9];
    private static int xWins = 0;
    private static int oWins = 0;

    private static final int[][] winningCombinations = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3 ,6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6},
    };

    Player() {
        randomPlayer();
        Arrays.fill(gameBoard, "");
    }

    // Randomiserar huruvida playerOne är True eller False
    private static void randomPlayer() {
        Random random = new Random();
        playerTurn = random.nextBoolean();
    }

    public static boolean getPlayerTurn() {
        return playerTurn;
    }

    public static void setPlayerTurn(boolean turn){
        playerTurn = turn;
    }

    public static String getPlayerString(){
        if (playerTurn) {
            return "Player 1 (X)";
        } else {
            return "Player 2 (O)";
        }
    }

    // Tar input från GUI och lägger in X/O i Array
    public static void setBoard(int position) {
        // Säkerställer så man inte kan välja samma knapp flera gånger (funkar inte)
        if (playerTurn) {
            gameBoard[position] = "X";
        } else {
            gameBoard[position] = "O";
        }
    }

    public static boolean isBoardFull() {
        for (String cell : gameBoard) {
            if (cell.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    public static String checkForWin() {
        for (int[] combination : winningCombinations) {

            String cell1 = gameBoard[combination[0]];
            String cell2 = gameBoard[combination[1]];
            String cell3 = gameBoard[combination[2]];

            if (cell1 != null && cell1.equals(cell2) && cell1.equals(cell3) && !cell1.isEmpty()) {
                if (cell1.equals("X")){
                    xWins++;
                } else{
                    oWins++;
                }
                return cell1;
            }
        }
        return "";
    }

    public static String getScore(){
        return "X wins: " + xWins + " || O wins: " + oWins;
    }

    public static void resetBoard() {
        Arrays.fill(gameBoard, "");
        randomPlayer();
    }

}
