package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;

public class OperationImpl implements Operation {

    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        Collection<String> exhibits = spot.getExhibits();
        for (Discoverer discoverer : discoverers) {
            if (discoverer.canDig()) {
                for (String exhibit : exhibits) {
                    discoverer.dig();
                    discoverer.getMuseum().getExhibits().add(exhibit);
                    exhibits.remove(exhibit);
                    if (!discoverer.canDig()) {
                        break;
                    }
                }
            }
        }
    }
}
