
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double[] data = {1, 2, 2, 3, 4, 5, 5, 5};
        StatsLibrary stats = new StatsLibrary(data);
        System.out.println("Mean: " + stats.getMean());
        System.out.println("Median: " + stats.getMedian());
        System.out.println("Mode: " + stats.getMode());
        System.out.println("Standard Deviation: " + stats.getStandardDeviation());
        Scanner scanner = new Scanner(System.in);
        // Get input for n and r and p;
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        System.out.print("Enter the value of r: ");
        int r = scanner.nextInt();
        System.out.print("Enter the value of p: ");
        double p =scanner.nextDouble();
        System.out.println("Permutation ="+ stats.calculatePermutation(n,r));
        System.out.println("Combinaton =" +stats.calculateCombination(n,r));
        // Probability Tests

        double probabilityA = 0.5;
        double probabilityB = 0.3;
        double probabilityIntersection = 0.15;
        double conditionalProbabilityB = 0.4;

        System.out.println("Independent Intersection: " +
                stats.probabilityOfBothIndependent(probabilityA, probabilityB));
        System.out.println("Dependent Intersection: " +
                stats.probabilityOfBothDependent(probabilityA, conditionalProbabilityB));
        System.out.println("Exclusive Union: " +
                stats.probabilityOfEither(probabilityA, probabilityB));
        System.out.println("Non-Exclusive Union: " +
                stats.probabilityOfEitherWithOverlap(probabilityA, probabilityB, probabilityIntersection));
        System.out.println("Are A and B independent? " +
                stats.areEventsIndependent(probabilityA, probabilityB, probabilityIntersection));
        System.out.println("Are A and B dependent? " +
                stats.areEventsDependent(probabilityA, probabilityB, probabilityIntersection));


        System.out.println("Binomial =" +stats.calculateBinomial(6,3,0.8));
        System.out.println("Geometric =" +stats.calculateGeometric(0.7,4));
        System.out.println("Hypergeometric Probability: " + stats.calculateHypoGeometric(196, 101, 10, 7));
        System.out.println("Negative Binomial Probability: " + stats.calculateNegativeBinomial(50, 5, 0.5));


    }
}
