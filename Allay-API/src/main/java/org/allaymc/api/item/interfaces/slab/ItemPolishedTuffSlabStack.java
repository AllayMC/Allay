package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedTuffSlabStack extends ItemStack {
  ItemType<ItemPolishedTuffSlabStack> POLISHED_TUFF_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemPolishedTuffSlabStack.class)
          .vanillaItem(VanillaItemId.POLISHED_TUFF_SLAB)
          .build();
}
