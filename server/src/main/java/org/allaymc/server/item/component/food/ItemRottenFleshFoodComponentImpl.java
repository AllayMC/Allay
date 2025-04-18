package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author IWareQ
 */
public class ItemRottenFleshFoodComponentImpl extends ItemFoodComponentImpl {
    public ItemRottenFleshFoodComponentImpl() {
        super(4, 0.8f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        if (0.8f >= ThreadLocalRandom.current().nextFloat()) {
            player.addEffect(EffectTypes.HUNGER.createInstance(0, 30 * 20)); // 1 lvl, 30 seconds
        }
    }
}
