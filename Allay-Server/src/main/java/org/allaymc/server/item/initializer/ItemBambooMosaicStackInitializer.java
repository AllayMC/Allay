package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBambooMosaicStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooMosaicStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_MOSAIC_TYPE = ItemTypeBuilder
            .builder(ItemBambooMosaicStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_MOSAIC)
            .build();
  }
}
