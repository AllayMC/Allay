package org.allaymc.api.world.biome;

import org.allaymc.api.utils.identifier.Identifier;

/**
 * @author daoge_cmd
 */
public interface BiomeType {
    /**
     * Get the identifier of the biome.
     *
     * @return the identifier of the biome
     */
    Identifier getIdentifier();

    /**
     * Get the id of the biome.
     *
     * @return the id of the biome
     */
    int getId();
}
