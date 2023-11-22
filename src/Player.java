import java.util.*;

public class Player {
    private static boolean playerTurn;
    private static String[] gameBoard = new String[9];

    Player() {
        randomPlayer();
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

    public static String[] getGameBoard(){
        return gameBoard;
    }
}
