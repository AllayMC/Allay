package org.allaymc.server.item.initializer.liquid;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.liquid.ItemWaterStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaterStackInitializer {
  static void init() {
    ItemTypes.WATER_TYPE = ItemTypeBuilder
            .builder(ItemWaterStack.class)
            .vanillaItem(VanillaItemId.WATER)
            .build();
  }
}
