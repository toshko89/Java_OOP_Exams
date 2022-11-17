package football_main.entities.field;

import football_main.entities.player.Player;

import java.util.stream.Collectors;

public class ArtificialTurf extends BaseField {

    private static final int CAPACITY = 150;

    public ArtificialTurf(String name) {
        super(name, CAPACITY);
    }

    @Override
    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s (%s):", super.getName(), getClass().getSimpleName()))
                .append(System.lineSeparator());
        if (this.getPlayers().size() <= 0) {
            stringBuilder.append("Player: none").append(System.lineSeparator());
        } else {
            stringBuilder.append("Player: ");
//            for (Player player : this.getPlayers()) {
//                stringBuilder.append(player.getName()).append(" ");
//            }
            String collect = this.getPlayers().stream()
                    .map(Player::getName).collect(Collectors.joining(" "));
            stringBuilder.append(collect).append(System.lineSeparator());
        }
        stringBuilder.append("Supplement: ").append(this.getSupplements().size()).append(System.lineSeparator());
        stringBuilder.append("Energy: ").append(this.sumEnergy());
        return stringBuilder.toString().trim();
    }
}
