package glacialExpedition_test.core;
import glacialExpedition.core.Controller;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.Repository;
import glacialExpedition.repositories.StateRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository<State> stateRepository;
    private Repository<Explorer> explorerRepository;
    private int statesExplored = 0;

    public ControllerImpl() {
        this.stateRepository = new StateRepository();
        this.explorerRepository = new ExplorerRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;
        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }
        explorerRepository.add(explorer);
        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        for (String exhibit : exhibits) {
            state.getExhibits().add(exhibit);
        }
        stateRepository.add(state);
        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        if (explorer == null) {
            throw new IllegalArgumentException(String
                    .format(EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        explorerRepository.remove(explorer);
        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {

        Mission mission = new MissionImpl();
        State state = stateRepository.byName(stateName);
        Collection<Explorer> explorersSuitable = explorerRepository.getCollection()
                .stream().filter(e -> e.getEnergy() > 50)
                .collect(Collectors.toList());
        if (explorersSuitable.isEmpty()) {
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }
        mission.explore(state, explorersSuitable);
        int retiredExplorers = (int) explorersSuitable.stream()
                .filter(e -> e.getEnergy() == 0)
                .count();
        statesExplored++;
        return String.format(STATE_EXPLORER, stateName, retiredExplorers);
    }

    @Override
    public String finalResult() {
        String allExplorersData = explorerRepository.getCollection()
                .stream().map(e-> e.toString())
                .collect(Collectors.joining("\n"));
        return String.format("%d states were explored.%nInformation for the explorers:%n%s",
                statesExplored, allExplorersData);
    }
}
