package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemStoneBlockSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneBlockSlabStackInitializer {
  static void init() {
    ItemTypes.STONE_BLOCK_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemStoneBlockSlabStack.class)
            .vanillaItem(VanillaItemId.STONE_BLOCK_SLAB)
            .build();
  }
}
