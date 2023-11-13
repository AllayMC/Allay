package org.allaymc.api.world.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;

/**
 * Allay Project 2023/6/3
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public enum GeneratorType {
    LIMIT(0),
    INFINITE(1),
    FLAT(2),
    NETHER(3),
    THE_END(4);

    // The generator id.
    private final int id;

    @Contract("_ -> new")
    public static GeneratorType of(@Range(from = 0, to = 4) int id) {
        return values()[id];
    }
}
