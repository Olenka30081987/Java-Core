package lesson4;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PhoneBook {
    private HashMap<String,String> mapPhone;

    public PhoneBook() {
        this.mapPhone = new HashMap<>();
    }

    public void add(String phone, String name) {
        if (!mapPhone.containsKey(phone)) {
            mapPhone.put(phone, name);
            System.out.println("Контакт " + phone + " " + name + " добавлен");
        } else {
            System.out.println("Номер телефона " + phone + " уже есть в справочнике");
        }
    }

    public void get(String name) {
        for (Map.Entry<String, String> o : mapPhone.entrySet()) {
            if (name == o.getValue()) {
                System.out.println(o.getKey() + ": " + o.getValue());
            }
        }
    }
}
