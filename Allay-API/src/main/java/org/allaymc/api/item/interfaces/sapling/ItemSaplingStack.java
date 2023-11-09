package org.allaymc.api.item.interfaces.sapling;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSaplingStack extends ItemStack {
    ItemType<ItemSaplingStack> SAPLING_TYPE = ItemTypeBuilder
            .builder(ItemSaplingStack.class)
            .vanillaItem(VanillaItemId.SAPLING)
            .build();
}
