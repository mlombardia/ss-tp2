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

/*
 [i-1,j-1] <- C. _____ . B -> [i-1, j+1]
              /         \
[i,j-1] <- D .   [i,j]   . A -> [i, j+1]
              \         /
[i+1,j-1] <- E . ---- . F -> [i+1, j-1]
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
                if (!hexagon.getProperties().get(S)) {  //if it is not a wall

                    // A -> 0°, [i, j+1]
                    if (hexagon.getProperties().get(A)) {
                        if (cells[i][j + 1].getProperties().get(S)) { //bumped into a wall
                            propagatedCells[i][j].getProperties().put(D, true);
                        } else {
                            propagatedCells[i][j + 1].getProperties().put(A, true);
                        }
                    }

                    // B -> 60°, [i-1, j+1]
                    if (hexagon.getProperties().get(B)) {
                        if (cells[i - 1][j + 1].getProperties().get(S)) { //bumped into a wall

                        } else {
                            propagatedCells[i - 1][j + 1].getProperties().put(B, true);
                        }
                    }

                    // C -> 120°, [i-1,j-1]
                    if (hexagon.getProperties().get(C)) {
                        if (cells[i - 1][j - 1].getProperties().get(S)) { //bumped into a wall

                        } else {
                            propagatedCells[i - 1][j - 1].getProperties().put(C, true);
                        }
                    }

                    // D -> 180°, [i,j-1]
                    if (hexagon.getProperties().get(D)) {
                        if (cells[i][j - 1].getProperties().get(S)) { //bumped into a wall
                            propagatedCells[i][j].getProperties().put(A, true);
                        } else {
                            propagatedCells[i][j - 1].getProperties().put(D, true);
                        }
                    }

                    // E -> 240°, [i+1,j-1]
                    if (hexagon.getProperties().get(E)) {
                        if (cells[i + 1][j - 1].getProperties().get(S)) { //bumped into a wall

                        } else {
                            propagatedCells[i + 1][j - 1].getProperties().put(C, true);
                        }
                    }

                    // F -> 300°, [i+1, j-1]
                    if (hexagon.getProperties().get(F)) {
                        if (cells[i + 1][j - 1].getProperties().get(S)) { //bumped into a wall

                        } else {
                            propagatedCells[i + 1][j - 1].getProperties().put(C, true);
                        }
                    }
                }
                hexagon.cleanHexagon();
            }
        }
    }

}
