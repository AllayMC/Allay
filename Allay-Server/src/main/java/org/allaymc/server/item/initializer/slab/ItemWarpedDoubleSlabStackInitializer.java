package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemWarpedDoubleSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedDoubleSlabStackInitializer {
  static void init() {
    ItemTypes.WARPED_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWarpedDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.WARPED_DOUBLE_SLAB)
            .build();
  }
}
