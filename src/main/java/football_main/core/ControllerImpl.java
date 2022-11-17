package football_main.core;


import football_main.entities.field.ArtificialTurf;
import football_main.entities.field.Field;
import football_main.entities.field.NaturalGrass;
import football_main.entities.player.Men;
import football_main.entities.player.Player;
import football_main.entities.player.Women;
import football_main.entities.supplement.Liquid;
import football_main.entities.supplement.Powdered;
import football_main.entities.supplement.Supplement;
import football_main.repositories.SupplementRepository;
import football_main.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

import static football_main.common.ConstantMessages.*;
import static football_main.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private SupplementRepository supplement = new SupplementRepositoryImpl();
    private Collection<Field> fields = new ArrayList<>();

    @Override
    public String addField(String fieldType, String fieldName) {
        switch (fieldType) {
            case "ArtificialTurf":
                Field field = new ArtificialTurf(fieldName);
                fields.add(field);
                return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
            case "NaturalGrass":
                Field field1 = new NaturalGrass(fieldName);
                fields.add(field1);
                return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
            default:
                throw new NullPointerException(INVALID_FIELD_TYPE);
        }
    }

    @Override
    public String deliverySupplement(String type) {
        switch (type) {
            case "Powdered":
                Supplement supplement1 = new Powdered();
                supplement.add(supplement1);
                return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
            case "Liquid":
                Supplement supplement2 = new Liquid();
                supplement.add(supplement2);
                return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
            default:
                throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Field field = fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
        Supplement supplement1 = supplement.findByType(supplementType);
        if (supplement1 == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }
        field.addSupplement(supplement1);
        supplement.remove(supplement1);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {

        Field field = fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst().orElse(null);

        switch (playerType) {
            case "Men":
                Player player = new Men(playerName, nationality, strength);
                if (field != null && field.getClass().getSimpleName().equals("NaturalGrass")) {
                    field.addPlayer(player);
                    return String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
                }
                return String.format(FIELD_NOT_SUITABLE);
            case "Women":
                Player women = new Women(playerName, nationality, strength);
                if (field != null && field.getClass().getSimpleName().equals("ArtificialTurf")) {
                    field.addPlayer(women);
                    return String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
                }
                return String.format(FIELD_NOT_SUITABLE);
            default:
                throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst().orElse(null);
        if (field != null) {
            field.drag();
        }

        int size = field.getPlayers().size();
        return String.format(PLAYER_DRAG, size);
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst().orElse(null);
        int sumStr = 0;
        if (field != null) {
            for (Player player : field.getPlayers()) {
                sumStr += player.getStrength();
            }
        }
        return String.format(STRENGTH_FIELD, fieldName, sumStr);
    }

    @Override
    public String getStatistics() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : fields) {
            stringBuilder.append(field.getInfo()).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
