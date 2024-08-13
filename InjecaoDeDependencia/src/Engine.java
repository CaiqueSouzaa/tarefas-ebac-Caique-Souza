public abstract class Engine {
    private String model;
    private float horsePower;

    public Engine(String model, float horsePower) {
        this.model = model;
        this.horsePower = horsePower;
    }

    public void engine(Vehicle vehicle) {
        System.out.printf("O motor do veículo %s agora está ligado e ele possui %s cavalos de potência.\n", vehicle.getModel(), this.horsePower);
    }
}
