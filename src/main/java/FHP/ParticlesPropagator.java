package FHP;

import Cells.Hexagon;

import static FHP.ParticlesGenerator.*;

/*
  120° <- C . _____ . B -> 60°
           /         \
180° <- D .     *     . A -> 0°
           \         /
   240° <- E . ---- . F -> 300°
*/

/*
[i-1,j-1] <- C . _____ . B -> [i-1, j+1]
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
                            propagatedCells[i][j].setProperties(D, true); //moves to opposite direction, same cell
                        } else {  //moves to right cell
                            propagatedCells[i][j + 1].setProperties(A, true);
                        }
                    }

                    // B -> 60°, [i-1, j+1]
                    if (hexagon.getProperties().get(B)) {
                        if (cells[i - 1][j + 1].getProperties().get(S)) { //bumped into a wall
                            // roof[i-1,j], wall[i,j+1]
                            if (cells[i - 1][j].getProperties().get(S) && cells[i][j + 1].getProperties().get(S)) { //if roof and wall -> bumps to [i,j]E
                                propagatedCells[i][j].setProperties(E, true);
                            } else if (cells[i - 1][j].getProperties().get(S)) { //elif roof -> bumps to [i][j+1]F
                                propagatedCells[i][j + 1].setProperties(F, true);
                            } else if (cells[i][j + 1].getProperties().get(S)) { //elif wall -> bumps to [i-1,j]C
                                propagatedCells[i - 1][j].setProperties(C, true);
                            }else{
                                propagatedCells[i][j].setProperties(E, true);
                            }
                        } else { //moves to upper right cell
                            propagatedCells[i - 1][j + 1].setProperties(B, true);
                        }
                    }

                    // C -> 120°, [i-1,j-1]
                    if (hexagon.getProperties().get(C)) {
                        if (cells[i - 1][j - 1].getProperties().get(S)) { //bumped into a wall
                            // roof[i-1,j], wall[i,j-1]
                            if (cells[i - 1][j].getProperties().get(S) && cells[i][j - 1].getProperties().get(S)) { //if roof and wall -> bumps to [i,j]F
                                propagatedCells[i][j].setProperties(F, true);
                            } else if (cells[i - 1][j].getProperties().get(S)) { //elif roof -> bumps to [i,j-1]E
                                propagatedCells[i][j - 1].setProperties(E, true);
                            } else if (cells[i][j - 1].getProperties().get(S)) { //elif wall -> bumps to [i-1][j]B
                                propagatedCells[i - 1][j].setProperties(B, true);
                            }else{
                                propagatedCells[i][j].setProperties(F, true);

                            }
                        } else { //moves to upper left cell
                            propagatedCells[i - 1][j - 1].setProperties(C, true);
                        }
                    }

                    // D -> 180°, [i,j-1]
                    if (hexagon.getProperties().get(D)) {
                        if (cells[i][j - 1].getProperties().get(S)) { //bumped into a wall
                            propagatedCells[i][j].setProperties(A, true); //moves to opposite direction, same cell
                        } else { //moves to left cell
                            propagatedCells[i][j - 1].setProperties(D, true);
                        }
                    }

                    // E -> 240°, [i+1,j-1]
                    if (hexagon.getProperties().get(E)) {
                        if (cells[i + 1][j - 1].getProperties().get(S)) { //bumped into a wall
                            // floor[i+1,j], wall[i,j-1]
                            if (cells[i + 1][j].getProperties().get(S) && cells[i][j - 1].getProperties().get(S)) { //if floor and wall -> bumps to [i,j]B
                                propagatedCells[i][j].setProperties(B, true);
                            } else if (cells[i + 1][j].getProperties().get(S)) { //elif floor -> bumps to [i][j-1]C
                                propagatedCells[i][j - 1].setProperties(C, true);
                            } else if (cells[i][j - 1].getProperties().get(S)) { //elif wall -> bumps to [i+1,j]F
                                propagatedCells[i + 1][j].setProperties(F, true);
                            } else {
                                propagatedCells[i][j].setProperties(B, true);
                            }
                        } else { //moves to bottom left cell
                            propagatedCells[i + 1][j - 1].setProperties(E, true);
                        }
                    }

                    // F -> 300°, [i+1, j+1]
                    if (hexagon.getProperties().get(F)) {
                        if (cells[i + 1][j + 1].getProperties().get(S)) { //bumped into a wall
                            // floor[i+1,j], wall[i,j+1]
                            if (cells[i + 1][j].getProperties().get(S) && cells[i][j + 1].getProperties().get(S)) { //if floor and wall -> bumps to [i,j]C
                                propagatedCells[i][j].setProperties(C, true);
                            } else if (cells[i + 1][j].getProperties().get(S)) { //elif floor -> bumps to [i][j+1]B
                                propagatedCells[i][j + 1].setProperties(B, true);
                            } else if (cells[i][j + 1].getProperties().get(S)) { //elif wall -> bumps to [i+1,j]E
                                propagatedCells[i + 1][j].setProperties(E, true);
                            } else {
                                propagatedCells[i][j].setProperties(C, true);
                            }
                        } else { //moves to bottom right cell
                            propagatedCells[i + 1][j + 1].setProperties(F, true);
                        }
                    }
                }
                hexagon.cleanHexagon();
            }
        }
    }

}
