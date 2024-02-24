package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStoneShovelStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneShovelStackInitializer {
  static void init() {
    ItemTypes.STONE_SHOVEL_TYPE = ItemTypeBuilder
            .builder(ItemStoneShovelStack.class)
            .vanillaItem(VanillaItemId.STONE_SHOVEL)
            .build();
  }
}
