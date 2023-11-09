package org.allaymc.api.item.interfaces.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBoatStack extends ItemStack {
    ItemType<ItemBoatStack> BOAT_TYPE = ItemTypeBuilder
            .builder(ItemBoatStack.class)
            .vanillaItem(VanillaItemId.BOAT)
            .build();
}
