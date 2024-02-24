package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemWaxedOxidizedDoubleCutCopperSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedOxidizedDoubleCutCopperSlabStackInitializer {
  static void init() {
    ItemTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWaxedOxidizedDoubleCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
            .build();
  }
}
