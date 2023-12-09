package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffBrickSlabStack extends ItemStack {
  ItemType<ItemTuffBrickSlabStack> TUFF_BRICK_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemTuffBrickSlabStack.class)
          .vanillaItem(VanillaItemId.TUFF_BRICK_SLAB)
          .build();
}
