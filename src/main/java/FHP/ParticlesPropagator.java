package FHP;

import Cells.Hexagon;

import static FHP.ParticlesGenerator.*;

/*
   120° <- C. _____ . B -> 60°
           /         \
180° <- D .     *     . A -> 0°
           \         /
   240° <- E . ---- . F -> 300°
*/

public class ParticlesPropagator {
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private static final String E = "E";
    private static final String F = "F";
    private static final String S = "S"; //wall


    public static void propagate() {
        Hexagon hexagon;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                hexagon = cells[i][j];
                propagatedCells[i][j].cleanHexagon(); //clean the hexagon im gonna write on

                if (!hexagon.getProperties().get(S)) {  //if it is not a wall
                    // A -> 0°
                    if (hexagon.getProperties().get(A)) {
                        if (cells[i][j + 1].getProperties().get(S)) { //bumped into a wall
                            propagatedCells[i][j].getProperties().put(D, true);
                        } else {
                            propagatedCells[i][j + 1].getProperties().put(A, true);
                        }
                    }
                    // B -> 60°
                    if (hexagon.getProperties().get(B)) {

                    }
                    // C -> 120°
                    if (hexagon.getProperties().get(C)) {

                    }
                    // D -> 180°
                    if (hexagon.getProperties().get(D)) {

                    }
                    // E -> 240°
                    if (hexagon.getProperties().get(E)) {

                    }
                    // F -> 300°
                    if (hexagon.getProperties().get(F)) {

                    }
                }
            }
        }
        cells = propagatedCells.clone(); //assign the new values
    }

}
