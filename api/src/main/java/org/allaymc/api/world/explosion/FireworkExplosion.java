package org.allaymc.api.world.explosion;

import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.data.FireworkType;
import org.cloudburstmc.nbt.NbtMap;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code FireworkExplosion} represents the shape, colors, and visual flags of
 * one firework burst and converts that descriptor to NBT. Its record
 * constructor retains both color lists without validation, so callers control
 * their mutability and lifetime.
 *
 * <p>
 * The shape loader requests a short tag, but the saver writes a byte tag. The
 * current NBT accessor returns zero for that byte, so loading this record's
 * own saved output selects {@code FireworkType.SMALL_SPHERE} regardless of the
 * saved shape. Colors use inverted dye bytes; loading substitutes white only
 * for an absent or empty primary palette, while an empty fade list remains
 * empty. Invalid short shape indices or dye indices throw instead of being
 * repaired.
 *
 * @param type firework explosion shape category
 *
 * @param colors {@code colors} retains the primary color list. Callers must
 * keep it stable while saving or encoding the firework.
 *
 * @param fadeColors {@code fadeColors} retains the fade color list. An empty
 * list disables fading; callers must keep it stable while consumed.
 *
 * @param flicker whether to enable flickering sparks
 *
 * @param trail whether to enable trailing sparks
 *
 * @author daoge_cmd
 */
public record FireworkExplosion(
        FireworkType type,
        List<DyeColor> colors, List<DyeColor> fadeColors,
        boolean flicker, boolean trail
) {

    /**
     * The firework NBT keys identify shape, color arrays, and visual flags in
     * saved item descriptors. This key addresses the shape ordinal.
     */
    private static final String TAG_FIREWORK_TYPE = "FireworkType";
    /**
     * NBT key for primary inverted dye bytes.
     */
    private static final String TAG_FIREWORK_COLOR = "FireworkColor";
    /**
     * NBT key for fade inverted dye bytes.
     */
    private static final String TAG_FIREWORK_FADE = "FireworkFade";
    /**
     * NBT key for flickering spark flag.
     */
    private static final String TAG_FIREWORK_FLICKER = "FireworkFlicker";
    /**
     * NBT key for trailing spark flag.
     */
    private static final String TAG_FIREWORK_TRAIL = "FireworkTrail";

    /**
     * Creates a descriptor from a short shape tag, inverted dye bytes, and
     * visual flags. Missing or empty primary colors produce white; missing
     * fade colors produce an empty list. Both decoded lists are newly
     * allocated and mutable.
     *
     * <p>
     * The current NBT accessor substitutes zero for missing or non-short shape
     * tags, including the byte written by {@code saveNBT}, so those inputs
     * select {@code FireworkType.SMALL_SPHERE}. An out-of-range short shape
     * index or inverted dye index throws {@link
     * ArrayIndexOutOfBoundsException}. This conversion does not retain the
     * input color arrays.
     */
    public static FireworkExplosion fromNBT(NbtMap nbt) {
        // NBT accessors distinguish numeric tag types; reading saved Byte
        // output through getShort takes its missing-type default rather than
        // widening the stored shape ordinal.
        var type = FireworkType.values()[nbt.getShort(TAG_FIREWORK_TYPE)];

        var colors = new ArrayList<DyeColor>();
        for (var colorIndex : nbt.getByteArray(TAG_FIREWORK_COLOR, new byte[0])) {
            colors.add(DyeColor.fromInverted(colorIndex));
        }
        // An absent or empty primary palette still needs a visible firework
        // color; white supplies that fallback without inventing fade colors.
        if (colors.isEmpty()) {
            colors.add(DyeColor.WHITE);
        }

        var fadeColors = new ArrayList<DyeColor>();
        for (var fadeColorIndex : nbt.getByteArray(TAG_FIREWORK_FADE, new byte[0])) {
            fadeColors.add(DyeColor.fromInverted(fadeColorIndex));
        }

        var flicker = nbt.getBoolean(TAG_FIREWORK_FLICKER);
        var trail = nbt.getBoolean(TAG_FIREWORK_TRAIL);

        return new FireworkExplosion(type, colors, fadeColors, flicker, trail);
    }

    /**
     * Serializes this descriptor using a byte shape ordinal and inverted dye
     * indices. The current {@code fromNBT} loader expects a short shape tag
     * and therefore does not preserve this saved shape on reload. Color arrays
     * are newly allocated, and the retained lists must remain stable during
     * iteration.
     */
    public NbtMap saveNBT() {
        return NbtMap.builder()
                .putByte(TAG_FIREWORK_TYPE, (byte) this.type.ordinal())
                .putByteArray(TAG_FIREWORK_COLOR, encodeColors(this.colors))
                .putByteArray(TAG_FIREWORK_FADE, encodeColors(this.fadeColors))
                .putBoolean(TAG_FIREWORK_FLICKER, this.flicker)
                .putBoolean(TAG_FIREWORK_TRAIL, this.trail)
                .build();
    }

    /**
     * Encodes each supplied dye color as its inverted ordinal byte. The list
     * must remain stable and contain usable dye values; the returned array is
     * new and can be changed independently of the list.
     */
    private static byte[] encodeColors(List<DyeColor> colors) {
        var array = new byte[colors.size()];
        for (int i = 0; i < colors.size(); i++) {
            // Firework palettes reverse dye declaration order, so ordinary ordinals
            // would decode into different colors when the saved palette is read.
            array[i] = (byte) colors.get(i).ordinalInverted();
        }
        return array;
    }
}
