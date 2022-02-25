package lesson2;

public class Lesson2 {

    public static void main(String[] args) {
        String[][] arr1 = {
                {"1", "4", "5"},
                {"1", "4", "5"},
                {"1", "4", "5"},
        };

        String[][] arr2 = {
                {"1", "4", "5", "10"},
                {"1", "4", "5", "10"},
                {"1", "4", "@", "10"},
                {"1", "4", "5", "10"},
        };

        String[][] arr3 = {
                {"1", "4", "5", "10"},
                {"1", "4", "5", "10"},
                {"1", "4", "5", "10"},
                {"1", "4", "5", "10"},
        };
        testArray(arr1);
        testArray(arr2);
        testArray(arr3);
    }
    
    static void array(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if(arr.length !=4 || arr[0].length !=4 ) {
            throw new MyArraySizeException();
        }
        int sum = 0;
        for (int i = 0; i<arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try{
                    int item = Integer.parseInt(arr[i][j]);
                    sum += item;
                }
                catch (Exception e){
                    throw new MyArrayDataException("Ошибка в ячейке: " + i+"."+j);
                }
            }
        }
        System.out.println("Сумма элементов массива " + sum);
    }

    static void  testArray(String[][] arr) {
        try {
            array(arr);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
}
