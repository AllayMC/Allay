package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWoodenShovelStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenShovelStackInitializer {
  static void init() {
    ItemTypes.WOODEN_SHOVEL_TYPE = ItemTypeBuilder
            .builder(ItemWoodenShovelStack.class)
            .vanillaItem(VanillaItemId.WOODEN_SHOVEL)
            .build();
  }
}
