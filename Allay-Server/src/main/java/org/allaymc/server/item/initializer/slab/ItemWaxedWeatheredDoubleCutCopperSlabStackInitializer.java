package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemWaxedWeatheredDoubleCutCopperSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredDoubleCutCopperSlabStackInitializer {
  static void init() {
    ItemTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWaxedWeatheredDoubleCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB)
            .build();
  }
}
