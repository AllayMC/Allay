package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code ItemEnchantedGoldenAppleEdibleComponentImpl} supplies enchanted
 * golden apple consumption behavior with regeneration, absorption, resistance,
 * and fire resistance. The superclass handles use eligibility, timing, and
 * eating-event checks; this specialization defines the nutrition and
 * additional player effects applied by its callback.
 *
 * @author IWareQ
 */
public class ItemEnchantedGoldenAppleEdibleComponentImpl extends ItemEdibleComponentImpl {
    /**
     * Creates enchanted golden apple behavior with four food points and 9.6
     * saturation points. The component permits use without the ordinary hunger
     * check and uses the inherited default eating duration.
     */
    public ItemEnchantedGoldenAppleEdibleComponentImpl() {
        super(4, 9.6f, DEFAULT_EATING_TIME, false, true);
    }

    /**
     * Applies ordinary nutrition, then requests four enchanted-apple effects
     * for the player. Regeneration lasts thirty seconds, absorption lasts two
     * minutes, and resistance plus fire resistance each last five minutes.
     */
    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        // Effect durations use twenty ticks per second; amplifiers are one less than levels.
        player.addEffect(EffectTypes.REGENERATION.createInstance(4, 30 * 20));
        player.addEffect(EffectTypes.ABSORPTION.createInstance(3, 60 * 2 * 20));
        player.addEffect(EffectTypes.RESISTANCE.createInstance(0, 60 * 5 * 20));
        player.addEffect(EffectTypes.FIRE_RESISTANCE.createInstance(0, 60 * 5 * 20));
    }
}
