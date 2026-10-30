package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.PotionType;

/**
 * {@code ItemArrowStack} represents ordinary and tipped arrows through a shared
 * stack metadata field. An ordinary arrow has metadata {@code 0}, while a
 * tipped arrow stores its potion's ordinal plus one. {@code getPotionType} and
 * {@code setPotionType} translate between that metadata and {@code PotionType}
 * values without assigning the ordinary-arrow marker to a potion.
 *
 * <p>
 * The offset keeps {@code PotionType.WATER}, whose ordinal is {@code 0},
 * distinct from an ordinary arrow. {@code setPotionType(null)} restores the
 * ordinary representation, while a non-{@code null} type replaces the previous
 * metadata. {@code getPotionType} subtracts the offset and returns {@code null}
 * for a negative decoded index; other indices use {@code PotionType.fromId}
 * directly and can fail when they are outside the declared potion range.
 */
public interface ItemArrowStack extends ItemStack {
    /**
     * Returns the potion decoded from this arrow's current metadata. The decoder
     * subtracts one before consulting {@code PotionType.fromId}, so metadata
     * {@code 1} maps to the first potion while metadata {@code 0} continues to
     * identify an ordinary arrow.
     *
     * <p>
     * A negative decoded index returns {@code null}; a nonnegative index is
     * looked up directly without clamping it into the declared potion range.
     * An unavailable index throws {@code ArrayIndexOutOfBoundsException}, and
     * this read leaves metadata unchanged. The subtraction uses ordinary
     * integer arithmetic, so arbitrary invalid integers need not decode as
     * ordinary arrows.
     *
     * @throws ArrayIndexOutOfBoundsException if metadata decodes to an unknown nonnegative potion index
     */
    default PotionType getPotionType() {
        // Offset positive arrow metadata so ordinary arrows retain zero while
        // the first potion still decodes to its zero-based enum ordinal.
        var potionId = getMeta() - 1;
        return potionId >= 0 ? PotionType.fromId(potionId) : null;
    }

    /**
     * Encodes the supplied potion type by replacing this arrow's metadata.
     * A {@code null} value stores {@code 0} for an ordinary arrow; a
     * non-{@code null} value stores {@code type.ordinal() + 1} so the first
     * potion remains distinct from the ordinary-arrow marker.
     *
     * <p>
     * The positive offset makes this operation the inverse of
     * {@code getPotionType} for declared potion values:
     * {@code PotionType.WATER} becomes metadata {@code 1} and decodes back to
     * that potion. The method writes to this stack through {@code setMeta}
     * without creating or returning a replacement stack.
     */
    default void setPotionType(PotionType type) {
        // Reserve zero for ordinary arrows; offset potion ordinals so the
        // first potion does not collide with that untipped representation.
        if (type == null) {
            setMeta(0);
        } else {
            setMeta(type.ordinal() + 1);
        }
    }
}
