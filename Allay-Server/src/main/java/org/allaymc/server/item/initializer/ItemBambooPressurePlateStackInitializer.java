package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBambooPressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooPressurePlateStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemBambooPressurePlateStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_PRESSURE_PLATE)
            .build();
  }
}
