package cn.allay.api.world.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Range;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/6/3 <br>
 * Allay Project <br>
 */
@AllArgsConstructor
@Getter
public enum GeneratorType {

    OLD(0),
    INFINITE(1),
    FLAT(2),
    ;

    // The generator id.
    private final int id;

    public static GeneratorType fromId(@Range(from = 0, to = 2) int id) {
        return switch (id) {
            case 0 -> OLD;
            case 1 -> INFINITE;
            case 2 -> FLAT;
            default -> throw new IllegalArgumentException("Unknown generator type id: " + id);
        };
    }
}
