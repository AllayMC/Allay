package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackstoneDoubleSlabStack extends ItemStack {
  ItemType<ItemBlackstoneDoubleSlabStack> BLACKSTONE_DOUBLE_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemBlackstoneDoubleSlabStack.class)
          .vanillaItem(VanillaItemId.BLACKSTONE_DOUBLE_SLAB)
          .build();
}