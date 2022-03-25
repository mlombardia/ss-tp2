package Cells;

import java.util.HashMap;
import java.util.Map;

public class Hexagon {
    private final long id;
    private Map<String, Boolean> properties = new HashMap<>();

    public Hexagon(long id, boolean A, boolean B, boolean C, boolean D, boolean E, boolean F, boolean S) {
        int max = 1, min = 0;
        this.id = id;
        properties.put("A", A);
        properties.put("B", B);
        properties.put("C", C);
        properties.put("D", D);
        properties.put("E", E);
        properties.put("F", F);
        properties.put("S", S);
        properties.put("R", (Math.random() * (max - min + 1) + min) > 0.5);
    }

    public Map<String, Boolean> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Boolean> properties) {
        this.properties = properties;
    }

    public long getId() {
        return id;
    }
}
