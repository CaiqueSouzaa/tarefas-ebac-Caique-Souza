package br.com.csouza.Interfaces;

public interface IPessoaJuridica extends IPessoa {

    void setCnpj(String cnpj);

    void setCompanyName(String companyName);

    String getCnpj();

    String getCompanyName();
}