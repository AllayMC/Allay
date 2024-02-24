package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemCherrySlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherrySlabStackInitializer {
  static void init() {
    ItemTypes.CHERRY_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemCherrySlabStack.class)
            .vanillaItem(VanillaItemId.CHERRY_SLAB)
            .build();
  }
}
