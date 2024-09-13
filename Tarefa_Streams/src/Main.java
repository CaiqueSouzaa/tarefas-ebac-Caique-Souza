import br.com.csouza.Application;
import br.com.csouza.Person;

import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        final char filterBySex = 'F';
//        final String fullSex = filterBySex == 'F' ? "Feminino" : filterBySex == 'M' ? "Masculino" : "Não identificado";
//        List<Person> personList = (List<Person>) new Person().populate();
//
//        System.out.println("----------- Exibindo todos os itens -----------");
//        showAllPersons(personList);
//
//        System.out.printf("\n----------- Exibindo somente os itens filtrados pelo sexo [ %s ] -----------\n", fullSex);
//        showFilteredPersons(personList, filterBySex);
        Application.init();
    }

    private static void showPerson(Person p) {
        System.out.println(p.getFormated());
    }

    private static void showAllPersons(Collection<Person> persons) {
        persons.forEach(Main::showPerson);
    }

    private static void showFilteredPersons(Collection<Person> persons, char filterBy) {
        persons.stream()
                .filter((Person p) -> p.getSex() == filterBy)
                .toList()
                .forEach(Main::showPerson);
    }
}
