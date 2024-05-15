import br.com.csouza.Caderno;
import br.com.csouza.Pagina;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final Caderno caderno1 = new Caderno("A história de Lorem Ipsum", 1400);
        final Pagina pagina1 = new Pagina(1,2);
        pagina1.adicionarNovaLinha("Caique Souza");
        try {
            pagina1.adicionarNovaLinha("É um homem que busca se tornar programador");
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
        System.out.println(pagina1.lerPagina());
    }
}