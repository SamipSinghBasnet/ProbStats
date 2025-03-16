// Defines Pokémon attacks
// Purpose:
// - Stores attack properties
// - Checks energy requirements
// - Executes attack effects
// - Calculates damage
public class Attack {
    private String name;        // Name of the attack (e.g., "Thunderbolt")
    private int damage;         // Amount of damage the attack deals
    private int energyNeeded;   // Number of energy cards required to use attack

    // Constructor to create a new attack
    public Attack(String name, int damage, int energyNeeded) {
        this.name = name;           // Set attack name
        this.damage = damage;       // Set damage amount
        this.energyNeeded = energyNeeded;  // Set required energy
    }

    // Check if Pokemon has enough energy to use this attack
    public boolean canUse(Pokemon pokemon) {
        int matchingEnergy = 0;     // Counter for matching energy cards
        for (Energy energy : pokemon.getAttachedEnergy()) {  // Loop through attached energy
            if (energy.getEnergyType().equals(pokemon.getPokemonType())) {  // Check if energy matches Pokemon type
                matchingEnergy++;    // Count matching energy
            }
        }
        return matchingEnergy >= energyNeeded;  // Return true if enough matching energy
    }

    // Execute the attack on a defending Pokemon
    public void execute(Pokemon attacker, Pokemon defender) {
        if (!canUse(attacker)) {    // Check if attack can be used
            System.out.println("Not enough matching energy to use this attack!");  // Error message
            return;                  // Exit if can't use attack
        }
        System.out.println(attacker.getName() + " uses " + name + "!");  // Announce attack
        defender.setHp(defender.getHp() - damage);  // Apply damage to defender
        System.out.println("Deals " + damage + " damage!");  // Show damage amount
    }

    // Getter for attack name
    public String getName() {
        return name;
    }

    // Getter for attack damage
    public int getDamage() {
        return damage;
    }

    // Getter for required energy
    public int getEnergyNeeded() {
        return energyNeeded;
    }
}
