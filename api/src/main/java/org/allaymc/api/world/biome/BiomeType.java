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
    
    /**
     * Retrieves the biome data associated with this biome type.
     *
     * @return a {@link BiomeData} object that contains information and properties of the biome
     */
    BiomeData getBiomeData();
}
