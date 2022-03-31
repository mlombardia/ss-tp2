package FHP;

import Cells.Hexagon;

import java.util.Set;

import static FHP.ParticlesGenerator.*;

public class CollisionSolver {
    public static void solve() {
        Hexagon hexagon;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                hexagon = propagatedCells[i][j];
                switch (hexagon.getParticlesAmount()) {
                    case 2:
                        if (particlesAreContiguous(hexagon.getOccupiedSlots())) {
                            if (hexagon.isRandom()) {
                                hexagon.setProperties(hexagon.getOccupiedSlots(), false);
                                hexagon.setProperties("C", true);
                                hexagon.setProperties("F", true);
                            } else {
                                hexagon.setProperties(hexagon.getOccupiedSlots(), false);
                                hexagon.setProperties("B", true);
                                hexagon.setProperties("E", true);
                            }
                        } else if (particlesAreDiagonal(hexagon.getOccupiedSlots())) {
                            hexagon.setProperties(hexagon.getOccupiedSlots(), false);
                            hexagon.setProperties("A", true);
                            hexagon.setProperties("D", true);
                        }
                        break;
                    case 3:
                        if (areSymmetrical(hexagon.getOccupiedSlots())) {
                            if (firstCase(hexagon.getOccupiedSlots())) {
                                hexagon.setProperties(hexagon.getOccupiedSlots(), false);
                                hexagon.setProperties("A", true);
                                hexagon.setProperties("C", true);
                                hexagon.setProperties("E", true);
                            } else {
                                hexagon.setProperties(hexagon.getOccupiedSlots(), false);
                                hexagon.setProperties("B", true);
                                hexagon.setProperties("D", true);
                                hexagon.setProperties("F", true);
                            }
                        }
                        break;
                    case 4:
                        if (correctForm(hexagon.getOccupiedSlots())) {
                            if (isBCEF(hexagon.getOccupiedSlots())) {
                                if (hexagon.isRandom()) {
                                    hexagon.setProperties(hexagon.getOccupiedSlots(), false);
                                    hexagon.setProperties("A", true);
                                    hexagon.setProperties("D", true);
                                    hexagon.setProperties("C", true);
                                    hexagon.setProperties("F", true);
                                } else {
                                    hexagon.setProperties(hexagon.getOccupiedSlots(), false);
                                    hexagon.setProperties("A", true);
                                    hexagon.setProperties("D", true);
                                    hexagon.setProperties("B", true);
                                    hexagon.setProperties("E", true);
                                }
                            } else {
                                hexagon.setProperties(hexagon.getOccupiedSlots(), false);
                                hexagon.setProperties("B", true);
                                hexagon.setProperties("E", true);
                                hexagon.setProperties("C", true);
                                hexagon.setProperties("F", true);
                            }
                        }
                        break;
                    default:
                        break;
                }
                cells[i][j] = new Hexagon(hexagon);
                hexagon.cleanHexagon();
            }
        }
    }

    private static boolean particlesAreContiguous(Set<String> particles) {
        return particles.contains("A") && particles.contains("D");
    }

    private static boolean particlesAreDiagonal(Set<String> particles) {
        return (particles.contains("C") && particles.contains("F")) || (particles.contains("B") && particles.contains("E"));
    }

    private static boolean firstCase(Set<String> particles){
        return (particles.contains("B") && particles.contains("D") && particles.contains("F"));
    }

    private static boolean areSymmetrical(Set<String> particles){
        return (particles.contains("B") && particles.contains("D") && particles.contains("F"))
                || (particles.contains("C") && particles.contains("A") && particles.contains("E"));
    }

    private static boolean isBCEF(Set<String> particles){
       return particles.contains("B") && particles.contains("C") && particles.contains("E") && particles.contains("F");
    }

    private static boolean correctForm(Set<String> particles){
        return (particles.contains("B") && particles.contains("C") && particles.contains("E") && particles.contains("F"))
                || (particles.contains("A") && particles.contains("D") && particles.contains("C") && particles.contains("F"))
                || (particles.contains("A") && particles.contains("D") && particles.contains("C") && particles.contains("E"));
    }
}
