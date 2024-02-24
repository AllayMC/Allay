package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemCobbledDeepslateSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobbledDeepslateSlabStackInitializer {
  static void init() {
    ItemTypes.COBBLED_DEEPSLATE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemCobbledDeepslateSlabStack.class)
            .vanillaItem(VanillaItemId.COBBLED_DEEPSLATE_SLAB)
            .build();
  }
}
