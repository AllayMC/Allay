package org.allaymc.api.world.generator;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * WorldGeneratorType is an enum that represents the type of world generator.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum WorldGeneratorType {
    /**
     * World generator for limited world
     */
    LIMIT(0),
    /**
     * World generator for infinite world
     */
    INFINITE(1),
    /**
     * World generator for flat world
     */
    FLAT(2),
    /**
     * World generator for nether
     */
    NETHER(3),
    /**
     * World generator for end
     */
    THE_END(4);

    /**
     * The generator id
     */
    private final int id;

    /**
     * Get the WorldGeneratorType by id
     *
     * @param id the generator id
     *
     * @return the WorldGeneratorType
     */
    public static WorldGeneratorType of(int id) {
        Preconditions.checkArgument(id >= 0 && id <= 4);
        return values()[id];
    }
}
