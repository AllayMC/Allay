package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code ItemPufferfishEdibleComponentImpl} supplies pufferfish consumption
 * behavior with hunger, nausea, and poison effects. The superclass handles use
 * eligibility, timing, and eating-event checks; this specialization defines
 * the nutrition and additional player effects applied by its callback.
 *
 * @author IWareQ
 */
public class ItemPufferfishEdibleComponentImpl extends ItemEdibleComponentImpl {
    /**
     * Creates pufferfish behavior with one food point and 0.2 saturation
     * points. The component uses the inherited default eating duration and
     * retains the ordinary hunger check and non-drink flag.
     */
    public ItemPufferfishEdibleComponentImpl() {
        super(1, 0.2f);
    }

    /**
     * Applies ordinary nutrition, then requests hunger, nausea, and poison
     * effects for the player. Hunger and nausea last fifteen seconds, while
     * poison lasts sixty seconds; the use handler separately consumes the held
     * item.
     */
    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        // Effect durations use twenty ticks per second; amplifiers are one less than levels.
        player.addEffect(EffectTypes.HUNGER.createInstance(2, 15 * 20));
        player.addEffect(EffectTypes.NAUSEA.createInstance(0, 15 * 20));
        player.addEffect(EffectTypes.POISON.createInstance(1, 60 * 20));
    }
}
