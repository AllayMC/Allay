package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author IWareQ
 */
public class ItemPoisonousPotatoFoodComponentImpl extends ItemFoodComponentImpl {
    public ItemPoisonousPotatoFoodComponentImpl() {
        super(2, 1.2f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        if (0.6f >= ThreadLocalRandom.current().nextFloat()) {
            player.addEffect(EffectTypes.POISON.createInstance(0, 5 * 20)); // 1 lvl, 5 seconds
        }
    }
}
