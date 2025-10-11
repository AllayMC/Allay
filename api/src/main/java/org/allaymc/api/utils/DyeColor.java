package org.allaymc.api.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.world.FireworkExplosion;

import java.awt.*;

/**
 * DyeColor contains the available colors used in dyeable blocks, items, and {@link FireworkExplosion}. The return value of method
 * {@link #ordinal()} is the meta of the corresponded item.
 *
 * @author harryxi | daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum DyeColor {
    WHITE(new Color(0xf0, 0xf0, 0xf0)),
    ORANGE(new Color(0xf9, 0x80, 0x1d)),
    MAGENTA(new Color(0xc7, 0x4e, 0xbd)),
    LIGHT_BLUE(new Color(0x3a, 0xb3, 0xda)),
    YELLOW(new Color(0xfe, 0xd8, 0x3d)),
    LIME(new Color(0x80, 0xc7, 0x1f)),
    PINK(new Color(0xf3, 0x8b, 0xaa)),
    GRAY(new Color(0x47, 0x4f, 0x52)),
    LIGHT_GRAY(new Color(0x9d, 0x9d, 0x97)),
    CYAN(new Color(0x16, 0x9c, 0x9c)),
    PURPLE(new Color(0x89, 0x32, 0xb8)),
    BLUE(new Color(0x3c, 0x44, 0xaa)),
    BROWN(new Color(0x83, 0x54, 0x32)),
    GREEN(new Color(0x5e, 0x7c, 0x16)),
    RED(new Color(0xb0, 0x2e, 0x26)),
    BLACK(new Color(0x1d, 0x1d, 0x21));

    private static final DyeColor[] VALUES = values();

    /**
     * The corresponded {@link Color} object of this dye color.
     */
    private final Color color;

    /**
     * Gets the dye color by its index.
     *
     * @param index the index of the dye color
     * @return the correspond dye color, or {@code null} if the provided index is invalid
     */
    public static DyeColor from(int index) {
        return VALUES[index];
    }
}
