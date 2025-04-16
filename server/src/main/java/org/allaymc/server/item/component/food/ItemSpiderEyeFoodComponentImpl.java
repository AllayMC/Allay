package org.allaymc.server.item.component.food;

import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author IWareQ
 */
public class ItemSpiderEyeFoodComponentImpl extends ItemFoodComponentImpl {
    public ItemSpiderEyeFoodComponentImpl() {
        super(2, 3.2f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.addEffect(EffectTypes.POISON.createInstance(0, 5 * 20)); // 1 lvl, 5 seconds
    }
}
