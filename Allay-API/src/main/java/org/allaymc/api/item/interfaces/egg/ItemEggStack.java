package org.allaymc.api.item.interfaces.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEggStack extends ItemStack {
    ItemType<ItemEggStack> EGG_TYPE = ItemTypeBuilder
            .builder(ItemEggStack.class)
            .vanillaItem(VanillaItemId.EGG)
            .build();
}
