import java.util.Random;
import java.util.Scanner;

// Manages turn order and flow
// Purpose:
// - Determines first player
// - Switches between players
// - Tracks current player
// - Handles coin flips
public class TurnHandler {
    private Player currentPlayer;  // Player whose turn it currently is
    private Player waitingPlayer;  // Player waiting for their turn
    private final Random random;

    public TurnHandler(Player player1, Player player2, Scanner scanner) {
        this.random = new Random();

        System.out.println("\n=== Coin Flip to Decide First Player ===");
        System.out.println(player1.getName() + " will call the flip!");
        System.out.println("Enter 'H' for Heads or 'T' for Tails: ");

        // Get player1's call
        String call = scanner.nextLine().toUpperCase();
        boolean calledHeads = call.startsWith("H");

        // Perform coin flip
        boolean isHeads = random.nextBoolean();

        System.out.println("\nFlipping coin...");
        System.out.println("The coin landed on: " + (isHeads ? "HEADS!" : "TAILS!"));

        // Determine first player based on call
        if ((isHeads && calledHeads) || (!isHeads && !calledHeads)) {
            currentPlayer = player1;  // Player1 won the flip
            waitingPlayer = player2;
        } else {
            currentPlayer = player2;  // Player1 lost the flip
            waitingPlayer = player1;
        }

        System.out.println("\n" + currentPlayer.getName() + " goes first!");
    }

    // Switch turns between players
    public void switchTurn() {
        Player temp = currentPlayer;
        currentPlayer = waitingPlayer;
        waitingPlayer = temp;

        System.out.println("\n" + currentPlayer.getName() + "'s turn");
    }

    // Get the player whose turn it is
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    // Get the player waiting for their turn
    public Player getWaitingPlayer() {
        return waitingPlayer;
    }
} 