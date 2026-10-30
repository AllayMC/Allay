package org.allaymc.api.block.data;

import org.allaymc.api.utils.DyeColor;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BannerPattern} represents one colored pattern layer in a banner's ordered
 * pattern list. NBT stores the pattern's short identifier and the color's inverted
 * dye ordinal, while this record retains the corresponding enum values for use by
 * banner components.
 *
 * @param type banner pattern kind
 * @param color pattern dye color
 *
 * @author daoge_cmd
 */
public record BannerPattern(BannerPatternType type, DyeColor color) {
    /**
     * {@code TAG_PATTERN} names the NBT string that identifies this layer's banner
     * pattern during serialization and lookup.
     */
    private static final String TAG_PATTERN = "Pattern";
    /**
     * {@code TAG_COLOR} names the NBT integer that stores this layer's color using
     * the inverted dye ordinal.
     */
    private static final String TAG_COLOR = "Color";

    /**
     * Serializes this layer using its pattern identifier and inverted dye ordinal.
     * Both record components must be non-{@code null}; the record constructor
     * itself does not validate either component.
     */
    public NbtMap toNBT() {
        // Banner NBT uses the reversed dye ordinal, so the stored color differs
        // from the ordinary dye-item metadata order.
        return NbtMap.builder()
                .putString(TAG_PATTERN, type.getId())
                .putInt(TAG_COLOR, color.ordinalInverted())
                .build();
    }

    /**
     * Reads a layer from its pattern identifier and inverted dye ordinal. An
     * unknown pattern returns {@code null}; a recognized pattern with an
     * out-of-range color index throws an array bounds exception instead of
     * returning {@code null}.
     */
    public static BannerPattern fromNBT(NbtMap nbt) {
        var type = BannerPatternType.fromId(nbt.getString(TAG_PATTERN));
        // Unknown pattern identifiers cannot identify a layer, so discard this
        // entry before decoding its color ordinal.
        if (type == null) {
            return null;
        }
        var color = DyeColor.fromInverted(nbt.getInt(TAG_COLOR));
        return new BannerPattern(type, color);
    }
}
