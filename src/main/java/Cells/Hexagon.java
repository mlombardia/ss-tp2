package Cells;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Hexagon {
    private final long id;
    private Map<String, Boolean> properties = new HashMap<>();

    public Hexagon (Hexagon copy){
        this.id = copy.id;
        this.properties = new HashMap<>(copy.properties);
    }

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

    public int getParticlesAmount() {
        int amount = 0;
        for (String key : properties.keySet()) {
            if (properties.get(key) && !key.equals("S") && !key.equals("R")) {
                amount++;
            }
        }
        return amount;
    }

    public Set<String> getAvailableSlots() {
        Set<String> keys = new HashSet<>(properties.keySet());
        keys.remove("S");
        keys.remove("R");
        for (String key : properties.keySet()) {
            if (properties.get(key)) {
                keys.remove(key);
            }
        }
        return keys;
    }

    public Set<String> getOccupiedSlots(){
        Set<String> keys = new HashSet<>(properties.keySet());
        keys.remove("S");
        keys.remove("R");
        for (String key: properties.keySet()) {
            if (!properties.get(key)){
                keys.remove(key);
            }
        }
        return keys;
    }

    public boolean isRandom(){
        return properties.get("R");
    }

    public Map<String, Boolean> getProperties() {
        return properties;
    }

    public void setProperties(String key, Boolean value) {
        this.properties.put(key, value);
    }

    public void setProperties(Set<String> keys, Boolean value){
        for (String key : keys){
            this.properties.put(key,value);
        }
    }

    public long getId() {
        return id;
    }

    public void cleanHexagon() {
        properties.put("A", false);
        properties.put("B", false);
        properties.put("C", false);
        properties.put("D", false);
        properties.put("E", false);
        properties.put("F", false);
    }
}
