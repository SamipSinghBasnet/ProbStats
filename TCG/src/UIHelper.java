import java.util.List;

// Handles game display and input
// Purpose:
// - Shows game state
// - Displays cards
// - Formats messages
// - Shows game rules
public class UIHelper {
    public static final String SEPARATOR = "====================";

    public static void displaySeparator() {
        System.out.println(SEPARATOR);
    }

    // Centralized card display methods
    public static void displayCard(Card card, int index) {
        if (card instanceof Pokemon) {
            displayPokemon((Pokemon) card, index);
        } else if (card instanceof Energy) {
            displayEnergy((Energy) card, index);
        } else if (card instanceof Trainer) {
            displayTrainer((Trainer) card, index);
        }
    }

    private static void displayPokemon(Pokemon pokemon, int index) {
        System.out.printf("%d. %s (HP: %d, Type: %s)%n",
                index + 1,
                pokemon.getName(),
                pokemon.getHp(),
                pokemon.getPokemonType());
    }

    private static void displayEnergy(Energy energy, int index) {
        System.out.printf("%d. %s Energy%n",
                index + 1,
                energy.getEnergyType());
    }

    private static void displayTrainer(Trainer trainer, int index) {
        System.out.printf("%d. %s - %s%n",
                index + 1,
                trainer.getName(),
                trainer.getEffect());
    }

    // Display lists of cards
    public static void displayHand(List<Card> hand) {
        System.out.println("\nHand:");
        for (int i = 0; i < hand.size(); i++) {
            displayCard(hand.get(i), i);
        }
        System.out.println();
    }

    public static void displayBench(List<Pokemon> bench) {
        System.out.println("\nBench Pokemon:");
        for (int i = 0; i < bench.size(); i++) {
            displayPokemon(bench.get(i), i);
        }
        System.out.println();
    }

    public static void displayActivePokemon(Pokemon pokemon) {
        if (pokemon != null) {
            System.out.println("\nActive Pokemon:");
            displayPokemon(pokemon, -1);
            System.out.println("Attached Energy: " + pokemon.getAttachedEnergy().size());
        }
    }

    public static void displayGameState(Player player, Player opponent) {
        displaySeparator();
        System.out.println(player.getName() + "'s Turn");
        displayActivePokemon(player.getActivePokemon());
        displayBench(player.getBench());
        System.out.println("Prize Cards Remaining: " + player.getPrizeCards().size());
        System.out.println("\nOpponent's Active Pokemon:");
        displayActivePokemon(opponent.getActivePokemon());
        displaySeparator();
    }

    public static void displayWelcome() {
        displaySeparator();
        System.out.println("Pokemon Card Game");
        displaySeparator();
        System.out.println();
    }


    public static void displayRules() {
        System.out.println("Game Rules:\n");
        System.out.println("Starting Setup:");
        System.out.println("- 7 cards in hand");
        System.out.println("- 1 Active Pokemon");
        System.out.println("- Up to 5 Bench Pokemon");
        System.out.println("- 6 Prize Cards\n");

        System.out.println("On Your Turn You Can:");
        System.out.println("- Play Pokemon to bench");
        System.out.println("- Add energy to Pokemon");
        System.out.println("- Use trainer cards");
        System.out.println("- Attack with active Pokemon\n");

        System.out.println("How to Win:");
        System.out.println("- Take all prize cards");
        System.out.println("- Knock out all opponent's Pokemon");
        System.out.println("- Win if opponent can't draw a card\n");
        displaySeparator();

        displayPokemonArt();

    }

    private static void displayPokemonArt() {
        System.out.println("        **********       ");
        System.out.println("    ****          ****   ");
        System.out.println("  **      *****      **  ");
        System.out.println(" **     **      **      ** ");
        System.out.println("** --- **   ()   ** - - - **");
        System.out.println(" **     **      **      ** ");
        System.out.println("  **      ******      **  ");
        System.out.println("    ****          ****    ");
        System.out.println("        **********        ");
        System.out.println("Pokemon gotta catch em all!");
    }

} 