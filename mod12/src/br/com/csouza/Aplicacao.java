package br.com.csouza;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;

public class Aplicacao {
    private final Set<Pessoa> pessoas = new HashSet<>();
    private final Grupos grupos = new Grupos();

    public void init() {
        this.defineInput();
        this.adicionarPessoasAosGrupos();
        System.out.println(this.obterGrupoFormatado());
    }

    private void defineInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira uma lista de pessoas separada por vírgula. Exemplo: Caique - M, João - M, Maria - F, ...\n>> ");
        String input = scanner.nextLine();
        String[] data = input.split(",");
        List<String> inputs = new ArrayList<>();
        for (String d : data) {
            inputs.add(d.trim());
        }
        criarPessoas(inputs);
    }

    private void criarPessoas(List<String> data) {
        for (String d : data) {
            String[] pessoaData = d.split("-");
            String nome = pessoaData[0].trim();
            char sexo = pessoaData[1].trim().charAt(0);
            Pessoa pessoa = new Pessoa(nome, sexo);
            this.pessoas.add(pessoa);
        }
    }

    private void adicionarPessoasAosGrupos() {
        grupos.addAll(this.pessoas);
    }

    private String obterGrupoFormatado() {
        StringBuilder gruposFormatado = new StringBuilder();
        Map<String, Set<Pessoa>> grupos = this.grupos.getGrupos();
        Set<Map.Entry<String, Set<Pessoa>>> data = grupos.entrySet();
        for (Map.Entry<String, Set<Pessoa>> g : data) {
            gruposFormatado.append(String.format("-------------------------\nGrupo %s\n", g.getKey()));
            StringBuilder nomes = new StringBuilder();
            for (Pessoa pessoa : g.getValue()) {
                nomes.append(String.format(">> %s\n", pessoa.getName()));
            }
            gruposFormatado.append(nomes);
        }

        return gruposFormatado.toString();
    }
}