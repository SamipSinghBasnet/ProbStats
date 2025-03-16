public class Tester {
    public static void main(String[] args) {
        int numTrials = 10000;  // Each experiment runs 10,000 times

        System.out.println("Pokemon Count | Mulligan Chance (%)");


        for (int numPokemon = 1; numPokemon <= 60; numPokemon++) {
            MulliganSimulator simulator = new MulliganSimulator(numPokemon);
            double mulliganChance = simulator.runSimulation(numTrials);
            System.out.printf("     %2d       |      %.2f%%\n", numPokemon, mulliganChance);

        }

        System.out.println("Rare Candies | Loss Probability (%)");
        System.out.println("------------------------------------");

        Brick brick = new Brick();

        for (int numRareCandies = 1; numRareCandies <= 4; numRareCandies++) {
            double lossPercentage = brick.runRareCandySimulation(1000, numRareCandies);  // Run simulation with 1000 trials
            System.out.println("Loss percentage with " + numRareCandies + " Rare Candies: " + lossPercentage + "%");
        }



    }
}
