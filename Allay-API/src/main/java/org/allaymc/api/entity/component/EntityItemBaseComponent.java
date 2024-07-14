package org.allaymc.api.entity.component;

import org.allaymc.api.entity.component.common.EntityBaseComponent;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public interface EntityItemBaseComponent extends EntityBaseComponent {
    ItemStack getItemStack();

    /**
     * Set the item stack of this entity item
     * @param itemStack the item stack, can be null
     */
    void setItemStack(ItemStack itemStack);

    int getPickupDelay();

    void setPickupDelay(int delay);

    int getAge();

    void setAge(int age);

    default boolean canBePicked() {
        return getPickupDelay() == 0;
    }

    @Override
    default boolean computeBlockCollisionMotion() {
        return true;
    }
}
