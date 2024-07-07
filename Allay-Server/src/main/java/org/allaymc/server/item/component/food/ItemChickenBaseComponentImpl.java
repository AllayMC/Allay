package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectHungerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Allay Project 07/07/2024
 *
 * @author IWareQ
 */
public class ItemChickenBaseComponentImpl extends ItemFoodComponentImpl {
    public ItemChickenBaseComponentImpl() {
        super(1, 1.2f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        if (0.3f >= Math.random())
            player.addEffect(EffectHungerType.HUNGER_TYPE.createInstance(0, 30 * 20)); // 1 lvl, 30 seconds
    }
}
