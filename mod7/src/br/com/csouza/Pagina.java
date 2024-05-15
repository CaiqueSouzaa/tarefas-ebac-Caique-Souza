package br.com.csouza;

import java.io.IOException;
import java.util.ArrayList;

/**
 * O objeto "Pagina" foi desenvolvido para compor as páginas do objeto "Caderno".
 * @see Caderno
 *
 * @author caique.souza
 * @version 1.0
 */
public class Pagina {
    private int numeroPagina;
    private int maxLinhas;
    private ArrayList<String> linhas = new ArrayList<String>(this.maxLinhas);

    /**
     * Método construtor do objeto "Pagina".
     * @param numeroPagina A variável "numeroPagina" define o número da página.
     *                     <br/>
     * @param maxLinhas A variável "maxLinhas" define a quantia maxima de linhas que a página pode ter
     */
    public Pagina(int numeroPagina, int maxLinhas) {
        this.numeroPagina = numeroPagina;
        this.maxLinhas = maxLinhas;
    }

    /**
     * Método para atualizar o número da página caso necessário.
     * @param numeroPagina
     */
    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    /**
     * Método para atualizar a quantia máxima de linhas da página caso necessário.
     * @param maxLinhas
     */
    public void setMaxLinhas(int maxLinhas) {
        this.maxLinhas = maxLinhas;
    }

    /**
     * Método para obter o número da página.
     * @return int numeroPagina - Número inteiro contendo o número da página.
     */
    public int getNumeroPagina() {
        return numeroPagina;
    }

    /**
     * Método para obter a quatia total de linhas que a página suporta
     * @return
     */
    public int getMaxLinhas() {
        return this.maxLinhas;
    }

    /**
     * Método para obter a quantia total de linhas em uso.
     * @return int usoTotal - Número inteiro informando o total de linhas em uso.
     */
    public int getTotalLinhas() {
        return this.linhas.size();
    }

    /**
     * Método para adicionar uma nova linha a página.
     * @param linha String - Novo texto a ser inserido na página.
     * @throws IOException Será lançado um "throw" caso tente adicionar uma nova linha e o limite de "maxLinhas" já tiver sido atingido.
     */
    public void adicionarNovaLinha(String linha) throws IOException {
        if (this.getTotalLinhas() >= this.maxLinhas) {
            throw new IOException("Não foi possivel adicionar uma nova linha. Quantia maxima de linhas atingida.");
        } else this.linhas.add(linha);
    }

    /**
     * Método para retornar o array do texto presente na página.
     * @return ArrayList<String> arrayLinha - ArrayList<String> contendo as linhas da página.
     */
    public ArrayList<String> getLinhas() {
        return this.linhas;
    }

    /**
     * Método para obter uma linha especifica da página.
     * @param numeroLinha Número inteiro informando a linha a ser retornada. Utilize o padrão de "index", 0, 1, 2, ..., para navegar entre as linhas.
     * @return String linha - String contendo a linha solicitada.
     * @throws IOException Será lançado um "throw" caso busque por uma linha que não exista na página.
     */
    public String obterUmaLinha(int numeroLinha) throws IOException {
        if (numeroLinha > this.getTotalLinhas()) {
            throw new IOException(String.format("A linha %s não existe", numeroLinha));
        }
        return this.linhas.get(numeroLinha);
    }

    /**
     * Método para obter as páginas em formato de página, e não em formato de array.
     * @return String pagina - String com quebras de texto para simular uma página.
     * @throws IOException Veja {@link br.com.csouza.Pagina#obterUmaLinha(int)}
     */
    public String lerPagina() throws IOException {
        String pagina = "";
        for (int i = 0; i < this.getTotalLinhas(); i ++) {
            try {
                pagina += this.obterUmaLinha(i) + "\n";
            } catch(Exception err) {
                System.out.print(err.getMessage());
            }
        }

        return pagina;
    }
}