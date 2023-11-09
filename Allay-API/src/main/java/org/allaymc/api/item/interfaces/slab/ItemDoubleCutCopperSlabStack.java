package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleCutCopperSlabStack extends ItemStack {
  ItemType<ItemDoubleCutCopperSlabStack> DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemDoubleCutCopperSlabStack.class)
          .vanillaItem(VanillaItemId.DOUBLE_CUT_COPPER_SLAB)
          .build();
}
