package App;

import br.com.csouza.Classes.Concretas.PessoaFisica;
import br.com.csouza.Classes.Concretas.PessoaJuridica;
import br.com.csouza.Classes.Concretas.User;
import br.com.csouza.Interfaces.IPessoaFisica;
import br.com.csouza.Interfaces.IPessoaJuridica;
import br.com.csouza.Interfaces.IUser;

import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

public class Application {
    private IUser user;
    private Set<IPessoaFisica> pessoasFisicas;
    private Set<IPessoaJuridica> pessoasJuridicas;

    public Application() {
        this.user = new User();
        this.pessoasFisicas = new HashSet<>();
        this.pessoasJuridicas = new HashSet<>();
    }

    public void init() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, insira seu nome: ");
        this.user.setName(scanner.nextLine());
        System.out.println("-------------------------\n>> Olá, " + this.user.getName());

        this.logic(scanner);

        scanner.close();
    }

    /**
     * Método para garantir que o usuário está inserindo um valor inteiro e evitar que aplicação quebre.
     * @param scanner Tipo Objeto Scanner
     * @return Número inteiro digitado pelo usuário.
     */
    private int intInputValidation(Scanner scanner) {
        boolean isValid = false;
        int number = 0;

        while (!isValid) {
            try {
                number = scanner.nextInt();
                isValid = true;
            } catch (Exception err) {
                System.out.println("Input não válido, tente novamente. Insira somente números inteiros.");
                System.out.print(">> ");
                scanner.next();
            }
        }

        return number;
    }

    private void logic(Scanner scanner) {
        while (true) {
            System.out.print("-------------------------\n[ Início ]\n-------------------------\nDigite o número da ação a ser executada:\n1 - Registros\n2 - Finalizar aplicação\n>> ");
            int response = this.intInputValidation(scanner);

            switch (response) {
                case 1:
                    this.registers(scanner);
                    break;

                case 2:
                    this.applicationStop();
                    break;

                default:
                    System.out.println("Opção não válida. Tente novamente.");
                    System.out.print(">> ");
                    scanner.next();
            }
        }
    }

    private void registers(Scanner scanner) {
        while (true) {
            System.out.println("-------------------------\n[ Registros ]\n-------------------------\nDigite o número da ação a ser executada:\n1 - Visualizar\n2 - Registrar\n3 - Voltar\n4 - Finalizar aplicação\n>> ");
            int response = this.intInputValidation(scanner);
            switch (response) {
                case 1:
                    this.registerOption1(scanner);
                    break;

                case 2:
                    this.registerOption2(scanner);
                    break;

                case 3:
                    this.logic(scanner);
                    break;

                case 4:
                    this.applicationStop();
                    break;

                default:
                    System.out.println("Opção não válida. Tente novamente.");
                    scanner.next();
                    System.out.print(">> ");
            }
        }
    }

    private void registerOption1(Scanner scanner) {
        while (true) {
            System.out.println("-------------------------\n[ Visualizar Registros ]\n-------------------------\nDigite o número da ação a ser executada:\n1 - Pessoas fisicas\n2 - Pessoas fisicas\n3 - Voltar\n4 - Finalizar aplicação\n>> ");
            int response = this.intInputValidation(scanner);
            switch (response) {
                case 1:
                    this.pageShowPessoasFisicas(scanner);
                    break;

                case 2:
                    this.pageShowPessoasJuridicas(scanner);
                    break;

                case 3:
                    this.registers(scanner);
                    break;

                case 4:
                    this.applicationStop();
                    break;

                default:
                    System.out.println("Opção não válida. Tente novamente.");
                    System.out.print(">> ");
                    scanner.next();
            }
        }
    }

    private void registerOption2(Scanner scanner) {
        while (true) {
            System.out.println("-------------------------\n[ Registrar ]\n-------------------------\nDigite o número da ação a ser executada:\n1 - Pessoa fisica\n2 - Pessoa juridica\n3 - Voltar\n4 - Finalizar aplicação\n>> ");
            int response = this.intInputValidation(scanner);
            switch (response) {
                case 1:
                    this.pageRegisterPessoaFisica(scanner);
                    break;

                case 2:
                    this.pageRegisterPessoaJuridica(scanner);
                    break;

                case 3:
                    this.registers(scanner);
                    break;

                case 4:
                    this.applicationStop();
                    break;

                default:
                    System.out.println("Opção não válida. Tente novamente.");
                    System.out.print(">> ");
                    scanner.next();
            }
        }
    }

    private void pageShowPessoasFisicas(Scanner scanner) {}

    private void pageShowPessoasJuridicas(Scanner scanner) {}

    private void pageRegisterPessoaFisica(Scanner scanner) {}

    private void pageRegisterPessoaJuridica(Scanner scanner) {}

    /**
     * Método para criar uma nova pessoa, tanto fisica quanto juridica.
     * @param name Tipo String - Nome da pessoa
     * @param surname Tipo String - Sobrenome da pessoa
     * @param cpf Tipo String - CPF da pessoa
     * @return Objeto PessoaFisica
     */
    private IPessoaFisica create(String name, String surname, String cpf) {
        return new PessoaFisica(name, surname, cpf);
    }

    /**
     * Método para criar uma nova pessoa, tanto fisica quanto juridica.
     * @param name Tipo String - Nome da pessoa
     * @param surname Tipo String - Sobrenome da pessoa
     * @param cnpj Tipo String - CNPJ da pessoa
     * @param companyName Tipo String - Nome da empresa da pessoa
     * @return Objeto PessoaJuridica
     */
    private IPessoaJuridica create(String name, String surname, String cnpj, String companyName) {
        return new PessoaJuridica(name, surname, cnpj, companyName);
    }

    /**
     * Método para adicionar pessoas fisicas a lista.
     * @param pessoaFisica Tipo Objeto PessoaFisica
     */
    private void add(IPessoaFisica pessoaFisica) {
        this.pessoasFisicas.add(pessoaFisica);
    }

    /**
     * Método para adicionar pessoas Juridicas a lista.
     * @param pessoaJuridica Tipo Objeto PessoaJuridica
     */
    private void add(IPessoaJuridica pessoaJuridica) {
        this.pessoasJuridicas.add((pessoaJuridica));
    }

    /**
     * Método para remover uma pessoa fisica da lista.
     * @param pessoaFisica Tipo Objeto PessoaFisica
     */
    private void remove(IPessoaFisica pessoaFisica) {
        this.pessoasFisicas.remove(pessoaFisica);
    }

    /**
     * Método para remover uma pessoa juridica da lista.
     * @param pessoaJuridica Tipo Objeto PessoaJuridica
     */
    private void remove(IPessoaJuridica pessoaJuridica) {
        this.pessoasJuridicas.remove(pessoaJuridica);
    }

    private void applicationStop() {
        System.exit(0);
    }
}