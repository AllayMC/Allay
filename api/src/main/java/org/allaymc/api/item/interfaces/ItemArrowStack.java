package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.PotionType;

public interface ItemArrowStack extends ItemStack {
    /**
     * Get the potion type of this arrow. Returns {@code null} if this arrow is not a tipped arrow.
     *
     * @return the potion type of this arrow, or {@code null} if this arrow is not a tipped arrow.
     */
    default PotionType getPotionType() {
        var potionId = getMeta() - 1;
        return potionId >= 0 ? PotionType.fromId(potionId) : null;
    }

    /**
     * Set the potion type of this arrow. Setting to {@code null} will make this arrow a normal arrow.
     *
     * @param type the potion type to set, or {@code null} to make this arrow a normal arrow.
     */
    default void setPotionType(PotionType type) {
        if (type == null) {
            setMeta(0);
        } else {
            setMeta(type.ordinal() + 1);
        }
    }
}
