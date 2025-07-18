package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author IWareQ
 */
public class ItemEnchantedGoldenAppleFoodComponentImpl extends ItemFoodComponentImpl {
    public ItemEnchantedGoldenAppleFoodComponentImpl() {
        super(4, 9.6f, DEFAULT_EATING_TIME, false, true);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.addEffect(EffectTypes.REGENERATION.createInstance(4, 30 * 20)); // 5 lvl, 30 seconds
        player.addEffect(EffectTypes.ABSORPTION.createInstance(3, 60 * 2 * 20)); // 4 lvl, 2 minutes
        player.addEffect(EffectTypes.RESISTANCE.createInstance(0, 60 * 5 * 20)); // 1 lvl, 5 minutes
        player.addEffect(EffectTypes.FIRE_RESISTANCE.createInstance(0, 60 * 5 * 20)); // 1 lvl, 5 minutes
    }
}
