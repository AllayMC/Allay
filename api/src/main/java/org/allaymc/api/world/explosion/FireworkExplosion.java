package org.allaymc.api.world.explosion;

import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.data.FireworkType;
import org.cloudburstmc.nbt.NbtMap;

import java.util.ArrayList;
import java.util.List;

/**
 * FireworkExplosion represents an explosion of a firework.
 *
 * @param type       The type of the explosion.
 * @param colors     The colors of the explosion.
 * @param fadeColors The colors of the explosion should fade into. Set this to an empty list will disable this feature.
 * @param flicker    Whether the explosion should flicker on explode.
 * @param trail      Whether the explosion should have a trail.
 * @author daoge_cmd
 */
public record FireworkExplosion(
        FireworkType type,
        List<DyeColor> colors, List<DyeColor> fadeColors,
        boolean flicker, boolean trail
) {

    private static final String TAG_FIREWORK_TYPE = "FireworkType";
    private static final String TAG_FIREWORK_COLOR = "FireworkColor";
    private static final String TAG_FIREWORK_FADE = "FireworkFade";
    private static final String TAG_FIREWORK_FLICKER = "FireworkFlicker";
    private static final String TAG_FIREWORK_TRAIL = "FireworkTrail";

    /**
     * Creates a {@link FireworkExplosion} instance from NBT data.
     *
     * @param nbt the NBT data containing firework explosion information
     * @return a new {@link FireworkExplosion} instance
     */
    public static FireworkExplosion fromNBT(NbtMap nbt) {
        var type = FireworkType.values()[nbt.getShort(TAG_FIREWORK_TYPE)];

        var colors = new ArrayList<DyeColor>();
        for (var colorIndex : nbt.getByteArray(TAG_FIREWORK_COLOR, new byte[0])) {
            colors.add(DyeColor.fromInverted(colorIndex));
        }
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
     * Serializes the firework explosion data into an {@code NbtMap}.
     *
     * @return an {@code NbtMap} containing serialized firework explosion data
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

    private static byte[] encodeColors(List<DyeColor> colors) {
        var array = new byte[colors.size()];
        for (int i = 0; i < colors.size(); i++) {
            array[i] = (byte) colors.get(i).ordinalInverted();
        }
        return array;
    }
}
