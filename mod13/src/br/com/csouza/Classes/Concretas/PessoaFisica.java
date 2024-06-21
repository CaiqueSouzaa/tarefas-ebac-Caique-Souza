package br.com.csouza.Classes.Concretas;

import br.com.csouza.Classes.Abstratas.Pessoa;
import br.com.csouza.Interfaces.IPessoaFisica;

public class PessoaFisica extends Pessoa implements IPessoaFisica {
    private String cpf;

    public PessoaFisica() {}

    public PessoaFisica(String name) {
        this.setName(name);
    }

    public PessoaFisica(String name, String surname) {
        this(name);
        this.setSurname(surname);
    }

    public PessoaFisica(String name, String surname, String cpf) {
        this(name, surname);
        this.cpf = cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    @Override
    public String toString() {
        return String.format("PessoaFisica: {'name': '%s', 'surname': '%s', 'cpf': '%s'}", this.getName(), this.getSurname(), this.getCpf());
    }
}