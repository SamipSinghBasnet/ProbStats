import java.util.Random;

public class Person {
    private String birthday; // Birthday in MM/DD format

    public Person() {
        this.birthday = generateRandomBirthday();
    }

    private String generateRandomBirthday() {
        Random random = new Random();

        // Randomly generate month (1-12)
        int month = random.nextInt(12) + 1;

        // Generate day based on the month
        int day;
        if (month == 2) {
            day = random.nextInt(28) + 1; // February (always 28 days)
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            day = random.nextInt(30) + 1; // Months with 30 days
        } else {
            day = random.nextInt(31) + 1; // Months with 31 days
        }

        //Format MM/DD
        return String.format("%02d/%02d", month, day);
    }

    public String getBirthday() {
        return birthday;
    }
}
