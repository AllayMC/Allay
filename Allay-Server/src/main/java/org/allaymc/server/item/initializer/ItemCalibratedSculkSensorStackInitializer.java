package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCalibratedSculkSensorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCalibratedSculkSensorStackInitializer {
  static void init() {
    ItemTypes.CALIBRATED_SCULK_SENSOR_TYPE = ItemTypeBuilder
            .builder(ItemCalibratedSculkSensorStack.class)
            .vanillaItem(VanillaItemId.CALIBRATED_SCULK_SENSOR)
            .build();
  }
}
