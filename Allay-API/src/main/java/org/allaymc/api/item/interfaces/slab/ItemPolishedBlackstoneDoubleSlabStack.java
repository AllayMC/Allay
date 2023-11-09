package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneDoubleSlabStack extends ItemStack {
  ItemType<ItemPolishedBlackstoneDoubleSlabStack> POLISHED_BLACKSTONE_DOUBLE_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemPolishedBlackstoneDoubleSlabStack.class)
          .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_DOUBLE_SLAB)
          .build();
}
