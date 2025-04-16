package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author IWareQ
 */
public class ItemHoneyBottleFoodComponentImpl extends ItemFoodComponentImpl {
    public ItemHoneyBottleFoodComponentImpl() {
        super(6, 1.2f, 40);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.removeEffect(EffectTypes.POISON);
    }
}
