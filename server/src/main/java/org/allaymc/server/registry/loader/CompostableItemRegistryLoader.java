package org.allaymc.server.registry.loader;

import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.RegistryLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code CompostableItemRegistryLoader} associates built-in item types with their chance of
 * raising a composter's fill level. Probabilities are fractions from zero to one; later
 * registrations replace earlier values for repeated item types, and unresolved item constants
 * are omitted from the returned table.
 *
 * @author daoge_cmd
 */
public class CompostableItemRegistryLoader implements RegistryLoader<Void, Map<ItemType<?>, Float>> {

    /**
     * Creates the mutable table of built-in compost probabilities, ignoring the input. Item types
     * must already be initialized; unresolved constants are skipped, and repeated types retain
     * the chance supplied by their last registration group.
     */
    @Override
    public Map<ItemType<?>, Float> load(Void $) {
        var map = new HashMap<ItemType<?>, Float>();

        // 30% chance items
        registerCompostable(map, 0.3f,
                ItemTypes.BEETROOT_SEEDS, ItemTypes.DRIED_KELP, ItemTypes.FERN,
                ItemTypes.GLOW_BERRIES, ItemTypes.SHORT_GRASS, ItemTypes.HANGING_ROOTS,
                ItemTypes.MANGROVE_PROPAGULE, ItemTypes.MANGROVE_ROOTS, ItemTypes.KELP,
                ItemTypes.MELON_SEEDS, ItemTypes.MOSS_CARPET, ItemTypes.PINK_PETALS,
                ItemTypes.PITCHER_POD, ItemTypes.PUMPKIN_SEEDS, ItemTypes.SEAGRASS,
                ItemTypes.SMALL_DRIPLEAF_BLOCK, ItemTypes.SWEET_BERRIES, ItemTypes.TORCHFLOWER_SEEDS,
                ItemTypes.WHEAT_SEEDS
        );

        // Register leaves (30%)
        registerCompostable(map, 0.3f,
                ItemTypes.OAK_LEAVES, ItemTypes.SPRUCE_LEAVES, ItemTypes.BIRCH_LEAVES,
                ItemTypes.JUNGLE_LEAVES, ItemTypes.ACACIA_LEAVES, ItemTypes.DARK_OAK_LEAVES,
                ItemTypes.MANGROVE_LEAVES, ItemTypes.CHERRY_LEAVES, ItemTypes.AZALEA_LEAVES,
                ItemTypes.AZALEA_LEAVES_FLOWERED, ItemTypes.PALE_OAK_LEAVES
        );

        // Register saplings (30%)
        registerCompostable(map, 0.3f,
                ItemTypes.OAK_SAPLING, ItemTypes.SPRUCE_SAPLING, ItemTypes.BIRCH_SAPLING,
                ItemTypes.JUNGLE_SAPLING, ItemTypes.ACACIA_SAPLING, ItemTypes.DARK_OAK_SAPLING,
                ItemTypes.MANGROVE_PROPAGULE, ItemTypes.CHERRY_SAPLING, ItemTypes.PALE_OAK_SAPLING
        );

        // 50% chance items
        registerCompostable(map, 0.5f,
                ItemTypes.CACTUS, ItemTypes.DRIED_KELP_BLOCK, ItemTypes.AZALEA_LEAVES_FLOWERED,
                ItemTypes.GLOW_LICHEN, ItemTypes.MELON_SLICE, ItemTypes.NETHER_SPROUTS,
                ItemTypes.SUGAR_CANE, ItemTypes.TALL_GRASS, ItemTypes.TWISTING_VINES,
                ItemTypes.VINE, ItemTypes.WEEPING_VINES
        );

        // 65% chance items
        registerCompostable(map, 0.65f,
                ItemTypes.APPLE, ItemTypes.AZALEA, ItemTypes.BEETROOT, ItemTypes.BIG_DRIPLEAF,
                ItemTypes.CARROT, ItemTypes.COCOA_BEANS, ItemTypes.CRIMSON_FUNGUS,
                ItemTypes.CRIMSON_ROOTS, ItemTypes.FLOWERING_AZALEA, ItemTypes.WATERLILY,
                ItemTypes.MELON_BLOCK, ItemTypes.MOSS_BLOCK, ItemTypes.BROWN_MUSHROOM,
                ItemTypes.RED_MUSHROOM, ItemTypes.MUSHROOM_STEM, ItemTypes.NETHER_WART,
                ItemTypes.PITCHER_PLANT, ItemTypes.POTATO, ItemTypes.PUMPKIN,
                ItemTypes.CARVED_PUMPKIN, ItemTypes.SEA_PICKLE, ItemTypes.SHROOMLIGHT,
                ItemTypes.SPORE_BLOSSOM, ItemTypes.WARPED_FUNGUS, ItemTypes.WARPED_ROOTS,
                ItemTypes.WHEAT, ItemTypes.WITHER_ROSE
        );

        // Register flowers (65%)
        registerCompostable(map, 0.65f,
                ItemTypes.DANDELION, ItemTypes.POPPY, ItemTypes.BLUE_ORCHID, ItemTypes.ALLIUM,
                ItemTypes.AZURE_BLUET, ItemTypes.RED_TULIP, ItemTypes.ORANGE_TULIP,
                ItemTypes.WHITE_TULIP, ItemTypes.PINK_TULIP, ItemTypes.OXEYE_DAISY,
                ItemTypes.CORNFLOWER, ItemTypes.LILY_OF_THE_VALLEY, ItemTypes.TORCHFLOWER,
                ItemTypes.SUNFLOWER, ItemTypes.LILAC, ItemTypes.ROSE_BUSH, ItemTypes.PEONY,
                ItemTypes.OPEN_EYEBLOSSOM, ItemTypes.CLOSED_EYEBLOSSOM, ItemTypes.GOLDEN_DANDELION
        );

        // 85% chance items
        registerCompostable(map, 0.85f,
                ItemTypes.BAKED_POTATO, ItemTypes.BREAD, ItemTypes.COOKIE,
                ItemTypes.HAY_BLOCK, ItemTypes.BROWN_MUSHROOM_BLOCK, ItemTypes.RED_MUSHROOM_BLOCK,
                ItemTypes.NETHER_WART_BLOCK, ItemTypes.WARPED_WART_BLOCK
        );

        // 100% chance items
        registerCompostable(map, 1.0f, ItemTypes.CAKE, ItemTypes.PUMPKIN_PIE);

        return map;
    }

    /**
     * Assigns the supplied probability to each resolved item type in {@code map}. A repeated item
     * overwrites its earlier chance, while {@code null} constants are skipped rather than being
     * used as map keys.
     */
    private void registerCompostable(Map<ItemType<?>, Float> map, float chance, ItemType<?>... itemTypes) {
        for (var itemType : itemTypes) {
            // Some item constants can be unavailable during partial initialization. Exclude
            // those entries so the composter table only contains resolved item type keys.
            if (itemType != null) {
                map.put(itemType, chance);
            }
        }
    }
}
