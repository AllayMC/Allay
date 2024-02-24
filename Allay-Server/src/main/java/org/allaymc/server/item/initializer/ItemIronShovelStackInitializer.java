package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemIronShovelStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronShovelStackInitializer {
  static void init() {
    ItemTypes.IRON_SHOVEL_TYPE = ItemTypeBuilder
            .builder(ItemIronShovelStack.class)
            .vanillaItem(VanillaItemId.IRON_SHOVEL)
            .build();
  }
}
