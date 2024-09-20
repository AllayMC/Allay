package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author IWareQ
 */
public class ItemPufferfishBaseComponentImpl extends ItemFoodComponentImpl {
    public ItemPufferfishBaseComponentImpl() {
        super(1, 0.2f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.addEffect(EffectTypes.HUNGER.createInstance(2, 15 * 20)); // 3 lvl, 15 seconds
        player.addEffect(EffectTypes.NAUSEA.createInstance(0, 15 * 20)); // 1 lvl, 15 seconds
        player.addEffect(EffectTypes.POISON.createInstance(1, 60 * 20)); // 2 lvl, 1 minutes
    }
}
