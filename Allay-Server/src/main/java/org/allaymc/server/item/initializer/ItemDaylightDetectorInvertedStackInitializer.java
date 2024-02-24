package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDaylightDetectorInvertedStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDaylightDetectorInvertedStackInitializer {
  static void init() {
    ItemTypes.DAYLIGHT_DETECTOR_INVERTED_TYPE = ItemTypeBuilder
            .builder(ItemDaylightDetectorInvertedStack.class)
            .vanillaItem(VanillaItemId.DAYLIGHT_DETECTOR_INVERTED)
            .build();
  }
}
