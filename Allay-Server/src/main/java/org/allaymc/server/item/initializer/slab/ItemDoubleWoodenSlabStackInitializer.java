package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemDoubleWoodenSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleWoodenSlabStackInitializer {
  static void init() {
    ItemTypes.DOUBLE_WOODEN_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemDoubleWoodenSlabStack.class)
            .vanillaItem(VanillaItemId.DOUBLE_WOODEN_SLAB)
            .build();
  }
}
