package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemOxidizedDoubleCutCopperSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedDoubleCutCopperSlabStackInitializer {
  static void init() {
    ItemTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemOxidizedDoubleCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
            .build();
  }
}
