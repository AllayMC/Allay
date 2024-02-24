package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemWaxedCutCopperSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedCutCopperSlabStackInitializer {
  static void init() {
    ItemTypes.WAXED_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWaxedCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.WAXED_CUT_COPPER_SLAB)
            .build();
  }
}
