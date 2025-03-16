// Enum for Pokémon type
// Purpose:
// - Define available Pokémon type
// - Map type to basic Pokémon
// - Handle type conversion
// - Manage type name
public enum PokemonType {
    ELECTRIC("Electric", "Pikachu"),
    FIRE("Fire", "Charmander"),
    WATER("Water", "Squirtle"),
    GRASS("Grass", "Bulbasaur");

    private final String name;  // Store type name
    private final String basicPokemon;  // Store basic Pokémon of that type

    // Constructor for initialize type with name and basic Pokémon
    PokemonType(String name, String basicPokemon) {
        this.name = name;
        this.basicPokemon = basicPokemon;
    }

    // Getter method for return type name
    public String getName() {
        return name;
    }

    // Convert Pokémon name to corresponding type
    public static PokemonType fromString(String pokemonName) {
        for (PokemonType type : values()) {  // Loop through all type
            if (type.basicPokemon.equals(pokemonName)) {  // Check if match Pokémon
                return type;
            }
        }
        return null;

    }
}
