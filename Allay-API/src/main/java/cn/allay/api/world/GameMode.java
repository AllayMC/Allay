package cn.allay.api.world;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Range;

/**
 * @author daoge_cmd <br>
 * @date 2023/6/3 <br>
 * Allay Project <br>
 */
@AllArgsConstructor
@Getter
public enum GameMode {

    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3)
    ;

    private final int id;

    public static GameMode fromId(@Range(from = 0, to = 3) int id) {
        return switch (id) {
            case 0 -> SURVIVAL;
            case 1 -> CREATIVE;
            case 2 -> ADVENTURE;
            case 3 -> SPECTATOR;
            default -> throw new IllegalArgumentException("Unknown game mode id: " + id);
        };
    }
}
