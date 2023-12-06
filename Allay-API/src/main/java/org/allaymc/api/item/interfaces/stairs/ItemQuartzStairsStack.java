package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemQuartzStairsStack extends ItemStack {
    ItemType<ItemQuartzStairsStack> QUARTZ_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemQuartzStairsStack.class)
            .vanillaItem(VanillaItemId.QUARTZ_STAIRS)
            .build();
}
