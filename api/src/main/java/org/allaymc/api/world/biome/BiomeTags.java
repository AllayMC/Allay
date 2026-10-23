package org.allaymc.api.world.biome;

import java.util.HashMap;
import java.util.Map;
import org.allaymc.api.annotation.MinecraftVersionSensitive;

@MinecraftVersionSensitive
public interface BiomeTags {
    Map<String, BiomeTag> NAME_TO_TAG = new HashMap<>();

    BiomeTag DEEP = create("deep");

    BiomeTag MONSTER = create("monster");

    BiomeTag OCEAN = create("ocean");

    BiomeTag OVERWORLD = create("overworld");

    BiomeTag SPAWNS_WARM_VARIANT_FARM_ANIMALS = create("spawns_warm_variant_farm_animals");

    BiomeTag FAST_FISHING = create("fast_fishing");

    BiomeTag HIGH_SEAS = create("high_seas");

    BiomeTag ANIMAL = create("animal");

    BiomeTag HILLS = create("hills");

    BiomeTag FOREST = create("forest");

    BiomeTag BEE_HABITAT = create("bee_habitat");

    BiomeTag HAS_STRUCTURE_ABANDONED_CAMP = create("has_structure_abandoned_camp");

    BiomeTag OAK_FOREST = create("oak_forest");

    BiomeTag MUTATED = create("mutated");

    BiomeTag PLAINS = create("plains");

    BiomeTag DESERT = create("desert");

    BiomeTag SPAWNS_GOLD_RABBITS = create("spawns_gold_rabbits");

    BiomeTag OVERWORLD_GENERATION = create("overworld_generation");

    BiomeTag EXTREME_HILLS = create("extreme_hills");

    BiomeTag MOUNTAIN = create("mountain");

    BiomeTag SPAWNS_COLD_VARIANT_FARM_ANIMALS = create("spawns_cold_variant_farm_animals");

    BiomeTag SPAWNS_WARM_VARIANT_FROGS = create("spawns_warm_variant_frogs");

    BiomeTag BEACH = create("beach");

    BiomeTag STONE = create("stone");

    BiomeTag EDGE = create("edge");

    BiomeTag FLOWER_FOREST = create("flower_forest");

    BiomeTag TAIGA = create("taiga");

    BiomeTag HAS_STRUCTURE_TRAIL_RUINS = create("has_structure_trail_ruins");

    BiomeTag SWAMP = create("swamp");

    BiomeTag SPAWNS_SLIMES_ON_SURFACE = create("spawns_slimes_on_surface");

    BiomeTag SLIME = create("slime");

    BiomeTag SWAMP_WATER_HUGE_MUSHROOM = create("swamp_water_huge_mushroom");

    BiomeTag JUNGLE = create("jungle");

    BiomeTag SPAWNS_JUNGLE_MOBS = create("spawns_jungle_mobs");

    BiomeTag SPAWNS_MORE_FREQUENT_DROWNED = create("spawns_more_frequent_drowned");

    BiomeTag SPAWNS_REDUCED_WATER_AMBIENT_MOBS = create("spawns_reduced_water_ambient_mobs");

    BiomeTag SPAWNS_RIVER_MOBS = create("spawns_river_mobs");

    BiomeTag RIVER = create("river");

    BiomeTag NETHER = create("nether");

    BiomeTag NETHER_WASTES = create("nether_wastes");

    BiomeTag SPAWN_ENDERMEN = create("spawn_endermen");

    BiomeTag SPAWN_FEW_PIGLINS = create("spawn_few_piglins");

    BiomeTag SPAWN_GHAST = create("spawn_ghast");

    BiomeTag SPAWN_MAGMA_CUBES = create("spawn_magma_cubes");

    BiomeTag SPAWNS_NETHER_MOBS = create("spawns_nether_mobs");

    BiomeTag SPAWN_ZOMBIFIED_PIGLIN = create("spawn_zombified_piglin");

    BiomeTag THE_END = create("the_end");

    BiomeTag SPAWNS_COLD_VARIANT_FROGS = create("spawns_cold_variant_frogs");

    BiomeTag LEGACY = create("legacy");

    BiomeTag FROZEN = create("frozen");

    BiomeTag SPAWNS_POLAR_BEARS_ON_ALTERNATE_BLOCKS = create("spawns_polar_bears_on_alternate_blocks");

    BiomeTag COLD = create("cold");

    BiomeTag SPAWNS_SNOW_FOXES = create("spawns_snow_foxes");

    BiomeTag SPAWNS_WHITE_RABBITS = create("spawns_white_rabbits");

    BiomeTag ICE = create("ice");

    BiomeTag ICE_PLAINS = create("ice_plains");

    BiomeTag MOOSHROOM_ISLAND = create("mooshroom_island");

    BiomeTag SHORE = create("shore");

    BiomeTag SPAWNS_WITHOUT_PATROLS = create("spawns_without_patrols");

    BiomeTag WARM = create("warm");

    BiomeTag RARE = create("rare");

    BiomeTag BIRCH = create("birch");

    BiomeTag ROOFED = create("roofed");

    BiomeTag NO_LEGACY_WORLDGEN = create("no_legacy_worldgen");

    BiomeTag MEGA = create("mega");

    BiomeTag PLATEAU = create("plateau");

    BiomeTag SAVANNA = create("savanna");

    BiomeTag SPAWNS_SAVANNA_MOBS = create("spawns_savanna_mobs");

    BiomeTag MESA = create("mesa");

    BiomeTag SPAWNS_MESA_MOBS = create("spawns_mesa_mobs");

    BiomeTag SURFACE_MINESHAFT = create("surface_mineshaft");

    BiomeTag TEMPERATE_OCEAN = create("temperate_ocean");

    BiomeTag LUKEWARM = create("lukewarm");

    BiomeTag BAMBOO = create("bamboo");

    BiomeTag SOULSAND_VALLEY = create("soulsand_valley");

    BiomeTag NETHERWART_FOREST = create("netherwart_forest");

    BiomeTag CRIMSON_FOREST = create("crimson_forest");

    BiomeTag SPAWN_FEW_ZOMBIFIED_PIGLINS = create("spawn_few_zombified_piglins");

    BiomeTag SPAWN_PIGLIN = create("spawn_piglin");

    BiomeTag WARPED_FOREST = create("warped_forest");

    BiomeTag BASALT_DELTAS = create("basalt_deltas");

    BiomeTag SPAWN_MANY_MAGMA_CUBES = create("spawn_many_magma_cubes");

    BiomeTag MOUNTAINS = create("mountains");

    BiomeTag JAGGED_PEAKS = create("jagged_peaks");

    BiomeTag FROZEN_PEAKS = create("frozen_peaks");

    BiomeTag SNOWY_SLOPES = create("snowy_slopes");

    BiomeTag GROVE = create("grove");

    BiomeTag MEADOW = create("meadow");

    BiomeTag CAVES = create("caves");

    BiomeTag LUSH_CAVES = create("lush_caves");

    BiomeTag SPAWNS_TROPICAL_FISH_AT_ANY_HEIGHT = create("spawns_tropical_fish_at_any_height");

    BiomeTag DRIPSTONE_CAVES = create("dripstone_caves");

    BiomeTag DEEP_DARK = create("deep_dark");

    BiomeTag MANGROVE_SWAMP = create("mangrove_swamp");

    BiomeTag CHERRY_GROVE = create("cherry_grove");

    BiomeTag PALE_GARDEN = create("pale_garden");

    BiomeTag SULFUR_CAVES = create("sulfur_caves");

    BiomeTag DAPPLED_FOREST = create("dappled_forest");

    static BiomeTag create(String name) {
        var tag = new BiomeTag(name);
        NAME_TO_TAG.put(name, tag);
        return tag;
    }

    static BiomeTag getTagByName(String name) {
        return NAME_TO_TAG.get(name);
    }
}
