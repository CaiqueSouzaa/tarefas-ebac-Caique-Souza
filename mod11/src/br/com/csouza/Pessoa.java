package br.com.csouza;

public class Pessoa implements Comparable<Pessoa> {
    private String name;
    private char sex;

    /**
     * Método construtor passando somente o parametro nome.
     * @param name Nome do objeto Pessoa a ser criado.
     */
    public Pessoa(String name) {
        this.name = name;
    }

    /**
     * Método construtor passando os parametros name e sex.
     * @param name Nome do objeto Pessoa a ser criado.
     * @param sex Sexo do objeto Pessoa. Necessário informar somente "F" para Feminino ou "M" para Masculino.
     */
    public Pessoa(String name, char sex) {
        this.name = name;
        this.sex = sex;
    }

    /**
     * Método para obter o nome da pessoa
     * @return String name - Nome da pessoa.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Método para obter o sexo da pessoa.
     * @return char sex - Sexo da pessoa.
     */
    public Character getSex() {
        return this.sex;
    }

    /**
     * Método para atualizar o nome da pessoa.
     * @param name Novo nome para o objeto Pessoa.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método para atualizar o sexo da pessoa.
     * @param sex Novo sexo para o objeto Pessoa.
     */
    public void setSex(char sex) {
        this.sex = sex;
    }

    @Override
    public int compareTo(Pessoa pessoa) {
        return this.getName().compareTo(pessoa.getName());
    }

    @Override
    public String toString() {
        return String.format("Pessoa {Nome: '%s', Sexo: '%s'}", this.getName(), this.getSex().equals('M') ? "Masculino" : "Feminino");
    }
}