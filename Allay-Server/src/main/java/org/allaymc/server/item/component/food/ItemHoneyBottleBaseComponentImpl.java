package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectPoisonType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemHoneyBottleStack;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
public class ItemHoneyBottleBaseComponentImpl extends ItemFoodBaseComponentImpl<ItemHoneyBottleStack> {
    public ItemHoneyBottleBaseComponentImpl(ItemStackInitInfo<ItemHoneyBottleStack> initInfo) {
        super(initInfo, 6, 1.2f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.removeEffect(EffectPoisonType.POISON_TYPE);
    }
}
