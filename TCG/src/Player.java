import java.util.ArrayList;  // Importing ArrayList for store dynamic list of elements
import java.util.List;       // Import List interface for make flexible list management

// Manage player state and action
// Purpose:
// - Track active Pokémon
// - Manage bench (up to 5 Pokémon)
// - Handle hand of card
// - Track prize card
// - Control per-turn action
public class Player {
    private String name;  // Store player name
    private Pokemon activePokemon;  // Store the Pokémon who currently active
    private List<Pokemon> bench;  // Store Pokémon in the bench (maximum 5 in real game)
    private List<Card> hand;  // Store the player hand of card
    private List<Card> prizeCards;  // Store prize card, usually 6 in actually game
    private TurnState turnState;  // Manage per-turn action restriction
    private Deck deck;  // Store the deck of player

    // Constructor to initial player with a name and empty list
    public Player(String name) {
        this.name = name;  // Assign provided name to player
        this.bench = new ArrayList<>();  // Initialize empty list for bench Pokémon
        this.hand = new ArrayList<>();  // Initialize empty list for hand card
        this.prizeCards = new ArrayList<>();  // Initialize empty list for prize card
        this.turnState = new TurnState();  // Create new TurnState instance for track turn
    }

    // Getter method for player name
    public String getName() {
        return name;
    }

    // Getter method for active Pokémon
    public Pokemon getActivePokemon() {
        return activePokemon;
    }

    // Setter method to set active Pokémon
    public void setActivePokemon(Pokemon pokemon) {
        this.activePokemon = pokemon;
    }

    // Getter method for list of bench Pokémon
    public List<Pokemon> getBench() {
        return bench;
    }

    // Getter method for player hand of card
    public List<Card> getHand() {
        return hand;
    }

    // Add list of card to the player hand
    public void addToHand(List<Card> cards) {
        hand.addAll(cards);  // Add all given card to hand list
    }

    // Getter method for prize card
    public List<Card> getPrizeCards() {
        return prizeCards;
    }

    // Setter method for set the prize card
    public void setPrizeCards(List<Card> prizeCards) {
        this.prizeCards = prizeCards;
    }

    // Check if player has already attach energy this turn
    public boolean hasAttachedEnergy() {
        return turnState.hasAttachedEnergy();
    }

    // Set whether player has attach energy this turn
    public void setAttachedEnergy(boolean attached) {
        turnState.setAttachedEnergy(attached);
    }

    // Check if player has use a Supporter card this turn
    public boolean hasUsedSupporter() {
        return turnState.hasUsedSupporter();
    }

    // Set whether player has use a Supporter card this turn
    public void setUsedSupporter(boolean used) {
        turnState.setUsedSupporter(used);
    }

    // Reset player turn state when new turn start
    public void resetTurn() {
        turnState.resetTurn();
    }

    // Getter method for player deck
    public Deck getDeck() {
        return deck;
    }

    // Setter method to assign deck to player
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    // Private inner class to track turn-based restriction for player
    private class TurnState {
        private boolean attachedEnergy = false;  // Track if energy card has attach this turn
        private boolean usedSupporterThisTurn = false;  // Track if Supporter card has use this turn

        // Check if energy has attach this turn
        public boolean hasAttachedEnergy() {
            return attachedEnergy;
        }

        // Set whether energy has attach this turn
        public void setAttachedEnergy(boolean attached) {
            this.attachedEnergy = attached;
        }

        // Check if Supporter card has use this turn
        public boolean hasUsedSupporter() {
            return usedSupporterThisTurn;
        }

        // Set whether Supporter card has use this turn
        public void setUsedSupporter(boolean used) {
            this.usedSupporterThisTurn = used;
        }

        // Reset the turn state when new turn start
        public void resetTurn() {
            attachedEnergy = false;  // Reset energy attach status
            usedSupporterThisTurn = false;  // Reset Supporter use status
        }
    }
}
