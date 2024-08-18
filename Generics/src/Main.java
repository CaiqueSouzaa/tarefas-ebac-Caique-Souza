import br.com.csouza.concrets.vehicles.airplanes.Boeing737800;
import br.com.csouza.concrets.vehicles.airplanes.CitationCJ4;
import br.com.csouza.concrets.vehicles.airplanes.GulfstreamG550;
import br.com.csouza.concrets.vehicles.boats.Azimut55Flybridge;
import br.com.csouza.concrets.vehicles.boats.PrincessV65;
import br.com.csouza.concrets.vehicles.boats.SunseekerPredator74;
import br.com.csouza.concrets.vehicles.cars.Ferrari488Pista;
import br.com.csouza.concrets.vehicles.cars.FordF150Raptor;
import br.com.csouza.concrets.vehicles.cars.TeslaModelSPlaid;
import br.com.csouza.concrets.vehicles.motorcycles.PanigaleV4;
import br.com.csouza.concrets.vehicles.motorcycles.R1250GSAdventure;
import br.com.csouza.concrets.vehicles.motorcycles.RoadKing;
import br.com.csouza.concrets.vehicles.trucks.Actros;
import br.com.csouza.concrets.vehicles.trucks.ScaniaR500;
import br.com.csouza.concrets.vehicles.trucks.VolvoFH16;
import br.com.csouza.dao.*;

public class Main {
    public static void main(String[] args) {

        // Aviões
        System.out.println("Aviões");
        AirplaneDAO airplaneDAO = new AirplaneDAO();

        airplaneDAO.store(new Boeing737800());
        airplaneDAO.store(new CitationCJ4());
        airplaneDAO.store(new GulfstreamG550());

        System.out.println(airplaneDAO.index());
        System.out.println(airplaneDAO.show(2));

        // Barcos
        System.out.println("\nBarcos");
        BoatDAO boatDAO = new BoatDAO();

        boatDAO.store(new Azimut55Flybridge());
        boatDAO.store(new PrincessV65());
        boatDAO.store(new SunseekerPredator74());

        System.out.println(boatDAO.index());
        System.out.println(boatDAO.show(2));

        // Carros
        System.out.println("\nCarros");
        CarDAO carDAO = new CarDAO();

        carDAO.store(new Ferrari488Pista());
        carDAO.store(new FordF150Raptor());
        carDAO.store(new TeslaModelSPlaid());

        System.out.println(carDAO.index());
        System.out.println(carDAO.show(2));

        // Motos
        System.out.println("\nMotos");
        MotorcycleDAO motorcycleDAO = new MotorcycleDAO();

        motorcycleDAO.store(new PanigaleV4());
        motorcycleDAO.store(new R1250GSAdventure());
        motorcycleDAO.store(new RoadKing());

        System.out.println(motorcycleDAO.index());
        System.out.println(motorcycleDAO.show(2));

        // Caminhões
        System.out.println("\nCaminhões");
        TruckDAO truckDAO = new TruckDAO();

        truckDAO.store(new Actros());
        truckDAO.store(new ScaniaR500());
        truckDAO.store(new VolvoFH16());

        System.out.println(truckDAO.index());
        System.out.println(truckDAO.show(2));
    }
}
