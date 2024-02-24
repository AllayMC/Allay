package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGoldenShovelStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenShovelStackInitializer {
  static void init() {
    ItemTypes.GOLDEN_SHOVEL_TYPE = ItemTypeBuilder
            .builder(ItemGoldenShovelStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_SHOVEL)
            .build();
  }
}
