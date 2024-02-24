package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemCrimsonDoubleSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonDoubleSlabStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_DOUBLE_SLAB)
            .build();
  }
}
