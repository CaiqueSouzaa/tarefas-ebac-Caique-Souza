import br.com.csouza.Nota;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // ArrayList contendo as notas do aluno.
        final ArrayList<Double> notas = new ArrayList<Double>(Arrays.asList(3.0, 10.0, 5.5, 8.5));

        // Criando o objeto "Nota" e passando as notas do aluno.
        Nota nota = new Nota(notas);

        // Definindo o valor meta de aprovação
        nota.setMeta(7.0);

        // Obtendo a média.
        System.out.printf("Média: %s.\n", nota.getMedia());

        // Retornando se o aluno foi aprovado ou reprovado.
        System.out.println("Status: " + nota.checkAprovado());
    }
}