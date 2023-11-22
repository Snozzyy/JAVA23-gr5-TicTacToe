import java.util.*;

public class Player {
    private static boolean playerTurn;
    private static String[] gameBoard = new String[9];

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
                return cell1;
            }
        }
        return "";
    }


    public static String[] getGameBoard(){
        return gameBoard;
    }
}
