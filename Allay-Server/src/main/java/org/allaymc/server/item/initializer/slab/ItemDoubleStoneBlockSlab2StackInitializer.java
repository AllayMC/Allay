package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemDoubleStoneBlockSlab2Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleStoneBlockSlab2StackInitializer {
  static void init() {
    ItemTypes.DOUBLE_STONE_BLOCK_SLAB2_TYPE = ItemTypeBuilder
            .builder(ItemDoubleStoneBlockSlab2Stack.class)
            .vanillaItem(VanillaItemId.DOUBLE_STONE_BLOCK_SLAB2)
            .build();
  }
}
