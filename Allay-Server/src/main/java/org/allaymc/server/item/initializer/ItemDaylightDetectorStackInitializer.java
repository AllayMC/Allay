package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDaylightDetectorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDaylightDetectorStackInitializer {
  static void init() {
    ItemTypes.DAYLIGHT_DETECTOR_TYPE = ItemTypeBuilder
            .builder(ItemDaylightDetectorStack.class)
            .vanillaItem(VanillaItemId.DAYLIGHT_DETECTOR)
            .build();
  }
}
