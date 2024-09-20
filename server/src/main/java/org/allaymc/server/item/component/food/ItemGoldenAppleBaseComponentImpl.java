package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author IWareQ
 */
public class ItemGoldenAppleBaseComponentImpl extends ItemFoodComponentImpl {
    public ItemGoldenAppleBaseComponentImpl() {
        super(4, 9.6f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.addEffect(EffectTypes.REGENERATION.createInstance(1, 5 * 20)); // 2 lvl, 5 seconds
        player.addEffect(EffectTypes.ABSORPTION.createInstance(0, 60 * 2 * 20)); // 1 lvl, 2 minutes
    }

    @Override
    public boolean canBeAlwaysEaten() {
        return true;
    }
}
