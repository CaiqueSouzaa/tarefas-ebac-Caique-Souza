package br.com.csouza;

public class Caderno {
    private String codigo;
    private String nome;
    private int paginas;
    private final int maximoCaracterPagina = 500;

    /**
     * Método construtor da classe Caderno.
     * @param nome Nome do caderno.
     * <br/>
     * Cada objeto do tipo "Caderno" deve possuir um nome.
     * <br/>
     * Exemplo:
     * <div style="color: purple">
     *      A história de Lorem Ipsum
     * </div>
     * @param paginas Número total de páginas
     */
    public Caderno(String nome, int paginas) {
        this.nome = nome;
        this.paginas = paginas;
    }
}