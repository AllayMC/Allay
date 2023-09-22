package cn.allay.api.entity.component.base;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.item.ItemStack;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public interface EntityItemBaseComponent extends EntityBaseComponent {
    @Inject
    ItemStack getItemStack();

    @Inject
    void setItemStack(ItemStack itemStack);

    @Inject
    int getPickupDelay();

    @Inject
    void setPickupDelay(int delay);

    @Inject
    int getAge();

    @Inject
    void setAge(int age);
}
