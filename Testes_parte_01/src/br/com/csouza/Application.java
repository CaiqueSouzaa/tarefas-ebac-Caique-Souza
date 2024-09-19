package br.com.csouza;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void init() {
        Scanner scanner = new Scanner(System.in);
        options(scanner);
        scanner.close();
    }

    public static void options(Scanner scanner) {
        boolean isValid = false;

        final String labelOptions = "1- Novo registro\n2- Obter registros\n3- Filtrar por sexo\n4- Finalizar";
        System.out.println(labelOptions);
        while (!isValid) {
            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                isValid = switch (option) {
                    case 1 -> {
                        getTerminalPerson(scanner);
                        yield true;
                    }
                    case 2 -> {
                        execShowAllPersons(scanner);
                        yield true;
                    }
                    case 3 -> {
                        execShowFilteredPersons(scanner);
                        yield true;
                    }
                    case 4 -> {
                        exit();
                        yield true;
                    }
                    default -> {
                        System.out.println("Valor inválido. Tente novamente");
                        System.out.println(labelOptions);
                        yield false;
                    }
                };

            } catch (Exception err) {
                isValid = false;
                System.out.println("Somente os válores 1, 2 e 3 são válidos");
                System.out.println(labelOptions);
                scanner.next();
            }
        }
    }

    public static void getTerminalPerson(Scanner scanner) {
        System.out.println("Informe o nome, sobrenome, idade e sexo.\nAs informações devem estar separadas por vírgula.\nO campo idade deve ser um número inteiro e o campo sexo deve ser somente 'F' para feminino ou 'M' para masculino.");
        String terminalData = scanner.nextLine();
        List<String> data = List.of(terminalData.split(","));
        List<String> trimData = data.stream()
                .map(String::trim)
                .toList();
        PersonsList.add(new Person(trimData.get(0), trimData.get(1), Integer.parseInt(trimData.get(2)), trimData.get(3).charAt(0)));
        System.out.println("-------------- Registro salvo com sucesso --------------");
        options(scanner);
    }

    public static void execShowAllPersons(Scanner scanner) {
        System.out.println("-------------- Registros obtidos com sucesso --------------");
        showAllPersons(PersonsList.getAll());
        options(scanner);
    }

    public static void execShowFilteredPersons(Scanner scanner) {
        final String labelOptions = "F - Feminino\nM - Masculino";

        System.out.println(labelOptions);

        boolean isValid = false;

        while (!isValid) {
            String filterBy = scanner.nextLine();
            if (!filterBy.equalsIgnoreCase("F") && !filterBy.equalsIgnoreCase("M")) {
                System.out.println("Valor inválido. Tente novamente\n" + labelOptions);
            } else {
                System.out.println("-------------- Registros obtidos com sucesso --------------");
                showFilteredPersons(PersonsList.getAll(), filterBy.charAt(0));
                isValid = true;
            }
        }

        options(scanner);
    }

    private static void showPerson(Person p) {
        System.out.println(p.getFormated());
    }

    private static void showAllPersons(Collection<Person> persons) {
        persons.forEach(Application::showPerson);
    }

    private static void showFilteredPersons(Collection<Person> persons, char filterBy) {
//        persons.stream()
//                .filter((Person p) -> String.valueOf(p.getSex()).equals(String.valueOf(filterBy).toUpperCase()))
//                .toList()
//                .forEach(Application::showPerson);

        PersonsList.getFiltered(filterBy)
                .forEach(Application::showPerson);
    }

    private static void exit() {
        System.out.println("-------------- Aplicação encerrada --------------\nbye");
        System.exit(0);
    }
}
