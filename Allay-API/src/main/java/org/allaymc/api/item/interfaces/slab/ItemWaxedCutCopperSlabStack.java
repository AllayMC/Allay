package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedCutCopperSlabStack extends ItemStack {
  ItemType<ItemWaxedCutCopperSlabStack> WAXED_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemWaxedCutCopperSlabStack.class)
          .vanillaItem(VanillaItemId.WAXED_CUT_COPPER_SLAB)
          .build();
}
