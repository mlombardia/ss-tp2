package FHP;


import static FHP.ParticlesGenerator.*;
import static Parser.CliParser.*;

public class FHPSimulationController {
    private static final int balanceParticlesAmount = (int) Math.floor((double) (N / 2) * 0.90); //90% of half the total amount of particles
    private static final FileGenerator fileGenerator = new FileGenerator();

    public static void simulate() {
        ParticlesGenerator.generate();

        fileGenerator.writeXYZ(propagatedCells, N);
        fileGenerator.writeCSV(0, N, N-getParticlesOnRight(), getParticlesOnRight());

        long startTime = System.currentTimeMillis();
        long endTime;
        int aux = 0;

        while (getParticlesOnRight() < balanceParticlesAmount) { //getParticlesOnRight() < balanceParticlesAmount;;

            CollisionSolver.solve();
            fileGenerator.writeXYZ(cells, N);

            ParticlesPropagator.propagate();
            endTime = System.currentTimeMillis();
            fileGenerator.writeXYZ(propagatedCells, N);
            fileGenerator.writeCSV(endTime - startTime, N,getParticlesOnLeft(), getParticlesOnRight());
        }
        endTime = System.currentTimeMillis();
        System.out.printf("Balanced reached with %d particles on the right and %d on left, %d in total. %d particles were lost.\n", getParticlesOnRight(), getParticlesOnLeft(), getParticlesOnLeft() + getParticlesOnRight(), N - getParticlesOnLeft() - getParticlesOnRight());
        System.out.printf("Time elapsed %d seconds\n", (endTime - startTime)/1000);
        fileGenerator.closeFiles();
    }

    private static int getParticlesOnRight() {
        int amount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = partitionCol; j < cols; j++) {
                amount += propagatedCells[i][j].getParticlesAmount();
            }
        }
        return amount;
    }

    private static int getParticlesOnLeft() {
        int amount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < partitionCol; j++) {
                amount += propagatedCells[i][j].getParticlesAmount();
            }
        }
        return amount;
    }
}
