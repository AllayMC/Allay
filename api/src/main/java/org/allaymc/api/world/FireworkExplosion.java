package org.allaymc.api.world;

import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.data.FireworkType;
import org.cloudburstmc.nbt.NbtMap;

/**
 * FireworkExplosion represents an explosion of a firework.
 *
 * @param type    The type of the explosion.
 * @param color   The color of the explosion.
 * @param fade    The color of the explosion should fade into. Set this to {@code null} will disable this feature.
 * @param flicker Whether the explosion should flicker on explode.
 * @param trail   Whether the explosion should have a trail.
 * @author daoge_cmd
 */
public record FireworkExplosion(
        FireworkType type,
        DyeColor color, DyeColor fade,
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

        DyeColor color = DyeColor.WHITE;
        var colorData = nbt.getByteArray(TAG_FIREWORK_COLOR);
        if (colorData != null && colorData.length > 0) {
            color = DyeColor.from(colorData[0]);
        }

        DyeColor fade = null;
        var fadeData = nbt.getByteArray(TAG_FIREWORK_FADE);
        if (fadeData != null && fadeData.length > 0) {
            fade = DyeColor.from(nbt.getByteArray(TAG_FIREWORK_FADE)[0]);
        }

        var flicker = nbt.getBoolean(TAG_FIREWORK_FLICKER);
        var trail = nbt.getBoolean(TAG_FIREWORK_TRAIL);

        return new FireworkExplosion(type, color, fade, flicker, trail);
    }

    /**
     * Serializes the firework explosion data into an {@code NbtMap}.
     *
     * @return an {@code NbtMap} containing serialized firework explosion data
     */
    public NbtMap saveNBT() {
        var builder = NbtMap.builder()
                .putShort(TAG_FIREWORK_TYPE, (short) this.type.ordinal())
                .putByteArray(TAG_FIREWORK_COLOR, new byte[]{(byte) this.color.ordinal()})
                .putBoolean(TAG_FIREWORK_FLICKER, this.flicker)
                .putBoolean(TAG_FIREWORK_TRAIL, this.trail);

        if (fade != null) {
            builder.putByteArray(TAG_FIREWORK_FADE, new byte[]{(byte) this.fade.ordinal()});
        }

        return builder.build();
    }
}
