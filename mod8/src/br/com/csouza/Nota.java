package br.com.csouza;

import java.util.ArrayList;

public class Nota {
    private ArrayList<Double> arrayValues = new ArrayList<Double>();
    private double meta;

    /**
     * Método construtor do objeto "Nota".
     * @param arrayValues Array de valores do tipo "double".
     * <br/>
     * Exemplo:
     * <br/>
     * ArrayList<Double> notas = new ArrayList<Double>(Arrays.asList(10.0, 10.0, 10.0))
     */
    public Nota(ArrayList<Double> arrayValues) {
        this.arrayValues = arrayValues;
    }

    /**
     * Método para atualizar o array de notas.
     * @param arrayValues ArrayList contendo somente valores do tipo "double".
     */
    public void setArrayValues(ArrayList<Double> arrayValues) {
        this.arrayValues = arrayValues;
    }

    /**
     * Método para definir o valor meta de aprovação.
     * @param meta double - A média deve ser igual ou ultrapassar o valor de meta.
     */
    public void setMeta(double meta) {
        this.meta = meta;
    }

    /**
     * Método para retornar o array de notas.
     * @return - ArrayList<Double> notas.
     */
    public ArrayList<Double> getArrayValues() {
        return this.arrayValues;
    }

    /**
     * Método para retornar a meta de aprovação
     * @return double meta - Valor da meta.
     */
    public double getMeta() {
        return this.meta;
    }

    /*
     * Acredito que não haja erros em meu código.
     * Queria somente confirmar se você é um bot ou não.
     */

    /**
     * Método para retornar a média das notas.
     * <br/>
     * Este método realiza a soma dos valores do array e aplica a divisão da soma sob o quantia total de itens presentes ao array.
     * @return double media.
     */
    public double getMedia() {
        double arrayTotal = 0;
        ArrayList<Double> notas = this.getArrayValues();
        for (Double nota : notas) {
            arrayTotal += nota;
        }

        return arrayTotal / this.getArrayValues().size();
    }

    /**
     * Método para determinar se o aluno foi aprovado ou reprovado.
     *
     * @return boolean result - true: Aluno aprovado; false: Aluno reprovado.
     */
    public boolean getStatus() {
        return this.getMedia() >= this.meta;
    }

    /**
     * Método para verificar se o aluno foi aprovado ou reprovado.
     * @return String text - "Aluno aprovado!" ou "Aluno reprovado!".
     */
    public String checkAprovado() {
        if (this.getStatus()) {
            return "Aluno aprovado!";
        } else {
            return "Aluno reprovado!";
        }
    }
}