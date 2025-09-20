package org.allaymc.server.registry.populator;

import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.server.world.biome.AllayBiomeType;
import org.allaymc.server.world.biome.BiomeId;

/**
 * @author daoge_cmd
 */
public class BiomeTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
        BiomeTypes.OCEAN = new AllayBiomeType(BiomeId.OCEAN);
        BiomeTypes.PLAINS = new AllayBiomeType(BiomeId.PLAINS);
        BiomeTypes.DESERT = new AllayBiomeType(BiomeId.DESERT);
        BiomeTypes.EXTREME_HILLS = new AllayBiomeType(BiomeId.EXTREME_HILLS);
        BiomeTypes.FOREST = new AllayBiomeType(BiomeId.FOREST);
        BiomeTypes.TAIGA = new AllayBiomeType(BiomeId.TAIGA);
        BiomeTypes.SWAMPLAND = new AllayBiomeType(BiomeId.SWAMPLAND);
        BiomeTypes.RIVER = new AllayBiomeType(BiomeId.RIVER);
        BiomeTypes.HELL = new AllayBiomeType(BiomeId.HELL);
        BiomeTypes.THE_END = new AllayBiomeType(BiomeId.THE_END);
        BiomeTypes.LEGACY_FROZEN_OCEAN = new AllayBiomeType(BiomeId.LEGACY_FROZEN_OCEAN);
        BiomeTypes.FROZEN_RIVER = new AllayBiomeType(BiomeId.FROZEN_RIVER);
        BiomeTypes.ICE_PLAINS = new AllayBiomeType(BiomeId.ICE_PLAINS);
        BiomeTypes.ICE_MOUNTAINS = new AllayBiomeType(BiomeId.ICE_MOUNTAINS);
        BiomeTypes.MUSHROOM_ISLAND = new AllayBiomeType(BiomeId.MUSHROOM_ISLAND);
        BiomeTypes.MUSHROOM_ISLAND_SHORE = new AllayBiomeType(BiomeId.MUSHROOM_ISLAND_SHORE);
        BiomeTypes.BEACH = new AllayBiomeType(BiomeId.BEACH);
        BiomeTypes.DESERT_HILLS = new AllayBiomeType(BiomeId.DESERT_HILLS);
        BiomeTypes.FOREST_HILLS = new AllayBiomeType(BiomeId.FOREST_HILLS);
        BiomeTypes.TAIGA_HILLS = new AllayBiomeType(BiomeId.TAIGA_HILLS);
        BiomeTypes.EXTREME_HILLS_EDGE = new AllayBiomeType(BiomeId.EXTREME_HILLS_EDGE);
        BiomeTypes.JUNGLE = new AllayBiomeType(BiomeId.JUNGLE);
        BiomeTypes.JUNGLE_HILLS = new AllayBiomeType(BiomeId.JUNGLE_HILLS);
        BiomeTypes.JUNGLE_EDGE = new AllayBiomeType(BiomeId.JUNGLE_EDGE);
        BiomeTypes.DEEP_OCEAN = new AllayBiomeType(BiomeId.DEEP_OCEAN);
        BiomeTypes.STONE_BEACH = new AllayBiomeType(BiomeId.STONE_BEACH);
        BiomeTypes.COLD_BEACH = new AllayBiomeType(BiomeId.COLD_BEACH);
        BiomeTypes.BIRCH_FOREST = new AllayBiomeType(BiomeId.BIRCH_FOREST);
        BiomeTypes.BIRCH_FOREST_HILLS = new AllayBiomeType(BiomeId.BIRCH_FOREST_HILLS);
        BiomeTypes.ROOFED_FOREST = new AllayBiomeType(BiomeId.ROOFED_FOREST);
        BiomeTypes.COLD_TAIGA = new AllayBiomeType(BiomeId.COLD_TAIGA);
        BiomeTypes.COLD_TAIGA_HILLS = new AllayBiomeType(BiomeId.COLD_TAIGA_HILLS);
        BiomeTypes.MEGA_TAIGA = new AllayBiomeType(BiomeId.MEGA_TAIGA);
        BiomeTypes.MEGA_TAIGA_HILLS = new AllayBiomeType(BiomeId.MEGA_TAIGA_HILLS);
        BiomeTypes.EXTREME_HILLS_PLUS_TREES = new AllayBiomeType(BiomeId.EXTREME_HILLS_PLUS_TREES);
        BiomeTypes.SAVANNA = new AllayBiomeType(BiomeId.SAVANNA);
        BiomeTypes.SAVANNA_PLATEAU = new AllayBiomeType(BiomeId.SAVANNA_PLATEAU);
        BiomeTypes.MESA = new AllayBiomeType(BiomeId.MESA);
        BiomeTypes.MESA_PLATEAU_STONE = new AllayBiomeType(BiomeId.MESA_PLATEAU_STONE);
        BiomeTypes.MESA_PLATEAU = new AllayBiomeType(BiomeId.MESA_PLATEAU);
        BiomeTypes.WARM_OCEAN = new AllayBiomeType(BiomeId.WARM_OCEAN);
        BiomeTypes.DEEP_WARM_OCEAN = new AllayBiomeType(BiomeId.DEEP_WARM_OCEAN);
        BiomeTypes.LUKEWARM_OCEAN = new AllayBiomeType(BiomeId.LUKEWARM_OCEAN);
        BiomeTypes.DEEP_LUKEWARM_OCEAN = new AllayBiomeType(BiomeId.DEEP_LUKEWARM_OCEAN);
        BiomeTypes.COLD_OCEAN = new AllayBiomeType(BiomeId.COLD_OCEAN);
        BiomeTypes.DEEP_COLD_OCEAN = new AllayBiomeType(BiomeId.DEEP_COLD_OCEAN);
        BiomeTypes.FROZEN_OCEAN = new AllayBiomeType(BiomeId.FROZEN_OCEAN);
        BiomeTypes.DEEP_FROZEN_OCEAN = new AllayBiomeType(BiomeId.DEEP_FROZEN_OCEAN);
        BiomeTypes.BAMBOO_JUNGLE = new AllayBiomeType(BiomeId.BAMBOO_JUNGLE);
        BiomeTypes.BAMBOO_JUNGLE_HILLS = new AllayBiomeType(BiomeId.BAMBOO_JUNGLE_HILLS);
        BiomeTypes.SUNFLOWER_PLAINS = new AllayBiomeType(BiomeId.SUNFLOWER_PLAINS);
        BiomeTypes.DESERT_MUTATED = new AllayBiomeType(BiomeId.DESERT_MUTATED);
        BiomeTypes.EXTREME_HILLS_MUTATED = new AllayBiomeType(BiomeId.EXTREME_HILLS_MUTATED);
        BiomeTypes.FLOWER_FOREST = new AllayBiomeType(BiomeId.FLOWER_FOREST);
        BiomeTypes.TAIGA_MUTATED = new AllayBiomeType(BiomeId.TAIGA_MUTATED);
        BiomeTypes.SWAMPLAND_MUTATED = new AllayBiomeType(BiomeId.SWAMPLAND_MUTATED);
        BiomeTypes.ICE_PLAINS_SPIKES = new AllayBiomeType(BiomeId.ICE_PLAINS_SPIKES);
        BiomeTypes.JUNGLE_MUTATED = new AllayBiomeType(BiomeId.JUNGLE_MUTATED);
        BiomeTypes.JUNGLE_EDGE_MUTATED = new AllayBiomeType(BiomeId.JUNGLE_EDGE_MUTATED);
        BiomeTypes.BIRCH_FOREST_MUTATED = new AllayBiomeType(BiomeId.BIRCH_FOREST_MUTATED);
        BiomeTypes.BIRCH_FOREST_HILLS_MUTATED = new AllayBiomeType(BiomeId.BIRCH_FOREST_HILLS_MUTATED);
        BiomeTypes.ROOFED_FOREST_MUTATED = new AllayBiomeType(BiomeId.ROOFED_FOREST_MUTATED);
        BiomeTypes.COLD_TAIGA_MUTATED = new AllayBiomeType(BiomeId.COLD_TAIGA_MUTATED);
        BiomeTypes.REDWOOD_TAIGA_MUTATED = new AllayBiomeType(BiomeId.REDWOOD_TAIGA_MUTATED);
        BiomeTypes.REDWOOD_TAIGA_HILLS_MUTATED = new AllayBiomeType(BiomeId.REDWOOD_TAIGA_HILLS_MUTATED);
        BiomeTypes.EXTREME_HILLS_PLUS_TREES_MUTATED = new AllayBiomeType(BiomeId.EXTREME_HILLS_PLUS_TREES_MUTATED);
        BiomeTypes.SAVANNA_MUTATED = new AllayBiomeType(BiomeId.SAVANNA_MUTATED);
        BiomeTypes.SAVANNA_PLATEAU_MUTATED = new AllayBiomeType(BiomeId.SAVANNA_PLATEAU_MUTATED);
        BiomeTypes.MESA_BRYCE = new AllayBiomeType(BiomeId.MESA_BRYCE);
        BiomeTypes.MESA_PLATEAU_STONE_MUTATED = new AllayBiomeType(BiomeId.MESA_PLATEAU_STONE_MUTATED);
        BiomeTypes.MESA_PLATEAU_MUTATED = new AllayBiomeType(BiomeId.MESA_PLATEAU_MUTATED);
        BiomeTypes.SOULSAND_VALLEY = new AllayBiomeType(BiomeId.SOULSAND_VALLEY);
        BiomeTypes.CRIMSON_FOREST = new AllayBiomeType(BiomeId.CRIMSON_FOREST);
        BiomeTypes.WARPED_FOREST = new AllayBiomeType(BiomeId.WARPED_FOREST);
        BiomeTypes.BASALT_DELTAS = new AllayBiomeType(BiomeId.BASALT_DELTAS);
        BiomeTypes.JAGGED_PEAKS = new AllayBiomeType(BiomeId.JAGGED_PEAKS);
        BiomeTypes.FROZEN_PEAKS = new AllayBiomeType(BiomeId.FROZEN_PEAKS);
        BiomeTypes.SNOWY_SLOPES = new AllayBiomeType(BiomeId.SNOWY_SLOPES);
        BiomeTypes.GROVE = new AllayBiomeType(BiomeId.GROVE);
        BiomeTypes.MEADOW = new AllayBiomeType(BiomeId.MEADOW);
        BiomeTypes.LUSH_CAVES = new AllayBiomeType(BiomeId.LUSH_CAVES);
        BiomeTypes.DRIPSTONE_CAVES = new AllayBiomeType(BiomeId.DRIPSTONE_CAVES);
        BiomeTypes.STONY_PEAKS = new AllayBiomeType(BiomeId.STONY_PEAKS);
        BiomeTypes.DEEP_DARK = new AllayBiomeType(BiomeId.DEEP_DARK);
        BiomeTypes.MANGROVE_SWAMP = new AllayBiomeType(BiomeId.MANGROVE_SWAMP);
        BiomeTypes.CHERRY_GROVE = new AllayBiomeType(BiomeId.CHERRY_GROVE);
        BiomeTypes.PALE_GARDEN = new AllayBiomeType(BiomeId.PALE_GARDEN);
    }
}
