package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author IWareQ
 */
public class ItemRottenFleshEdibleComponentImpl extends ItemEdibleComponentImpl {
    public ItemRottenFleshEdibleComponentImpl() {
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
