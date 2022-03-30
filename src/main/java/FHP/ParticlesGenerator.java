package FHP;

import Cells.Hexagon;

import java.util.Arrays;
import java.util.Set;

import static Parser.CliParser.N;


public class ParticlesGenerator {
    public static int rows = 202;
    public static int cols = 203;
    public static int partitionCol = 101;
    public static Hexagon[][] cells = new Hexagon[rows][cols];
    public static Hexagon[][] propagatedCells = new Hexagon[rows][cols];

    public static void generate() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                long id = (long) Math.abs(Math.pow(2, i) * Math.pow(3, j));
                if (i == 0) {
                    cells[0][j] = new Hexagon(id, false, false, false, false, false, false, true);
                } else if (i == rows - 1) {
                    cells[rows - 1][j] = new Hexagon(id, false, false, false, false, false, false, true);
                } else if (j == 0) {
                    cells[i][0] = new Hexagon(id, false, false, false, false, false, false, true);
                } else if (j == cols - 1) {
                    cells[i][cols - 1] = new Hexagon(id, false, false, false, false, false, false, true);
                } else if (isPartition(i) && j == partitionCol) {
                    cells[i][j] = new Hexagon(id, false, false, false, false, false, false, false);
                } else if (!isPartition(i) && j == partitionCol) {
                    cells[i][j] = new Hexagon(id, false, false, false, false, false, false, true);
                }
            }
        }
        for (int i = 1; i < rows - 1; i++) {
            for (int j = partitionCol + 1; j < cols - 1; j++) {
                long id = (long) Math.abs(Math.pow(2, i) * Math.pow(3, j));
                cells[i][j] = new Hexagon(id, false, false, false, false, false, false, false);
            }
        }
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < partitionCol; j++) {
                long id = (long) Math.abs(Math.pow(2, i) * Math.pow(3, j));
                cells[i][j] = new Hexagon(id, false, false, false, false, false, false, false);
            }
        }

        propagatedCells = Arrays.stream(cells).map(Hexagon[]::clone).toArray(Hexagon[][]::new);
        for (int particle = 0; particle < N; particle++) {
            createParticle();
        }

    }

    private static void createParticle() {
        boolean particleInserted = false;
        int row, col;
        while (!particleInserted) {
            row = getRandom(1, rows - 2);
            col = getRandom(1, partitionCol - 2);
            if (propagatedCells[row][col].getParticlesAmount() < 6) {
                setParticleDirection(propagatedCells[row][col]);
                particleInserted = true;
            }
        }

    }

    private static void setParticleDirection(Hexagon hexagon) {
        Set<String> directions = hexagon.getAvailableSlots();
        String[] directionsArray = new String[directions.size()];
        directionsArray = directions.toArray(directionsArray);
        int direction = getRandom(0, directions.size() - 1);
        hexagon.getProperties().put(directionsArray[direction], true);
    }

    private static int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private static boolean isPartition(int row) {
        return row > 70 && row < 121;
    }
}
