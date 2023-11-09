package org.allaymc.api.item.interfaces.leaves;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeavesStack extends ItemStack {
    ItemType<ItemLeavesStack> LEAVES_TYPE = ItemTypeBuilder
            .builder(ItemLeavesStack.class)
            .vanillaItem(VanillaItemId.LEAVES)
            .build();
}
