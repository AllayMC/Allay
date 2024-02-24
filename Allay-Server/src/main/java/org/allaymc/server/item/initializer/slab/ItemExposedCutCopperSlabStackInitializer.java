package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemExposedCutCopperSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCutCopperSlabStackInitializer {
  static void init() {
    ItemTypes.EXPOSED_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemExposedCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_CUT_COPPER_SLAB)
            .build();
  }
}
