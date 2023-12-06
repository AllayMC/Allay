package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredCutCopperStairsStack extends ItemStack {
    ItemType<ItemWaxedWeatheredCutCopperStairsStack> WAXED_WEATHERED_CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemWaxedWeatheredCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
            .build();
}
