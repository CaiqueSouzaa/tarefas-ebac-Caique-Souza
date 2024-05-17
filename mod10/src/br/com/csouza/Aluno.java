package br.com.csouza;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caique.souza
 * @version 1.0
 */
public class Aluno {
    private String nome;
    private Nota notas;

    /**
     * Método construtor do objeto "Aluno".
     * @param nome - String nome - Nome do aluno.
     */
    public Aluno(String nome) {
        this.nome = nome;
    }

    /**
     * Método para atualizat o nome do aluno caso necessário.
     * @param nome - String nome - Nome do aluno.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método para definir as notas do aluno.
     * @param notas - ArrayList Double notas - Notas do aluno.
     */
    public void setNotas(ArrayList<Double> notas) {
        this.notas = new Nota(notas);
    }

    /**
     * Método para obter o nome do aluno.
     * @return String nome - Nome do aluno.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Método para obter acesso aos atributos do objeto "Nota" presente ao objeto "Aluno".
     * @return Nota notas - Objeto "Nota" referente as notas do aluno.
     */
    public Nota notas() {
        return this.notas;
    }

    /**
     *  Método para retornar uma lista contendo as notas do aluno.
     * @return List Double - Notas do aluno.
     */
    public List<Double> getNotas() {
        return this.notas().getNotas();
    }
}