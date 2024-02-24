package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemJunglePressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJunglePressurePlateStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemJunglePressurePlateStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_PRESSURE_PLATE)
            .build();
  }
}
