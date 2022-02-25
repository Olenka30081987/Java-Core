package lesson4;

import java.util.*;

public class Lesson4 {

    public static void main(String[] args) {
        // Задача № 1
        List<String> listWord = new ArrayList<>(Arrays.asList("cat", "dog", "rabbit", "cat", "cock", "hare", "dog", "cat", "hare","cat","cat","hare"));
        System.out.println("Массив слов: " + listWord);
        HashSet<String> listSet = new HashSet<>(listWord);
        System.out.println("Массив из уникальных слов: " + listSet);
        HashMap<String, Integer> mapWord = new HashMap<>();
        for (String tempStr : listWord) {
            if (!mapWord.containsKey(tempStr)) {
                mapWord.put(tempStr, 1);
            } else {
                mapWord.put(tempStr, mapWord.get(tempStr) + 1);
            }
        }
        for (Map.Entry<String, Integer> pair: mapWord.entrySet()) {
            System.out.println("Слово: " + pair.getKey() + ", повторений: " + pair.getValue());
        }

        // Задача № 2
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("89873966018", "Orlov");
        phoneBook.add("89873966018", "Petrov");
        phoneBook.add("89873986018", "Petrov");
        phoneBook.add("81873986019", "Petrov");
        phoneBook.add("89873916018", "Ivanov");

        phoneBook.get("Petrov");
        phoneBook.get("Orlov");



    }




}
