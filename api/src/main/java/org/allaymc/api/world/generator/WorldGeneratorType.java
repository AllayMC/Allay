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
    LIMIT,
    /**
     * World generator for infinite world
     */
    INFINITE,
    /**
     * World generator for flat world
     */
    FLAT,
    /**
     * World generator for nether
     */
    NETHER,
    /**
     * World generator for end
     */
    THE_END,
    /**
     * World generator for void
     */
    VOID;

    /**
     * Get the WorldGeneratorType by id
     *
     * @param id the generator id
     * @return the WorldGeneratorType
     */
    public static WorldGeneratorType byId(int id) {
        Preconditions.checkArgument(id >= 0 && id <= 4);
        return values()[id];
    }
}
