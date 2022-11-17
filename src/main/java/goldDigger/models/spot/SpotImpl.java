package goldDigger.models.spot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static goldDigger.common.ExceptionMessages.SPOT_NAME_NULL_OR_EMPTY;

public class SpotImpl implements Spot {

    private String name;
    private List<String> exhibits;

    public SpotImpl(String name) {
        setName(name);
        this.exhibits = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(SPOT_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }


    public void add(String... spot) {
        this.exhibits.addAll(Arrays.asList(spot));
    }

    boolean remove(String spot) {
        return this.exhibits.remove(spot);
    }


    @Override
    public List<String> getExhibits() {
        return this.exhibits;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
