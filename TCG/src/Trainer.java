import java.util.Scanner;

// Represents a Trainer card
// Purpose:
// - Provides special effects (heal, switch, draw)
// - One-time use cards
// - Discarded after use
public class Trainer extends Card implements IEffect {
    private final TrainerEffect effect;

    public Trainer(String name) {
        super(name, CardType.TRAINER);
        this.effect = TrainerEffect.fromName(name);
    }

    @Override
    public void play(Player player, Player opponent, Scanner scanner) {
        useEffect(player, opponent, player.getDeck(), scanner);
        player.getHand().remove(this);
    }

    @Override
    public String getEffect() {
        return effect.getDescription();
    }

    @Override
    public void useEffect(Player player, Player opponent, Deck deck, Scanner scanner) {
        effect.execute(player, opponent, deck, scanner);
    }
}

