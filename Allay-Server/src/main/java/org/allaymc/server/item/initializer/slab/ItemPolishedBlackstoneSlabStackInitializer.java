package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemPolishedBlackstoneSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneSlabStackInitializer {
  static void init() {
    ItemTypes.POLISHED_BLACKSTONE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneSlabStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_SLAB)
            .build();
  }
}
