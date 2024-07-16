package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectPoisonType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
public class ItemHoneyBottleBaseComponentImpl extends ItemFoodComponentImpl {
    public ItemHoneyBottleBaseComponentImpl() {
        super(6, 1.2f, 40);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.removeEffect(EffectPoisonType.POISON);
    }
}
