package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffSlabStack extends ItemStack {
  ItemType<ItemTuffSlabStack> TUFF_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemTuffSlabStack.class)
          .vanillaItem(VanillaItemId.TUFF_SLAB)
          .build();
}
