package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;

public class MissionImpl implements Mission {
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        for (Astronaut astronaut : astronauts) {
            if (astronaut.canBreath()) {
                while (planet.getItems().size() != 0) {
                    String item = planet.getItems().stream().findFirst().get();
                    astronaut.getBag().getItems().add(item);
                    astronaut.breath();
                    planet.getItems().remove(item);
                    if (!astronaut.canBreath()) {
                        break;
                    }
                }

            }
        }
    }
}
