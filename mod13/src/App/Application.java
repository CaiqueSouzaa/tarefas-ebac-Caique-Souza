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
                System.out.print(">> ");
                number = scanner.nextInt();
                isValid = true;
            } catch (Exception err) {
                System.out.println("Input não válido, tente novamente. Insira somente números inteiros.");
                scanner.next();
            }
        }

        return number;
    }

    private void logic(Scanner scanner) {
        while (true) {
            System.out.print("-------------------------\n[ Início ]\n-------------------------\nDigite o número da ação a ser executada:\n1 - Registros\n2 - Finalizar aplicação\n");
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
            System.out.println("-------------------------\n[ Registros ]\n-------------------------\nDigite o número da ação a ser executada:\n1 - Visualizar\n2 - Registrar\n3 - Voltar\n4 - Finalizar aplicação");
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
                    System.out.print(">> ");
                    scanner.next();
            }
        }
    }

    private void registerOption1(Scanner scanner) {
        while (true) {
            System.out.println("-------------------------\n[ Visualizar Registros ]\n-------------------------\nDigite o número da ação a ser executada:\n1 - Visualizar pessoas fisicas\n2 - Visualizar pessoas juridicas\n3 - Voltar\n4 - Finalizar aplicação");
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
            System.out.println("-------------------------\n[ Registrar ]\n-------------------------\nDigite o número da ação a ser executada:\n1 - Registrar pessoa fisica\n2 - Registrar pessoa juridica\n3 - Voltar\n4 - Finalizar aplicação");
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

    private void pageShowPessoasFisicas(Scanner scanner) {
        if (this.pessoasFisicas.isEmpty()) {
            System.out.println("-------------------------\n--- [ Não há registros de pessoas fisicas ] ---");
        } else {
            System.out.println("--- [ Pessoas fisicas ] ---");
            for (IPessoaFisica pessoa : this.pessoasFisicas) {
                System.out.println("|> " + pessoa.getName() + ' ' + pessoa.getSurname());
            }
        }
    }

    private void pageShowPessoasJuridicas(Scanner scanner) {
        if (this.pessoasJuridicas.isEmpty()) {
            System.out.println("-------------------------\n--- [ Não há registros de pessoas juridicas ] ---");
        } else {
            System.out.println("--- [ Pessoas juridicas ] ---");
            for (IPessoaJuridica pessoa : this.pessoasJuridicas) {
                System.out.println("|> " + pessoa.getName() + ' ' + pessoa.getSurname() + " - " + pessoa.getCompanyName());
            }
        }
    }

    private void pageRegisterPessoaFisica(Scanner scanner) {
        String name = "";
        String surname = "";
        String cpf = "";

        // Obtendo o nome da pessoa
        System.out.print("Nome: ");
        while (name.isEmpty()) {
            name = scanner.nextLine();
        }

        // Obtendo o sobrenome da pessoa
        System.out.print("Sobrenome: ");
        while (surname.isEmpty()) {
            surname = scanner.nextLine();
        }

        // Obtendo o cpf da pessoa
        System.out.print("CPF: ");
        while (cpf.isEmpty()) {
            cpf = scanner.nextLine();
        }

        // Registrando a pessoa
        this.add(new PessoaFisica(name, surname, cpf));

        System.out.println("--- [ Pessoa registrada com sucesso! ] ---\n" + "|> " + name + ' ' + surname);
    }

    private void pageRegisterPessoaJuridica(Scanner scanner) {
        String name = "";
        String surname = "";
        String cnpj = "";
        String companyName = "";

        // Obtendo o nome da pessoa
        System.out.print("Nome: ");
        while (name.isEmpty()) {
            name = scanner.nextLine();
        }

        // Obtendo o sobrenome da pessoa
        System.out.print("Sobrenome: ");
        while (surname.isEmpty()) {
            surname = scanner.nextLine();
        }

        // Obtendo o cpf da pessoa
        System.out.print("CNPJ: ");
        while (cnpj.isEmpty()) {
            cnpj = scanner.nextLine();
        }

        // Obtendo o nome da empresa
        System.out.print("Nome da empresa: ");
        while (companyName.isEmpty()) {
            companyName = scanner.nextLine();
        }

        // Registrando a pessoa
        this.add(new PessoaJuridica(name, surname, cnpj, companyName));

        System.out.println("--- [ Pessoa registrada com sucesso! ] ---\n" + "|> " + name + ' ' + surname + " - " + companyName);

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

    private void applicationStop() {
        System.exit(0);
    }
}