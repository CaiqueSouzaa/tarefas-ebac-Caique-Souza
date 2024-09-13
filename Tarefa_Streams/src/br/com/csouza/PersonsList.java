package br.com.csouza;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PersonsList {
    private static List<Person> persons = new ArrayList<>();

    public static void add(Person p) {
        persons.add(p);
    }

    public static boolean remove(Person p) {
        persons.remove(p);
        return !persons.contains(p);
    }

    public static Person get(int index) {
        return persons.get(index);
    }

    public static Collection<Person> getAll() {
        return persons;
    }

    public static Collection<Person> getFiltered(char filterBy) {
        return persons.stream()
                .filter((Person p) -> p.getSex() == filterBy)
                .toList();
    }
}
