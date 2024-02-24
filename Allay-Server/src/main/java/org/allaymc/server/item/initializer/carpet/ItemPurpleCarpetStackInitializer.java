package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemPurpleCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleCarpetStackInitializer {
  static void init() {
    ItemTypes.PURPLE_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemPurpleCarpetStack.class)
            .vanillaItem(VanillaItemId.PURPLE_CARPET)
            .build();
  }
}
