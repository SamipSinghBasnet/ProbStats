import java.util.ArrayList;
import java.util.Collections;

public class Brick {
    private int numPokemon= 30 ;  // Number of Pokémon cards
    private int numEnergy;   // Number of Energy cards
    private int numRareCandies;
    private final int DECK_SIZE = 60;  // Deck always has 60 cards

    // Constructor to initialize the number of Pokémon
    public Brick() {

        this.numEnergy = DECK_SIZE - numPokemon-numRareCandies;  // Rest are Energy cards
    }

    // Method to create a deck with Pokémon and Energy cards
    public ArrayList<String> createDeck(int numRareCandies) {  // Accept numRareCandies as a parameter
        ArrayList<String> deck = new ArrayList<>();

        // Add Pokémon cards to the deck
        for (int i = 0; i < numPokemon; i++) {
            deck.add("P");
        }

        // Add Energy cards to the deck
        for (int i = 0; i < numEnergy; i++) {
            deck.add("E");
        }

        for (int i = 0; i < numRareCandies; i++) {  // Use the parameter here
            deck.add("T");
        }

        return deck;  // Return the deck
    }



    // Method to run the simulation for Rare Candies in the prize pile
    public double runRareCandySimulation(int numTrials, int numRareCandies) {
        int lossCount = 0;  // Count how many times all Rare Candies are in the prize pile

        for (int i = 0; i < numTrials; i++) {
            ArrayList<String> deck =  new ArrayList<>(createDeck(numRareCandies));  // Create deck with Rare Candies
            Collections.shuffle(deck);  // Shuffle the deck


            ArrayList<String> prizePile = new ArrayList<>(deck.subList(7, 13));  // Assume 6 prize cards

            // Check if all Rare Candies are in the prize pile
            int rareCandyCountInPrize = 0;
            for (String card : prizePile) {
                if (card.equals("T")) {
                    rareCandyCountInPrize++;
                }
            }

            // Count the loss if there's at least one Pokémon in hand and all Rare Candies are in the prize pile
            if (  rareCandyCountInPrize >0) {
                lossCount++;  // Count the loss
            }
        }

        return (lossCount / (double) numTrials) * 100;  // Convert to percentage
    }



}
