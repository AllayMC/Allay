package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectPoisonType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemSpiderEyeStack;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
public class ItemSpiderEyeBaseComponentImpl extends ItemFoodBaseComponentImpl<ItemSpiderEyeStack> {
    public ItemSpiderEyeBaseComponentImpl(ItemStackInitInfo<ItemSpiderEyeStack> initInfo) {
        super(initInfo, 2, 3.2f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.addEffect(EffectPoisonType.POISON_TYPE.createInstance(0, 5 * 20)); // 1 lvl, 5 seconds
    }
}
