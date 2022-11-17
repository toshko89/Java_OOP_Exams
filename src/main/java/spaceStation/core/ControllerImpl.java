package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private AstronautRepository astronautRepository = new AstronautRepository();
    private PlanetRepository planetRepository = new PlanetRepository();

    @Override
    public String addAstronaut(String type, String astronautName) {
        switch (type) {
            case "Biologist":
                Biologist biologist = new Biologist(astronautName);
                astronautRepository.add(biologist);
                return String.format(ASTRONAUT_ADDED, type, astronautName);
            case "Geodesist":
                Geodesist geodesist = new Geodesist(astronautName);
                astronautRepository.add(geodesist);
                return String.format(ASTRONAUT_ADDED, type, astronautName);
            case "Meteorologist":
                Meteorologist meteorologist = new Meteorologist(astronautName);
                astronautRepository.add(meteorologist);
                return String.format(ASTRONAUT_ADDED, type, astronautName);
            default:
                throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }

    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        planet.getItems().addAll(Arrays.stream(items).collect(Collectors.toList()));
        planetRepository.add(planet);
        return String.format(PLANET_ADDED, planetName);
    }

    private int retired = 0;

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut byName = astronautRepository.findByName(astronautName);
        if (byName == null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        boolean remove = astronautRepository.remove(byName);
        retired++;
        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    private int exploredPlanetsCount = 0;

    @Override
    public String explorePlanet(String planetName) {
        Planet planet = planetRepository.findByName(planetName);
        List<Astronaut> astronautsWithOxigen60 = astronautRepository
                .getModels().stream().filter(astronaut -> astronaut.getOxygen() > 60)
                .collect(Collectors.toList());
        if (astronautsWithOxigen60.size() == 0) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        Mission mission = new MissionImpl();
        mission.explore(planet, astronautsWithOxigen60);
        exploredPlanetsCount++;
        List<Astronaut> astronautList = astronautsWithOxigen60.stream().filter(a -> a.getOxygen() == 0).collect(Collectors.toList());
        return String.format(PLANET_EXPLORED, planetName, astronautList.size());
    }

    @Override
    public String report() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(REPORT_PLANET_EXPLORED, exploredPlanetsCount))
                .append(System.lineSeparator())
                .append(REPORT_ASTRONAUT_INFO)
                .append(System.lineSeparator());

        for (Astronaut astronaut : astronautRepository.getModels()) {

            String bags = astronaut.getBag().getItems().size() > 0
                    ? astronaut.getBag().getItems().stream().collect(Collectors.joining(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER))
                    : "none";
            stringBuilder.append(String.format(REPORT_ASTRONAUT_NAME, astronaut.getName()))
                    .append(System.lineSeparator())
                    .append(String.format(REPORT_ASTRONAUT_OXYGEN, astronaut.getOxygen()))
                    .append(System.lineSeparator())
                    .append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, bags))
                    .append(System.lineSeparator());

        }

        return stringBuilder.toString().trim();
    }
}
