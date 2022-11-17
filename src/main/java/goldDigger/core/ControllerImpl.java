package goldDigger.core;

import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.Repository;
import goldDigger.repositories.SpotRepository;

import java.util.List;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Discoverer> discovererRepository;
    private Repository<Spot> spotRepository;
    private int spotCount;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {

        switch (kind) {
            case "Archaeologist":
                Archaeologist archaeologist = new Archaeologist(discovererName);
                discovererRepository.add(archaeologist);
                return String.format(DISCOVERER_ADDED, kind, discovererName);
            case "Geologist":
                Geologist geologist = new Geologist(discovererName);
                discovererRepository.add(geologist);
                return String.format(DISCOVERER_ADDED, kind, discovererName);
            case "Anthropologist":
                Anthropologist anthropologist = new Anthropologist(discovererName);
                discovererRepository.add(anthropologist);
                return String.format(DISCOVERER_ADDED, kind, discovererName);
            default:
                throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        SpotImpl spot = new SpotImpl(spotName);
        spot.add(exhibits);
        spotRepository.add(spot);
        return String.format(SPOT_ADDED, spotName);
    }

    private static int removed = 0;

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer byName = discovererRepository.byName(discovererName);
        boolean remove = discovererRepository.remove(byName);
        if (remove) {
            removed++;
            return String.format(DISCOVERER_EXCLUDE, discovererName);
        }

        throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST, discovererName));
    }

    @Override
    public String inspectSpot(String spotName) {
        int count = 0;
//        List<Discoverer> discovererList = discovererRepository
//                .getDiscoverers()
//                .stream()
//                .filter(discoverer -> discoverer.getEnergy() > 45)
//                .collect(Collectors.toList());
//        operation.startOperation((Spot) spotRepository.byName(spotName), discovererList);
        for (Discoverer discoverer : this.discovererRepository.getCollection()) {
            if (discoverer.getEnergy() > 45) {
                count++;
            }
        }
        if (count == 0) {
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }else {
            return String.format(INSPECT_SPOT, spotName, removed);
        }

    }

    @Override
    public String getStatistics() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(FINAL_SPOT_INSPECT, spotRepository.getCollection().size())).append(System.lineSeparator());
        stringBuilder.append(String.format(FINAL_DISCOVERER_INFO)).append(System.lineSeparator());

        for (Discoverer discoverer : this.discovererRepository.getCollection()) {
            stringBuilder
                    .append(String.format(FINAL_DISCOVERER_NAME, discoverer.getName()))
                    .append(System.lineSeparator())
                    .append(String.format(FINAL_DISCOVERER_ENERGY, discoverer.getEnergy()))
                    .append(System.lineSeparator());

            if (discoverer.getMuseum().getExhibits().size() > 0) {
                stringBuilder.append("Museum exhibits: ").append(discoverer.getMuseum().getExhibits().stream().collect(Collectors.joining(", "))).append(System.lineSeparator());
            } else {
                stringBuilder.append("Museum exhibits: None").append(System.lineSeparator());
            }
        }

        return stringBuilder.toString().trim();
    }
}
