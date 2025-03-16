public class HallGame {
    public static void main(String[] args) {
        Door game = new Door(10000);
        game.runSimulation();
        System.out.println("Staying wins: " + game.getStayWins() + " out of " + game.getTrials());
        System.out.println("Switching wins: " + game.getSwitchWins() + " out of " + game.getTrials());
        double staywins= ((double) game.getStayWins() /(double)game.getTrials())*100.00;
        double switchwins =((double) game.getSwitchWins() /(double)game.getTrials())*100.00;
        System.out.println("%staywins: " + staywins);
        System.out.println("%switchwins: " + switchwins);
    }

}