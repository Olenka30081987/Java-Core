package lesson3;
import java.util.Arrays;

public class Box <T extends Fruit> {
    private T[] fruits;

    public Box(T[] fruits) {
        this.fruits = fruits;
    }

    public float getWeight() {
        float boxWeight = 0.0f;
        for (T t : fruits) {
            if (t != null) {
                float weight = t.getWeight();
                boxWeight = fruits.length * weight;
            }
        }
        return boxWeight;
    }

    public boolean compare(Box<?> anotherBox) {
        return Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.0001;
    }

    public void transfer(Box<T>  anotherBox ) {
        anotherBox.fruits = Arrays.copyOf(anotherBox.fruits, this.fruits.length + anotherBox.fruits.length);
        System.arraycopy(this.fruits, 0, anotherBox.fruits, anotherBox.fruits.length - this.fruits.length, this.fruits.length);
        Arrays.fill( this.fruits, null);
   }

   public void addFruit(Fruit fruit[]) {
       this.fruits = Arrays.copyOf(this.fruits, this.fruits.length + fruit.length);
       System.arraycopy(fruit, 0, this.fruits, this.fruits.length - fruit.length, fruit.length);
   }

    @Override
    public String toString() {
        return Arrays.toString(fruits);
    }

}



