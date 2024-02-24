package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemBambooDoubleSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooDoubleSlabStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemBambooDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_DOUBLE_SLAB)
            .build();
  }
}
