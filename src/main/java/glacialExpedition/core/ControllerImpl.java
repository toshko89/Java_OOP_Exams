package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.Repository;
import glacialExpedition.repositories.StateRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    Repository<Explorer> explorerRepository = new ExplorerRepository();
    Repository<State> stateRepository = new StateRepository();

    @Override
    public String addExplorer(String type, String explorerName) {
        switch (type) {
            case "NaturalExplorer":
                Explorer naturalExplorer = new NaturalExplorer(explorerName);
                explorerRepository.add(naturalExplorer);
                return String.format(EXPLORER_ADDED, type, explorerName);
            case "GlacierExplorer":
                Explorer GlacierExplorer = new GlacierExplorer(explorerName);
                explorerRepository.add(GlacierExplorer);
                return String.format(EXPLORER_ADDED, type, explorerName);
            case "AnimalExplorer":
                Explorer AnimalExplorer = new AnimalExplorer(explorerName);
                explorerRepository.add(AnimalExplorer);
                return String.format(EXPLORER_ADDED, type, explorerName);
            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        List<String> collect = Arrays.stream(exhibits).collect(Collectors.toList());
        state.getExhibits().addAll(collect);
        stateRepository.add(state);
        return String.format(STATE_ADDED, stateName);
    }

    private int retiredExp = 0;

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        if (explorer == null) {
            throw new IllegalArgumentException(EXPLORER_DOES_NOT_EXIST);
        }
        boolean remove = explorerRepository.remove(explorer);
        if (remove) {
            retiredExp++;
            return String.format(EXPLORER_RETIRED, explorerName);
        }
        return "!!!";
    }

    private int countStates = 0;

    @Override
    public String exploreState(String stateName) {
        MissionImpl mission = new MissionImpl();
        State state = stateRepository.byName(stateName);
        retiredExp = 0;
        List<Explorer> explorerList = explorerRepository.getCollection()
                .stream()
                .filter(explorer -> explorer.getEnergy() > 50)
                .collect(Collectors.toList());

        if (explorerList.size() == 0) {
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }
        mission.explore(state, explorerList);
        countStates++;
        return String.format(STATE_EXPLORER, stateName, retiredExp);
    }

    @Override
    public String finalResult() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(FINAL_STATE_EXPLORED, countStates))
                .append(System.lineSeparator())
                .append(FINAL_EXPLORER_INFO)
                .append(System.lineSeparator());

        for (Explorer explorer : explorerRepository.getCollection()) {
            String exhibits = explorer.getSuitcase().getExhibits().size() > 0
                    ? explorer.getSuitcase().getExhibits()
                    .stream().collect(Collectors.joining(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER))
                    : "None";
            stringBuilder.append(String.format(FINAL_EXPLORER_NAME, explorer.getName()))
                    .append(System.lineSeparator())
                    .append(String.format(FINAL_EXPLORER_ENERGY, explorer.getEnergy()))
                    .append(System.lineSeparator())
                    .append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, exhibits))
                    .append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();

    }
}
