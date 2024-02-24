package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWarpedPressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedPressurePlateStackInitializer {
  static void init() {
    ItemTypes.WARPED_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemWarpedPressurePlateStack.class)
            .vanillaItem(VanillaItemId.WARPED_PRESSURE_PLATE)
            .build();
  }
}
