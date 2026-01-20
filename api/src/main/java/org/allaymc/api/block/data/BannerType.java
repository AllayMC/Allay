package org.allaymc.api.block.data;

/**
 * Represents the type of a banner.
 *
 * @author daoge_cmd
 */
public enum BannerType {
    NORMAL,
    OMINOUS;

    private static final BannerType[] VALUES = values();

    /**
     * Gets a BannerType from its ordinal index.
     *
     * @param index the ordinal index
     * @return the corresponding BannerType, or NORMAL if index is out of range
     */
    public static BannerType from(int index) {
        if (index < 0 || index >= VALUES.length) {
            return NORMAL;
        }
        return VALUES[index];
    }
}
