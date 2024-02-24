package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemCrimsonSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonSlabStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonSlabStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_SLAB)
            .build();
  }
}
