package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemWeatheredCutCopperSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCutCopperSlabStackInitializer {
  static void init() {
    ItemTypes.WEATHERED_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_CUT_COPPER_SLAB)
            .build();
  }
}
