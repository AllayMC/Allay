package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDetectorRailStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDetectorRailStackInitializer {
  static void init() {
    ItemTypes.DETECTOR_RAIL_TYPE = ItemTypeBuilder
            .builder(ItemDetectorRailStack.class)
            .vanillaItem(VanillaItemId.DETECTOR_RAIL)
            .build();
  }
}
