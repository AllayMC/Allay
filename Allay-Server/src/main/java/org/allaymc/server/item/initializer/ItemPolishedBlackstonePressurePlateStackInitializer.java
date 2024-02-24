package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPolishedBlackstonePressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstonePressurePlateStackInitializer {
  static void init() {
    ItemTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstonePressurePlateStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_PRESSURE_PLATE)
            .build();
  }
}
