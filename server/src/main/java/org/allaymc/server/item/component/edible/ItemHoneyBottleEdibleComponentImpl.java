package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code ItemHoneyBottleEdibleComponentImpl} supplies honey bottle consumption
 * behavior with removal of the poison effect. The superclass handles use
 * eligibility, timing, and eating-event checks; this specialization defines
 * the nutrition and additional player effects applied by its callback.
 *
 * @author IWareQ
 */
public class ItemHoneyBottleEdibleComponentImpl extends ItemEdibleComponentImpl {
    /**
     * Creates honey bottle behavior with six food points and 1.2 saturation
     * points. The component uses forty ticks for consumption and retains the
     * superclass's ordinary hunger check and non-drink flag.
     */
    public ItemHoneyBottleEdibleComponentImpl() {
        super(6, 1.2f, 40);
    }

    /**
     * Requests ordinary nutrition and then attempts to remove the player's
     * poison effect. The player's removal event can retain poison through
     * cancellation, while the surrounding use handler still attempts held-item
     * consumption afterward.
     */
    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.removeEffect(EffectTypes.POISON);
    }
}
