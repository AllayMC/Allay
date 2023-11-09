package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneBlockSlab2Stack extends ItemStack {
  ItemType<ItemStoneBlockSlab2Stack> STONE_BLOCK_SLAB2_TYPE = ItemTypeBuilder
          .builder(ItemStoneBlockSlab2Stack.class)
          .vanillaItem(VanillaItemId.STONE_BLOCK_SLAB2)
          .build();
}
