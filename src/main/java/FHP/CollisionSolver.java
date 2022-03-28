package FHP;

import Cells.Hexagon;

import static FHP.ParticlesGenerator.*;

public class CollisionSolver {
    public static void solve() {
        Hexagon hexagon;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                hexagon = propagatedCells[i][j];
                // do stuff -> insert new configuration into cells. NOT into propagatedCells.
                // They switch between this function and the propagator.
                hexagon.cleanHexagon();
            }
        }
    }
}
