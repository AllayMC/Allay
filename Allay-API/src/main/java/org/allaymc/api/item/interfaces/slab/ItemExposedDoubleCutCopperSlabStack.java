package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedDoubleCutCopperSlabStack extends ItemStack {
  ItemType<ItemExposedDoubleCutCopperSlabStack> EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemExposedDoubleCutCopperSlabStack.class)
          .vanillaItem(VanillaItemId.EXPOSED_DOUBLE_CUT_COPPER_SLAB)
          .build();
}
