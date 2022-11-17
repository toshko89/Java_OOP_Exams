package glacialExpedition.models.states;

import java.util.ArrayList;
import java.util.Collection;

import static glacialExpedition.common.ExceptionMessages.STATE_NAME_NULL_OR_EMPTY;

public class StateImpl implements State {

    private String name;
    private Collection<String> exhibits = new ArrayList<>();

    public StateImpl(String name) {
        setName(name);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(STATE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<String> getExhibits() {
        return exhibits;
    }

    @Override
    public String getName() {
        return name;
    }
}
