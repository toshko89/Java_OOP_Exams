package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private DecorationRepository decorations = new DecorationRepository();
    private Collection<Aquarium> aquariums = new ArrayList<>();

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        switch (aquariumType) {
            case "FreshwaterAquarium":
                Aquarium freshwateraquarium = new FreshwaterAquarium(aquariumName);
                aquariums.add(freshwateraquarium);
                return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
            case "SaltwaterAquarium":
                Aquarium saltwateraquarium = new SaltwaterAquarium(aquariumName);
                aquariums.add(saltwateraquarium);
                return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
            default:
                throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }
    }

    @Override
    public String addDecoration(String type) {
        switch (type) {
            case "Ornament":
                Decoration ornament = new Ornament();
                decorations.add(ornament);
                return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
            case "Plant":
                Decoration plant = new Plant();
                decorations.add(plant);
                return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
            default:
                throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Aquarium foundAquarium = aquariums.stream()
                .filter(aquarium -> aquarium.getName().equals(aquariumName))
                .findFirst().orElse(null);
        Decoration decorationsByType = decorations.findByType(decorationType);
        if (decorationsByType == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }
        foundAquarium.addDecoration(decorationsByType);
        decorations.remove(decorationsByType);
        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Aquarium foundAquarium = aquariums.stream()
                .filter(aquarium -> aquarium.getName().equals(aquariumName))
                .findFirst().orElse(null);

        String simpleName = foundAquarium.getClass().getSimpleName();
        switch (fishType) {
            case "FreshwaterFish":
                Fish freshwaterfish = new FreshwaterFish(fishName, fishSpecies, price);
                if (!"FreshwaterAquarium".equals(simpleName)) {
                    return String.format(WATER_NOT_SUITABLE);
                }
                foundAquarium.addFish(freshwaterfish);
                return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
            case "SaltwaterFish":
                Fish saltwaterfish = new SaltwaterFish(fishName, fishSpecies, price);
                if (!"SaltwaterAquarium".equals(simpleName)) {
                    return String.format(WATER_NOT_SUITABLE);
                }
                foundAquarium.addFish(saltwaterfish);
                return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
            default:
                throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium foundAquarium = aquariums.stream()
                .filter(aquarium -> aquarium.getName().equals(aquariumName))
                .findFirst().orElse(null);
        foundAquarium.feed();
        return String.format(FISH_FED, foundAquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium foundAquarium = aquariums.stream()
                .filter(aquarium -> aquarium.getName().equals(aquariumName))
                .findFirst().orElse(null);
        double sumDecorations = foundAquarium.getDecorations().stream()
                .mapToDouble(Decoration::getPrice).reduce(Double::sum).orElse(0d);
        double sumFish = foundAquarium.getFish().stream()
                .mapToDouble(Fish::getPrice).reduce(Double::sum).orElse(0d);
        double totalSum = sumDecorations + sumFish;
        return String.format(VALUE_AQUARIUM, aquariumName, totalSum);
    }

    @Override
    public String report() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Aquarium aquarium : aquariums) {
            stringBuilder.append(aquarium.getInfo()).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
