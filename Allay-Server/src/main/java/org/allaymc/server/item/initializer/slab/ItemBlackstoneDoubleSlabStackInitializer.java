package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemBlackstoneDoubleSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackstoneDoubleSlabStackInitializer {
  static void init() {
    ItemTypes.BLACKSTONE_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemBlackstoneDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.BLACKSTONE_DOUBLE_SLAB)
            .build();
  }
}
