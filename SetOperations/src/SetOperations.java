import java.util.ArrayList; // Import ArrayList to store sets

// Class to perform basic set operations
public class SetOperations {
    private ArrayList<String> set; // List to hold the set values

    // Constructor
    public SetOperations() {}

    // Get the current set
    public ArrayList<String> getSet() {
        return set;
    }

    // Set a new list as the set
    public void setSet(ArrayList<String> set) {
        this.set = set;
    }

    // Union of two sets (combines unique elements)
    public ArrayList<String> union(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            if (!list2.contains(list1.get(i))) {
                result.add(list1.get(i)); // Add unique elements from list1
            } else {
                result.add(list1.get(i)); // Add common elements once
            }
        }
        return result;
    }

    // Intersection of two sets (common elements)
    public ArrayList<String> intersection(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            if (list2.contains(list1.get(i))) {
                result.add(list1.get(i)); // Add common elements
            }
        }
        return result;
    }

    // Complement of a subset in a universal set (elements only in universal set)
    public ArrayList<String> complement(ArrayList<String> universalSet, ArrayList<String> subset) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < universalSet.size(); i++) {
            if (!subset.contains(universalSet.get(i))) {
                result.add(universalSet.get(i)); // Add elements not in the subset
            }
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
}



