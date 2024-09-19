package br.com.csouza;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PersonListTest {
    @Test
    public void femaleListOnly() {
        PersonsList.setPersons(Person.populate());

        Stream<Character> femaleList = PersonsList.getAll()
                .stream()
                .map(Person::getSex)
                .filter(s -> s == 'F');

//        System.out.println(femaleList.distinct().toList());
//        System.out.println(femaleList.allMatch(s -> s == 'F'));

        Assert.assertEquals(true, femaleList.allMatch(s -> s == 'F'));
    }
}
