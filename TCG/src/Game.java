import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main game controller
// Purpose:
// - Sets up game (decks, players)
// - Manages game loop
// - Handles turn order
// - Checks win conditions
public class Game {
    private Player player1;
    private Player player2;
    private Deck player1Deck;
    private Deck player2Deck;
    private Scanner scanner;

    public Game() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        player1Deck = new Deck();
        player2Deck = new Deck();
        scanner = new Scanner(System.in);
    }

    private void setupPlayerDeck(Deck deck) {
        // Adding Basic Pokémon - 24 cards (6 copies of each)
        Object[][] basicPokemons = {
                {"Pikachu", 60, 15},
                {"Charmander", 50, 18},
                {"Squirtle", 50, 10},
                {"Bulbasaur", 50, 14}
        };

        for (Object[] pokemon : basicPokemons) {
            for (int i = 0; i < 6; i++) {  // 6 copies each (24 total)
                deck.addCard(new Pokemon(
                        (String)pokemon[0],
                        (Integer)pokemon[1],
                        (Integer)pokemon[2]
                ));
            }
        }

        // Adding Energy cards - 24 cards (6 copies of each type)
        String[] energyTypes = {"Electric", "Fire", "Water", "Grass"};
        for (String energyType : energyTypes) {
            for (int i = 0; i < 6; i++) {  // 6 copies each (24 total)
                deck.addCard(new Energy(energyType));
            }
        }

        // Adding Trainer cards - 12 cards (4 copies of each)
        String[][] trainers = {
                {"Potion", "Heal 30 HP"},
                {"Switch", "Swap Active Pokémon with a Benched Pokémon"},
                {"Professor's Research", "Discard hand, draw 7 new cards"}
        };

        for (String[] trainer : trainers) {
            for (int i = 0; i < 4; i++) {  // 4 copies each (12 total)
                deck.addCard(new Trainer(trainer[0]));
            }
        }

        deck.shuffle();

        // Verify deck size is still 60
        if (deck.getCards().size() != 60) {
            throw new IllegalStateException("Deck size is " + deck.getCards().size() + " but should be 60");
        }
    }

    public void setupDecks() {
        // Setup and shuffle both players' decks
        setupPlayerDeck(player1Deck);
        setupPlayerDeck(player2Deck);

        // Add these two lines to set the decks for each player
        player1.setDeck(player1Deck);
        player2.setDeck(player2Deck);

        System.out.println("Both players' 60-card decks are ready!");
    }

    public void drawStartingHand() {
        // Draw initial hands and prize cards for both players
        player1.addToHand(player1Deck.draw(7));  // Draw 7 cards for hand
        player1.setPrizeCards(player1Deck.draw(6));  // Draw 6 prize cards

        player2.addToHand(player2Deck.draw(7));  // Draw 7 cards for hand
        player2.setPrizeCards(player2Deck.draw(6));  // Draw 6 prize cards

        // Check and handle mulligans for both players
        handleMulligans(player1, player1Deck);
        handleMulligans(player2, player2Deck);

        // Display final hands
        System.out.println("\nPlayer 1's Starting Hand:");
        for (Card card : player1.getHand()) {
            System.out.println(card.getName());
        }
        System.out.println("\nPlayer 2's Starting Hand:");
        for (Card card : player2.getHand()) {
            System.out.println(card.getName());
        }

        System.out.println("\nPrize Cards Set Up (6 each)");
    }

    private void handleMulligans(Player player, Deck playerDeck) {
        while (!hasBasicPokemon(player)) {
            System.out.println("\n" + player.getName() + " has no Basic Pokémon! Mulligan!");
            // Return cards to deck
            playerDeck.returnCards(player.getHand());
            playerDeck.shuffle();
            // Draw new hand
            player.addToHand(playerDeck.draw(7));
            // Also redraw prize cards
            playerDeck.returnCards(player.getPrizeCards());
            player.setPrizeCards(playerDeck.draw(6));
        }
    }

    private boolean hasBasicPokemon(Player player) {
        for (Card card : player.getHand()) {
            if (card instanceof Pokemon) {
                return true;
            }
        }
        return false;
    }

    public void placeActiveAndBench(Player player) {
        List<Pokemon> availablePokemon = new ArrayList<>();

        // First collect all Pokémon from hand
        for (Card card : player.getHand()) {
            if (card instanceof Pokemon) {
                availablePokemon.add((Pokemon) card);
            }
        }

        if (availablePokemon.isEmpty()) {
            System.out.println(player.getName() + " has no Pokémon to place!");
            return;
        }

        // Let player choose active Pokémon
        System.out.println("\n" + player.getName() + ", choose your Active Pokémon:");
        for (int i = 0; i < availablePokemon.size(); i++) {
            Pokemon p = availablePokemon.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (HP: " + p.getHp() + ")");
        }

        int choice = scanner.nextInt() - 1;
        if (choice >= 0 && choice < availablePokemon.size()) {
            Pokemon chosenPokemon = availablePokemon.get(choice);
            player.setActivePokemon(chosenPokemon);
            player.getHand().remove(chosenPokemon);
            availablePokemon.remove(choice);
            System.out.println(player.getName() + "'s Active Pokémon: " + chosenPokemon.getName());
        }

        // Ask if player wants to place Pokémon on bench
        while (!availablePokemon.isEmpty() && player.getBench().size() < 5) {
            System.out.println("\nDo you want to place a Pokémon on your bench? (1: Yes, 2: No)");
            int benchChoice = scanner.nextInt();

            if (benchChoice == 2) break;

            System.out.println("Choose Pokémon to place on bench:");
            for (int i = 0; i < availablePokemon.size(); i++) {
                Pokemon p = availablePokemon.get(i);
                System.out.println((i + 1) + ". " + p.getName() + " (HP: " + p.getHp() + ")");
            }

            choice = scanner.nextInt() - 1;
            if (choice >= 0 && choice < availablePokemon.size()) {
                Pokemon benchPokemon = availablePokemon.get(choice);
                player.getBench().add(benchPokemon);
                player.getHand().remove(benchPokemon);
                availablePokemon.remove(choice);
                System.out.println(player.getName() + "'s Benched Pokémon: " + benchPokemon.getName());
            }
        }
    }

    public void gameSetup() {
        setupDecks();  // 1. Setup decks

        // 2. Coin flip to determine first player
        TurnHandler turnHandler = new TurnHandler(player1, player2, scanner);

        // 3. Draw starting hands
        drawStartingHand();

        // 4. Setup active and bench Pokémon
        System.out.println("\n=== Setting up Active and Bench Pokémon ===");
        UIHelper.displayGameState(player1, player2);
        placeActiveAndBench(player1);
        placeActiveAndBench(player2);

        // 5. Start game loop
        gameLoop(turnHandler);
    }

    public void gameLoop(TurnHandler turnHandler) {
        try {
            while (true) {
                Player currentPlayer = turnHandler.getCurrentPlayer();
                Player opponent = turnHandler.getWaitingPlayer();

                currentPlayer.resetTurn();
                GameAction.handlePlayerTurn(currentPlayer, opponent, currentPlayer.getDeck(), scanner);

                // Check win conditions
                if (currentPlayer.getDeck().getCards().isEmpty()) {
                    System.out.println(opponent.getName() + " WINS! " + currentPlayer.getName() + " has no cards left to draw!");
                    break;
                }
                if (opponent.getActivePokemon() == null && opponent.getBench().isEmpty()) {
                    System.out.println(currentPlayer.getName() + " WINS! All opponent's Pokemon have fainted!");
                    break;
                }
                if (currentPlayer.getPrizeCards().isEmpty()) {
                    System.out.println(currentPlayer.getName() + " WINS! All prize cards collected!");
                    break;
                }

                turnHandler.switchTurn();
            }
        } finally {
            scanner.close();
        }
    }
}
