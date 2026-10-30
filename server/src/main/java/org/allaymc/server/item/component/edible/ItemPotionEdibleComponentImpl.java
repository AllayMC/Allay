package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.component.annotation.Dependency;

/**
 * {@code ItemPotionEdibleComponentImpl} supplies drinkable potion consumption
 * behavior with the selected potion effects and an empty glass bottle. The
 * superclass handles use eligibility, timing, and eating-event checks; this
 * specialization defines the nutrition and additional player effects applied
 * by its callback.
 *
 * @author daoge_cmd
 */
public class ItemPotionEdibleComponentImpl extends ItemEdibleComponentImpl {

    /**
     * {@code potionComponent} holds the injected potion selector.
     * {@code onEaten} reads its current type when consumption occurs, so
     * metadata changes affect subsequent drinks.
     */
    @Dependency
    protected ItemPotionComponent potionComponent;

    /**
     * Creates drinkable potion behavior with zero food and saturation points.
     * The component permits use without the ordinary hunger check and uses the
     * inherited default eating duration.
     */
    public ItemPotionEdibleComponentImpl() {
        super(0, 0, DEFAULT_EATING_TIME, true, true);
    }

    /**
     * Applies the superclass consumption behavior, the selected potion
     * effects, and one empty glass bottle. {@code tryAddItem} drops any
     * inventory remainder; the surrounding handler attempts held-item
     * consumption after this callback returns.
     */
    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        potionComponent.getPotionType().applyTo(player);
        player.tryAddItem(ItemTypes.GLASS_BOTTLE.createItemStack(1));
    }
}
