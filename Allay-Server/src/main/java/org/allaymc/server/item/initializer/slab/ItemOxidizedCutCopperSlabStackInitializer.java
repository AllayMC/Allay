package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemOxidizedCutCopperSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedCutCopperSlabStackInitializer {
  static void init() {
    ItemTypes.OXIDIZED_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemOxidizedCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.OXIDIZED_CUT_COPPER_SLAB)
            .build();
  }
}
