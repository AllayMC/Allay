package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBirchPressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchPressurePlateStackInitializer {
  static void init() {
    ItemTypes.BIRCH_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemBirchPressurePlateStack.class)
            .vanillaItem(VanillaItemId.BIRCH_PRESSURE_PLATE)
            .build();
  }
}
