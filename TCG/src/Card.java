// This is the parent class for all cards in the game
public abstract class Card implements ICard {
    protected String name;                              // Every card has a name
    protected ICard.CardType type;                     // Every card has a type (Pokemon, Energy, or Trainer)

    // Create a new card
    public Card(String name, ICard.CardType type) {
        this.name = name;                              // Set the card's name
        this.type = type;                              // Set the card's type
    }

    // Get the card's name
    @Override
    public String getName() {
        return name;                                   // Return the card's name
    }

    // Get the card's type
    @Override
    public ICard.CardType getType() {
        return type;                                   // Return the card's type
    }
}
