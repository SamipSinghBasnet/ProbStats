import java.util.Random;
public class Door {
    // Instance variables to track simulation results
    private int stayWins = 0, switchWins = 0;    // Counters for wins when staying vs switching
    private int trials;                          // Number of trials to run
    private int prize;                          // Door number containing the prize (0-2)
    private int choice;                         // Player's initial door choice (0-2)
    int switchedChoice;                         // Door number after switching (0-2)
    private Random random = new Random();       // Random number generator

    // Constructor to initialize number of trials
    public Door(int trials) {
        this.trials = trials;
    }

    // Getter methods for accessing results
    public int getStayWins() {
        return stayWins;
    }

    public int getSwitchWins() {
        return switchWins;
    }

    public int getTrials() {
        return trials;
    }

    public void runSimulation() {
        for (int j = 0; j < trials; j++) {          // Run the specified number of trials
            prize = random.nextInt(3);              // Randomly place prize behind door 0-2
            choice = random.nextInt(3);             // Randomly select initial door 0-2

            int revealedDoor = -1;                  // Door that Monty Hall will reveal
            for (int i = 0; i < 3; i++) {
                if (i != prize && i != choice) {    // Find a door that's not the prize or chosen door
                    revealedDoor = i;
                    break;                          // Stop after finding first valid door
                }
            }
            // Calculate the door number if player switches (sum of all doors (0+1+2=3) minus chosen and revealed doors)
            switchedChoice = 3 - choice - revealedDoor;

            if (choice == prize) {                  // If initial choice was correct
                stayWins++;                        // Increment stay wins counter
            } else if (switchedChoice == prize) {   // If switching would lead to prize
                switchWins++;                      // Increment switch wins counter
            }
        }
    }
}


   