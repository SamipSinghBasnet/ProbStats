import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {

        ArrayList<String> daysOfWeek = new ArrayList<>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        daysOfWeek.add("Thursday");
        daysOfWeek.add("Friday");
        daysOfWeek.add("Saturday");
        daysOfWeek.add("Sunday");

        ArrayList<String>  subset = new ArrayList<>();
        subset.add("Monday");
        subset.add("Tuesday");
        subset.add("Friday");

        SetOperations setOps= new SetOperations();
        System.out.println("Union" + setOps.union(daysOfWeek, subset));
        System.out.println("Intersection" + setOps.intersection(daysOfWeek, subset));
        System.out.println("Complement" + setOps.complement(daysOfWeek, subset));


    }
}