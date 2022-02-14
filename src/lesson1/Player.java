package lesson1;

public class Player {
    private String name;
    private String gender;
    private int weight;
    private int height;
    private int age;
    private boolean result;

    Player (String name, String gender, int weight, int height, int age) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.result = true;
    }
    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
        public String toString() {
            return "\nИгрок: " + name + ", вес" + weight + ", рост" + height + ", возраст" + age;

        }

}

