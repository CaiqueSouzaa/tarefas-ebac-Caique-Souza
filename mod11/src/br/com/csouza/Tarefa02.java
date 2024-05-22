package br.com.csouza;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tarefa02 {
    private final List<Pessoa> masculino = new ArrayList<>();
    private final List<Pessoa> feminino = new ArrayList<>();

    public void init() {
        System.out.println("--------------- Tarefa #02 ---------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira os nomes e sexos das pessoas separadamente por vírgulas. Exemplo: Caique - M, Maria - F, etc...: ");
        obterListaPessoas(scanner);
        System.out.println("Pessoas do sexo masculino: " + this.masculino);
        System.out.println("Pessoas do sexo feminino: " + this.feminino);

        scanner.close();
    }

    private void obterListaPessoas(Scanner scanner) {
        String nomes = scanner.nextLine();
        String[] listaStringPessoa = nomes.split(",", 0);
        for (String pessoa : listaStringPessoa) {
            String[] pessoaData = pessoa.split("-", 0);
            Character sex = pessoaData[1].toUpperCase().trim().charAt(0);
            Pessoa p = new Pessoa(pessoaData[0].trim(), sex);
            if (sex.equals('F')) {
                this.feminino.add(p);
            } else if (sex.equals('M')) {
                this.masculino.add(p);
            }
        }
    }
}