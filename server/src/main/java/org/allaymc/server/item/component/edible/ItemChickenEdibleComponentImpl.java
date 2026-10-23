package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code ItemChickenEdibleComponentImpl} supplies raw chicken consumption
 * behavior with a thirty-percent chance of thirty seconds of level-one hunger.
 * The superclass handles use eligibility, timing, and eating-event checks;
 * this specialization defines the nutrition and additional player effects
 * applied by its callback.
 *
 * @author IWareQ
 */
public class ItemChickenEdibleComponentImpl extends ItemEdibleComponentImpl {
    /**
     * Creates raw chicken behavior with two food points and 1.2 saturation
     * points. The component uses the inherited default eating duration and
     * retains the ordinary hunger check and non-drink flag.
     */
    public ItemChickenEdibleComponentImpl() {
        super(2, 1.2f);
    }

    /**
     * Applies ordinary nutrition and burping, then rolls for the raw-chicken
     * hunger effect. Successful rolls request level-one hunger for thirty seconds;
     * held-item consumption remains the surrounding use handler's
     * responsibility.
     */
    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        // The thirty-percent effect roll leaves nutrition already applied by the superclass.
        if (0.3f >= ThreadLocalRandom.current().nextFloat()) {
            // Effect durations use twenty ticks per second; amplifiers are one less than levels.
            player.addEffect(EffectTypes.HUNGER.createInstance(0, 30 * 20));
        }
    }
}
