package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code ItemGoldenAppleEdibleComponentImpl} supplies golden apple consumption
 * behavior with regeneration and absorption effects. The superclass handles
 * use eligibility, timing, and eating-event checks; this specialization
 * defines the nutrition and additional player effects applied by its callback.
 *
 * @author IWareQ
 */
public class ItemGoldenAppleEdibleComponentImpl extends ItemEdibleComponentImpl {
    /**
     * Creates golden apple behavior with four food points and 9.6 saturation
     * points. The component permits use without the ordinary hunger check and
     * uses the inherited default eating duration.
     */
    public ItemGoldenAppleEdibleComponentImpl() {
        super(4, 9.6f, DEFAULT_EATING_TIME, false, true);
    }

    /**
     * Applies ordinary nutrition, then requests the golden apple's
     * regeneration and absorption effects. Level-two regeneration lasts five
     * seconds, while level-one absorption lasts two minutes; held-item
     * consumption remains with the use handler.
     */
    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        // Effect durations use twenty ticks per second; amplifiers are one less than levels.
        player.addEffect(EffectTypes.REGENERATION.createInstance(1, 5 * 20));
        player.addEffect(EffectTypes.ABSORPTION.createInstance(0, 60 * 2 * 20));
    }
}
