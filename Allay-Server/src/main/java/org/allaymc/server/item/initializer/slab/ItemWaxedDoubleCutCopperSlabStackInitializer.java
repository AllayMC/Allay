package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemWaxedDoubleCutCopperSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedDoubleCutCopperSlabStackInitializer {
  static void init() {
    ItemTypes.WAXED_DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWaxedDoubleCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.WAXED_DOUBLE_CUT_COPPER_SLAB)
            .build();
  }
}
