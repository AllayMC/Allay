package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMangrovePressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangrovePressurePlateStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemMangrovePressurePlateStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_PRESSURE_PLATE)
            .build();
  }
}
