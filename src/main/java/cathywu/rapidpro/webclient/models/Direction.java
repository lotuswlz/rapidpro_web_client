package cathywu.rapidpro.webclient.models;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzwu
 * @since 2/3/16
 */
public enum Direction {
    FROM_USER(1), TO_USER(2);

    private int value;
    private Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private static Map<Integer, Direction> valueMap = new HashMap<Integer, Direction>();
    static {
        for (Direction direction : Direction.values()) {
            valueMap.put(direction.getValue(), direction);
        }
    }

    public static Direction fromValue(int value) {
        return valueMap.get(value);
    }
}
