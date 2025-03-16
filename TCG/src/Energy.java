import java.util.Scanner;

// This class represents an Energy card that powers up Pokémon
public class Energy extends Card {
    private String type;  // What type of energy this is (Electric, Fire, Water, Grass)

    // Create a new Energy card
    public Energy(String type) {
        super(type + " Energy", ICard.CardType.ENERGY);  // Tell parent class this is an Energy card
        this.type = type;                                // Set what type of energy it is
    }

    // Get what type this energy card is
    public String getEnergyType() {
        return type;                                     // Return the energy type
    }

    // When playing this card from hand
    @Override
    public void play(Player player, Player opponent, Scanner scanner) {
        if (player.getActivePokemon() == null) {         // Check if player has an active Pokémon
            System.out.println("No active Pokemon to attach energy to!");  // Can't attach if no active Pokémon
            return;
        }
        player.getActivePokemon().attachEnergy(this);    // Attach this energy to active Pokémon
        player.getHand().remove(this);                   // Remove energy from player's hand
        player.setAttachedEnergy(true);                  // Mark that player has attached energy this turn
    }
}