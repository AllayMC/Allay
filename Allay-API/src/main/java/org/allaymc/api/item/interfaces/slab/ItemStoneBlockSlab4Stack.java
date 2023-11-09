package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneBlockSlab4Stack extends ItemStack {
  ItemType<ItemStoneBlockSlab4Stack> STONE_BLOCK_SLAB4_TYPE = ItemTypeBuilder
          .builder(ItemStoneBlockSlab4Stack.class)
          .vanillaItem(VanillaItemId.STONE_BLOCK_SLAB4)
          .build();
}
