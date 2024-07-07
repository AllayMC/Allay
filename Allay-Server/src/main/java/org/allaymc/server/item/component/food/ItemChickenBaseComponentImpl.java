package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectHungerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemChickenStack;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
public class ItemChickenBaseComponentImpl extends ItemFoodBaseComponentImpl<ItemChickenStack> {
    public ItemChickenBaseComponentImpl(ItemStackInitInfo<ItemChickenStack> initInfo) {
        super(initInfo, 1, 1.2f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        if (0.3f >= Math.random())
            player.addEffect(EffectHungerType.HUNGER_TYPE.createInstance(0, 30 * 20)); // 1 lvl, 30 seconds
    }
}
