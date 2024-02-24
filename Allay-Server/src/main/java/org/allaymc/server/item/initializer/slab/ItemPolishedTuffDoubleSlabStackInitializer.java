package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemPolishedTuffDoubleSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedTuffDoubleSlabStackInitializer {
  static void init() {
    ItemTypes.POLISHED_TUFF_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemPolishedTuffDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.POLISHED_TUFF_DOUBLE_SLAB)
            .build();
  }
}
