import br.com.csouza.classes.abstracts.Car;
import br.com.csouza.classes.abstracts.Factory;
import br.com.csouza.classes.concretes.Client;
import br.com.csouza.classes.concretes.factories.WithContract;
import br.com.csouza.classes.concretes.factories.WithoutContract;

public class Main {
    public static void main(String[] args) {
        Client client = new Client("Caique", "Souza", false, 'B');

        Factory factory = getFactory(client);

        Car car = factory.buildCar(client);
        if (car != null) {
            car.start();
        }
    }

    private static Factory getFactory(Client client) {
        return client.isContract() ? new WithContract() : new WithoutContract();
    }
}
