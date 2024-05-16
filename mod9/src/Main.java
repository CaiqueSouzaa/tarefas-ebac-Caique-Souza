import br.com.csouza.App;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.init();

        final Integer value = app.getValue();
        final int length = value.toString().length();
        System.out.printf("O número informado possui [%s] %s.", value.toString().length(), length > 1 ? "caracteres" : "caractere");
    }
}