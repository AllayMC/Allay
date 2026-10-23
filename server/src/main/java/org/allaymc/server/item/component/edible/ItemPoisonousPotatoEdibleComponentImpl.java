package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code ItemPoisonousPotatoEdibleComponentImpl} supplies poisonous potato
 * consumption behavior with a sixty-percent chance of five seconds of
 * level-one poison. The superclass handles use eligibility, timing, and
 * eating-event checks; this specialization defines the nutrition and
 * additional player effects applied by its callback.
 *
 * @author IWareQ
 */
public class ItemPoisonousPotatoEdibleComponentImpl extends ItemEdibleComponentImpl {
    /**
     * Creates poisonous potato behavior with two food points and 1.2
     * saturation points. The component uses the inherited default eating
     * duration and retains the ordinary hunger check and non-drink flag.
     */
    public ItemPoisonousPotatoEdibleComponentImpl() {
        super(2, 1.2f);
    }

    /**
     * Applies ordinary nutrition and burping, then rolls for the
     * poisonous-potato effect. Successful rolls request level-one poison for five
     * seconds; held-item consumption remains the surrounding use handler's
     * responsibility.
     */
    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        // The sixty-percent effect roll leaves nutrition already applied by the superclass.
        if (0.6f >= ThreadLocalRandom.current().nextFloat()) {
            // Effect durations use twenty ticks per second; amplifiers are one less than levels.
            player.addEffect(EffectTypes.POISON.createInstance(0, 5 * 20));
        }
    }
}
