package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSculkSensorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSculkSensorStackInitializer {
  static void init() {
    ItemTypes.SCULK_SENSOR_TYPE = ItemTypeBuilder
            .builder(ItemSculkSensorStack.class)
            .vanillaItem(VanillaItemId.SCULK_SENSOR)
            .build();
  }
}
