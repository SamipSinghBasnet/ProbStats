import java.util.Scanner;

// This enum defines all the different trainer card effects
public enum TrainerEffect {
    // Potion heals a Pokémon for 30 HP
    POTION("Heal 30 HP") {
        @Override
        public void execute(Player player, Player opponent, Deck deck, Scanner scanner) {
            if (player.getActivePokemon() == null) {    // Check if there's an active Pokémon
                System.out.println("No active Pokémon to heal!");  // Can't heal if no active
                return;
            }
            Pokemon pokemon = player.getActivePokemon(); // Get the active Pokémon
            pokemon.setHp(pokemon.getHp() + 30);        // Heal it for 30 HP
            System.out.println(pokemon.getName() + " was healed for 30 HP!");  // Confirm healing
        }
    },

    // Switch lets you swap active Pokémon with one from bench
    SWITCH("Swap Active Pokémon with a Benched Pokémon") {
        @Override
        public void execute(Player player, Player opponent, Deck deck, Scanner scanner) {
            if (player.getBench().isEmpty()) {          // Check if there are Pokémon on bench
                System.out.println("No Pokémon on bench to switch with!");  // Can't switch if no bench
                return;
            }
            // Show bench Pokémon to choose from
            System.out.println("Choose Pokémon to switch with (0 to cancel):");
            for (int i = 0; i < player.getBench().size(); i++) {
                Pokemon p = player.getBench().get(i);
                System.out.println((i + 1) + ". " + p.getName() + " (HP: " + p.getHp() + ")");
            }
            int choice = scanner.nextInt() - 1;         // Get player's choice
            if (choice >= 0 && choice < player.getBench().size()) {
                Pokemon temp = player.getActivePokemon();  // Save current active
                player.setActivePokemon(player.getBench().remove(choice));  // Set new active
                player.getBench().add(temp);             // Put old active on bench
            }
        }
    },

    // Professor's Research lets you discard hand and draw 7 new cards
    PROFESSORS_RESEARCH("Discard hand, draw 7 new cards") {
        @Override
        public void execute(Player player, Player opponent, Deck deck, Scanner scanner) {
            player.getHand().clear();                   // Clear current hand
            player.addToHand(deck.draw(7));             // Draw 7 new cards
            System.out.println("Drew 7 new cards!");    // Confirm drawing
        }
    };

    private final String description;                   // Description of what the trainer does

    // Create a new trainer effect with description
    TrainerEffect(String description) {
        this.description = description;                 // Set the description
    }

    // Get the description of this trainer effect
    public String getDescription() {
        return description;                            // Return the description
    }

    // Abstract method that each trainer must implement
    public abstract void execute(Player player, Player opponent, Deck deck, Scanner scanner);

    // Convert trainer name to enum value
    public static TrainerEffect fromName(String name) {
        switch (name) {                                // Match name to effect
            case "Potion":
                return POTION;                         // Return Potion effect
            case "Switch":
                return SWITCH;                         // Return Switch effect
            case "Professor's Research":
                return PROFESSORS_RESEARCH;            // Return Professor's Research effect
            default:
                throw new IllegalArgumentException("Unknown trainer: " + name);  // Error if unknown
        }
    }
} 