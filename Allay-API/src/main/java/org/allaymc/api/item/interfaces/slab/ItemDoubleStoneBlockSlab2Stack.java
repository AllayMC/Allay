package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleStoneBlockSlab2Stack extends ItemStack {
  ItemType<ItemDoubleStoneBlockSlab2Stack> DOUBLE_STONE_BLOCK_SLAB2_TYPE = ItemTypeBuilder
          .builder(ItemDoubleStoneBlockSlab2Stack.class)
          .vanillaItem(VanillaItemId.DOUBLE_STONE_BLOCK_SLAB2)
          .build();
}
