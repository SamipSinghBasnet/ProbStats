import java.util.ArrayList;
import java.util.Collections;

public class MulliganSimulator {
    private int numPokemon;  // Number of Pokémon cards
    private int numEnergy;   // Number of Energy cards
    private final int DECK_SIZE = 60;  // Deck always has 60 cards

    // Constructor to initialize the number of Pokémon
    public MulliganSimulator(int numPokemon) {
        this.numPokemon = numPokemon;
        this.numEnergy = DECK_SIZE - numPokemon;  // Rest are Energy cards
    }

    // Method to create a deck with Pokémon and Energy cards
    public ArrayList<String> createDeck() {
        ArrayList<String> deck = new ArrayList<>();

        // Add Pokémon cards to the deck
        for (int i = 0; i < numPokemon; i++) {
            deck.add("P");
        }

        // Add Energy cards to the deck
        for (int i = 0; i < numEnergy; i++) {
            deck.add("E");
        }

        return deck;  // Return the deck
    }



    // Runs the simulation multiple times and calculates mulligan probability
    public double runSimulation(int numTrials) {
        int mulliganCount = 0;

        for (int i = 0; i < numTrials; i++) {
            ArrayList<String> deck = createDeck();  // Get a new deck
            Collections.shuffle(deck);  // Shuffle the deck

            // Draw the first 7 cards
            ArrayList<String> hand = new ArrayList<>(deck.subList(0, 7));

            if (!hand.contains("P")) {
                mulliganCount++;  // Count how many times a mulligan happens
            }
        }

        return (mulliganCount / (double) numTrials) * 100;  // Convert to percentage
    }
}
