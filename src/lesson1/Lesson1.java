package lesson1;

public class Lesson1 {

    public static void main(String[] args) {
        Player[] players = new Player[]{
                new Player("Ivan", "man", 70, 185, 30),
                new Player("Denis", "man", 80, 165, 25),
                new Player("Anna", "woman", 55, 170, 28),
                new Player("Olga", "woman", 60, 180, 29),
        };

        Trials[] trials = new Trials[]{
                new Trials("run", 10, 40),
                new Trials("swim", 1, 20),
        };

        Team team1 = new Team("Rocket", "Forward to victory!", 2018, players);
        System.out.println(team1);

        Course courses1 = new Course("level1", trials);
        courses1.doIt(team1);
        System.out.println();
        team1.showResults();
    }
}
