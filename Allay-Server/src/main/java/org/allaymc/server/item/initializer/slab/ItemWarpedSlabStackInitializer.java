package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemWarpedSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedSlabStackInitializer {
  static void init() {
    ItemTypes.WARPED_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWarpedSlabStack.class)
            .vanillaItem(VanillaItemId.WARPED_SLAB)
            .build();
  }
}
