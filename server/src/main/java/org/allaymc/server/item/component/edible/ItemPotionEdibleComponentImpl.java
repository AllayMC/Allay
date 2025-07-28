package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.component.annotation.Dependency;

/**
 * @author daoge_cmd
 */
public class ItemPotionEdibleComponentImpl extends ItemEdibleComponentImpl {

    @Dependency
    protected ItemPotionComponent potionComponent;

    public ItemPotionEdibleComponentImpl() {
        super(0, 0, DEFAULT_EATING_TIME, true, true);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        potionComponent.getPotionType().applyTo(player);
        player.tryAddItem(ItemTypes.GLASS_BOTTLE.createItemStack(1));
    }
}
