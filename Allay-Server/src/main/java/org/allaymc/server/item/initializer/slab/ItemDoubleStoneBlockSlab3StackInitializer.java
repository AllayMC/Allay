package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemDoubleStoneBlockSlab3Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleStoneBlockSlab3StackInitializer {
  static void init() {
    ItemTypes.DOUBLE_STONE_BLOCK_SLAB3_TYPE = ItemTypeBuilder
            .builder(ItemDoubleStoneBlockSlab3Stack.class)
            .vanillaItem(VanillaItemId.DOUBLE_STONE_BLOCK_SLAB3)
            .build();
  }
}
