import FHP.FHPSimulationController;
import FHP.ParticlesGenerator;
import Parser.CliParser;

public class App {
    public static void main(String[] args) {
        new CliParser(args);
        FHPSimulationController.simulate();
    }
}
