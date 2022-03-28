package FHP;

import static FHP.ParticlesGenerator.*;
import static Parser.CliParser.*;

public class FHPSimulationController {
    private static final int balanceParticlesAmount = (int) Math.floor((double) (N / 2) * 0.95); //95% of half the total amount of particles

    public static void simulate() {
        ParticlesGenerator.generate();
        while (getParticlesOnRight() < balanceParticlesAmount) {
            CollisionSolver.solve();
            ParticlesPropagator.propagate();
        }
    }

    private static int getParticlesOnRight() {
        int amount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = cols / 2; j < cols; j++) {
                amount += propagatedCells[i][j].getParticlesAmount();
            }
        }
        return amount;
    }
}
