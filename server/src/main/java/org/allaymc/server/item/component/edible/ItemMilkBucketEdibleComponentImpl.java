package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.type.ItemTypes;

public class ItemMilkBucketEdibleComponentImpl extends ItemEdibleComponentImpl {
    public ItemMilkBucketEdibleComponentImpl() {
        super(0, 0, DEFAULT_EATING_TIME, true, true);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        player.removeAllEffects();
        player.tryAddItem(ItemTypes.BUCKET.createItemStack(1));
    }
}
