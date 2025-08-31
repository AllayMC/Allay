package org.allaymc.api.utils;

/**
 * DyeColor contains the available colors used in dyeable blocks and items. The return value of method
 * {@link #ordinal()} is the meta of the correspond item.
 *
 * @author harryxi | daoge_cmd
 */
public enum DyeColor {
    WHITE,
    ORANGE,
    MAGENTA,
    LIGHT_BLUE,
    YELLOW,
    LIME,
    PINK,
    GRAY,
    LIGHT_GRAY,
    CYAN,
    PURPLE,
    BLUE,
    BROWN,
    GREEN,
    RED,
    BLACK;

    private static final DyeColor[] VALUES = values();

    /**
     * Gets the dye color by its index.
     *
     * @param index the index of the dye color
     *
     * @return the correspond dye color, or {@code null} if the provided index is invalid
     */
    public static DyeColor from(int index) {
        return VALUES[index];
    }
}
