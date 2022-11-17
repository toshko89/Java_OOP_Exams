package football_main.core;

import football_main.common.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private Controller controller;
    private BufferedReader reader;

    public EngineImpl() {
        this.controller = new ControllerImpl();//TODO implement first
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if (result.equals("Exit")) {
                    break;
                }
            } catch (NullPointerException | IllegalArgumentException | IllegalStateException | IOException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s+");

        Command command = Command.valueOf(tokens[0]);
        String result = null;
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        switch (command) {
            case AddField:
                result = addField(data);
                break;
            case DeliverySupplement:
                result = deliverySupplement(data);
                break;
            case SupplementForField:
                result = supplementForField(data);
                break;
            case AddPlayer:
                result = addPlayer(data);
                break;
            case DragPlayer:
                result = dragPlayer(data);
                break;
            case CalculateStrength:
                result = calculateStrength(data);
                break;
            case GetStatistics:
                result = getStatistics();
                break;
            case Exit:
                result = Command.Exit.name();
                break;
        }
        return result;
    }

    private String addField(String[] data) {
        String fieldType = data[0];
        String fieldName = data[1];
        return controller.addField(fieldType, fieldName);
    }

    private String deliverySupplement(String[] data) {
        String supplementType = data[0];
        return controller.deliverySupplement(supplementType);

    }

    private String supplementForField(String[] data) {
        String fieldName = data[0];
        String supplementType = data[1];
        return controller.supplementForField(fieldName, supplementType);

    }

    private String addPlayer(String[] data) {
        String fieldName = data[0];
        String playerType = data[1];
        String playerName = data[2];
        String playerNationality = data[3];
        int strength = Integer.parseInt(data[4]);
        return controller.addPlayer(fieldName, playerType, playerName, playerNationality, strength);

    }

    private String dragPlayer(String[] data) {
        return controller.dragPlayer(data[0]);

    }

    private String calculateStrength(String[] data) {
        return controller.calculateStrength(data[0]);

    }

    private String getStatistics() {
        return controller.getStatistics();

    }
}
