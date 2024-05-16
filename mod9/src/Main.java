import br.com.csouza.App;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.init();

        final Integer value = app.getValue();
        System.out.printf("O número informado possui [%s] caracteres.", value.toString().length());
    }
}