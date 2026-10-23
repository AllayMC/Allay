package org.allaymc.api.world.feature;

/**
 * {@code WorldFeatures} exposes built-in tree, mushroom, and fungus feature
 * references assigned during registry initialization. Every reference starts
 * as {@code null}; the runtime constructs and registers the features before
 * populating this catalog, and later reassignment does not change registry
 * entries.
 *
 * @author daoge_cmd
 */
public final class WorldFeatures {

    // These basic tree references select species-specific trunk and canopy
    // placement; runtime registry initialization assigns them before sapling
    // growth uses the catalog.
    /**
     * Oak tree placement feature.
     */
    public static WorldFeature OAK_TREE;
    /**
     * Birch tree placement feature.
     */
    public static WorldFeature BIRCH_TREE;
    /**
     * Spruce tree placement feature.
     */
    public static WorldFeature SPRUCE_TREE;
    /**
     * Jungle tree placement feature.
     */
    public static WorldFeature JUNGLE_TREE;
    /**
     * Acacia tree placement feature.
     */
    public static WorldFeature ACACIA_TREE;
    /**
     * Dark oak tree placement feature.
     */
    public static WorldFeature DARK_OAK_TREE;
    /**
     * Cherry tree placement feature.
     */
    public static WorldFeature CHERRY_TREE;
    /**
     * Mangrove tree placement feature.
     */
    public static WorldFeature MANGROVE_TREE;
    /**
     * Pale oak tree placement feature.
     */
    public static WorldFeature PALE_OAK_TREE;

    // The azalea reference selects rooted-dirt and mixed-leaf placement for
    // runtime azalea growth through the same registered feature lookup.
    /**
     * Azalea tree placement feature.
     */
    public static WorldFeature AZALEA_TREE;

    // These variant references select expanded trunks or canopies when growth
    // chooses a larger feature instead of its ordinary species shape.
    /**
     * Mega spruce tree placement feature.
     */
    public static WorldFeature MEGA_SPRUCE_TREE;
    /**
     * Mega pine tree placement feature.
     */
    public static WorldFeature MEGA_PINE_TREE;
    /**
     * Mega jungle tree placement feature.
     */
    public static WorldFeature MEGA_JUNGLE_TREE;
    /**
     * Tall birch tree placement feature.
     */
    public static WorldFeature TALL_BIRCH_TREE;
    /**
     * Fancy oak tree placement feature.
     */
    public static WorldFeature FANCY_OAK_TREE;

    // These mushroom references select cap-specific placement while sharing
    // substrate, height sampling, and stem logic through the mushroom base.
    /**
     * Huge brown mushroom placement feature.
     */
    public static WorldFeature HUGE_BROWN_MUSHROOM;
    /**
     * Huge red mushroom placement feature.
     */
    public static WorldFeature HUGE_RED_MUSHROOM;

    // These fungus references select stem and wart materials for the common
    // nylium-based placement algorithm used by both Nether variants.
    /**
     * Huge crimson fungus placement feature.
     */
    public static WorldFeature HUGE_CRIMSON_FUNGUS;
    /**
     * Huge warped fungus placement feature.
     */
    public static WorldFeature HUGE_WARPED_FUNGUS;
}
