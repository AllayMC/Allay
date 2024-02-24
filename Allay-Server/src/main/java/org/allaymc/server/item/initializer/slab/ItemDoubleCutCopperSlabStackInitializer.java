package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemDoubleCutCopperSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleCutCopperSlabStackInitializer {
  static void init() {
    ItemTypes.DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemDoubleCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.DOUBLE_CUT_COPPER_SLAB)
            .build();
  }
}
