// This is the main entry point of the game
// Purpose:
// - Starts the game
// - Shows welcome message
// - Initializes game components
// - Handles errors
public class LaunchTcg {
    public static void main(String[] args) {
        System.out.println("\n=== Starting Pokémon Trading Card Game ===\n");  // Show welcome

        UIHelper.displayWelcome();                  // Show welcome message
        UIHelper.displayRules();                    // Show game rules
        Game game = new Game();                     // Create new game
        game.gameSetup();                           // Set up the game

    }
} 