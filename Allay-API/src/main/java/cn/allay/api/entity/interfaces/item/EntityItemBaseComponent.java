package cn.allay.api.entity.interfaces.item;

import cn.allay.api.entity.component.base.EntityBaseComponent;
import cn.allay.api.item.ItemStack;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public interface EntityItemBaseComponent extends EntityBaseComponent {
    ItemStack getItemStack();

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
