package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneBlockSlabStack extends ItemStack {
  ItemType<ItemStoneBlockSlabStack> STONE_BLOCK_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemStoneBlockSlabStack.class)
          .vanillaItem(VanillaItemId.STONE_BLOCK_SLAB)
          .build();
}
