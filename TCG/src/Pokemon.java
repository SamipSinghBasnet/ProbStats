import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This class represents a Pokémon card in the game
// Purpose:
// - Holds Pokémon stats (HP, type)
// - Manages attached energy cards
// - Handles attacks
// - Can be placed as active or bench Pokémon
public class Pokemon extends Card implements IAttackable {
    private int hp;                                    // How much health the Pokémon has
    private final List<Attack> attacks = new ArrayList<>();  // List of attacks this Pokémon can use
    private final List<Energy> attachedEnergy = new ArrayList<>();  // Energy cards attached to this Pokémon
    private final String type;                        // What type this Pokémon is (Electric, Fire, etc.)

    // Create a new Pokémon with a name, HP, and base damage
    public Pokemon(String name, int hp, int damage) {
        super(name, CardType.POKEMON);                // Tell the parent Card class this is a Pokémon
        this.hp = hp;                                // Set the Pokémon's health
        this.type = PokemonType.fromString(name).getName();  // Figure out what type this Pokémon is
        setupAttacks(damage);                        // Give this Pokémon its attacks
    }

    // When playing this card from hand
    @Override
    public void play(Player player, Player opponent, Scanner scanner) {
        if (player.getBench().size() >= 5) {         // Check if bench is full
            System.out.println("Bench is full!");     // Can't play if bench is full
            return;
        }
        player.getBench().add(this);                 // Add this Pokémon to the bench
        player.getHand().remove(this);               // Remove it from player's hand
        System.out.println("Played " + getName() + " to bench!");  // Tell player what happened
    }

    // Check if this Pokémon can attack
    @Override
    public boolean canAttack() {
        return !getAvailableAttacks().isEmpty();     // Can attack if it has any available attacks
    }

    // Give this Pokémon its attacks
    private void setupAttacks(int damage) {
        attacks.add(new Attack("Basic Attack", damage, 1));     // Give it a basic attack
        attacks.add(new Attack("Power Attack", damage * 2, 2)); // Give it a stronger attack
    }

    // Get list of attacks this Pokémon can use right now
    public List<Attack> getAvailableAttacks() {
        List<Attack> available = new ArrayList<>();   // Make a list for available attacks
        for (Attack attack : attacks) {               // Look at each attack
            if (attack.canUse(this)) {                // If Pokémon can use this attack
                available.add(attack);                 // Add it to available attacks
            }
        }
        return available;                             // Return list of attacks it can use
    }

    // Get and set the Pokémon's health
    public int getHp() { return hp; }                 // Get current health
    public void setHp(int hp) { this.hp = hp; }       // Set new health

    // Attach an energy card to this Pokémon
    public void attachEnergy(Energy energy) {
        if (energy.getEnergyType().equals(this.type)) {  // Check if energy matches Pokémon type
            attachedEnergy.add(energy);                    // Add the energy if it matches
        } else {
            throw new IllegalArgumentException("Cannot attach " + energy.getEnergyType() + " energy to a " + this.type + " Pokemon!");  // Error if types don't match
        }
    }

    // Get list of energy cards attached to this Pokémon
    public List<Energy> getAttachedEnergy() {
        return attachedEnergy;                          // Return all attached energy cards
    }

    // Get what type this Pokémon is
    public String getPokemonType() {
        return type;                                    // Return the Pokémon's type
    }
}

