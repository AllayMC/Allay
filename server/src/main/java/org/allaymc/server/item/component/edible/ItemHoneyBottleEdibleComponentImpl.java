package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * @author IWareQ
 */
public class ItemHoneyBottleEdibleComponentImpl extends ItemEdibleComponentImpl {
    public ItemHoneyBottleEdibleComponentImpl() {
        super(6, 1.2f, 40);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.removeEffect(EffectTypes.POISON);
    }
}
