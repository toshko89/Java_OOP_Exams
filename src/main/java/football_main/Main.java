package football_main;

import football_main.core.Engine;
import football_main.core.EngineImpl;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
