package org.allaymc.api.entity.component;

import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
public interface EntityItemBaseComponent extends EntityPickableBaseComponent {
    /**
     * Get the item stack of this entity item.
     *
     * @return the item stack, can be {@code null}
     */
    ItemStack getItemStack();

    /**
     * Set the item stack of this entity item.
     *
     * @param itemStack the item stack, can be {@code null}
     */
    void setItemStack(ItemStack itemStack);
}
