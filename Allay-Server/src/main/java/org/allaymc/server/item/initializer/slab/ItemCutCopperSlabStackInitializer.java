package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemCutCopperSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCutCopperSlabStackInitializer {
  static void init() {
    ItemTypes.CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.CUT_COPPER_SLAB)
            .build();
  }
}
