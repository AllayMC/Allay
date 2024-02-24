package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemWoodenSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenSlabStackInitializer {
  static void init() {
    ItemTypes.WOODEN_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWoodenSlabStack.class)
            .vanillaItem(VanillaItemId.WOODEN_SLAB)
            .build();
  }
}
