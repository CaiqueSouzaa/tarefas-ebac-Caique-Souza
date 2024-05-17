package br.com.csouza;

import java.util.ArrayList;

/**
 * Desenvolvido para ser usado em conjunto ao objeto "Aluno" para armazenadar suas notas.
 *
 * @author caique.souza
 * @version 1.0
 */
public class Nota {
    private ArrayList<Double> notas = new ArrayList<>();

    /**
     * Método construtor do objeto "Nota".
     * @param notas ArrayList Double notas - Notas do aluno.
     */
    public Nota(ArrayList<Double> notas) {
        this.notas = notas;
    }

    /**
     * Método para atualizar todas as notas.
     * @param notas ArrayList Double notas - Notas do aluno.
     */
    public void setNotas(ArrayList<Double> notas) {
        this.notas = notas;
    }

    /**
     * Método para adicionar uma nova nota ao objeto.
     * @param nota Double nota - Novo valor a ser adicionado ao objeto.
     */
    public void addNotas(double nota) {
        this.notas.add(nota);
    }

    /**
     * Método para obter as notas do aluno.
     * @return ArrayList Double notas - Notas do aluno no formato ArrayList Double.
     */
    public ArrayList<Double> getNotas() {
        return this.notas;
    }

    /**
     * Método para obter a soma total de todas as notas do aluno.
     * @return double notasSoma - Resulta da soma das notas do aluno.
     */
    public double getNotasSomaTotal() {
        int notasSoma = 0;

        for (Double nota : this.getNotas()) {
            notasSoma += nota;
        }

        return notasSoma;
    }

    /**
     * Método para retornar a quantia de notas que o aluno possui.
     * @return int tamanhoNotas - Número inteiro representando a quantia total de notas.
     */
    public int getNotasTamanhoLista() {
        return this.getNotas().size();
    }

    /**
     * Método para obter a média das notas.
     * @return Double media - Retorno no formato "Double" contendo a média das notas.
     */
    public Double getNotasMediaTotal() {
        return (double) (this.getNotasSomaTotal() / this.getNotasTamanhoLista());
    }

    /**
     * Método para retornar a porcentagem da nota referente a uma meta.
     * @param meta - double meta - Valor que servirá de base para obter a porcentagem sobre a média.
     * @return Double porcentagem - Valor representa a porcentagem da média sobre a meta informada.
     */
    public Double getPorcentagemSobreMeta(double meta) {
        return (this.getNotasMediaTotal() * 100) / meta;
    }
}