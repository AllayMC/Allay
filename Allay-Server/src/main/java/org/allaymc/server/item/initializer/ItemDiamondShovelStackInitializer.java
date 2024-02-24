package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDiamondShovelStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondShovelStackInitializer {
  static void init() {
    ItemTypes.DIAMOND_SHOVEL_TYPE = ItemTypeBuilder
            .builder(ItemDiamondShovelStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_SHOVEL)
            .build();
  }
}
