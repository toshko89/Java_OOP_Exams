package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission {
    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        List<String> clone = new ArrayList<>();
        for (Explorer explorer : explorers) {
            while (explorer.canSearch()) {
                clone.addAll(state.getExhibits());
                for (String exhibit : clone) {
                    explorer.search();
                    explorer.getSuitcase().getExhibits().add(exhibit);
                    state.getExhibits().remove(exhibit);
                    if (!explorer.canSearch()) {
                        break;
                    }
                    if(state.getExhibits().size() == 0){
                        return;
                    }
                }
            }
        }
    }
}
