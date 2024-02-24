package org.allaymc.server.item.initializer.liquid;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.liquid.ItemFlowingWaterStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlowingWaterStackInitializer {
  static void init() {
    ItemTypes.FLOWING_WATER_TYPE = ItemTypeBuilder
            .builder(ItemFlowingWaterStack.class)
            .vanillaItem(VanillaItemId.FLOWING_WATER)
            .build();
  }
}
