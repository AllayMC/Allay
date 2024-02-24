package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemBlackstoneSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackstoneSlabStackInitializer {
  static void init() {
    ItemTypes.BLACKSTONE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemBlackstoneSlabStack.class)
            .vanillaItem(VanillaItemId.BLACKSTONE_SLAB)
            .build();
  }
}
