import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Number of people
        System.out.print("Enter the number of people in the group: ");
        int numberOfPeople = scanner.nextInt();

        // Input: Number of simulation runs
        System.out.print("Enter the number of simulation runs: ");
        int numberOfRuns = scanner.nextInt();

        // Run the simulation
        BirthdaySimulation simulation = new BirthdaySimulation(numberOfPeople, numberOfRuns);
        double probability = simulation.runSimulation();

        // Output the result
        System.out.printf("The probability of at least two people sharing a birthday in a group of %d is: %.2f%%\n",
                numberOfPeople, probability * 100);

        scanner.close();
    }
}