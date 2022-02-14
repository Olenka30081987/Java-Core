package lesson1;

import java.util.Random;

public class Course {
    private String name;
    private Trials[] trials;
    private Random random;

    Course(String name, Trials[] trials) {
        this.name = name;
        this.trials = trials;
    }

    public void doIt(Team team1) {
        random = new Random();
        for (Player player : team1.getPlayers()) {
            for (Trials trial : trials) {
                int passing = random.nextInt(2);
                if (passing == 1) {
                    player.setResult(true);
                } else {
                    player.setResult(false);
                    break;
                }
            }
        }
    }
}


