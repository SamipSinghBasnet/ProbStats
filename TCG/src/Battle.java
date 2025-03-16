import java.util.List;
import java.util.Scanner;

// Main class that handles Pokémon battles
public class Battle {
    // Main method to handle an attack between two Pokémon
    public static void executeAttack(Player attacker, Player defender, Scanner scanner) {
        Pokemon attackingPokemon = attacker.getActivePokemon();    // Get the attacking Pokémon
        Pokemon defendingPokemon = defender.getActivePokemon();    // Get the defending Pokémon

        if (defendingPokemon == null) {                           // Check if there's a Pokémon to attack
            System.out.println("No defending Pokemon to attack!");
            return;
        }

        List<Attack> availableAttacks = attackingPokemon.getAvailableAttacks();  // Get list of possible attacks
        if (availableAttacks.isEmpty()) {                         // Check if any attacks can be used
            System.out.println("No attacks available! Need more energy!");
            System.out.println("Turn ended.");
            return;
        }

        displayAvailableAttacks(availableAttacks);                // Show attack options to player
        Attack selectedAttack = chooseAttack(availableAttacks, scanner);  // Let player pick an attack

        if (selectedAttack != null) {                            // If an attack was chosen
            selectedAttack.execute(attackingPokemon, defendingPokemon);   // Do the attack
            handleAttackResult(defender, attacker, defendingPokemon);     // Handle what happens after
        }
    }

    // Shows the player what attacks they can use
    private static void displayAvailableAttacks(List<Attack> availableAttacks) {
        System.out.println("\nChoose attack (0 to cancel):");
        for (int i = 0; i < availableAttacks.size(); i++) {      // Loop through each attack
            Attack attack = availableAttacks.get(i);
            System.out.printf("%d. %s (Damage: %d, Energy Required: %d)%n",  // Show attack details
                    i + 1,
                    attack.getName(),
                    attack.getDamage(),
                    attack.getEnergyNeeded());
        }
    }

    // Lets player select which attack to use
    private static Attack chooseAttack(List<Attack> availableAttacks, Scanner scanner) {
        try {
            int choice = scanner.nextInt();                       // Get player's choice
            scanner.nextLine();                                   // Clear input buffer

            if (choice == 0) return null;                        // Player cancelled
            if (choice >= 1 && choice <= availableAttacks.size()) {  // Valid attack chosen
                return availableAttacks.get(choice - 1);
            }
        } catch (Exception e) {                                  // Handle invalid input
            scanner.nextLine();                                  // Clear input buffer
        }
        System.out.println("Invalid choice!");
        return null;
    }

    // Checks what happened after the attack
    private static void handleAttackResult(Player defender, Player attacker, Pokemon defendingPokemon) {
        if (defendingPokemon.getHp() <= 0) {                    // If defending Pokémon fainted
            handleFaintedPokemon(defender, attacker);            // Handle fainting
        } else {                                                // If Pokémon survived
            System.out.println(defendingPokemon.getName() + " has " +
                    defendingPokemon.getHp() + " HP remaining!");
        }
    }

    // Handles what happens when a Pokémon faints
    private static void handleFaintedPokemon(Player defender, Player attacker) {
        Pokemon fainted = defender.getActivePokemon();           // Get the fainted Pokémon
        System.out.println(fainted.getName() + " has fainted!");
        defender.setActivePokemon(null);                        // Remove fainted Pokémon

        collectPrizeCard(attacker);                             // Attacker gets a prize card
        replaceFaintedPokemon(defender);                        // Defender must replace Pokémon
    }

    // Gives attacker a prize card for knocking out a Pokémon
    private static void collectPrizeCard(Player attacker) {
        if (!attacker.getPrizeCards().isEmpty()) {              // If there are prize cards left
            Card prizeCard = attacker.getPrizeCards().remove(0);  // Take one prize card
            attacker.getHand().add(prizeCard);                  // Add it to hand
            System.out.println(attacker.getName() + " takes a prize card!");
        }
    }

    // Replaces fainted Pokémon with one from the bench
    private static void replaceFaintedPokemon(Player defender) {
        if (!defender.getBench().isEmpty()) {                   // If there's a Pokémon on bench
            Pokemon newActive = defender.getBench().remove(0);   // Take first benched Pokémon
            defender.setActivePokemon(newActive);               // Make it the active Pokémon
            System.out.println(defender.getName() + " sends out " + newActive.getName() + "!");
        }
    }
} 