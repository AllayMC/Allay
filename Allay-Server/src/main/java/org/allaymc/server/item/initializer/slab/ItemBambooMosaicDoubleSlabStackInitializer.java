package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemBambooMosaicDoubleSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooMosaicDoubleSlabStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_MOSAIC_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemBambooMosaicDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_MOSAIC_DOUBLE_SLAB)
            .build();
  }
}
