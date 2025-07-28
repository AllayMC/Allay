package org.allaymc.server.item.component.edible;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.data.PotionType;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd
 */
public class ItemPotionComponentImpl extends ItemEdibleComponentImpl implements ItemPotionComponent {

    public ItemPotionComponentImpl() {
        super(0, 0, DEFAULT_EATING_TIME, true, true);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);
        this.getPotionType().applyTo(player);
        player.tryAddItem(ItemTypes.GLASS_BOTTLE.createItemStack(1));
    }

    @Override
    public PotionType getPotionType() {
        return PotionType.fromItemMeta(thisItemStack.getMeta());
    }
}
