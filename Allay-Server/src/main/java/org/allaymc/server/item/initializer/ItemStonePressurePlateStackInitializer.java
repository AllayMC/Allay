package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStonePressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStonePressurePlateStackInitializer {
  static void init() {
    ItemTypes.STONE_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemStonePressurePlateStack.class)
            .vanillaItem(VanillaItemId.STONE_PRESSURE_PLATE)
            .build();
  }
}
