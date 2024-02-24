package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCrimsonPressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonPressurePlateStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonPressurePlateStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_PRESSURE_PLATE)
            .build();
  }
}
