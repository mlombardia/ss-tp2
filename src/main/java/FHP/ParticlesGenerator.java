package FHP;

import Cells.Hexagon;
import Parser.CliParser;

import java.util.Set;

import static Parser.CliParser.N;


public class ParticlesGenerator {
    public static int rows = 202;
    public static int cols = 203;
    public static int partitionCol = (cols - 1) / 2;
    public static Hexagon[][] cells = new Hexagon[rows][cols];

    public static void generate() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                long id = (long) Math.abs(Math.pow(2, i) * Math.pow(3, j));
                if (i == 0 || i == rows - 1) {
                    cells[0][j] = new Hexagon(id, false, false, false, false, false, false, true);
                    cells[rows - 1][j] = new Hexagon(id, false, false, false, false, false, false, true);
                } else if (j == 0 || j == cols - 1) {
                    cells[i][0] = new Hexagon(id, false, false, false, false, false, false, true);
                    cells[i][cols - 1] = new Hexagon(id, false, false, false, false, false, false, true);
                }
                if (j == partitionCol) {
                    if (isPartition(i)) {
                        cells[i][partitionCol] = new Hexagon(id, false, false, false, false, false, false, false);
                    } else if (!isPartition(i)) {
                        cells[i][partitionCol] = new Hexagon(id, false, false, false, false, false, false, true);
                    }
                } else {
                    System.out.println(i + " " + j);
                    cells[i][j] = new Hexagon(id, false, false, false, false, false, false, false);
                }
            }
        }
        for (int particle = 0; particle < N; particle++) {
            createParticle();
        }
    }

    private static void createParticle() {
        boolean particleInserted = false;
        int row, col;
        while (!particleInserted) {
            row = getRandom(0, rows - 1);
            col = getRandom(0, partitionCol);
            System.out.println("row: " + row + " | col: " + col);
            if (cells[row][col].getParticlesAmount() < 6) {
                setParticleDirection(cells[row][col]);
                particleInserted = true;
            }
        }

    }

    private static void setParticleDirection(Hexagon hexagon) {
        Set<String> directions = hexagon.getAvailableSlots();
        String[] directionsArray = new String[directions.size()];
        directionsArray = directions.toArray(directionsArray);
        int direction = getRandom(0, directions.size()-1);
        hexagon.getProperties().put(directionsArray[direction], true);
    }

    private static int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private static boolean isPartition(int row) {
        return row > 75 && row < 126;
    }
}
