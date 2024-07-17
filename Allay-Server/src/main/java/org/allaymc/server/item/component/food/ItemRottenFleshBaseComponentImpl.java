package org.allaymc.server.item.component.food;

import org.allaymc.api.data.VanillaEffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
public class ItemRottenFleshBaseComponentImpl extends ItemFoodComponentImpl {
    public ItemRottenFleshBaseComponentImpl() {
        super(4, 0.8f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        if (0.8f >= ThreadLocalRandom.current().nextFloat())
            player.addEffect(VanillaEffectTypes.HUNGER.createInstance(0, 30 * 20)); // 1 lvl, 30 seconds
    }
}
