public abstract class Vehicle {
    private String model;
    private Engine engine = new EngineV2();

    public Vehicle(String model) {
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void powerOn() {
        if (this.engine == null) {
            System.out.printf("O veículo %s não possuí um motor", this.model);
        } else {
            this.engine.engine(this);
        }
    }
}
