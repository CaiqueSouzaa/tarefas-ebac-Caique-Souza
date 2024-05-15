package br.com.csouza;

import java.io.IOException;
import java.util.ArrayList;

/**
 * O objeto "Caderno" permite criar cadernos, páginas, preencher as páginas com informações e ler as informações.
 * @see Pagina
 *
 * @author caique.souza
 * @version 1.0
 */
public class Caderno {
    private String nome;
    private int maxLinhasPagina = 10;
    private int quantPaginas;
    private ArrayList<Pagina> objPaginas = new ArrayList<Pagina>();

    /**
     * Método construtor da classe "Caderno".
     * @param nome Nome do caderno.
     * <br/>
     * Cada objeto do tipo "Caderno" deve possuir um nome.
     * <br/>
     * Exemplo:
     * <div style="color: purple">
     *      A história de Lorem Ipsum
     * </div>
     * @param quantPaginas Número inteiro para definição total de páginas.
     * <br/>
     * @param  maxLinhasPagina Número inteiro para definição total de linhas que as páginas podem ter.
     */
    public Caderno(String nome, int quantPaginas, int maxLinhasPagina) {
        this.nome = nome;
        this.maxLinhasPagina = maxLinhasPagina;
        this.quantPaginas = quantPaginas;
        this.criarPaginas();
    }

    /**
     * Método para atualizar o nome do caderno caso necessário.
     * @param nome String novoNome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método para obter o nome do caderno.
     * @return String nome
     * <br/>
     * Exemplo: <span style="color: purple">A história de Lorem Ipsum</span>
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Método para retornar o total de páginas que o caderno possui.
     * @return int quatPaginas
     * <br/>
     * Exemplo: <span style="color: purple">10</span>
     */
    public int getQuantPaginas() {
        return this.quantPaginas;
    }

    /**
     * Método para retornar um array contendo todos os objetos "Pagina" presente ao caderno. Esse método é util para o caso de precisar manipular os objetos "Pagina".
     * @return ArrayList<Pagina> objPaginas
     * <br/>
     * Exemplo: <span style="color: purple">[Pagina1, Pagina2, Pagina3]</span>
     */
    public ArrayList<Pagina> getObjPaginas() {
        return this.objPaginas;
    }

    /**
     * <span style="color: red">Atenção! Este método é chamado somente no construtor!</span>
     * Método inicial responsável por criar as páginas no caderno.
     */
    private void criarPaginas() {
        for (int i = 0; i <= this.quantPaginas; i++) {
            Pagina pagina = new Pagina((i + 1), this.maxLinhasPagina);
            this.objPaginas.add(pagina);
        }
    }

    /**
     * Método para realizar a inserção de informações nas páginas do caderno.
     * @param pagina Número inteiro representando a página a ser escrita.
     * <br/>
     * @param texto String - Texto a ser escrito.
     * @throws IOException Veja {@link br.com.csouza.Pagina#adicionarNovaLinha(String)}
     */
    public void escreverLinha(int pagina, String texto) throws  IOException {
        if (this.checarPaginaExiste(pagina)) {
            this.paginaNaoExiste(pagina);
        }

        this.objPaginas.get(pagina).adicionarNovaLinha(texto);
    }

    /**
     * Método para realizar a leitura de uma página em especifica.
     * @param pagina Número inteiro representando a página a ser lida.
     * @return String texto - Texto da contido na página.
     * @throws IOException Veja {@link Pagina#lerPagina()}
     */
    public String lerUmaPagina(int pagina) throws IOException {
        if (this.checarPaginaExiste(pagina)) {
            this.paginaNaoExiste(pagina);
        }
        return String.format("------ Página %s ------\n%s", String.valueOf(pagina), this.objPaginas.get(pagina).lerPagina());
    }

    /**
     * Método para realizar a leitura de uma página em especifica.
     *
     * @return String texto - Texto da contido das páginas.
     * @throws IOException Veja {@link br.com.csouza.Caderno#lerUmaPagina(int)}
     */
    public String lerTodasPaginas() throws IOException {
        String caderno = "";
        for (int i = 1; i <= this.getQuantPaginas(); i++) {
            caderno += this.lerUmaPagina(i) + "\n";
        }

        return caderno;
    }

    /**
     * Método para verificar se uma página existe ou não, retornando "<span style="color: green">true</span>" para o caso da página existir e "<span style="color: red">false</span>" para o caso de não existir.
     * @param pagina Número inteiro representando a página a ser verificada.
     * @return boolean result - Resultado contendo o valor "true" para o caso da página existir e "false" para o caso de não existir.
     */
    public boolean checarPaginaExiste(int pagina) {
        return pagina > this.getQuantPaginas() || pagina <= 0;
    }

    /**
     * Método privado.
     * Método para retornar um "throw" configurado.
     * @param pagina Número inteiro representando a página que não existe.
     * @throws IOException Retornar um "throw" informando que a página não existe.
     * <br/>
     * Exemplo: "<span style="color: red">A página 8 não existe</span>"
     */
    private void paginaNaoExiste(int pagina) throws IOException {
        throw new IOException(String.format("A página %s não existe", String.valueOf(pagina)));
    }
}