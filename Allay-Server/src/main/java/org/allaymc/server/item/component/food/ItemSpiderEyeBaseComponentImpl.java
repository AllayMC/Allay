package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectPoisonType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
public class ItemSpiderEyeBaseComponentImpl extends ItemFoodComponentImpl {
    public ItemSpiderEyeBaseComponentImpl() {
        super(2, 3.2f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.addEffect(EffectPoisonType.POISON_TYPE.createInstance(0, 5 * 20)); // 1 lvl, 5 seconds
    }
}
