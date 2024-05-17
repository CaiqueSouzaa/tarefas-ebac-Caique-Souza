package br.com.csouza;

import java.util.Scanner;
import java.util.ArrayList;

public class Aplicacao {
    public void init() throws Exception {
        Scanner scanner = new Scanner(System.in);

        Escola escola = this.criarEscola(scanner);
        int periodoLetivo = this.definirPeriodoLetivo(scanner);
        int quantAlunos = this.quantAlunos(scanner);
        ArrayList<Aluno> alunos = this.criarAlunos(quantAlunos, periodoLetivo, scanner);

        for (Aluno aluno : alunos) {
            System.out.println(aluno.notas().getNotasMediaTotal());
        }

        scanner.close();
    }

    private Escola criarEscola(Scanner scanner) {
        System.out.print("Defina o nome da escola: ");
        String nome = scanner.nextLine();
        return new Escola(nome);
    }

    private int definirPeriodoLetivo(Scanner scanner){
        boolean sucesso = false;
        int periodoLetivo = 0;
        while (!sucesso) {
            try {
                System.out.print("Defina o periodo letivo: ");
                periodoLetivo = scanner.nextInt();
                if (periodoLetivo <= 0) {
                    System.out.println("O periodo letivo não pode ser inferior ou igual a zero. Tente novamente.");
                } else {
                    sucesso = true;
                }
            } catch (Exception err) {
                System.out.println("Input inválido. Digite somente números inteiros.");
                scanner.next();
            }
        }

        return periodoLetivo;
    }

    private int quantAlunos(Scanner scanner) {
        boolean sucesso = false;
        int quantAlunos = 0;

        while (!sucesso) {
            try {
                System.out.print("Quantia de alunos: ");
                quantAlunos = scanner.nextInt();
                scanner.nextLine();
                if (quantAlunos <= 0) {
                    System.out.println("A quantia de alunos não pode ser inferior ou igual a zero. Tente novamente.");
                } else {
                    sucesso = true;
                }
            } catch (Exception err) {
                System.out.println("Input inválido. Digite somente números inteiros.");
                scanner.next();
            }
        }
        return quantAlunos;
    }

    private ArrayList<Aluno> criarAlunos(int quantAlunos, int periodoLetivo, Scanner scanner) {
        ArrayList<Aluno> alunos = new ArrayList<>();
        for (int i = 0; i < quantAlunos; i++) {
            System.out.printf("Nome aluno %d: ", i+1);
            String nome = scanner.nextLine();
            Aluno aluno = new Aluno(nome);
            ArrayList<Double> notas = this.criarNotasAluno(periodoLetivo, scanner);
            aluno.setNotas(notas);
            alunos.add(aluno);
        }

        return alunos;
    }

    private ArrayList<Double> criarNotasAluno(int periodoLetivo, Scanner scanner) {
        ArrayList<Double> notas = new ArrayList<>();
        for (int i = 0; i < periodoLetivo; i++) {
            boolean sucesso = false;

            while (!sucesso) {
                try {
                    System.out.printf("Nota referente ao periodo %s: ", i+1);
                    double nota = scanner.nextDouble();
                    scanner.nextLine();

                    if (nota > 10.0) {
                        nota = 10.0;
                    }

                    notas.add(nota);

                    if (nota < 0) {
                        System.out.println("As notas de alunos não pode ser inferior a zero. Tente novamente.");
                    } else {
                        sucesso = true;
                    }

                } catch (Exception err) {
                    System.out.println("Input inválido. Digite somente números inteiros ou de pontos flutuantes.");
                }
            }
        }

        return notas;
    }
}