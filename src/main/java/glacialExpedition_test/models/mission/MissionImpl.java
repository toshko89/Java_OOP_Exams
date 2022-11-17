package glacialExpedition_test.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.states.State;

import java.util.ArrayDeque;
import java.util.Collection;

public class MissionImpl implements Mission {
    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        ArrayDeque<String> exhibits = new ArrayDeque<>(state.getExhibits());
        for (Explorer explorer : explorers) {
            while (!exhibits.isEmpty() && explorer.canSearch()) {
                String exhibit = exhibits.pop();
                explorer.getSuitcase().getExhibits().add(exhibit);
                explorer.search();
            }

        }
    }
}
