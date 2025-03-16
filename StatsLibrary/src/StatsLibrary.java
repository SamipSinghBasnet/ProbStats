import java.util.Arrays;

public class StatsLibrary {
    private double[] numbers; // Array to hold data
    private int size; // Size of the data set

    /**
     * Constructor to initialize the stats library with a dataset
     * @param numbers Array of numbers to analyze
     */
    public StatsLibrary(double[] numbers) {
        this.numbers = numbers;
        this.size = numbers.length;
    }

    /**
     * Calculates the arithmetic mean of the dataset
     * @return The mean value, or 0 if dataset is empty
     */
    public double getMean() {
        if (size == 0) return 0;
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += numbers[i];
        }
        return sum / size;
    }

    /**
     * Calculates the median (middle value) of the dataset
     * @return The median value, or 0 if dataset is empty
     */
    public double getMedian() {
        if (size == 0) return 0;
        double[] sortedNumbers = numbers.clone();
        Arrays.sort(sortedNumbers);
        if (size % 2 == 0) {
            return (sortedNumbers[size / 2 - 1] + sortedNumbers[size / 2]) / 2.0;
        } else {
            return sortedNumbers[size / 2];
        }
    }

    /**
     * Calculates the mode (most frequent value) of the dataset
     * @return The mode value, or first value if no clear mode exists
     */
    public double getMode() {
        if (size == 0) return 0;
        double mode = numbers[0];
        int maxCount = 0;
        for (int i = 0; i < size; i++) {
            int count = 0;
            for (int j = 0; j < size; j++) {
                if (numbers[j] == numbers[i]) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mode = numbers[i];
            }
        }
        return mode;
    }

    /**
     * Calculates the standard deviation of the dataset
     * @return The standard deviation, or 0 if dataset is empty
     */
    public double getStandardDeviation() {
        if (size == 0) return 0;
        double mean = getMean();
        double sumSquaredDiffs = 0;
        for (int i = 0; i < size; i++) {
            sumSquaredDiffs += Math.pow(numbers[i] - mean, 2);
        }
        return Math.sqrt(sumSquaredDiffs / size);
    }

    /**
     * Calculates the factorial of a number
     * @param num The number to calculate factorial for
     * @return The factorial result as a double, or 0 if input is negative
     */
    public double factorial(int num) {
        if (num < 0) return 0;
        if (num == 0 || num == 1) return 1;
        double result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Calculates combinations (nCr)
     * @param n Total number of items
     * @param r Number of items being chosen
     * @return Number of possible combinations
     */
    public double calculateCombination(int n, int r) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;
        if (r > n - r) r = n - r;  // Optimize calculation

        double result = 1;
        for (int i = 0; i < r; i++) {
            result *= (n - i);
            result /= (i + 1);
        }
        return result;
    }

    /**
     * Calculates permutations (nPr)
     * @param n Total number of items
     * @param r Number of items being arranged
     * @return Number of possible permutations
     */
    public double calculatePermutation(int n, int r) {
        if (r > n || n < 0 || r < 0) return 0;
        if (r == 0) return 1;

        double result = 1;
        for (int i = 0; i < r; i++) {
            result *= (n - i);
        }
        return result;
    }

    // Probability of both A and B occurring independently
    public double probabilityOfBothIndependent(double probA, double probB) {
        return probA * probB;
    }

    // Probability of both A and B occurring when dependent
    public double probabilityOfBothDependent(double probA, double probBGivenA) {
        return probA * probBGivenA;
    }

    // Probability of A or B occurring (no overlap)
    public double probabilityOfEither(double probA, double probB) {
        return probA + probB;
    }

    // Probability of A or B occurring (with overlap)
    public double probabilityOfEitherWithOverlap(double probA, double probB, double probBoth) {
        return probA + probB - probBoth;
    }

    // Check if events are independent
    public boolean areEventsIndependent(double probA, double probB, double probBoth) {
        double expectedIndependentProb = probA * probB;
        return Math.abs(probBoth - expectedIndependentProb) < 0.0001;
    }

    // Check if events are dependent
    public boolean areEventsDependent(double probA, double probB, double probBoth) {
        return !areEventsIndependent(probA, probB, probBoth);
    }

    /**
     * Calculates binomial probability
     * @param n Number of trials
     * @param r Number of successes
     * @param p Probability of success on each trial
     * @return Binomial probability
     */
    public double calculateBinomial(int n, int r, double p) {
        if (p < 0 || p > 1 || r > n || n < 0 || r < 0) return 0;
        return calculateCombination(n, r) * Math.pow(p, r) * Math.pow(1 - p, n - r);
    }

    /**
     * Calculates geometric probability
     * @param p Probability of success
     * @param r Number of trials until first success
     * @return Geometric probability
     */
    public double calculateGeometric(double p, int r) {
        if (p <= 0 || p >= 1 || r < 1) return 0;
        return p * Math.pow(1 - p, r - 1);
    }

    /**
     * Calculates hypergeometric probability
     * @param N Population size
     * @param K Number of success states in population
     * @param n Number of draws
     * @param k Number of observed successes
     * @return Hypergeometric probability
     */
    public double calculateHypoGeometric(int N, int K, int n, int k) {
        if (N < 0 || K < 0 || n < 0 || k < 0) return 0;
        if (K > N || n > N || k > K || k > n || (n - k) > (N - K)) return 0;

        try {
            double numerator = (calculateCombination(K, k) * calculateCombination(N - K, n - k));
            double denominator = calculateCombination(N, n);

            if (denominator == 0) return 0;
            return numerator / denominator;
        } catch (ArithmeticException e) {
            return 0;
        }
    }

    /**
     * Calculates negative binomial probability
     * @param y Total number of trials
     * @param r Required number of successes
     * @param p Probability of success on each trial
     * @return Negative binomial probability
     */
    public double calculateNegativeBinomial(int y, int r, double p) {
        if (p <= 0 || p >= 1 || r < 1 || y < r) return 0;
        return calculateCombination(y - 1, r - 1) * Math.pow(p, r) * Math.pow(1 - p, y - r);
    }
}