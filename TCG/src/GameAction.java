import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

// Handles player actions during turns
// Purpose:
// - Manages turn actions
// - Handles card playing
// - Controls energy attachment
// - Manages trainer card use
// - Executes attacks
public class GameAction {
    // Handle a player's entire turn
    public static void handlePlayerTurn(Player player, Player opponent, Deck playerDeck, Scanner scanner) {
        boolean turnEnded = false;                      // Track if turn is over
        boolean hasRetreated = false;                   // Track if Pokémon retreated

        // Draw a card at start of turn
        List<Card> drawnCard = playerDeck.draw(1);     // Draw one card
        if (drawnCard.isEmpty()) {                      // If no cards left
            return;                                     // End turn (game will handle win)
        }
        player.addToHand(drawnCard);                    // Add card to hand
        System.out.println("\nDrew: " + drawnCard.get(0).getName());  // Show what was drawn

        // Keep going until turn ends
        while (!turnEnded) {
            System.out.println("\n" + player.getName() + "'s turn");  // Show whose turn it is
            System.out.println("Choose action:");       // Show available actions
            System.out.println("1. Play Basic Pokémon to Bench");
            System.out.println("2. Attach Energy");
            System.out.println("3. Retreat Active Pokémon");
            System.out.println("4. Use Trainer Card");
            System.out.println("5. Attack");
            System.out.println("6. View Hand");
            System.out.println("7. End Turn");

            int choice = scanner.nextInt();              // Get player's choice
            switch (choice) {                           // Handle their choice
                case 1:
                    playBasicPokemon(player, scanner);  // Play a Pokémon
                    break;
                case 2:
                    attachEnergy(player, scanner);      // Attach energy
                    break;
                case 3:
                    if (!hasRetreated) {                // If haven't retreated yet
                        retreatPokemon(player, scanner); // Retreat Pokémon
                        hasRetreated = true;            // Mark as retreated
                    } else {
                        System.out.println("Already retreated this turn!");  // Can't retreat again
                    }
                    break;
                case 4:
                    useTrainerCard(player, opponent, playerDeck, scanner);  // Use trainer card
                    break;
                case 5:
                    Battle.executeAttack(player, opponent, scanner);  // Attack
                    turnEnded = true;                   // End turn after attack
                    break;
                case 6:
                    displayHand(player);                // Show hand
                    break;
                case 7:
                    System.out.println("Turn ended without attacking.");  // End turn
                    turnEnded = true;
                    break;
            }
        }
    }

    // Handle playing a Pokémon to bench
    private static void playBasicPokemon(Player player, Scanner scanner) {
        // Get all Pokémon from hand
        List<Card> basicPokemon = player.getHand().stream()
                .filter(card -> card instanceof Pokemon)
                .collect(Collectors.toList());

        if (basicPokemon.isEmpty()) {                   // If no Pokémon in hand
            System.out.println("No Pokémon in hand!");
            return;
        }

        UIHelper.displayHand(basicPokemon);             // Show available Pokémon

        if (player.getBench().size() >= 5) {           // If bench is full
            System.out.println("Bench is full!");
            return;
        }

        // Let player choose which Pokémon to play
        System.out.println("Choose Basic Pokémon to play to bench:");
        for (int i = 0; i < basicPokemon.size(); i++) {
            Pokemon p = (Pokemon) basicPokemon.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (HP: " + p.getHp() + ")");
        }

        int choice = scanner.nextInt() - 1;            // Get player's choice
        if (choice >= 0 && choice < basicPokemon.size()) {
            Pokemon pokemon = (Pokemon) basicPokemon.get(choice);  // Get chosen Pokémon
            player.getBench().add(pokemon);            // Add to bench
            player.getHand().remove(pokemon);          // Remove from hand
            System.out.println("Played " + pokemon.getName() + " to bench!");  // Confirm play
        }
    }

    // Handle retreating a Pokémon
    private static void retreatPokemon(Player player, Scanner scanner) {
        if (player.getBench().isEmpty()) {             // If no Pokémon on bench
            System.out.println("No Pokémon on bench to retreat to!");
            return;
        }

        // Let player choose which Pokémon to switch to
        System.out.println("Choose Pokémon from bench to make active:");
        for (int i = 0; i < player.getBench().size(); i++) {
            Pokemon p = player.getBench().get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (HP: " + p.getHp() + ")");
        }

        int choice = scanner.nextInt() - 1;            // Get player's choice
        if (choice >= 0 && choice < player.getBench().size()) {
            Pokemon newActive = player.getBench().remove(choice);  // Get new active Pokémon
            Pokemon oldActive = player.getActivePokemon();         // Get current active Pokémon
            player.setActivePokemon(newActive);                    // Set new active
            player.getBench().add(oldActive);                      // Put old active on bench
            System.out.println("Retreated " + oldActive.getName() + " for " + newActive.getName() + "!");  // Confirm switch
        }
    }

    // Handle attaching energy
    private static void attachEnergy(Player player, Scanner scanner) {
        if (player.hasAttachedEnergy()) {              // If already attached energy this turn
            System.out.println("You can only attach one energy card per turn!");
            return;
        }

        Pokemon activePokemon = player.getActivePokemon();  // Get active Pokémon
        if (activePokemon == null) {                   // If no active Pokémon
            System.out.println("No active Pokémon to attach energy to!");
            return;
        }

        // Get all energy cards from hand
        List<Card> energyCards = player.getHand().stream()
                .filter(card -> card instanceof Energy)
                .collect(Collectors.toList());

        if (energyCards.isEmpty()) {                   // If no energy cards
            System.out.println("No energy cards in hand!");
            return;
        }

        // Show active Pokémon and energy options
        System.out.println("\nActive Pokémon: " + activePokemon.getName() +
                " (" + activePokemon.getPokemonType() + " type)");
        System.out.println("Current energy attached: " + activePokemon.getAttachedEnergy().size());
        System.out.println("\nChoose energy card to attach (0 to cancel):");

        // Show each energy card
        for (int i = 0; i < energyCards.size(); i++) {
            Energy energy = (Energy) energyCards.get(i);
            System.out.println((i + 1) + ". " + energy.getName() +
                    (energy.getEnergyType().equals(activePokemon.getPokemonType())
                            ? " (Matches Pokémon type!)" : ""));
        }

        try {
            int choice = scanner.nextInt();             // Get player's choice
            scanner.nextLine(); // Clear buffer

            if (choice == 0) return;                    // Player cancelled
            if (choice >= 1 && choice <= energyCards.size()) {
                Energy energy = (Energy) energyCards.get(choice - 1);  // Get chosen energy
                try {
                    activePokemon.attachEnergy(energy); // Try to attach energy
                    player.getHand().remove(energy);    // Remove from hand
                    player.setAttachedEnergy(true);     // Mark as attached
                    System.out.println("Successfully attached " + energy.getName() +
                            " to " + activePokemon.getName());  // Confirm attachment
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());  // Show error if types don't match
                }
            }
        } catch (Exception e) {
            scanner.nextLine(); // Clear buffer on error
            System.out.println("Invalid choice!");      // Show error for invalid choice
        }
    }

    // Handle using a trainer card
    private static void useTrainerCard(Player player, Player opponent, Deck deck, Scanner scanner) {
        // Get all trainer cards from hand
        List<Card> trainerCards = player.getHand().stream()
                .filter(card -> card instanceof Trainer)
                .collect(Collectors.toList());

        if (trainerCards.isEmpty()) {                   // If no trainer cards
            System.out.println("No trainer cards in hand!");
            return;
        }

        // Let player choose which trainer to use
        System.out.println("Choose trainer card to use (0 to cancel):");
        for (int i = 0; i < trainerCards.size(); i++) {
            Trainer trainer = (Trainer) trainerCards.get(i);
            System.out.println((i + 1) + ". " + trainer.getName() + " - " + trainer.getEffect());
        }

        try {
            int choice = scanner.nextInt();             // Get player's choice
            scanner.nextLine(); // Clear buffer

            if (choice == 0) return;                    // Player cancelled
            if (choice >= 1 && choice <= trainerCards.size()) {
                Trainer trainer = (Trainer) trainerCards.get(choice - 1);  // Get chosen trainer
                trainer.useEffect(player, opponent, deck, scanner);  // Use the trainer's effect
                player.getHand().remove(trainer);       // Remove from hand after use
            }
        } catch (Exception e) {
            scanner.nextLine(); // Clear buffer on error
            System.out.println("Invalid choice!");      // Show error for invalid choice
        }
    }

    // Show player's hand
    private static void displayHand(Player player) {
        UIHelper.displayHand(player.getHand());         // Use UI helper to show hand
    }
} 