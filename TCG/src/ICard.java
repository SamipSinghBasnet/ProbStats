import java.util.Scanner;

// This is the base interface that all cards must implement
public interface ICard {
    // Get the name of the card
    String getName();

    // Get what type of card this is (Pokemon, Energy, or Trainer)
    CardType getType();

    // Play the card from hand
    void play(Player player, Player opponent, Scanner scanner);

    // Define the different types of cards
    enum CardType {
        POKEMON,    // A Pokémon card
        ENERGY,     // An energy card
        TRAINER     // A trainer card
    }
} 