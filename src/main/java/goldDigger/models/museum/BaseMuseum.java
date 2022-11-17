package goldDigger.models.museum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BaseMuseum implements Museum {

    private Collection<String> exhibits = new ArrayList<>();

    @Override
    public Collection<String> getExhibits() {
        return exhibits;
    }

    public void setExhibits(String exhibit) {
        exhibits.add(exhibit);
    }
}
