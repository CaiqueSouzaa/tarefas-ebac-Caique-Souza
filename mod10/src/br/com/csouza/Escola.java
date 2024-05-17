package br.com.csouza;

import java.util.ArrayList;

/**
 * @author caique.souza
 * @version 1.0
 */
public class Escola {
    private String nome;
    private double metaAprovado = 7;
    private double metaRecuperacao = 5;
    private ArrayList<Aluno> alunos = new ArrayList<>();

    /**
     * Método construtor do objeto "Escola".
     * @param nome - String nome - Nome da escola.
     */
    public Escola(String nome) {
        this.nome = nome;
    }

    /**
     * Método para atualizar o nome da escola caso necessário.
     * @param nome - String nome - Nome da escola.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método para definir o valor meta de aprovação para as médias dos alunos.
     * @param metaAprovado - double metaAprovacao - Valor que os alunos devem alcançar para serem aprovados.
     */
    public void setMetaAprovado(double metaAprovado) {
        this.metaAprovado = metaAprovado;
    }

    /**
     * Método para definir o valor meta de recuperação para as médias dos alunos.
     * @param metaRecuperacao - double metaRecuperacao - Valor que os alunos devem alcançar para estarem em recuperação.
     */
    public void setMetaRecuperacao(double metaRecuperacao) {
        this.metaRecuperacao = metaRecuperacao;
    }

    /**
     * Método para adicionar uma lista de alunos ao objeto "Escola".
     * @param alunos ArrayList Double alunos - ArrayList de alunos.
     */
    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    /**
     * Método para obeter o nome da escola.
     * @return String nome - Nome da escola.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Método para obter o valor meta de aprovação.
     * @return Double metaAprovacao - Valor ao qual a média do aluno deve ser igual ou superior para ser aprovado.
     */
    public Double getMetaAprovado() {
        return this.metaAprovado;
    }

    /**
     * Método par aobter o valor meta de recuperação.
     * @return Double metaRecuperacao - Valor ao qual a média do aluno deve ser igual, superior e inferior a meta de aprovação para estar de recuperação.
     */
    public Double getMetaRecuperacao() {
        return this.metaRecuperacao;
    }

    /**
     * Método para obter a lista de alunos.
     * @return ArrayList Alunos - Lista dos alunos.
     */
    public ArrayList<Aluno> getAlunos() {
        return this.alunos;
    }

    /**
     * Método para adicionar um novo aluno ao objeto "Escola".
     * @param aluno Aluno aluno - Novo objeto "Aluno" a ser adicionado ao objeto "Escola".
     */
    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    /**
     * Método para verificar o status de aprovação de um aluno.
     * @param aluno Aluno aluno - Objeto "Aluno" a ser verificado.
     * @return String status - O estado pode ser "APROVADO", "RECUPERAÇÃO" ou "REPROVADO".
     */
    public String alunoStatus(Aluno aluno) {
        if (aluno.notas().getNotasMediaTotal() >= this.getMetaAprovado()) {
            return "APROVADO";
        } else if (aluno.notas().getNotasMediaTotal() >= this.getMetaRecuperacao()) {
            return "RECUPERAÇÃO";
        } else {
            return "REPROVADO";
        }
    }

    /**
     * Método para obter o relatório dos status dos alunos presente ao objeto "Escola".
     * @return String relatorio - Relatório informando o status dos alunos de aprovação dos alunos.
     */
    public String relatorioAlunos() {
        StringBuilder relatorio = new StringBuilder();
        for (Aluno aluno : this.getAlunos()) {
            relatorio.append(String.format("Nome: %s | Média: %s | Status: %s\n", aluno.getNome(), aluno.notas().getNotasMediaTotal(), this.alunoStatus(aluno)));
        }

        return relatorio.toString();
    }
}