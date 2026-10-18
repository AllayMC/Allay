package org.allaymc.api.world.biome;

/**
 * {@code BiomeTypes} exposes references to the built-in biome types populated during registry
 * initialization. Fields begin as {@code null} and are assigned by the runtime biome
 * registry populator; changing a catalog reference later does not itself
 * replace the corresponding registry entry or persisted biome ID.
 *
 * @author daoge_cmd
 */
public final class BiomeTypes {
    /**
     * Ocean biome type.
     */
    public static BiomeType OCEAN;
    /**
     * Plains biome type.
     */
    public static BiomeType PLAINS;
    /**
     * Desert biome type.
     */
    public static BiomeType DESERT;
    /**
     * Extreme hills biome type.
     */
    public static BiomeType EXTREME_HILLS;
    /**
     * Forest biome type.
     */
    public static BiomeType FOREST;
    /**
     * Taiga biome type.
     */
    public static BiomeType TAIGA;
    /**
     * Swampland biome type.
     */
    public static BiomeType SWAMPLAND;
    /**
     * River biome type.
     */
    public static BiomeType RIVER;
    /**
     * Hell biome type.
     */
    public static BiomeType HELL;
    /**
     * The end biome type.
     */
    public static BiomeType THE_END;
    /**
     * Legacy frozen ocean biome type.
     */
    public static BiomeType LEGACY_FROZEN_OCEAN;
    /**
     * Frozen river biome type.
     */
    public static BiomeType FROZEN_RIVER;
    /**
     * Ice plains biome type.
     */
    public static BiomeType ICE_PLAINS;
    /**
     * Ice mountains biome type.
     */
    public static BiomeType ICE_MOUNTAINS;
    /**
     * Mushroom island biome type.
     */
    public static BiomeType MUSHROOM_ISLAND;
    /**
     * Mushroom island shore biome type.
     */
    public static BiomeType MUSHROOM_ISLAND_SHORE;
    /**
     * Beach biome type.
     */
    public static BiomeType BEACH;
    /**
     * Desert hills biome type.
     */
    public static BiomeType DESERT_HILLS;
    /**
     * Forest hills biome type.
     */
    public static BiomeType FOREST_HILLS;
    /**
     * Taiga hills biome type.
     */
    public static BiomeType TAIGA_HILLS;
    /**
     * Extreme hills edge biome type.
     */
    public static BiomeType EXTREME_HILLS_EDGE;
    /**
     * Jungle biome type.
     */
    public static BiomeType JUNGLE;
    /**
     * Jungle hills biome type.
     */
    public static BiomeType JUNGLE_HILLS;
    /**
     * Jungle edge biome type.
     */
    public static BiomeType JUNGLE_EDGE;
    /**
     * Deep ocean biome type.
     */
    public static BiomeType DEEP_OCEAN;
    /**
     * Stone beach biome type.
     */
    public static BiomeType STONE_BEACH;
    /**
     * Cold beach biome type.
     */
    public static BiomeType COLD_BEACH;
    /**
     * Birch forest biome type.
     */
    public static BiomeType BIRCH_FOREST;
    /**
     * Birch forest hills biome type.
     */
    public static BiomeType BIRCH_FOREST_HILLS;
    /**
     * Roofed forest biome type.
     */
    public static BiomeType ROOFED_FOREST;
    /**
     * Cold taiga biome type.
     */
    public static BiomeType COLD_TAIGA;
    /**
     * Cold taiga hills biome type.
     */
    public static BiomeType COLD_TAIGA_HILLS;
    /**
     * Mega taiga biome type.
     */
    public static BiomeType MEGA_TAIGA;
    /**
     * Mega taiga hills biome type.
     */
    public static BiomeType MEGA_TAIGA_HILLS;
    /**
     * Extreme hills plus trees biome type.
     */
    public static BiomeType EXTREME_HILLS_PLUS_TREES;
    /**
     * Savanna biome type.
     */
    public static BiomeType SAVANNA;
    /**
     * Savanna plateau biome type.
     */
    public static BiomeType SAVANNA_PLATEAU;
    /**
     * Mesa biome type.
     */
    public static BiomeType MESA;
    /**
     * Mesa plateau stone biome type.
     */
    public static BiomeType MESA_PLATEAU_STONE;
    /**
     * Mesa plateau biome type.
     */
    public static BiomeType MESA_PLATEAU;
    /**
     * Warm ocean biome type.
     */
    public static BiomeType WARM_OCEAN;
    /**
     * Deep warm ocean biome type.
     */
    public static BiomeType DEEP_WARM_OCEAN;
    /**
     * Lukewarm ocean biome type.
     */
    public static BiomeType LUKEWARM_OCEAN;
    /**
     * Deep lukewarm ocean biome type.
     */
    public static BiomeType DEEP_LUKEWARM_OCEAN;
    /**
     * Cold ocean biome type.
     */
    public static BiomeType COLD_OCEAN;
    /**
     * Deep cold ocean biome type.
     */
    public static BiomeType DEEP_COLD_OCEAN;
    /**
     * Frozen ocean biome type.
     */
    public static BiomeType FROZEN_OCEAN;
    /**
     * Deep frozen ocean biome type.
     */
    public static BiomeType DEEP_FROZEN_OCEAN;
    /**
     * Bamboo jungle biome type.
     */
    public static BiomeType BAMBOO_JUNGLE;
    /**
     * Bamboo jungle hills biome type.
     */
    public static BiomeType BAMBOO_JUNGLE_HILLS;
    /**
     * Sunflower plains biome type.
     */
    public static BiomeType SUNFLOWER_PLAINS;
    /**
     * Desert mutated biome type.
     */
    public static BiomeType DESERT_MUTATED;
    /**
     * Extreme hills mutated biome type.
     */
    public static BiomeType EXTREME_HILLS_MUTATED;
    /**
     * Flower forest biome type.
     */
    public static BiomeType FLOWER_FOREST;
    /**
     * Taiga mutated biome type.
     */
    public static BiomeType TAIGA_MUTATED;
    /**
     * Swampland mutated biome type.
     */
    public static BiomeType SWAMPLAND_MUTATED;
    /**
     * Ice plains spikes biome type.
     */
    public static BiomeType ICE_PLAINS_SPIKES;
    /**
     * Jungle mutated biome type.
     */
    public static BiomeType JUNGLE_MUTATED;
    /**
     * Jungle edge mutated biome type.
     */
    public static BiomeType JUNGLE_EDGE_MUTATED;
    /**
     * Birch forest mutated biome type.
     */
    public static BiomeType BIRCH_FOREST_MUTATED;
    /**
     * Birch forest hills mutated biome type.
     */
    public static BiomeType BIRCH_FOREST_HILLS_MUTATED;
    /**
     * Roofed forest mutated biome type.
     */
    public static BiomeType ROOFED_FOREST_MUTATED;
    /**
     * Cold taiga mutated biome type.
     */
    public static BiomeType COLD_TAIGA_MUTATED;
    /**
     * Redwood taiga mutated biome type.
     */
    public static BiomeType REDWOOD_TAIGA_MUTATED;
    /**
     * Redwood taiga hills mutated biome type.
     */
    public static BiomeType REDWOOD_TAIGA_HILLS_MUTATED;
    /**
     * Extreme hills plus trees mutated biome type.
     */
    public static BiomeType EXTREME_HILLS_PLUS_TREES_MUTATED;
    /**
     * Savanna mutated biome type.
     */
    public static BiomeType SAVANNA_MUTATED;
    /**
     * Savanna plateau mutated biome type.
     */
    public static BiomeType SAVANNA_PLATEAU_MUTATED;
    /**
     * Mesa bryce biome type.
     */
    public static BiomeType MESA_BRYCE;
    /**
     * Mesa plateau stone mutated biome type.
     */
    public static BiomeType MESA_PLATEAU_STONE_MUTATED;
    /**
     * Mesa plateau mutated biome type.
     */
    public static BiomeType MESA_PLATEAU_MUTATED;
    /**
     * Soulsand valley biome type.
     */
    public static BiomeType SOULSAND_VALLEY;
    /**
     * Crimson forest biome type.
     */
    public static BiomeType CRIMSON_FOREST;
    /**
     * Warped forest biome type.
     */
    public static BiomeType WARPED_FOREST;
    /**
     * Basalt deltas biome type.
     */
    public static BiomeType BASALT_DELTAS;
    /**
     * Jagged peaks biome type.
     */
    public static BiomeType JAGGED_PEAKS;
    /**
     * Frozen peaks biome type.
     */
    public static BiomeType FROZEN_PEAKS;
    /**
     * Snowy slopes biome type.
     */
    public static BiomeType SNOWY_SLOPES;
    /**
     * Grove biome type.
     */
    public static BiomeType GROVE;
    /**
     * Meadow biome type.
     */
    public static BiomeType MEADOW;
    /**
     * Lush caves biome type.
     */
    public static BiomeType LUSH_CAVES;
    /**
     * Dripstone caves biome type.
     */
    public static BiomeType DRIPSTONE_CAVES;
    /**
     * Stony peaks biome type.
     */
    public static BiomeType STONY_PEAKS;
    /**
     * Deep dark biome type.
     */
    public static BiomeType DEEP_DARK;
    /**
     * Mangrove swamp biome type.
     */
    public static BiomeType MANGROVE_SWAMP;
    /**
     * Cherry grove biome type.
     */
    public static BiomeType CHERRY_GROVE;
    /**
     * Pale garden biome type.
     */
    public static BiomeType PALE_GARDEN;
    public static BiomeType SULFUR_CAVES;
    public static BiomeType DAPPLED_FOREST;
}
