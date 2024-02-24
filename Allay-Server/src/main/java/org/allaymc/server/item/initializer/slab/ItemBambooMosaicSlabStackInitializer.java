package org.allaymc.server.item.initializer.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.slab.ItemBambooMosaicSlabStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooMosaicSlabStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_MOSAIC_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemBambooMosaicSlabStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_MOSAIC_SLAB)
            .build();
  }
}
