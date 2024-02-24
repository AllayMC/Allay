package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWoodenPressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenPressurePlateStackInitializer {
  static void init() {
    ItemTypes.WOODEN_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemWoodenPressurePlateStack.class)
            .vanillaItem(VanillaItemId.WOODEN_PRESSURE_PLATE)
            .build();
  }
}
