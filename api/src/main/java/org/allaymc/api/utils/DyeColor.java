package org.allaymc.api.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.world.explosion.FireworkExplosion;

import java.awt.*;

/**
 * {@code DyeColor} represents the sixteen dye colors used by blocks, items, and firework
 * explosions. Each constant carries its RGB color and an ordinal metadata position; reversed
 * index helpers support consumers whose metadata order runs from black through white instead
 * of the declaration order.
 *
 * @author harryxi | daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum DyeColor {
    /**
     * White dye color.
     */
    WHITE(new Color(0xf0, 0xf0, 0xf0)),
    /**
     * Orange dye color.
     */
    ORANGE(new Color(0xf9, 0x80, 0x1d)),
    /**
     * Magenta dye color.
     */
    MAGENTA(new Color(0xc7, 0x4e, 0xbd)),
    /**
     * Light blue dye color.
     */
    LIGHT_BLUE(new Color(0x3a, 0xb3, 0xda)),
    /**
     * Yellow dye color.
     */
    YELLOW(new Color(0xfe, 0xd8, 0x3d)),
    /**
     * Lime dye color.
     */
    LIME(new Color(0x80, 0xc7, 0x1f)),
    /**
     * Pink dye color.
     */
    PINK(new Color(0xf3, 0x8b, 0xaa)),
    /**
     * Gray dye color.
     */
    GRAY(new Color(0x47, 0x4f, 0x52)),
    /**
     * Light gray dye color.
     */
    LIGHT_GRAY(new Color(0x9d, 0x9d, 0x97)),
    /**
     * Cyan dye color.
     */
    CYAN(new Color(0x16, 0x9c, 0x9c)),
    /**
     * Purple dye color.
     */
    PURPLE(new Color(0x89, 0x32, 0xb8)),
    /**
     * Blue dye color.
     */
    BLUE(new Color(0x3c, 0x44, 0xaa)),
    /**
     * Brown dye color.
     */
    BROWN(new Color(0x83, 0x54, 0x32)),
    /**
     * Green dye color.
     */
    GREEN(new Color(0x5e, 0x7c, 0x16)),
    /**
     * Red dye color.
     */
    RED(new Color(0xb0, 0x2e, 0x26)),
    /**
     * Black dye color.
     */
    BLACK(new Color(0x1d, 0x1d, 0x21));

    /**
     * {@code VALUES} caches declaration order for indexed and reversed-index lookups. Invalid
     * indexes reach array access directly and therefore raise an index exception rather than
     * selecting a default color.
     */
    private static final DyeColor[] VALUES = values();

    /**
     * RGB value for this dye.
     */
    private final Color color;

    /**
     * Returns this color's position when declaration order is reversed.
     */
    public int ordinalInverted() {
        return VALUES.length - ordinal() - 1;
    }

    /**
     * Returns the color at the supplied declaration-order index. Valid indexes run from zero
     * through fifteen; an index outside that range raises {@code ArrayIndexOutOfBoundsException}
     * rather than returning {@code null}.
     */
    public static DyeColor from(int index) {
        return VALUES[index];
    }

    /**
     * Returns the color at a reverse-order index, with zero selecting black and fifteen selecting
     * white. An index outside that range raises {@code ArrayIndexOutOfBoundsException} rather
     * than returning {@code null}.
     */
    public static DyeColor fromInverted(int indexInverted) {
        return VALUES[VALUES.length - indexInverted - 1];
    }
}
