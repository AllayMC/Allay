package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectHungerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemRottenFleshStack;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
public class ItemRottenFleshBaseComponentImpl extends ItemFoodBaseComponentImpl<ItemRottenFleshStack> {
    public ItemRottenFleshBaseComponentImpl(ItemStackInitInfo<ItemRottenFleshStack> initInfo) {
        super(initInfo, 4, 0.8f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        if (0.8f >= Math.random())
            player.addEffect(EffectHungerType.HUNGER_TYPE.createInstance(0, 30 * 20)); // 1 lvl, 30 seconds
    }
}
