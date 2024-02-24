package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemMangroveSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveSlabStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemMangroveSlabStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_SLAB)
            .build();
  }
}
