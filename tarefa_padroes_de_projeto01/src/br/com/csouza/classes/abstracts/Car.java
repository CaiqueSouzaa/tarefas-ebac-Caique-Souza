package br.com.csouza.classes.abstracts;

public abstract class Car {
    private String model;
    private String manufacturer;
    private boolean isClean;
    private float fuel;
    private boolean isEngine;

    public Car(String model, String manufacturer, boolean isClean, float fuel, boolean isEngine) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.isClean = isClean;
        this.fuel = fuel;
        this.isEngine = isEngine;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public boolean isClean() {
        return isClean;
    }

    public float getFuel() {
        return fuel;
    }

    public boolean isEngine() {
        return isEngine;
    }

    public void start() {
        if(!isEngine) {
            isEngine = true;
            System.out.printf("O carro %s agora está ligado\n", model);
        } else {
            System.out.printf("O carro já %s está ligado\n", model);
        }
    }

    public void clean() {
        if(!isClean) {
            isClean = true;
            System.out.printf("O carro %s agora está limpo\n", model);
        } else {
            System.out.printf("O carro já %s está limpo\n", model);
        }
    }

    public void fullTank() {
        if(fuel < 100.00f) {
            fuel = 100.00f;
            System.out.printf("O tanque do carro %s agora está cheio\n", model);
        } else {
            System.out.printf("O tanque do carro já %s está cheio\n", model);
        }
    }
}
