package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSprucePressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSprucePressurePlateStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemSprucePressurePlateStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_PRESSURE_PLATE)
            .build();
  }
}
