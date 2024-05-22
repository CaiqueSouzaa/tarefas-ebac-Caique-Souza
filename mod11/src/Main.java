import br.com.csouza.Tarefa01;
import br.com.csouza.Tarefa02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite '1' para acessar a tarefa #01 ou '2' para a tarefa #02: ");
        int option = option(scanner);

        if (option == 1) {
            Tarefa01 tarefa01 = new Tarefa01();
            tarefa01.init();
        } else if (option == 2) {
            Tarefa02 tarefa02 = new Tarefa02();
            tarefa02.init();
        }

        scanner.close();
    }

    private static int option(Scanner scanner) {
        int value = 1;
        boolean sucess = false;
        String text = "Larga mão de ser chato e digite os valores corretos!\nTente novamente: ";

        while(!sucess) {
            try {
                value = scanner.nextInt();
                scanner.nextLine();

                if (value < 1 || value > 2) {
                    System.out.print(text);
                } else {
                    sucess = true;
                }

            } catch (Exception err) {
                System.out.print(text);
                scanner.next();
            }
        }

        return value;
    }
}