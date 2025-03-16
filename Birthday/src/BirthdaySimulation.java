public class BirthdaySimulation {
    private int numberOfPeople;
    private int numberOfRuns;

    public BirthdaySimulation(int numberOfPeople, int numberOfRuns) {
        this.numberOfPeople = numberOfPeople;
        this.numberOfRuns = numberOfRuns;
    }

    public double runSimulation() {
        int sharedBirthdayCount = 0;

        // Run the simulation for the specified number of runs
        for (int i = 0; i < numberOfRuns; i++) {
            if (hasSharedBirthday()) {
                sharedBirthdayCount++;
            }
        }

        return (double) sharedBirthdayCount / numberOfRuns; // Return probability
    }

    private boolean hasSharedBirthday() {
        // Use an array to store birthdays
        String[] birthdays = new String[numberOfPeople];

        // Generate random birthdays for the group
        for (int i = 0; i < numberOfPeople; i++) {
            Person person = new Person();
            birthdays[i] = person.getBirthday();
        }

        // Check for duplicates in the array
        for (int i = 0; i < numberOfPeople; i++) {
            for (int j = i + 1; j < numberOfPeople; j++) {
                if (birthdays[i].equals(birthdays[j])) {
                    return true; // Duplicate birthday found
                }
            }
        }

        return false; // No duplicates
    }
}

