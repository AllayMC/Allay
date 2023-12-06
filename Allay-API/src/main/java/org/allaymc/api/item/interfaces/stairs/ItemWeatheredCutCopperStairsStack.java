package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCutCopperStairsStack extends ItemStack {
    ItemType<ItemWeatheredCutCopperStairsStack> WEATHERED_CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_CUT_COPPER_STAIRS)
            .build();
}
