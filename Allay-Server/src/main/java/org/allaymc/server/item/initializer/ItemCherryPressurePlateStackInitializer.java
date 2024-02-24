package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCherryPressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryPressurePlateStackInitializer {
  static void init() {
    ItemTypes.CHERRY_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemCherryPressurePlateStack.class)
            .vanillaItem(VanillaItemId.CHERRY_PRESSURE_PLATE)
            .build();
  }
}
