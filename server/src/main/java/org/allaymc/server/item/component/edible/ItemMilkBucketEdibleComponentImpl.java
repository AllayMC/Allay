package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.type.ItemTypes;

/**
 * {@code ItemMilkBucketEdibleComponentImpl} supplies milk bucket consumption
 * behavior with removal of all effects and an empty bucket. The superclass
 * handles use eligibility, timing, and eating-event checks; this
 * specialization defines the nutrition and additional player effects applied
 * by its callback.
 */
public class ItemMilkBucketEdibleComponentImpl extends ItemEdibleComponentImpl {
    /**
     * Creates milk bucket behavior with zero food and saturation points. The
     * component permits use without the ordinary hunger check and uses the
     * inherited default eating duration.
     */
    public ItemMilkBucketEdibleComponentImpl() {
        super(0, 0, DEFAULT_EATING_TIME, true, true);
    }

    /**
     * Requests the superclass consumption behavior, removal of active effects,
     * and return of one empty bucket. Individual effect-removal events can
     * cancel removal; {@code tryAddItem} drops inventory remainders before the
     * surrounding handler attempts held-item consumption.
     */
    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.removeAllEffects();
        player.tryAddItem(ItemTypes.BUCKET.createItemStack(1));
    }
}
