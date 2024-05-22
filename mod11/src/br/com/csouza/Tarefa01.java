package br.com.csouza;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tarefa01 {
    public void init() {
        System.out.println("--------------- Tarefa #01 ---------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira os nomes das pessoas separadamente por vírgulas: ");
        List<String> pessoas = obterListaNomes(scanner);
        Collections.sort(pessoas);

        System.out.print("Nomes organizados por ordem alfabética: " + pessoas);

        scanner.close();
    }

    private List<String> obterListaNomes(Scanner scanner) {
        String nomes = scanner.nextLine();
        String[] listaNomes = nomes.split(",", 0);
        List<String> pessoas = new ArrayList<>();
        for (String s : listaNomes) {
            Pessoa pessoa = new Pessoa(s.trim());
            pessoas.add(pessoa.getName());
        }

        return pessoas;
    }
}