package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemTuffDoubleSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffDoubleSlabStackInitializer {
  static void init() {
    ItemTypes.TUFF_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemTuffDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.TUFF_DOUBLE_SLAB)
            .build();
  }
}
