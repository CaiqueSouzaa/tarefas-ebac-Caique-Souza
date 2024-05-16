package br.com.csouza;

import java.util.Scanner;

/**
 * @author caique.souza
 * @version 1.0
 */
public class App {
    private Integer value;
    private boolean execStatus = false;

    /**
     * Método para definir o valor a ser convertido para o tipo "Integer".
     * @param value int value - Número inteiro a ser convertido para "Integer".
     */
    public void setValue(int value) {
        System.out.println("Executando o [Casting] para adicionar o valor do tipo [int] a uma variável do tipo [Wrapper Integer].");
        this.value = value;
    }

    /**
     * Método para atualizar o valor de execStatus.
     * @param status boolean status
     */
    private void setExecStatus(boolean status) {
        this.execStatus = status;
    }

    /**
     * Método para retornar o valor convertido para o tipo "Integer".
     * @return Integer value - Valor convertido para o tipo "Integer".
     */
    public Integer getValue() {
        return this.value;
    }

    /**
     * Método para retornar o valor de "execStatus".
     * @return - boolean status - Valor informando se a aplicação executou com sucesso.
     */
    public boolean getExecStatus() {
        return this.execStatus;
    }

    /**
     * Método para inicializar a aplicação.
     */
    public void init() {
        this.logic();
        if (this.getExecStatus()) {
            showIntergerValue();
        }
    }

    /**
     * Método contendo a lógica da aplicação.
     */
    private void logic() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Digite um valor inteiro: ");
            int value = scanner.nextInt();
            this.setValue(value);
            scanner.close();
            this.setExecStatus(true);
        } catch (Exception err) {
            this.setExecStatus(false);
            System.out.println("Valor inválido! Digite somente números interos.\nTente novamente.");
            this.logic();
        }
    }

    /**
     * Método para exibir o valor convertido.
     */
    private void showIntergerValue() {
        System.out.println("Valor do tipo [int] convertido para o tipo [Wrapper Integer]: Integer " + this.getValue());
    }
}