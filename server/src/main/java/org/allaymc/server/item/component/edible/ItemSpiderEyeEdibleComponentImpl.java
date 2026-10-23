package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code ItemSpiderEyeEdibleComponentImpl} supplies spider eye consumption
 * behavior with five seconds of level-one poison. The superclass handles use
 * eligibility, timing, and eating-event checks; this specialization defines
 * the nutrition and additional player effects applied by its callback.
 *
 * @author IWareQ
 */
public class ItemSpiderEyeEdibleComponentImpl extends ItemEdibleComponentImpl {
    /**
     * Creates spider eye behavior with two food points and 3.2 saturation
     * points. The component uses the inherited default eating duration and
     * retains the ordinary hunger check and non-drink flag.
     */
    public ItemSpiderEyeEdibleComponentImpl() {
        super(2, 3.2f);
    }

    /**
     * Applies ordinary nutrition and burping before requesting level-one
     * poison for five seconds. This callback does not reduce the held stack;
     * the surrounding use handler attempts that consumption afterward.
     */
    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        // Effect durations use twenty ticks per second; amplifiers are one less than levels.
        player.addEffect(EffectTypes.POISON.createInstance(0, 5 * 20));
    }
}
