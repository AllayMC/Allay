package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetheriteSwordStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteSwordStackInitializer {
  static void init() {
    ItemTypes.NETHERITE_SWORD_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteSwordStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_SWORD)
            .build();
  }
}
