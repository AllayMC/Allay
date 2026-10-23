package org.allaymc.api.player;

import org.allaymc.api.annotation.MinecraftVersionSensitive;

/**
 * {@code FogIds} names built-in client fog definitions accepted by
 * {@code Player.pushFog}. These string constants select client presentation
 * resources rather than server weather or block data; clients must have the
 * corresponding resource definition for the requested fog to render.
 *
 * @author daoge_cmd
 */
@MinecraftVersionSensitive
public interface FogIds {
    /**
     * Client fog identifier for bamboo jungle.
     */
    String FOG_BAMBOO_JUNGLE = "minecraft:fog_bamboo_jungle";
    /**
     * Client fog identifier for bamboo jungle hills.
     */
    String FOG_BAMBOO_JUNGLE_HILLS = "minecraft:fog_bamboo_jungle_hills";
    /**
     * Client fog identifier for basalt deltas.
     */
    String FOG_BASALT_DELTAS = "minecraft:fog_basalt_deltas";
    /**
     * Client fog identifier for beach.
     */
    String FOG_BEACH = "minecraft:fog_beach";
    /**
     * Client fog identifier for birch forest.
     */
    String FOG_BIRCH_FOREST = "minecraft:fog_birch_forest";
    /**
     * Client fog identifier for birch forest hills.
     */
    String FOG_BIRCH_FOREST_HILLS = "minecraft:fog_birch_forest_hills";
    /**
     * Client fog identifier for cherry grove.
     */
    String FOG_CHERRY_GROVE = "minecraft:fog_cherry_grove";
    /**
     * Client fog identifier for cold beach.
     */
    String FOG_COLD_BEACH = "minecraft:fog_cold_beach";
    /**
     * Client fog identifier for cold ocean.
     */
    String FOG_COLD_OCEAN = "minecraft:fog_cold_ocean";
    /**
     * Client fog identifier for cold taiga.
     */
    String FOG_COLD_TAIGA = "minecraft:fog_cold_taiga";
    /**
     * Client fog identifier for cold taiga hills.
     */
    String FOG_COLD_TAIGA_HILLS = "minecraft:fog_cold_taiga_hills";
    /**
     * Client fog identifier for cold taiga mutated.
     */
    String FOG_COLD_TAIGA_MUTATED = "minecraft:fog_cold_taiga_mutated";
    /**
     * Client fog identifier for crimson forest.
     */
    String FOG_CRIMSON_FOREST = "minecraft:fog_crimson_forest";
    /**
     * Client fog identifier for deep cold ocean.
     */
    String FOG_DEEP_COLD_OCEAN = "minecraft:fog_deep_cold_ocean";
    /**
     * Client fog identifier for deep frozen ocean.
     */
    String FOG_DEEP_FROZEN_OCEAN = "minecraft:fog_deep_frozen_ocean";
    /**
     * Client fog identifier for deep lukewarm ocean.
     */
    String FOG_DEEP_LUKEWARM_OCEAN = "minecraft:fog_deep_lukewarm_ocean";
    /**
     * Client fog identifier for deep ocean.
     */
    String FOG_DEEP_OCEAN = "minecraft:fog_deep_ocean";
    /**
     * Client fog identifier for deep warm ocean.
     */
    String FOG_DEEP_WARM_OCEAN = "minecraft:fog_deep_warm_ocean";
    /**
     * Client fog identifier for default.
     */
    String FOG_DEFAULT = "minecraft:fog_default";
    /**
     * Client fog identifier for desert.
     */
    String FOG_DESERT = "minecraft:fog_desert";
    /**
     * Client fog identifier for desert hills.
     */
    String FOG_DESERT_HILLS = "minecraft:fog_desert_hills";
    /**
     * Client fog identifier for extreme hills.
     */
    String FOG_EXTREME_HILLS = "minecraft:fog_extreme_hills";
    /**
     * Client fog identifier for extreme hills edge.
     */
    String FOG_EXTREME_HILLS_EDGE = "minecraft:fog_extreme_hills_edge";
    /**
     * Client fog identifier for extreme hills mutated.
     */
    String FOG_EXTREME_HILLS_MUTATED = "minecraft:fog_extreme_hills_mutated";
    /**
     * Client fog identifier for extreme hills plus trees.
     */
    String FOG_EXTREME_HILLS_PLUS_TREES = "minecraft:fog_extreme_hills_plus_trees";
    /**
     * Client fog identifier for extreme hills plus trees mutated.
     */
    String FOG_EXTREME_HILLS_PLUS_TREES_MUTATED = "minecraft:fog_extreme_hills_plus_trees_mutated";
    /**
     * Client fog identifier for flower forest.
     */
    String FOG_FLOWER_FOREST = "minecraft:fog_flower_forest";
    /**
     * Client fog identifier for forest.
     */
    String FOG_FOREST = "minecraft:fog_forest";
    /**
     * Client fog identifier for forest hills.
     */
    String FOG_FOREST_HILLS = "minecraft:fog_forest_hills";
    /**
     * Client fog identifier for frozen ocean.
     */
    String FOG_FROZEN_OCEAN = "minecraft:fog_frozen_ocean";
    /**
     * Client fog identifier for frozen river.
     */
    String FOG_FROZEN_RIVER = "minecraft:fog_frozen_river";
    /**
     * Client fog identifier for hell.
     */
    String FOG_HELL = "minecraft:fog_hell";
    /**
     * Client fog identifier for ice mountains.
     */
    String FOG_ICE_MOUNTAINS = "minecraft:fog_ice_mountains";
    /**
     * Client fog identifier for ice plains.
     */
    String FOG_ICE_PLAINS = "minecraft:fog_ice_plains";
    /**
     * Client fog identifier for ice plains spikes.
     */
    String FOG_ICE_PLAINS_SPIKES = "minecraft:fog_ice_plains_spikes";
    /**
     * Client fog identifier for jungle.
     */
    String FOG_JUNGLE = "minecraft:fog_jungle";
    /**
     * Client fog identifier for jungle edge.
     */
    String FOG_JUNGLE_EDGE = "minecraft:fog_jungle_edge";
    /**
     * Client fog identifier for jungle hills.
     */
    String FOG_JUNGLE_HILLS = "minecraft:fog_jungle_hills";
    /**
     * Client fog identifier for jungle mutated.
     */
    String FOG_JUNGLE_MUTATED = "minecraft:fog_jungle_mutated";
    /**
     * Client fog identifier for lukewarm ocean.
     */
    String FOG_LUKEWARM_OCEAN = "minecraft:fog_lukewarm_ocean";
    /**
     * Client fog identifier for mangrove swamp.
     */
    String FOG_MANGROVE_SWAMP = "minecraft:fog_mangrove_swamp";
    /**
     * Client fog identifier for mega spruce taiga.
     */
    String FOG_MEGA_SPRUCE_TAIGA = "minecraft:fog_mega_spruce_taiga";
    /**
     * Client fog identifier for mega spruce taiga mutated.
     */
    String FOG_MEGA_SPRUCE_TAIGA_MUTATED = "minecraft:fog_mega_spruce_taiga_mutated";
    /**
     * Client fog identifier for mega taiga.
     */
    String FOG_MEGA_TAIGA = "minecraft:fog_mega_taiga";
    /**
     * Client fog identifier for mega taiga hills.
     */
    String FOG_MEGA_TAIGA_HILLS = "minecraft:fog_mega_taiga_hills";
    /**
     * Client fog identifier for mega taiga mutated.
     */
    String FOG_MEGA_TAIGA_MUTATED = "minecraft:fog_mega_taiga_mutated";
    /**
     * Client fog identifier for mesa.
     */
    String FOG_MESA = "minecraft:fog_mesa";
    /**
     * Client fog identifier for mesa bryce.
     */
    String FOG_MESA_BRYCE = "minecraft:fog_mesa_bryce";
    /**
     * Client fog identifier for mesa mutated.
     */
    String FOG_MESA_MUTATED = "minecraft:fog_mesa_mutated";
    /**
     * Client fog identifier for mesa plateau.
     */
    String FOG_MESA_PLATEAU = "minecraft:fog_mesa_plateau";
    /**
     * Client fog identifier for mesa plateau stone.
     */
    String FOG_MESA_PLATEAU_STONE = "minecraft:fog_mesa_plateau_stone";
    /**
     * Client fog identifier for mushroom island.
     */
    String FOG_MUSHROOM_ISLAND = "minecraft:fog_mushroom_island";
    /**
     * Client fog identifier for mushroom island shore.
     */
    String FOG_MUSHROOM_ISLAND_SHORE = "minecraft:fog_mushroom_island_shore";
    /**
     * Client fog identifier for ocean.
     */
    String FOG_OCEAN = "minecraft:fog_ocean";
    /**
     * Client fog identifier for plains.
     */
    String FOG_PLAINS = "minecraft:fog_plains";
    /**
     * Client fog identifier for river.
     */
    String FOG_RIVER = "minecraft:fog_river";
    /**
     * Client fog identifier for roofed forest.
     */
    String FOG_ROOFED_FOREST = "minecraft:fog_roofed_forest";
    /**
     * Client fog identifier for savanna.
     */
    String FOG_SAVANNA = "minecraft:fog_savanna";
    /**
     * Client fog identifier for savanna mutated.
     */
    String FOG_SAVANNA_MUTATED = "minecraft:fog_savanna_mutated";
    /**
     * Client fog identifier for savanna plateau.
     */
    String FOG_SAVANNA_PLATEAU = "minecraft:fog_savanna_plateau";
    /**
     * Client fog identifier for soulsand valley.
     */
    String FOG_SOULSAND_VALLEY = "minecraft:fog_soulsand_valley";
    /**
     * Client fog identifier for stone beach.
     */
    String FOG_STONE_BEACH = "minecraft:fog_stone_beach";
    /**
     * Client fog identifier for sunflower plains.
     */
    String FOG_SUNFLOWER_PLAINS = "minecraft:fog_sunflower_plains";
    /**
     * Client fog identifier for swampland.
     */
    String FOG_SWAMPLAND = "minecraft:fog_swampland";
    /**
     * Client fog identifier for swampland mutated.
     */
    String FOG_SWAMPLAND_MUTATED = "minecraft:fog_swampland_mutated";
    /**
     * Client fog identifier for taiga.
     */
    String FOG_TAIGA = "minecraft:fog_taiga";
    /**
     * Client fog identifier for taiga hills.
     */
    String FOG_TAIGA_HILLS = "minecraft:fog_taiga_hills";
    /**
     * Client fog identifier for taiga mutated.
     */
    String FOG_TAIGA_MUTATED = "minecraft:fog_taiga_mutated";
    /**
     * Client fog identifier for the end.
     */
    String FOG_THE_END = "minecraft:fog_the_end";
    /**
     * Client fog identifier for warm ocean.
     */
    String FOG_WARM_OCEAN = "minecraft:fog_warm_ocean";
    /**
     * Client fog identifier for warped forest.
     */
    String FOG_WARPED_FOREST = "minecraft:fog_warped_forest";
}
