package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleStoneBlockSlab4Stack extends ItemStack {
  ItemType<ItemDoubleStoneBlockSlab4Stack> DOUBLE_STONE_BLOCK_SLAB4_TYPE = ItemTypeBuilder
          .builder(ItemDoubleStoneBlockSlab4Stack.class)
          .vanillaItem(VanillaItemId.DOUBLE_STONE_BLOCK_SLAB4)
          .build();
}
