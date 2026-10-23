package org.allaymc.api.block.data;

/**
 * {@code TintMethod} identifies the color treatment attached to a block state's
 * rendering data. The constants distinguish direct map colors from foliage, grass,
 * water, stem, and redstone treatments so rendering consumers can select the
 * appropriate calculation for the stored base color.
 *
 * @author daoge_cmd
 */
public enum TintMethod {
    /**
     * Unmodified base color treatment.
     */
    NONE,
    /**
     * Default biome foliage treatment.
     */
    DEFAULT_FOLIAGE,
    /**
     * Birch foliage color treatment.
     */
    BIRCH_FOLIAGE,
    /**
     * Evergreen foliage color treatment.
     */
    EVERGREEN_FOLIAGE,
    /**
     * Dry foliage color treatment.
     */
    DRY_FOLIAGE,
    /**
     * Biome grass color treatment.
     */
    GRASS,
    /**
     * Biome water color treatment.
     */
    WATER,
    /**
     * Stem growth color treatment.
     */
    STEM,
    /**
     * Redstone wire power treatment.
     */
    RED_STONE_WIRE
}
