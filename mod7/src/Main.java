import br.com.csouza.Caderno;
import br.com.csouza.Pagina;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.printf("\nTítulo: %s\n%n%s", escrever().getNome(), escrever().lerTodasPaginas());
    }

    public static Caderno escrever() throws IOException {
        final Caderno caderno1 = new Caderno("O Pequeno Sonhador - Caique Souza", 10, 4);
        caderno1.escreverLinha(1, "Meu nome é Caique Souza");
        caderno1.escreverLinha(1, "e meu grande sonho é");
        caderno1.escreverLinha(1, "conseguir trabalhar como garoto de programa");
        caderno1.escreverLinha(1, "(programador).");

        caderno1.escreverLinha(2, "Escrevendo um pouquinho na página 2 agora");
        caderno1.escreverLinha(7, "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");

        return caderno1;
    }
}