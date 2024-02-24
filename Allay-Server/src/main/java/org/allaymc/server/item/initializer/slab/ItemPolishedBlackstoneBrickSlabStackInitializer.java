package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemPolishedBlackstoneBrickSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneBrickSlabStackInitializer {
  static void init() {
    ItemTypes.POLISHED_BLACKSTONE_BRICK_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneBrickSlabStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICK_SLAB)
            .build();
  }
}
