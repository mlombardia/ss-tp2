import FHP.ParticlesGenerator;
import Parser.CliParser;

public class App {
    public static void main(String[] args) {
        CliParser parser = new CliParser(args);
        ParticlesGenerator.generate();
    }
}
