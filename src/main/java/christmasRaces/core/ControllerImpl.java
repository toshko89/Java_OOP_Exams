package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Car> carRepository;

    private Repository<Driver> driverRepository;

    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        Driver newDriver = new DriverImpl(driver);
        Driver driverExists = driverRepository
                .getAll().stream()
                .filter(driver1 -> driver1.getName().equals(driver))
                .findFirst().orElse(null);
        if (driverExists != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }
        driverRepository.add(newDriver);
        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        switch (type) {
            case "Muscle":
                Car newCar = new MuscleCar(model, horsePower);
                Car carExists = carRepository.getAll().stream()
                        .filter(car -> car.getModel().equals(model)).findFirst().orElse(null);
                if (carExists != null) {
                    throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
                }
                carRepository.add(newCar);
                return String.format(CAR_CREATED, "MuscleCar", model);
            case "Sports":
                Car newCar2 = new SportsCar(model, horsePower);
                Car carExists2 = carRepository.getAll().stream()
                        .filter(car -> car.getModel().equals(model)).findFirst().orElse(null);
                if (carExists2 != null) {
                    throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
                }
                carRepository.add(newCar2);
                return String.format(CAR_CREATED, "SportsCar", model);
            default:
                return "";
        }
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        Car car = carRepository.getByName(carModel);
        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        int laps = race.getLaps();
//        List<Double> collect =
//                race.getDrivers().stream()
//                        .map(driver -> driver.getCar().calculateRacePoints(laps))
//                        .sorted()
//                        .collect(Collectors.toList());

        List<Driver> participants =
                race.getDrivers().stream()
                        .filter(Driver::getCanParticipate)
                        .collect(Collectors.toList());

        if (participants.size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        Collections.sort(
                participants,
                (d1, d2) -> (int) (d2.getCar().calculateRacePoints(laps)
                        - d1.getCar().calculateRacePoints(laps)));

        StringBuilder stringBuilder = new StringBuilder();

        raceRepository.remove(race);
        return stringBuilder
                .append(String.format(DRIVER_FIRST_POSITION, participants.get(0).getName(), raceName))
                .append(System.lineSeparator())
                .append(String.format(DRIVER_SECOND_POSITION, participants.get(1).getName(), raceName))
                .append(System.lineSeparator())
                .append(String.format(DRIVER_THIRD_POSITION, participants.get(2).getName(), raceName))
                .toString().trim();

    }

    @Override
    public String createRace(String name, int laps) {
        Race race = new RaceImpl(name, laps);
        Race byName = raceRepository.getByName(name);
        if (byName != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }
        raceRepository.add(race);
        return String.format(RACE_CREATED, name);
    }
}
