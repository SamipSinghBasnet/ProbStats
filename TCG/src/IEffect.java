import java.util.Scanner;

// This interface defines what a card needs to have an effect
public interface IEffect {
    // Get description of what this effect does
    String getEffect();

    // Use this effect
    void useEffect(Player player, Player opponent, Deck deck, Scanner scanner);
} 