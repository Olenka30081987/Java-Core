package lesson1;

import java.util.Arrays;

public class Team {
    private String name;
    private String slogan;
    private int yearOfCreation;
    private Player[] players;

    Team(String name, String slogan, int yearOfCreation, Player[] players) {
        this.name = name;
        this.slogan = slogan;
        this.yearOfCreation = yearOfCreation;
        this.players = players;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void showResults() {
        boolean x = false;
        for (Player player : players) {
//            System.out.println("Прошли дистанцию: ");
            if (player.getResult()) {
//                System.out.println(player);
                x = true;
                break;
            }
        }
        if (x) {
            System.out.println("Прошли дистанцию: ");
            for (Player player : players) {
                if (player.getResult()) {
                    System.out.println(player);
                }
            }
        } else {
            System.out.println("Никто не прошел дистанцию");
        }
    }

    @Override
    public String toString() {
        return "Команда:" + name + ", слоган:" + slogan + ", год основания:" + yearOfCreation + ", \n участники: " + Arrays.toString(players);
    }
}


