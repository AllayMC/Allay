package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author IWareQ
 */
public class ItemChickenFoodComponentImpl extends ItemFoodComponentImpl {
    public ItemChickenFoodComponentImpl() {
        super(2, 1.2f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        if (0.3f >= ThreadLocalRandom.current().nextFloat()) {
            player.addEffect(EffectTypes.HUNGER.createInstance(0, 30 * 20)); // 1 lvl, 30 seconds
        }
    }
}
