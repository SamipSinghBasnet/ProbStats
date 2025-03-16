import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// This class manages a collection of cards (the deck)
public class Deck {
    private List<Card> cards;                          // List to hold all cards in deck

    // Create a new empty deck
    public Deck() {
        cards = new ArrayList<>();                     // Start with empty list of cards
    }

    // Add a card to the deck
    public void addCard(Card card) {
        cards.add(card);                               // Add new card to deck
    }

    // Mix up all the cards in the deck
    public void shuffle() {
        Collections.shuffle(cards);                    // Randomly reorder all cards
    }

    // Draw cards from the top of the deck
    public List<Card> draw(int num) {
        List<Card> drawnCards = new ArrayList<>();     // List to hold drawn cards
        for (int i = 0; i < num && !cards.isEmpty(); i++) {  // Draw up to num cards if deck isn't empty
            drawnCards.add(cards.remove(0));           // Take top card and add to drawn cards
        }
        return drawnCards;                             // Return the cards that were drawn
    }

    // Get all cards in the deck
    public List<Card> getCards() {
        return cards;                                  // Return the list of all cards
    }

    // Put cards back into the deck
    public void returnCards(List<Card> cardsToReturn) {
        cards.addAll(cardsToReturn);                   // Add all returned cards to deck
        cardsToReturn.clear();                         // Clear the returned cards list
        shuffle();                                     // Mix up the deck
    }
}
