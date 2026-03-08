package org.allaymc.api.block.data;

import org.allaymc.api.utils.DyeColor;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Represents a single pattern layer on a banner, consisting of a pattern type and a color.
 *
 * @param type  the pattern type
 * @param color the color of the pattern
 * @author daoge_cmd
 */
public record BannerPattern(BannerPatternType type, DyeColor color) {
    private static final String TAG_PATTERN = "Pattern";
    private static final String TAG_COLOR = "Color";

    /**
     * Serializes this banner pattern to NBT format.
     *
     * @return the NBT representation of this pattern
     */
    public NbtMap toNBT() {
        return NbtMap.builder()
                .putString(TAG_PATTERN, type.getId())
                .putInt(TAG_COLOR, color.ordinalInverted())
                .build();
    }

    /**
     * Deserializes a banner pattern from NBT format.
     *
     * @param nbt the NBT data to read from
     * @return the deserialized banner pattern, or {@code null} if the data is invalid
     */
    public static BannerPattern fromNBT(NbtMap nbt) {
        var type = BannerPatternType.fromId(nbt.getString(TAG_PATTERN));
        if (type == null) {
            return null;
        }
        var color = DyeColor.fromInverted(nbt.getInt(TAG_COLOR));
        return new BannerPattern(type, color);
    }
}
