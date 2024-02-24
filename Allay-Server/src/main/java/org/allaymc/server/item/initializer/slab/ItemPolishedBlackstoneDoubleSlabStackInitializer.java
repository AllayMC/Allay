package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemPolishedBlackstoneDoubleSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneDoubleSlabStackInitializer {
  static void init() {
    ItemTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_DOUBLE_SLAB)
            .build();
  }
}
