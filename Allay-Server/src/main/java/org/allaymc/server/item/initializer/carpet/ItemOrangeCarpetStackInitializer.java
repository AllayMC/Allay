package org.allaymc.server.item.initializer.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.carpet.ItemOrangeCarpetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeCarpetStackInitializer {
  static void init() {
    ItemTypes.ORANGE_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemOrangeCarpetStack.class)
            .vanillaItem(VanillaItemId.ORANGE_CARPET)
            .build();
  }
}
