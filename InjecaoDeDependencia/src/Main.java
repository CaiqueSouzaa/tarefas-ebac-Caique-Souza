public class Main {
    public static void main(String[] args) {
        Vehicle mt09  = new MT09();
        Vehicle teslaS = new TeslaModelS();

        mt09.setEngine(new EngineV1());
        mt09.powerOn();

        teslaS.setEngine(new EngineV2());
        teslaS.powerOn();
    }
}
