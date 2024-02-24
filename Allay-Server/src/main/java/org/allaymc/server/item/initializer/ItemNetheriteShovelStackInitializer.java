package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetheriteShovelStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteShovelStackInitializer {
  static void init() {
    ItemTypes.NETHERITE_SHOVEL_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteShovelStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_SHOVEL)
            .build();
  }
}
