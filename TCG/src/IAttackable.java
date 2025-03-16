import java.util.List;

// Interface for anything that can attack
// Purpose:
// - Defines attack-related methods
// - Manages energy attachments
// - Handles HP and damage
// - Controls type checking
public interface IAttackable {
    // Check if this card can attack right now
    boolean canAttack();

    // Get list of attacks this card can use
    List<Attack> getAvailableAttacks();

    // Attach an energy card to this card
    void attachEnergy(Energy energy);

    // Get and set the HP of this card
    int getHp();
    void setHp(int hp);

    // Get what type this card is
    String getPokemonType();

    // Get list of energy cards attached to this card
    List<Energy> getAttachedEnergy();
} 