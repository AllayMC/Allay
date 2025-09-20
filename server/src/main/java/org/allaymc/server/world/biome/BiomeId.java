package org.allaymc.server.world.biome;

import lombok.Getter;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.HashMap;

@Getter
public enum BiomeId {
    OCEAN("minecraft:ocean", 0, "Ocean"),

    PLAINS("minecraft:plains", 1, "Plain"),

    DESERT("minecraft:desert", 2, "Desert"),

    EXTREME_HILLS("minecraft:extreme_hills", 3, "ExtremeHills"),

    FOREST("minecraft:forest", 4, "Forest"),

    TAIGA("minecraft:taiga", 5, "Taiga"),

    SWAMPLAND("minecraft:swampland", 6, "Swamp"),

    RIVER("minecraft:river", 7, "River"),

    HELL("minecraft:hell", 8, "Hell"),

    THE_END("minecraft:the_end", 9, "TheEnd"),

    LEGACY_FROZEN_OCEAN("minecraft:legacy_frozen_ocean", 10, "Ocean"),

    FROZEN_RIVER("minecraft:frozen_river", 11, "River"),

    ICE_PLAINS("minecraft:ice_plains", 12, "Ice"),

    ICE_MOUNTAINS("minecraft:ice_mountains", 13, "Ice"),

    MUSHROOM_ISLAND("minecraft:mushroom_island", 14, "MushroomIsland"),

    MUSHROOM_ISLAND_SHORE("minecraft:mushroom_island_shore", 15, "MushroomIsland"),

    BEACH("minecraft:beach", 16, "Beach"),

    DESERT_HILLS("minecraft:desert_hills", 17, "Desert"),

    FOREST_HILLS("minecraft:forest_hills", 18, "Forest"),

    TAIGA_HILLS("minecraft:taiga_hills", 19, "Taiga"),

    EXTREME_HILLS_EDGE("minecraft:extreme_hills_edge", 20, "ExtremeHills"),

    JUNGLE("minecraft:jungle", 21, "Jungle"),

    JUNGLE_HILLS("minecraft:jungle_hills", 22, "Jungle"),

    JUNGLE_EDGE("minecraft:jungle_edge", 23, "Jungle"),

    DEEP_OCEAN("minecraft:deep_ocean", 24, "Ocean"),

    STONE_BEACH("minecraft:stone_beach", 25, "StoneBeach"),

    COLD_BEACH("minecraft:cold_beach", 26, "Beach"),

    BIRCH_FOREST("minecraft:birch_forest", 27, "Forest"),

    BIRCH_FOREST_HILLS("minecraft:birch_forest_hills", 28, "Forest"),

    ROOFED_FOREST("minecraft:roofed_forest", 29, "Forest"),

    COLD_TAIGA("minecraft:cold_taiga", 30, "Taiga"),

    COLD_TAIGA_HILLS("minecraft:cold_taiga_hills", 31, "Taiga"),

    MEGA_TAIGA("minecraft:mega_taiga", 32, "Taiga"),

    MEGA_TAIGA_HILLS("minecraft:mega_taiga_hills", 33, "Taiga"),

    EXTREME_HILLS_PLUS_TREES("minecraft:extreme_hills_plus_trees", 34, "ExtremeHills"),

    SAVANNA("minecraft:savanna", 35, "Savanna"),

    SAVANNA_PLATEAU("minecraft:savanna_plateau", 36, "Savanna"),

    MESA("minecraft:mesa", 37, "Mesa"),

    MESA_PLATEAU_STONE("minecraft:mesa_plateau_stone", 38, "Mesa"),

    MESA_PLATEAU("minecraft:mesa_plateau", 39, "Mesa"),

    WARM_OCEAN("minecraft:warm_ocean", 40, "Ocean"),

    DEEP_WARM_OCEAN("minecraft:deep_warm_ocean", 41, "Ocean"),

    LUKEWARM_OCEAN("minecraft:lukewarm_ocean", 42, "Ocean"),

    DEEP_LUKEWARM_OCEAN("minecraft:deep_lukewarm_ocean", 43, "Ocean"),

    COLD_OCEAN("minecraft:cold_ocean", 44, "Ocean"),

    DEEP_COLD_OCEAN("minecraft:deep_cold_ocean", 45, "Ocean"),

    FROZEN_OCEAN("minecraft:frozen_ocean", 46, "Ocean"),

    DEEP_FROZEN_OCEAN("minecraft:deep_frozen_ocean", 47, "Ocean"),

    BAMBOO_JUNGLE("minecraft:bamboo_jungle", 48, "Jungle"),

    BAMBOO_JUNGLE_HILLS("minecraft:bamboo_jungle_hills", 49, "Jungle"),

    SUNFLOWER_PLAINS("minecraft:sunflower_plains", 129, "Plain"),

    DESERT_MUTATED("minecraft:desert_mutated", 130, "Desert"),

    EXTREME_HILLS_MUTATED("minecraft:extreme_hills_mutated", 131, "ExtremeHills"),

    FLOWER_FOREST("minecraft:flower_forest", 132, "Forest"),

    TAIGA_MUTATED("minecraft:taiga_mutated", 133, "Taiga"),

    SWAMPLAND_MUTATED("minecraft:swampland_mutated", 134, "Swamp"),

    ICE_PLAINS_SPIKES("minecraft:ice_plains_spikes", 140, "Ice"),

    JUNGLE_MUTATED("minecraft:jungle_mutated", 149, "Jungle"),

    JUNGLE_EDGE_MUTATED("minecraft:jungle_edge_mutated", 151, "Jungle"),

    BIRCH_FOREST_MUTATED("minecraft:birch_forest_mutated", 155, "Forest"),

    BIRCH_FOREST_HILLS_MUTATED("minecraft:birch_forest_hills_mutated", 156, "Forest"),

    ROOFED_FOREST_MUTATED("minecraft:roofed_forest_mutated", 157, "Forest"),

    COLD_TAIGA_MUTATED("minecraft:cold_taiga_mutated", 158, "Taiga"),

    REDWOOD_TAIGA_MUTATED("minecraft:redwood_taiga_mutated", 160, "Taiga"),

    REDWOOD_TAIGA_HILLS_MUTATED("minecraft:redwood_taiga_hills_mutated", 161, "Taiga"),

    EXTREME_HILLS_PLUS_TREES_MUTATED("minecraft:extreme_hills_plus_trees_mutated", 162, "ExtremeHills"),

    SAVANNA_MUTATED("minecraft:savanna_mutated", 163, "Savanna"),

    SAVANNA_PLATEAU_MUTATED("minecraft:savanna_plateau_mutated", 164, "Savanna"),

    MESA_BRYCE("minecraft:mesa_bryce", 165, "Mesa"),

    MESA_PLATEAU_STONE_MUTATED("minecraft:mesa_plateau_stone_mutated", 166, "Mesa"),

    MESA_PLATEAU_MUTATED("minecraft:mesa_plateau_mutated", 167, "Mesa"),

    SOULSAND_VALLEY("minecraft:soulsand_valley", 178, "DataDriven"),

    CRIMSON_FOREST("minecraft:crimson_forest", 179, "DataDriven"),

    WARPED_FOREST("minecraft:warped_forest", 180, "DataDriven"),

    BASALT_DELTAS("minecraft:basalt_deltas", 181, "DataDriven"),

    JAGGED_PEAKS("minecraft:jagged_peaks", 182, "DataDriven"),

    FROZEN_PEAKS("minecraft:frozen_peaks", 183, "DataDriven"),

    SNOWY_SLOPES("minecraft:snowy_slopes", 184, "DataDriven"),

    GROVE("minecraft:grove", 185, "DataDriven"),

    MEADOW("minecraft:meadow", 186, "DataDriven"),

    LUSH_CAVES("minecraft:lush_caves", 187, "DataDriven"),

    DRIPSTONE_CAVES("minecraft:dripstone_caves", 188, "DataDriven"),

    STONY_PEAKS("minecraft:stony_peaks", 189, "DataDriven"),

    DEEP_DARK("minecraft:deep_dark", 190, "DataDriven"),

    MANGROVE_SWAMP("minecraft:mangrove_swamp", 191, "DataDriven"),

    CHERRY_GROVE("minecraft:cherry_grove", 192, "DataDriven"),

    PALE_GARDEN("minecraft:pale_garden", 193, "Forest");

    private static final BiomeId[] MAP1;

    private static final HashMap<Identifier, BiomeId> MAP2;

    static {
        MAP1 = new BiomeId[256];
        MAP2 = new HashMap<>();
        for (var biome : values()) {
            MAP1[biome.id] = biome;
            MAP2.put(biome.identifier, biome);
        }
    }

    private final Identifier identifier;

    private final int id;

    private final String type;

    BiomeId(String identifier, int id, String type) {
        this.identifier = new Identifier(identifier);
        this.id = id;
        this.type = type;
    }

    public static BiomeId fromId(int id) {
        return MAP1[id];
    }

    public static BiomeId fromIdentifier(Identifier identifier) {
        return MAP2.get(identifier);
    }
}
