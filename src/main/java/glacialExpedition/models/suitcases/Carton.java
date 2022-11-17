package glacialExpedition.models.suitcases;

import java.util.ArrayList;
import java.util.Collection;

public class Carton implements Suitcase {

    private Collection<String> exhibits = new ArrayList<>();

    @Override
    public Collection<String> getExhibits() {
        return exhibits;
    }

}
