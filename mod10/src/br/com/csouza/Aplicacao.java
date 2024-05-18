package br.com.csouza;

import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Aplicacao {
    private Escola escola;
    private int periodoLetivo = 0;
    private int quantAlunos = 0;
    private ArrayList<Aluno> alunos = new ArrayList<>();

    public void init() throws Exception {
        boolean execStatus = true;
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        // Obrigatório definir o nome da escola e quantia de periodos letivos no inicio da execução
        this.criarEscola(scanner);
        this.definirPeriodoLetivo(scanner);

        while (execStatus) {
            int opcao = this.garantirOpcaoValida(scanner);

            switch (opcao) {
                case 1:
                    System.out.println("__________________________\n>> Relatório escolar");
                    if (!this.alunos.isEmpty()) {
                        System.out.println(this.escola.relatorioAlunos());
                    } else {
                        System.out.printf(">> Não há alunos registrados na escola %s.\n", this.escola.getNome());
                    }
                    break;

                case 2:
                    this.criarAlunos(1, this.periodoLetivo, scanner);
                    break;

                case 3:
                    execStatus = false;
                    System.out.println("Fim de execução.");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    public void opcoes() {
        System.out.println("__________________________\n1 - Exibir relatório escolar\n2 - Adicionar novo aluno\n3 - Finalizar a aplicação\n__________________________");
        System.out.print(">> ");
    }

    public int garantirOpcaoValida(Scanner scanner) {
        boolean status = false;
        int opcao = 0;

        while (!status) {
            try {
                this.opcoes();
                opcao = scanner.nextInt();

                if (opcao < 1 || opcao > 3) {
                    System.out.println("O valor da opção deve ser somente '1', '2' ou '3'.");
                } else {
                    status = true;
                }

                scanner.nextLine();
            } catch (Exception err) {
                System.out.println("Input inválido. Digite somente números inteiros.");
                scanner.next();
            }
        }

        return opcao;
    }

    private void criarEscola(Scanner scanner) {
        System.out.print("Defina o nome da escola: ");
        String nome = scanner.nextLine();
        this.escola = new Escola(nome);
    }

    private void definirPeriodoLetivo(Scanner scanner){
        boolean sucesso = false;
        int periodoLetivo = 0;
        while (!sucesso) {
            try {
                System.out.print("Defina a quantia de periodos letivos: ");
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

        this.periodoLetivo = periodoLetivo;
    }

    private void definirAlunos(Scanner scanner) {
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
        this.quantAlunos = quantAlunos;
    }

    private void criarAlunos(int quantAlunos, int periodoLetivo, Scanner scanner) {
        for (int i = 0; i < quantAlunos; i++) {
            System.out.print("Nome do aluno: ");
            String nome = scanner.nextLine();
            Aluno aluno = new Aluno(nome);
            System.out.printf("__________________________\n>> Aluno [%s] registrado com sucesso.\n", nome);
            ArrayList<Double> notas = this.criarNotasAluno(periodoLetivo, scanner);
            aluno.setNotas(notas);
            this.alunos.add(aluno);
            this.escola.addAluno(aluno);
        }
    }

    private ArrayList<Double> criarNotasAluno(int periodoLetivo, Scanner scanner) {
        ArrayList<Double> notas = new ArrayList<>();
        for (int i = 0; i < periodoLetivo; i++) {
            boolean sucesso = false;

            while (!sucesso) {
                try {
                    System.out.printf("Nota referente ao periodo %s: ", i+1);
                    double nota = scanner.nextDouble();
                    if (nota > 10.0) {
                        nota = 10.0;
                    }

                    notas.add(nota);

                    if (nota < 0) {
                        System.out.println("As notas de alunos não pode ser inferior a zero. Tente novamente.");
                    } else {
                        sucesso = true;
                    }

                    scanner.nextLine();

                } catch (Exception err) {
                    System.out.println("Input inválido. Digite somente números inteiros ou de pontos flutuantes.");
                    scanner.next();
                }
            }
        }

        return notas;
    }
}