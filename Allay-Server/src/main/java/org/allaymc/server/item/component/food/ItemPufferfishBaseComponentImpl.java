package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectHungerType;
import org.allaymc.api.entity.effect.type.EffectNauseaType;
import org.allaymc.api.entity.effect.type.EffectPoisonType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemPufferfishStack;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
public class ItemPufferfishBaseComponentImpl extends ItemFoodBaseComponentImpl<ItemPufferfishStack> {
    public ItemPufferfishBaseComponentImpl(ItemStackInitInfo<ItemPufferfishStack> initInfo) {
        super(initInfo, 1, 0.2f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.addEffect(EffectHungerType.HUNGER_TYPE.createInstance(2, 15 * 20)); // 3 lvl, 15 seconds
        player.addEffect(EffectNauseaType.NAUSEA_TYPE.createInstance(0, 15 * 20)); // 1 lvl, 15 seconds
        player.addEffect(EffectPoisonType.POISON_TYPE.createInstance(1, 60 * 20)); // 2 lvl, 1 minutes
    }
}
