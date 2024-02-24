package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSoulSoilStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulSoilStackInitializer {
  static void init() {
    ItemTypes.SOUL_SOIL_TYPE = ItemTypeBuilder
            .builder(ItemSoulSoilStack.class)
            .vanillaItem(VanillaItemId.SOUL_SOIL)
            .build();
  }
}
