package org.allaymc.api.world.biome;

import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code BiomeType} identifies a registered biome and exposes the data consumed by gameplay and
 * network encoding. Resource identifiers provide namespaced lookup, while
 * numeric IDs identify biome values stored in chunk palettes; implementations
 * supply the associated climate and rendering data.
 *
 * @author daoge_cmd
 */
public interface BiomeType {
    /**
     * Returns the namespaced identifier used to register this biome type.
     */
    Identifier getIdentifier();

    /**
     * Returns the numeric biome identifier used by chunk palette storage.
     */
    int getId();
    
    /**
     * Returns the climate and rendering data associated with this biome type.
     */
    BiomeData getBiomeData();
}
