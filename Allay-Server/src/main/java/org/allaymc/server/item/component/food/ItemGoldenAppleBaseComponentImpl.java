package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectAbsorptionType;
import org.allaymc.api.entity.effect.type.EffectRegenerationType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
public class ItemGoldenAppleBaseComponentImpl extends ItemFoodComponentImpl {
    public ItemGoldenAppleBaseComponentImpl() {
        super(4, 9.6f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.addEffect(EffectRegenerationType.REGENERATION_TYPE.createInstance(1, 5 * 20)); // 2 lvl, 5 seconds
        player.addEffect(EffectAbsorptionType.ABSORPTION_TYPE.createInstance(0, 60 * 2 * 20)); // 1 lvl, 2 minutes
    }
}
