package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCutCopperStairsStack extends ItemStack {
    ItemType<ItemCutCopperStairsStack> CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.CUT_COPPER_STAIRS)
            .build();
}
