package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemExposedDoubleCutCopperSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedDoubleCutCopperSlabStackInitializer {
  static void init() {
    ItemTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemExposedDoubleCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_DOUBLE_CUT_COPPER_SLAB)
            .build();
  }
}
