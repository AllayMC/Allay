package org.allaymc.api.item.interfaces.wood;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodStack extends ItemStack {
    ItemType<ItemWoodStack> WOOD_TYPE = ItemTypeBuilder
            .builder(ItemWoodStack.class)
            .vanillaItem(VanillaItemId.WOOD)
            .build();
}
