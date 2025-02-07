import java.util.ArrayList;
public class SetOperations {
    private ArrayList<String> set;

    public SetOperations() {

    }

    public ArrayList<String> getSet() {
        return set;
    }

    public void setSet(ArrayList<String> set) {
        this.set = set;
    }
    public ArrayList<String> union(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            if (!list2.contains(list1.get(i))) {
                result.add(list1.get(i));
            }else {
                result.add(list1.get(i));
            }
        }
        return result;
    }
    public ArrayList<String> intersection(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            if (list2.contains(list1.get(i))) {
                result.add(list1.get(i));
            }
        }
        return result;

    }
    public ArrayList<String> complement(ArrayList<String> universalSet, ArrayList<String> subset) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < universalSet.size(); i++) {
            if (!subset.contains(universalSet.get(i))) {
                result.add(universalSet.get(i));
            }
        }
        return result;
    }
}
