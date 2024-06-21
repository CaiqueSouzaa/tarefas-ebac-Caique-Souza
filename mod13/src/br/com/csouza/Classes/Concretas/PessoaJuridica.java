package br.com.csouza.Classes.Concretas;

import br.com.csouza.Classes.Abstratas.Pessoa;
import br.com.csouza.Interfaces.IPessoaJuridica;

public class PessoaJuridica extends Pessoa implements IPessoaJuridica {
    private String cnpj;
    private String companyName;

    public PessoaJuridica() {}

    public PessoaJuridica(String name) {
        this.setName(name);
    }

    public PessoaJuridica(String name, String surname) {
        this(name);
        this.setSurname(surname);
    }

    public PessoaJuridica(String name, String surname, String cnpj) {
        this(name, surname);
        this.cnpj = cnpj;
    }

    public PessoaJuridica(String name, String surname, String cnpj, String companyName) {
        this(name, surname, cnpj);
        this.companyName = companyName;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    @Override
    public String toString() {
        return String.format("PessoaJuridica: {'name': '%s', 'surname': '%s', 'cnpj': '%s', 'companyName': '%s'}", this.getName(), this.getSurname(), this.getCnpj(), this.getCompanyName());
    }
}