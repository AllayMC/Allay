package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemPolishedDeepslateSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedDeepslateSlabStackInitializer {
  static void init() {
    ItemTypes.POLISHED_DEEPSLATE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemPolishedDeepslateSlabStack.class)
            .vanillaItem(VanillaItemId.POLISHED_DEEPSLATE_SLAB)
            .build();
  }
}
