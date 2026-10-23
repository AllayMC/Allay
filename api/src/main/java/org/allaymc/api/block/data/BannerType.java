package org.allaymc.api.block.data;

/**
 * {@code BannerType} distinguishes ordinary banners from ominous banners in stored
 * banner data. The constants provide the ordinal values consumed by banner
 * components, and numeric lookup treats an unsupported stored value as an ordinary
 * banner rather than rejecting the data.
 *
 * @author daoge_cmd
 */
public enum BannerType {
    /**
     * Ordinary customizable banner variant.
     */
    NORMAL,
    /**
     * Ominous illager banner variant.
     */
    OMINOUS;

    /**
     * {@code VALUES} caches declaration-order variants so stored ordinal values can
     * be decoded without allocating another enum array.
     */
    private static final BannerType[] VALUES = values();

    /**
     * Returns the banner variant at the supplied declaration-order index. Negative
     * indices and indices beyond the known variants resolve to {@code NORMAL},
     * preserving an ordinary banner fallback for unsupported data.
     */
    public static BannerType from(int index) {
        // Unsupported stored ordinals select the ordinary variant so banner loading
        // can continue with a known type.
        if (index < 0 || index >= VALUES.length) {
            return NORMAL;
        }
        return VALUES[index];
    }
}
