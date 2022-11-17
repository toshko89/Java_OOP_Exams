package football_main.entities.field;

import football_main.entities.player.Player;
import football_main.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;

import static football_main.common.ConstantMessages.NOT_ENOUGH_CAPACITY;
import static football_main.common.ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY;

public abstract class BaseField implements Field {

    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;


    protected BaseField(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    @Override
    public int sumEnergy() {
        return supplements.stream().mapToInt(Supplement::getEnergy).sum();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(FIELD_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public void addPlayer(Player player) {
        if (capacity < this.players.size() ) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        this.players.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        this.players.stream()
                .filter(p -> p.equals(player))
                .findFirst()
                .ifPresent(value -> this.players.remove(value));
//        if (player1 != null) {
//            this.players.remove(player1);
//        }
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void drag() {
        for (Player player : this.players) {
            player.stimulation();
        }
    }

//    @Override
//    public String getInfo() {
////        StringBuilder stringBuilder = new StringBuilder();
////        stringBuilder.append(String.format("%s (%s): ",this.name,).append(System.lineSeparator());
//    }

    @Override
    public Collection<Player> getPlayers() {
        return players;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public String getName() {
        return name;
    }
}
