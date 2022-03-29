package FHP;

import Cells.Hexagon;

import static FHP.ParticlesGenerator.*;
import static Parser.CliParser.*;

public class FHPSimulationController {
    private static final int balanceParticlesAmount = (int) Math.floor((double) (N / 2) * 0.95); //95% of half the total amount of particles

    public static void simulate() {
        ParticlesGenerator.generate();
        while (getParticlesOnRight() < balanceParticlesAmount) {
            System.out.println(getParticlesOnRight());
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

    public static int getAllParticles (Hexagon[][] hex){
        int amount = 0;
        for (int i = 0; i < hex.length; i++){
            for (int j = 0; j< hex[1].length; j++){
                amount+= hex[i][j].getParticlesAmount();
            }
        }
        return amount;
    }
}
