package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredCutCopperSlabStack extends ItemStack {
  ItemType<ItemWaxedWeatheredCutCopperSlabStack> WAXED_WEATHERED_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemWaxedWeatheredCutCopperSlabStack.class)
          .vanillaItem(VanillaItemId.WAXED_WEATHERED_CUT_COPPER_SLAB)
          .build();
}
