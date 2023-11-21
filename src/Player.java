import java.util.*;

public class Player {
    private static boolean playerOne;
    private static String[] gameBoard = new String[9];

    Player() {
        randomPlayer();
    }

    // Randomiserar huruvida playerOne är True eller False
    private void randomPlayer() {
        Random random = new Random();
        playerOne = random.nextBoolean();
    }

    // Tar input från GUI och lägger in X/O i Array samt skiftar mellan spelare.
    public static void setBoard(int position) {
        // Säkerställer så man inte kan välja samma knapp flera gånger
        if (gameBoard[position] == null) {
            if (playerOne) {
                gameBoard[position] = "X";
                playerOne = false;
            } else {
                gameBoard[position] = "O";
                playerOne = true;
            }
        } else {
            System.out.println("Invalid choice");
        }
    }

    public static String[] getGameBoard(){
        return gameBoard;
    }
}
