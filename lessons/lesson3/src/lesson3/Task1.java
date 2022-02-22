package lesson3;

public class Task1 <T> {

    public T[] swapsPlacesArray(T[] arr, int indexX, int indexY) {
        if(indexX >= arr.length) {
            throw new RuntimeException(String.format("Индекс %d больше длинны массива", indexX));
        }
        if(indexY >= arr.length) {
            throw new RuntimeException(String.format("Индекс %d больше длинны массива", indexY));
        }
        T varTemporary = arr[indexX];
        arr[indexX] = arr[indexY];
        arr[indexY] = varTemporary;
        return arr;
    }
}
