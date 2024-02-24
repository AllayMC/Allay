package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemDoubleStoneBlockSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleStoneBlockSlabStackInitializer {
  static void init() {
    ItemTypes.DOUBLE_STONE_BLOCK_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemDoubleStoneBlockSlabStack.class)
            .vanillaItem(VanillaItemId.DOUBLE_STONE_BLOCK_SLAB)
            .build();
  }
}
